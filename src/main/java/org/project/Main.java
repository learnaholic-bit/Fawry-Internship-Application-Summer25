package org.project;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create products
        GroceryProduct cheese = new GroceryProduct("Cheese 400g", 100.0, 10, 0.4, LocalDate.now().plusDays(30));
        GroceryProduct biscuits = new GroceryProduct("Biscuits 700g", 150.0, 15, 0.7, LocalDate.now().plusDays(60));
        ElectronicsProduct tv = new ElectronicsProduct("Smart TV", 1200.0, 5, 15.0);
        DigitalProduct scratchCard = new DigitalProduct("Mobile Scratch Card", 50.0, 100);

        // Store initial quantities for verification
        int initialCheeseQty = cheese.getQuantity();
        int initialBiscuitsQty = biscuits.getQuantity();
        int initialTvQty = tv.getQuantity();
        int initialScratchCardQty = scratchCard.getQuantity();

        // Create customer
        Customer customer = new Customer("John Doe", 2000.0);
        double initialBalance = customer.getBalance();

        // Add products to cart
        customer.addToCart(cheese, 2);
        customer.addToCart(biscuits, 1);
        customer.addToCart(tv, 1);
        customer.addToCart(scratchCard, 1);

        // Process checkout
        try {
            // CheckoutService handles printing the receipt
            CheckoutService.processCheckout(customer);

            // Verification
//            System.out.println("\n=== Verification ===");
//            System.out.println("Cart empty: " + customer.getCart().isEmpty());
//            System.out.println("Balance reduced: " + (initialBalance > customer.getBalance()));
//            System.out.println("Cheese quantity reduced: " + (cheese.getQuantity() == initialCheeseQty - 2));
//            System.out.println("Biscuits quantity reduced: " + (biscuits.getQuantity() == initialBiscuitsQty - 1));
//            System.out.println("TV quantity reduced: " + (tv.getQuantity() == initialTvQty - 1));
//            System.out.println("Scratch card quantity reduced: " + (scratchCard.getQuantity() == initialScratchCardQty - 1));
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}