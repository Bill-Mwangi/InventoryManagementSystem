package com.bill;

import java.sql.Timestamp;

public class SuppliedProduct {
    private final String productName;
    private final String supplierName;
    private final int quantity;
    private final Timestamp timestamp;

    public SuppliedProduct(String productName, String supplierName, int quantity, Timestamp timestamp) {
        this.productName = productName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public String getProductName() {
        return productName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTime() {
        return timestamp.toLocalDateTime().toString().replace("T", " ");
    }

}
