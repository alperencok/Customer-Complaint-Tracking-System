import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComplaintManager {

    private final List<Product> products;
    private final List<Customer> customers;
    private final List<Employee> employees;
    private final List<Complaint> allComplaints;

    public ComplaintManager() {
        this.products = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.allComplaints = new ArrayList<>();
    }

    public void registerProduct(Product p) {
        products.add(p);
    }

    public void registerCustomer(Customer c) {
        customers.add(c);
    }

    public void registerEmployee(Employee e) {
        employees.add(e);
    }

    public void logComplaint(Complaint c) {
        allComplaints.add(c);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public List<Complaint> getAllComplaints() {
        return Collections.unmodifiableList(allComplaints);
    }

    public void printSystemAuditReport() {
        System.out.println("================================================================================");
        System.out.println("                    ENTERPRISE COMPLAINT MANAGEMENT AUDIT REPORT                ");
        System.out.println("================================================================================");
        System.out.println("Registered Products:  " + products.size());
        System.out.println("Registered Customers: " + customers.size());
        System.out.println("Support Staff:        " + employees.size());
        System.out.println("Total Incidents:      " + allComplaints.size());
        System.out.println("--------------------------------------------------------------------------------");

        System.out.println("Product Inventory Status:");
        for (Product p : products) {
            System.out.printf(" - %-25s | Price: $%8.2f | Incidents: %2d | Loss: $%8.2f | On Sale: %b%n",
                    p.getName(), p.getPrice(), p.getComplaintCount(), p.getTotalLoss(), p.isOnSale());
        }

        System.out.println("\nCustomer Activity Summary:");
        for (Customer c : customers) {
            System.out.printf(" - %-20s | Items In Cart: %d | Total Purchases: %d | Complaints Filed: %d%n",
                    c.getFirstName() + " " + c.getLastName(),
                    c.getProductCount(),
                    c.getPurchaseHistory().size(),
                    c.getComplaintHistory().size());
        }

        System.out.println("\nEmployee Workload & Resolution Statistics:");
        for (Employee e : employees) {
            System.out.printf(" - %-20s | Pending In Queue: %d | Total Resolved: %d%n",
                    e.getFirstName() + " " + e.getLastName(),
                    e.getAssignedComplaints().size(),
                    e.getResolvedComplaints().size());
        }
        System.out.println("================================================================================\n");
    }
}
