public class Test {

    public static Product asusLaptop, acerLaptop, monsterLaptop;
    public static Employee staffMember;
    public static Customer customer1, customer2, customer3;
    public static ComplaintManager manager;

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("             CUSTOMER COMPLAINT TRACKING SYSTEM - INTEGRATION SUITE             ");
        System.out.println("================================================================================\n");

        manager = new ComplaintManager();

        asusLaptop = new Product(101, "Asus Gaming Laptop", 53000.0);
        acerLaptop = new Product(102, "Acer Gaming Laptop", 48000.0);
        monsterLaptop = new Product(103, "Monster Gaming Laptop", 40000.0);

        manager.registerProduct(asusLaptop);
        manager.registerProduct(acerLaptop);
        manager.registerProduct(monsterLaptop);

        staffMember = new Employee(1, "Alperen", "Cok");
        manager.registerEmployee(staffMember);

        customer1 = new Customer(201, "Alex", "Smith");
        customer2 = new Customer(202, "Emma", "Davis");
        customer3 = new Customer(203, "John", "Doe");

        manager.registerCustomer(customer1);
        manager.registerCustomer(customer2);
        manager.registerCustomer(customer3);

        buyingTest();
        complaintAndRefundTest();
        statisticalAnalysisTest();
        complexRuleAuditTest();
        removeAndRebuyTest();
        returnTest();
        polymorphismTest();
        verificationTest();
    }

    public static void buyingTest() {
        System.out.println(">>> 1. COMMENCING CUSTOMER PURCHASING SCENARIO:");
        customer1.buy(asusLaptop);
        customer1.buy(monsterLaptop);
        customer2.buy(monsterLaptop);
        customer3.buy(acerLaptop);
        customer3.buy(monsterLaptop);
        System.out.println();
    }

    public static void complaintAndRefundTest() {
        System.out.println(">>> 2. FILING COMPLAINTS AND PROCESSING REFUNDS:");

        Complaint c1 = customer1.createComplaint("Expensive but quality below expectation", asusLaptop);
        manager.logComplaint(c1);
        staffMember.receiveComplaint(c1);

        Complaint c2 = customer1.createComplaint("SSD Issue", "Freezes under heavy workload", monsterLaptop);
        manager.logComplaint(c2);
        staffMember.receiveComplaint(c2);

        Complaint c3 = customer2.createComplaint("SSD Issue", "Frequent read/write dropouts", monsterLaptop);
        manager.logComplaint(c3);
        staffMember.receiveComplaint(c3);

        Complaint c4 = customer3.createComplaint("Excessive Fan Noise", acerLaptop);
        manager.logComplaint(c4);
        staffMember.receiveComplaint(c4);

        Complaint c5 = customer3.createComplaint("SSD Failure", "Blue screen on cold boot", monsterLaptop);
        manager.logComplaint(c5);
        staffMember.receiveComplaint(c5);

        System.out.println();

        staffMember.check(c1);
        staffMember.check(c2);
        staffMember.check(c3);
        staffMember.check(c4);
        staffMember.check(c5);

        System.out.println();

        customer1.returnProduct(asusLaptop);
        customer1.returnProduct(monsterLaptop);
        customer2.returnProduct(monsterLaptop);
        customer3.returnProduct(acerLaptop);
        customer3.returnProduct(monsterLaptop);

        System.out.println();
    }

    public static void statisticalAnalysisTest() {
        System.out.println(">>> 3. STATISTICAL INCIDENT ANALYSIS (Requirement 3.c):");
        Product[] catalog = {asusLaptop, acerLaptop, monsterLaptop};
        staffMember.identifyWorstProduct(catalog);
    }

    public static void complexRuleAuditTest() {
        System.out.println(">>> 4. COMPLEX BUSINESS RULE AUDIT (Requirement 4):");
        Product[] catalog = {asusLaptop, acerLaptop, monsterLaptop};
        staffMember.evaluateProductStatus(catalog, 35000.0);
    }

    public static void removeAndRebuyTest() {
        System.out.println(">>> 5. VALIDATING PURCHASE RESTRICTION ON PULLED PRODUCTS:");
        customer1.buy(monsterLaptop);
        System.out.println();
    }

    public static void returnTest() {
        System.out.println(">>> 6. VALIDATING CART REMOVAL INTEGRITY:");
        customer1.returnProduct(acerLaptop);
        System.out.println();
    }

    public static void polymorphismTest() {
        System.out.println(">>> 7. POLYMORPHISM & METHOD DISPATCH DEMONSTRATION:");
        Person[] people = {staffMember, customer1, customer2, customer3};
        for (Person p : people) {
            p.whoAmI();
        }
        System.out.println();
    }

    public static void verificationTest() {
        System.out.println(">>> 8. COMPREHENSIVE REPOSITORY LIST VERIFICATION (0% DATA LOSS):");
        manager.printSystemAuditReport();
    }
}
