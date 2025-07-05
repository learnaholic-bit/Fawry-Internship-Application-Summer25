package org.project;

import java.util.UUID;

/**
 * Represents the base for all products in the e-commerce system.
 * This class is abstract because we should never create a "generic" product.
 * Every product must belong to a specific category (e.g., Electronics, Grocery).
 */
public abstract class Product {
    private final String id; // Unique identifier
    protected String name;
    protected double price;
    protected int quantity; // Available stock

    /**
     * Constructor for the Product class.
     * It's called by subclasses to initialize common properties.
     *
     * @param name     The name of the product.
     * @param price    The price of the product.
     * @param quantity The available quantity in stock.
     */
    public Product(String name, double price, int quantity) {
        this.id = UUID.randomUUID().toString();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative.");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the unique identifier of the product.
     *
     * @return The product's unique identifier.
     */
    public String getId() {
        return id;
    }

    // --- Getters ---

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // --- Mutators ---

    /**
     * Updates the quantity of the product.
     * This is crucial for managing stock during checkout.
     *
     * @param quantity The new quantity of the product.
     */
    public void setQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    /**
     * Reduces the quantity of the product by a specified amount.
     * This method is used during checkout to decrease stock.
     *
     * @param quantity The amount to reduce the quantity by.
     */
    public void reduceQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity -= quantity;
    }

    /**
     * Increases the quantity of the product by a specified amount.
     * This method is used when restocking products.
     *
     * @param quantity The amount to increase the quantity by.
     */
    public void increaseQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity += quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }


    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative.");
        }
        this.price = price;
    }

    /**
     * Sets the name of the product.
     * This method is used to update the product's name.
     *
     * @param name The new name of the product.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Checks if this product has the same business attributes as another product.
     * Used by the cart to identify "identical" products by name and price.
     *
     * @param other The product to compare with.
     * @return true if the products have the same name and price, false otherwise.
     */
    public boolean hasSameBusinessKey(Product other) {
        if (other == null) {
            return false;
        }
        return name.equals(other.name) && price == other.price;
    }

    // --- Overridden Methods from Object ---

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id.equals(product.id);
    }
}