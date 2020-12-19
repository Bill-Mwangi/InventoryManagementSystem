package com.bill.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getall();
    void add();
    void modify();

}
