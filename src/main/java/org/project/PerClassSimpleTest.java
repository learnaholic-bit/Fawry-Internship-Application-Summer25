package org.project;

import java.time.LocalDate;

public class PerClassSimpleTest {
    public static void main(String[] args) {
        System.out.println("===== E-Commerce System Testing =====\n");

        // Test product creation and validation
        testProductCreation();

        // Test cart functionality
        testCartFunctionality();

        // Test customer operations
        testCustomerOperations();

        // Test checkout process
        testCheckoutProcess();

        // Test product identification
        testProductIdentification();

        System.out.println("\n===== All tests completed =====");
    }

    private static void testProductCreation() {
        System.out.println("Testing Product Creation:");

        // Test valid product creation
        try {
            ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);
            System.out.println(" Correct: Created electronics product: " + tv.getName());

            GroceryProduct cheese = new GroceryProduct("Cheddar Cheese", 5.99, 20, 0.5, LocalDate.now().plusDays(30));
            System.out.println(" Correct: Created grocery product: " + cheese.getName());

            DigitalProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 10.0, 100);
            System.out.println(" Correct: Created digital product: " + scratchCard.getName());
        } catch (Exception e) {
            System.out.println(" Incorrect:  Unexpected error during valid product creation: " + e.getMessage());
        }

        // Test invalid product creation
        try {
            new ElectronicsProduct("", 999.99, 5, 15.0);
            System.out.println(" Incorrect:  Failed to catch empty product name");
        } catch (IllegalArgumentException e) {
            System.out.println(" Correct: Correctly rejected empty product name");
        }

        try {
            new ElectronicsProduct("Laptop", -100, 5, 15.0);
            System.out.println(" Incorrect:  Failed to catch negative price");
        } catch (IllegalArgumentException e) {
            System.out.println(" Correct: Correctly rejected negative price");
        }

        try {
            new ElectronicsProduct("Laptop", 100, -5, 15.0);
            System.out.println(" Incorrect:  Failed to catch negative quantity");
        } catch (IllegalArgumentException e) {
            System.out.println(" Correct: Correctly rejected negative quantity");
        }

        try {
            new ElectronicsProduct("Laptop", 100, 5, -15.0);
            System.out.println(" Incorrect:  Failed to catch negative weight");
        } catch (IllegalArgumentException e) {
            System.out.println(" Correct: Correctly rejected negative weight");
        }

        System.out.println();
    }

    private static void testCartFunctionality() {
        System.out.println("Testing Cart Functionality:");

        // Create test products
        ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);
        GroceryProduct cheese = new GroceryProduct("Cheddar Cheese", 5.99, 20, 0.5, LocalDate.now().plusDays(30));

        // Create a cart and add products
        Cart cart = new Cart();

        try {
            cart.addProduct(tv, 2);
            System.out.println(" Correct: Added TV to cart");

            cart.addProduct(cheese, 3);
            System.out.println(" Correct: Added cheese to cart");

            // Test adding same product again (should update quantity using hasSameBusinessKey)
            ElectronicsProduct sameTV = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);
            cart.addProduct(sameTV, 1);
            boolean found = false;
            for (CartItem item : cart.getItems()) {
                if (item.getProduct().hasSameBusinessKey(tv) && item.getQuantity() == 3) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println(" Correct: Successfully updated quantity when adding same product");
            } else {
                System.out.println(" Incorrect:  Failed to update quantity when adding same product");
            }

            // Test subtotal calculation
            double expectedSubtotal = (tv.getPrice() * 3) + (cheese.getPrice() * 3);
            if (Math.abs(cart.getSubtotal() - expectedSubtotal) < 0.001) {
                System.out.println(" Correct: Subtotal calculation correct: $" + cart.getSubtotal());
            } else {
                System.out.println(" Incorrect:  Subtotal calculation incorrect");
            }

            // Test adding more than available quantity
            try {
                cart.addProduct(tv, 10); // Already have 3, only 5 in stock
                System.out.println(" Incorrect:  Failed to catch exceeding available stock");
            } catch (IllegalArgumentException e) {
                System.out.println(" Correct: Correctly rejected exceeding available stock");
            }

            // Test cart clear
            cart.clear();
            if (cart.isEmpty()) {
                System.out.println(" Correct: Cart clear works correctly");
            } else {
                System.out.println(" Incorrect:  Cart clear failed");
            }

        } catch (Exception e) {
            System.out.println(" Incorrect:  Unexpected error during cart test: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testCustomerOperations() {
        System.out.println("Testing Customer Operations:");

        try {
            // Create customer
            Customer customer = new Customer("John Doe", 1000.0);
            System.out.println(" Correct: Created customer: " + customer.getName());

            // Test balance
            if (customer.getBalance() == 1000.0) {
                System.out.println(" Correct: Customer balance correct: $" + customer.getBalance());
            } else {
                System.out.println(" Incorrect:  Customer balance incorrect");
            }

            // Test adding to cart
            ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 500.0, 5, 15.0);
            customer.addToCart(tv, 1);
            if (!customer.getCart().isEmpty() && customer.getCart().getItems().size() == 1) {
                System.out.println(" Correct: Customer's addToCart works correctly");
            } else {
                System.out.println(" Incorrect:  Customer's addToCart failed");
            }

            // Test balance deduction
            customer.deductBalance(200.0);
            if (customer.getBalance() == 800.0) {
                System.out.println(" Correct: Balance deduction works correctly");
            } else {
                System.out.println(" Incorrect:  Balance deduction failed");
            }

            // Test insufficient balance
            try {
                customer.deductBalance(1000.0);
                System.out.println(" Incorrect:  Failed to catch insufficient balance");
            } catch (IllegalArgumentException e) {
                System.out.println(" Correct: Correctly rejected insufficient balance");
            }

        } catch (Exception e) {
            System.out.println(" Incorrect:  Unexpected error during customer test: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testCheckoutProcess() {
        System.out.println("Testing Checkout Process:");

        // Test successful checkout
        try {
            Customer customer = new Customer("Jane Smith", 2000.0);
            ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);
            GroceryProduct cheese = new GroceryProduct("Cheddar Cheese", 5.99, 20, 0.5, LocalDate.now().plusDays(30));
            DigitalProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 10.0, 100);

            customer.addToCart(tv, 1);
            customer.addToCart(cheese, 2);
            customer.addToCart(scratchCard, 3);

            System.out.println("\n  Testing successful checkout:");
            CheckoutService.processCheckout(customer);

            // Verify cart is empty after checkout
            if (customer.getCart().isEmpty()) {
                System.out.println(" Correct: Cart cleared after checkout");
            } else {
                System.out.println(" Incorrect:  Cart not cleared after checkout");
            }

            // Check if product quantities were reduced
            if (tv.getQuantity() == 4 && cheese.getQuantity() == 18 && scratchCard.getQuantity() == 97) {
                System.out.println(" Correct: Product quantities reduced correctly");
            } else {
                System.out.println(" Incorrect:  Product quantities not reduced correctly");
            }

        } catch (Exception e) {
            System.out.println(" Incorrect:  Unexpected error during successful checkout test: " + e.getMessage());
        }

        // Test checkout with insufficient balance
        try {
            Customer customer = new Customer("Poor Bob", 100.0);
            ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);

            customer.addToCart(tv, 1);

            System.out.println("\n  Testing checkout with insufficient balance:");
            CheckoutService.processCheckout(customer);
            System.out.println(" Incorrect:  Failed to catch insufficient balance during checkout");
        } catch (IllegalStateException e) {
            System.out.println(" Correct: Correctly rejected checkout with insufficient balance");
        }

        // Test checkout with expired product
        try {
            Customer customer = new Customer("Alice", 1000.0);
            GroceryProduct expiredMilk = new GroceryProduct("Expired Milk", 3.99, 10, 0.5, LocalDate.now().minusDays(1));

            customer.addToCart(expiredMilk, 1);

            System.out.println("\n  Testing checkout with expired product:");
            CheckoutService.processCheckout(customer);
            System.out.println(" Incorrect:  Failed to catch expired product during checkout");
        } catch (IllegalStateException e) {
            System.out.println(" Correct: Correctly rejected checkout with expired product");
        }

        // Test checkout with empty cart
        try {
            Customer customer = new Customer("Empty Cart Guy", 1000.0);

            System.out.println("\n  Testing checkout with empty cart:");
            CheckoutService.processCheckout(customer);
            System.out.println(" Incorrect:  Failed to catch empty cart during checkout");
        } catch (IllegalStateException e) {
            System.out.println(" Correct: Correctly rejected checkout with empty cart");
        }

        System.out.println();
    }

    private static void testProductIdentification() {
        System.out.println("Testing Product Identification:");

        // Create two identical products
        ElectronicsProduct tv1 = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);
        ElectronicsProduct tv2 = new ElectronicsProduct("Smart TV", 999.99, 5, 15.0);

        // Test UUID-based equals
        if (!tv1.equals(tv2)) {
            System.out.println(" Correct: Different instances with same attributes are not equal (UUID works)");
        } else {
            System.out.println(" Incorrect:  Different instances with same attributes are equal (UUID failed)");
        }

        // Test business key comparison
        if (tv1.hasSameBusinessKey(tv2)) {
            System.out.println(" Correct: Business key comparison works correctly");
        } else {
            System.out.println(" Incorrect:  Business key comparison failed");
        }

        // Test cart merging of identical products
        Cart cart = new Cart();
        cart.addProduct(tv1, 1);
        cart.addProduct(tv2, 2);

        if (cart.getItems().size() == 1) {
            CartItem item = cart.getItems().get(0);
            if (item.getQuantity() == 3) {
                System.out.println(" Correct: Cart correctly merged identical products using business key");
            } else {
                System.out.println(" Incorrect:  Cart failed to merge quantities correctly");
            }
        } else {
            System.out.println(" Incorrect:  Cart failed to merge identical products using business key");
        }

        // Create product with different price
        ElectronicsProduct tv3 = new ElectronicsProduct("Smart TV", 899.99, 5, 15.0);

        if (!tv1.hasSameBusinessKey(tv3)) {
            System.out.println(" Correct: Products with different prices are correctly identified as different");
        } else {
            System.out.println(" Incorrect:  Products with different prices are incorrectly identified as the same");
        }

    }
}


/*
Product Creation Tests
    Valid creation of different product types
    Invalid input validation (empty names, negative values)


Cart Functionality Tests
    Adding products to cart
    Merging identical products (using hasSameBusinessKey)
    Subtotal calculation
    Stock quantity validation
    Cart clearing


Customer Operations Tests
    Customer creation and balance management
    Adding products to customer's cart
    Balance deduction validation


Checkout Process Tests
    Successful checkout with multiple products
    Checkout with insufficient balance
    Checkout with expired products
    Checkout with empty cart


Product Identification Tests
    UUID-based equality testing
    Business key comparison
    Cart merging of identical products
 */