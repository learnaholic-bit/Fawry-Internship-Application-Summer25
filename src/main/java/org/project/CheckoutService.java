package org.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides functionality to process the checkout operation for a customer.
 * Validates the cart, calculates costs, updates product quantities, deducts customer balance,
 * and handles shipping of items if applicable.
 */
public class CheckoutService {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * This class is not meant to be instantiated, as it only contains static methods.
     */
    private CheckoutService() {
        // Prevent instantiation
        throw new UnsupportedOperationException("CheckoutService is a utility class and cannot be instantiated.");
    }

    /**
     * Processes the checkout for the given customer.
     * Validates the cart, checks product availability and expiration, calculates costs,
     * deducts the customer's balance, and ships items if applicable.
     *
     * @param customer The customer performing the checkout.
     * @throws IllegalStateException if the cart is empty, a product is expired or out of stock,
     *                               or the customer's balance is insufficient.
     */
    public static void processCheckout(Customer customer) {
        // Validate cart
        if (customer.getCart().isEmpty()) {
            throw new IllegalStateException("Cannot checkout with an empty cart.");
        }

        // Check product availability and expiration
        List<Shippable> itemsToShip = new ArrayList<>();
        double subtotal = 0;

        for (CartItem item : customer.getCart().getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            // Check if product is expired
            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                throw new IllegalStateException("Product " + product.getName() + " is expired.");
            }

            // Check if product is out of stock
            if (product.getQuantity() < quantity) {
                throw new IllegalStateException("Product " + product.getName() + " is out of stock.");
            }

            // Add to shipping list if shippable
            if (product instanceof Shippable) {
                // Add each item individually to match the quantity
                for (int i = 0; i < quantity; i++) {
                    itemsToShip.add((Shippable) product);
                }
            }

            // Calculate subtotal
            subtotal += product.getPrice() * quantity;
        }

        // Calculate shipping fees
        double shippingFees = ShippingService.calculateShippingCost(itemsToShip);

        // Calculate total cost
        double totalCost = subtotal + shippingFees;

        // Check customer balance
        if (customer.getBalance() < totalCost) {
            throw new IllegalStateException("Insufficient balance. Required: $" + totalCost + ", Available: $" + customer.getBalance());
        }

        // Reduce product quantities
        for (CartItem item : customer.getCart().getItems()) {
            Product product = item.getProduct();
            product.reduceQuantity(item.getQuantity());
        }

        // Deduct customer balance
        customer.deductBalance(totalCost);

        // Print checkout details
        System.out.println("** Checkout receipt **");
        for (CartItem item : customer.getCart().getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " +
                    (int)(item.getProduct().getPrice() * item.getQuantity()));
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFees);
        System.out.println("Amount " + (int)totalCost);

        // Ship items if applicable
        if (!itemsToShip.isEmpty()) {
            ShippingService.shipItems(itemsToShip);
        }

        // Clear cart
        customer.getCart().clear();
    }
}