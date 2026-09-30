import java.time.LocalDate;

public class Complaint {

    private static int idCounter = 1000;

    private final int complaintId;
    private final Customer customer;
    private final Product product;
    private String description;
    private String status; // PENDING, RESOLVED_REFUNDED, REJECTED
    private final LocalDate creationDate;
    private LocalDate resolutionDate;
    private Employee handledBy;

    public Complaint(Customer customer, Product product, String description) {
        this.complaintId = ++idCounter;
        this.customer = customer;
        this.product = product;
        this.description = description;
        this.status = "PENDING";
        this.creationDate = LocalDate.now();
    }

    public Complaint(String description, Product product) {
        this(null, product, description);
    }

    public void resolve(Employee employee, boolean refundApproved) {
        this.handledBy = employee;
        this.resolutionDate = LocalDate.now();
        if (refundApproved) {
            this.status = "RESOLVED_REFUNDED";
            if (product != null) {
                product.processRefund();
            }
        } else {
            this.status = "REJECTED";
        }
    }

    public int getComplaintId() {
        return complaintId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getResolutionDate() {
        return resolutionDate;
    }

    public Employee getHandledBy() {
        return handledBy;
    }

    @Override
    public String toString() {
        String custName = (customer != null) ? customer.getFirstName() + " " + customer.getLastName() : "Anonymous";
        String prodName = (product != null) ? product.getName() : "General";
        return String.format("Complaint #%d [%s] - Customer: %s | Product: %s | Issue: %s",
                complaintId, status, custName, prodName, description);
    }
}
