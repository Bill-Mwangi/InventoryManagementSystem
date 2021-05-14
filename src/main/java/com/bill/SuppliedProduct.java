package com.bill;

import java.sql.Date;
import java.sql.Time;

public class SuppliedProduct {
    private final String product_name;
    private final String supplierName;
    private final int quantity;
    private final Date date;
    private final Time time;

    public SuppliedProduct(String productName, String supplierName, int quantity, Date date, Time time) {
        this.product_name = productName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
