import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer extends Person {

    private final Product[] inventory; // Array usage with shift removal (Requirement 3.a)
    private int productCount;
    private final List<Product> purchaseHistory;   // Tracks complete purchase history
    private final List<Complaint> complaintHistory; // Tracks all complaints filed by this customer

    public Customer(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.inventory = new Product[5];
        this.productCount = 0;
        this.purchaseHistory = new ArrayList<>();
        this.complaintHistory = new ArrayList<>();
    }

    public Customer(String firstName, String lastName) {
        this(0, firstName, lastName);
    }

    public void buy(Product p) {
        if (!p.isOnSale()) {
            System.out.println("Operation Failed: '" + p.getName() + "' is currently not for sale.");
            return;
        }

        if (productCount < inventory.length) {
            inventory[productCount] = p;
            productCount++;
            purchaseHistory.add(p);
            System.out.println(getFirstName() + " " + getLastName() + " bought: " + p.getName());
        } else {
            System.out.println("Cart is full! Cannot add more items.");
        }
    }

    public void Buy(Product p) {
        buy(p);
    }

    // Overloaded method: Standard complaint
    public Complaint createComplaint(String description, Product p) {
        Complaint c = new Complaint(this, p, description);
        complaintHistory.add(c);
        if (p != null) {
            p.addComplaint(c);
        }
        return c;
    }

    // Overloaded method: Detailed complaint with supplementary context
    public Complaint createComplaint(String description, String extraDetails, Product p) {
        String fullDesc = description + " (" + extraDetails + ")";
        Complaint c = new Complaint(this, p, fullDesc);
        complaintHistory.add(c);
        if (p != null) {
            p.addComplaint(c);
        }
        return c;
    }

    // Element removal with shifting algorithm (Requirement 3.a)
    public void returnProduct(Product p) {
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (inventory[i] == p) {
                // Shift subsequent elements left
                for (int j = i; j < productCount - 1; j++) {
                    inventory[j] = inventory[j + 1];
                }
                inventory[productCount - 1] = null;
                productCount--;
                found = true;
                System.out.println(getFirstName() + " " + getLastName() + " returned product: " + p.getName());
                break;
            }
        }
        if (!found) {
            System.out.println("Error: Product '" + p.getName() + "' not found in " + getFirstName() + "'s cart.");
        }
    }

    public Product[] getInventory() {
        return inventory;
    }

    public int getProductCount() {
        return productCount;
    }

    public List<Product> getPurchaseHistory() {
        return Collections.unmodifiableList(purchaseHistory);
    }

    public List<Complaint> getComplaintHistory() {
        return Collections.unmodifiableList(complaintHistory);
    }

    @Override
    public void whoAmI() {
        System.out.println("Customer: " + getFirstName() + " " + getLastName() +
                " [Active Items: " + productCount + ", Complaints Filed: " + complaintHistory.size() + "]");
    }
}
