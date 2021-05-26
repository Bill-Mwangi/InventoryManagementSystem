package com.bill;

import java.sql.Date;
import java.sql.Time;

public class AssignedProduct {
    private final String product_name;
    private final String first_name;
    private final String last_name;
    private final int quantity;
    private final Date date;
    private final Time time;

    public AssignedProduct(String product_name, String first_name, String last_name, int quantity, Date date, Time time) {
        this.product_name = product_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
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

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
