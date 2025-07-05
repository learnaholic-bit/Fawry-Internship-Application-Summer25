# E-Commerce System

A simple Java-based e-commerce application developed as an internship task. This system simulates a basic online shopping platform with product management, shopping cart functionality, and checkout processing.

## Features

- **Product Management**: Different product types (Grocery, Electronics, Digital)
- **Shopping Cart**: Add products, update quantities, calculate subtotals
- **Customer Management**: Balance tracking and cart operations
- **Checkout Process**: Validation, payment processing, and receipt generation
- **Shipping**: Weight-based shipping cost calculation and shipment notices
- **Product Validation**: Stock quantity checks and expiration date verification

## System Architecture

The system follows object-oriented design principles with:

- Abstract classes and inheritance for product types
- Interfaces for specialized behaviors (Shippable, Expirable)
- Service classes for cross-cutting concerns (CheckoutService, ShippingService)
- Business logic validation throughout the codebase

## Class Structure

- **Product**: Abstract base class for all product types
  - **GroceryProduct**: Implements Shippable and Expirable
  - **ElectronicsProduct**: Implements Shippable
  - **DigitalProduct**: Non-shippable digital items

- **Customer**: Manages user information, balance and shopping cart
- **Cart/CartItem**: Manages the shopping experience
- **CheckoutService**: Handles the checkout process
- **ShippingService**: Calculates shipping costs and generates shipping notices

## Usage Example

```java
// Create products
GroceryProduct cheese = new GroceryProduct("Cheese 400g", 100.0, 10, 0.4, LocalDate.now().plusDays(30));
ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 1200.0, 5, 15.0);
DigitalProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 50.0, 100);

// Create customer
Customer customer = new Customer("John Doe", 2000.0);

// Add products to cart
customer.addToCart(cheese, 2);
customer.addToCart(tv, 1);
customer.addToCart(scratchCard, 1);

// Process checkout
CheckoutService.processCheckout(customer);
```

## Setup

1. Ensure you have Java (version 17+) installed
2. Clone the repository
3. Build with Maven: `mvn clean install`
4. Run the main class: `java -cp target/classes org.project.Main`

## Design Considerations

- **Interface vs. Inheritance**: Shippable and Expirable are interfaces following the "composition over inheritance" principle
- **Unique Identifiers**: Products use UUIDs for identity, while business key comparison allows finding equivalent products
- **Validation**: Comprehensive validation throughout ensures data integrity

## Project Status

This project is a demonstration of Java programming concepts including inheritance, interfaces, encapsulation, and basic design patterns. It's intended as an educational example rather than a production-ready system.
