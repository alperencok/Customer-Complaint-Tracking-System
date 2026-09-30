import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product {

    private int id;
    private String name;
    private double price;
    private double totalLoss;
    private boolean onSale;
    private final List<Complaint> complaints;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.totalLoss = 0.0;
        this.onSale = true;
        this.complaints = new ArrayList<>();
    }

    public Product(String name, double price) {
        this(0, name, price);
    }

    public void addComplaint(Complaint c) {
        this.complaints.add(c);
    }

    public List<Complaint> getComplaints() {
        return Collections.unmodifiableList(complaints);
    }

    public int getComplaintCount() {
        return complaints.size();
    }

    public void processRefund() {
        this.totalLoss += this.price;
    }

    public void stopSale() {
        this.onSale = false;
        System.out.println("ALERT: Product '" + name + "' has been removed from sale due to quality issues.");
    }

    public void resumeSale() {
        this.onSale = true;
        System.out.println("INFO: Product '" + name + "' has been restored to sale.");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalLoss() {
        return totalLoss;
    }

    public boolean isOnSale() {
        return onSale;
    }

    @Override
    public String toString() {
        return String.format("%s (Price: $%.2f | Loss: $%.2f | Incidents: %d | On Sale: %b)",
                name, price, totalLoss, getComplaintCount(), onSale);
    }
}
