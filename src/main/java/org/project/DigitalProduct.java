package org.project;

/**
 * Represents a digital product that is neither shippable nor expirable.
 * Examples: Mobile scratch cards, e-books, software licenses.
 * This class extends Product and adds no new capabilities.
 */
public class DigitalProduct extends Product {

    /**
     * Constructs a new DigitalProduct.
     *
     * @param name     The name of the digital product.
     * @param price    The price of the digital product.
     * @param quantity The available quantity.
     */
    public DigitalProduct(String name, double price, int quantity) {
        // Calls the parent constructor to set the common properties.
        super(name, price, quantity);
    }
}
