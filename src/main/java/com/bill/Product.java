package com.bill;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private int quantity;

    public Product(int productID, String productName, String description, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
    }
    public Product(String productName, String description, int quantity) {
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
    }
    public Product(int productID, String productName, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

