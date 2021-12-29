package asm2;

import java.util.concurrent.atomic.AtomicInteger;

public class Product implements Comparable<Product> {
    private static AtomicInteger idCounter = new AtomicInteger();
    private String id;
    private String name;
    private double price;
    private int quantity;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Constructor method to initialize a product
     * 
     * @param name     Name of the product
     * @param price    Price of the product
     * @param quantity Quantity of the product
     */
    public Product(String name, double price, int quantity) {
        this.id = String.format("P_%s", idCounter.getAndIncrement());
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, double price) {
        this(name, price, 0);
    }

    public Product(String name) {
        this(name, 0, 0);
    }

    @Override
    public String toString() {
        return String.format("\n{ ID: %6s, Name: %20s, Price %,9.2f, Quantity: %4d }", id, name,
                price, quantity);
    }

    @Override
    public int compareTo(Product o) {
        int lengthComparison = Integer.compare(this.id.length(), o.id.length());

        if (lengthComparison != 0) {
            return lengthComparison;
        } else {
            return this.id.compareTo(o.id);
        }
    }
}
