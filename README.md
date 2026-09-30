# Customer Complaint Tracking System

An object-oriented Java application designed to manage customer complaints, track product defect histories, handle product returns, and process refunds.

## Features

- **Product Management:** Register products with price, stock, and sale availability status.
- **Customer Shopping & Returns:** Customers can purchase products into a fixed-size cart and return items using array shifting.
- **Complaint Processing:** Support staff can review and resolve customer complaints with full history tracking.
- **Incident Analysis:** Evaluates total complaints, identifies products with high defect counts, and automatically flags or pulls problematic items from sale.

## Class Overview

- `Person`: Abstract base class for Customer and Employee.
- `Customer`: Handles purchasing, cart array operations, returns, and complaint creation.
- `Employee`: Reviews pending complaints, approves refunds, and analyzes product quality.
- `Product`: Stores product details, pricing, sale status, and defect history.
- `Complaint`: Represents customer issue reports with description and resolution state.
- `ComplaintManager`: Central registry coordinating products, customers, and employees.
- `Test`: Main driver demonstrating purchases, complaints, refunds, and system audit reports.

## How to Run

1. Compile all Java source files:
   ```bash
   javac *.java
   ```

2. Run the integration test:
   ```bash
   java Test
   ```

## Author

- **alperencok** (https://github.com/alperencok)

## License

This project is licensed under the MIT License.
