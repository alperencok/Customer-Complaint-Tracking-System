import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee extends Person {

    private final List<Complaint> assignedComplaints; // Active pending complaints (Requirement 3.b)
    private final List<Complaint> resolvedComplaints; // Historical resolved complaints (Preserving resolved records)

    public Employee(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.assignedComplaints = new ArrayList<>();
        this.resolvedComplaints = new ArrayList<>();
    }

    public Employee(String firstName, String lastName) {
        this(0, firstName, lastName);
    }

    public void receiveComplaint(Complaint c) {
        assignedComplaints.add(c);
        String prod = (c.getProduct() != null) ? c.getProduct().getName() : "General";
        System.out.println(getFirstName() + " " + getLastName() + " received complaint for '" + prod + "': " + c.getDescription());
    }

    public void check(Complaint c) {
        for (int i = 0; i < assignedComplaints.size(); i++) {
            if (assignedComplaints.get(i) == c) {
                c.resolve(this, true);
                assignedComplaints.remove(i);
                resolvedComplaints.add(c);
                System.out.println("Refund Approved for " + c.getProduct().getName() + " by " + getFirstName() + " " + getLastName());
                return;
            }
        }
        System.out.println("Complaint not found in active assigned list.");
    }

    public void Check(Complaint c) {
        check(c);
    }

    // Complex Rule Audit (Requirement 4):
    // Identifies expensive products with high complaint ratios and pulls them from sale
    public void evaluateProductStatus(Product[] allProducts, double priceLimit) {
        System.out.println("\n--- SYSTEM AUDIT STARTED ---");
        int totalComplaints = 0;
        for (Product p : allProducts) {
            totalComplaints += p.getComplaintCount();
        }

        if (totalComplaints == 0) {
            System.out.println("No complaints recorded across products. System status optimal.");
            return;
        }

        for (Product p : allProducts) {
            double ratio = ((double) p.getComplaintCount() / totalComplaints) * 100.0;
            if (p.getPrice() > priceLimit && ratio > 20.0) {
                System.out.printf("CRITICAL WARNING: Product '%s' exceeds price threshold ($%.2f) and complaint ratio (%.1f%%)%n",
                        p.getName(), p.getPrice(), ratio);
                p.stopSale();
            }
        }
        System.out.println("--- SYSTEM AUDIT FINISHED ---\n");
    }

    public void refundProductCheck(Product[] refundProducts, int limit) {
        evaluateProductStatus(refundProducts, limit);
    }

    // Statistical Analysis (Requirement 3.c): identifies worst-performing product
    public void identifyWorstProduct(Product[] productList) {
        if (productList == null || productList.length == 0) {
            System.out.println("No products available to analyze.");
            return;
        }

        Product worst = productList[0];
        int totalComplaints = 0;

        for (Product p : productList) {
            totalComplaints += p.getComplaintCount();
            if (p.getComplaintCount() > worst.getComplaintCount()) {
                worst = p;
            }
        }

        System.out.println("\n--- PRODUCT QUALITY REPORT ---");
        System.out.println("Total System Complaints: " + totalComplaints);
        if (totalComplaints > 0) {
            System.out.println("Worst Performing Product: " + worst.getName());
            System.out.printf("Total Financial Loss Generated: $%.2f%n", worst.getTotalLoss());
            System.out.println("Total Incidents Logged: " + worst.getComplaintCount());
        }
        System.out.println("-------------------------------\n");
    }

    public void badProduct(Product[] productList) {
        identifyWorstProduct(productList);
    }

    public List<Complaint> getAssignedComplaints() {
        return Collections.unmodifiableList(assignedComplaints);
    }

    public List<Complaint> getResolvedComplaints() {
        return Collections.unmodifiableList(resolvedComplaints);
    }

    @Override
    public void whoAmI() {
        System.out.println("Employee: " + getFirstName() + " " + getLastName() +
                " [Active Queue: " + assignedComplaints.size() + ", Resolved: " + resolvedComplaints.size() + "]");
    }
}
