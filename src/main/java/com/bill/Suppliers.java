package com.bill;

public class Suppliers {
    private int supplierID;
    private String supplierName;
    private String description;
    private String products;

    public Suppliers(String supplierName, String description, String products) {
        this.supplierName = supplierName;
        this.description = description;
        this.products = products;
    }

    public Suppliers(int supplierID, String supplierName, String description, String products) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.description = description;
        this.products = products;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
}
