package org.project;

/**
 * Represents a customer in the e-commerce system.
 * A customer has a name, a balance, and a shopping cart.
 */
public class Customer {
    private final String name; // The name of the customer.
    private double balance; // The current balance of the customer.
    private final Cart cart; // The shopping cart associated with the customer.

    /**
     * Constructs a Customer with the specified name and initial balance.
     *
     * @param name The name of the customer.
     * @param initialBalance The initial balance of the customer.
     * @throws IllegalArgumentException if the name is null, empty, or the initial balance is negative.
     */
    public Customer(String name, double initialBalance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be null or empty.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.name = name;
        this.balance = initialBalance;
        this.cart = new Cart();
    }

    /**
     * Gets the name of the customer.
     *
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current balance of the customer.
     *
     * @return The current balance of the customer.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the shopping cart associated with the customer.
     *
     * @return The customer's shopping cart.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Adds a product to the customer's shopping cart with the specified quantity.
     *
     * @param product The product to add to the cart.
     * @param quantity The quantity of the product to add.
     * @throws IllegalArgumentException if the product is null or the quantity is invalid.
     */
    public void addToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    /**
     * Deducts the specified amount from the customer's balance.
     *
     * @param amount The amount to deduct.
     * @throws IllegalArgumentException if the amount exceeds the customer's current balance.
     */
    public void deductBalance(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }
}