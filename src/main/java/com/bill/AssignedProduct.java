package com.bill;

import java.sql.Timestamp;

public class AssignedProduct {
    private final String product_name;
    private final String first_name;
    private final String last_name;
    private final int quantity;
    private final Timestamp timestamp;

    public AssignedProduct(String product_name, String first_name, String last_name, int quantity,Timestamp timestamp) {
        this.product_name = product_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTime() {
        return timestamp.toLocalDateTime().toString().replace("T", " ");
    }
}
