package org.project;

/**
 * Represents an electronics product that is shippable but does not expire.
 * Examples: TV, Mobile Phone, Laptop.
 * This class implements the Shippable interface, requiring it to provide a weight.
 */
public class ElectronicsProduct extends Product implements Shippable {

    private final double weight; // Weight in kilograms

    /**
     * Constructs a new ElectronicsProduct.
     *
     * @param name     The name of the electronics product.
     * @param price    The price of the product.
     * @param quantity The available quantity.
     * @param weight   The shipping weight of the product in kg.
     */
    public ElectronicsProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity); // Initialize base product properties
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive for a shippable product.");
        }
        this.weight = weight;
    }

    /**
     * Gets the weight of the product.
     * This method is required by the Shippable interface.
     *
     * @return The weight in kilograms.
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     * The getName() method is also required by the Shippable interface,
     * but it's already implemented in the abstract Product class,
     * so we inherit it automatically.
     */
    @Override
    public String getName() {
        return super.getName(); // Inherits the name from the Product class
    }
}
