package org.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart that holds items selected by a customer.
 * Provides functionality to add products, retrieve items, calculate subtotal, and clear the cart.
 */
public class Cart {
    private final List<CartItem> items = new ArrayList<CartItem>(); // List of items in the cart.

    /**
     * Adds a product to the cart with the specified quantity.
     * If the product is already in the cart, updates the quantity.
     *
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product to add.
     * @throws IllegalArgumentException if the product is null, quantity is non-positive,
     *                                  or requested quantity exceeds available stock.
     */
    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }

        // Check if product is already in cart by comparing business attributes
        for (CartItem item : items) {
            if (item.getProduct().hasSameBusinessKey(product)) {
                int newQuantity = item.getQuantity() + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Requested quantity exceeds available stock.");
                }
                item.setQuantity(newQuantity);
                return;
            }
        }

        // If product is not in cart, add it
        items.add(new CartItem(product, quantity));
    }

    /**
     * Retrieves the list of items in the cart.
     *
     * @return A copy of the list of items in the cart.
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Checks if the cart is empty.
     *
     * @return true if the cart is empty, false otherwise.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Clears all items from the cart.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Calculates the subtotal of all items in the cart.
     * The subtotal is the sum of the subtotals of all cart items.
     *
     * @return The subtotal of the cart.
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }
}