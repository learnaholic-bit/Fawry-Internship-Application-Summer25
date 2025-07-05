package org.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides shipping-related functionality for the e-commerce system.
 * Includes methods to ship items and calculate shipping costs based on item weights.
 */
public class ShippingService {
    /**
     * The shipping rate per kilogram.
     * Used to calculate the shipping cost for items.
     */
    public static final double SHIPPING_RATE_PER_KG = 5.0; // $5 per kg

    /**
     * Ships the given list of shippable items.
     * Prints the details of each item being shipped to the console.
     *
     * @param items The list of items to be shipped. Each item must implement the Shippable interface.
     */
    public static void shipItems(List<Shippable> items) {
        System.out.println("** Shipment notice **");

        // Create maps to track counts and item references
        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Shippable> itemReferences = new HashMap<>();
        double totalWeight = 0;

        // Count occurrences and store references
        for (Shippable item : items) {
            String key = item.getName() + "_" + item.getWeight();
            itemCounts.put(key, itemCounts.getOrDefault(key, 0) + 1);
            if (!itemReferences.containsKey(key)) {
                itemReferences.put(key, item);
            }
            totalWeight += item.getWeight();
        }

        // Print items with their counts
        for (String key : itemCounts.keySet()) {
            int count = itemCounts.get(key);
            Shippable item = itemReferences.get(key);

            if (count > 1) {
                System.out.println(count + "x " + item.getName());
            } else {
                System.out.println("1x " + item.getName());
            }
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }

    /**
     * Calculates the total shipping cost for the given list of shippable items.
     * The cost is determined by multiplying the total weight of all items by the shipping rate per kilogram.
     *
     * @param items The list of items to calculate the shipping cost for. Each item must implement the Shippable interface.
     * @return The total shipping cost for the items.
     */
    public static double calculateShippingCost(List<Shippable> items) {
        double totalWeight = 0;
        for (Shippable item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight * SHIPPING_RATE_PER_KG;
    }
}