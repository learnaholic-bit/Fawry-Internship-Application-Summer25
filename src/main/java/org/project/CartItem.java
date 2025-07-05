package org.project;

/**
 * Represents an item in a shopping cart.
 * Each CartItem is associated with a specific product and a quantity.
 */
public class CartItem {
    private final Product product; // The product associated with this cart item.
    private int quantity; // The quantity of the product in the cart.

    /**
     * Constructs a CartItem with the specified product and quantity.
     *
     * @param product The product to be added to the cart.
     * @param quantity The quantity of the product.
     * @throws IllegalArgumentException if the product is null or quantity is negative.
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Gets the product associated with this cart item.
     *
     * @return The product.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets the quantity of the product in the cart.
     *
     * @return The quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart.
     *
     * @param quantity The new quantity.
     * @throws IllegalArgumentException if the quantity is negative.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the subtotal for this cart item.
     * The subtotal is the product of the product's price and the quantity.
     *
     * @return The subtotal for this cart item.
     */
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}