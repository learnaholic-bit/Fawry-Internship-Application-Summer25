package org.project;

public interface Expirable {
    /**
     * Returns the expiration date of the product.
     *
     * @return the expiration date as a String
     */
    String getExpirationDate();

    /**
     * Checks if the product is expired.
     *
     * @return true if the product is expired, false otherwise
     */
    boolean isExpired();
}
