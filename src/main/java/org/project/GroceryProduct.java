package org.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a grocery product that is both shippable and expirable.
 * Examples: Cheese, Biscuits, Milk.
 * This class implements both the Shippable and Expirable interfaces.
 */
public class GroceryProduct extends Product implements Shippable, Expirable {

    private final double weight; // Weight in kilograms
    private final LocalDate expirationDate;

    /**
     * Constructs a new GroceryProduct.
     *
     * @param name           The name of the grocery product.
     * @param price          The price of the product.
     * @param quantity       The available quantity.
     * @param weight         The shipping weight of the product in kg.
     * @param expirationDate The date when the product expires.
     */
    public GroceryProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity); // Initialize base product properties
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive for a shippable product.");
        }
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null for a grocery product.");
        }
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    // --- Shippable Interface Implementation ---

    @Override
    public double getWeight() {
        return this.weight;
    }
    // getName() is inherited from Product.

    // --- Expirable Interface Implementation ---

    @Override
    public String getExpirationDate() {
        // Format the LocalDate into a user-friendly string as per the interface contract.
        return this.expirationDate.format(DateTimeFormatter.ISO_LOCAL_DATE); // e.g., "2025-12-31"
    }

    @Override
    public boolean isExpired() {
        // The product is expired if its expiration date is before today's date.
        return this.expirationDate.isBefore(LocalDate.now());
    }
}
