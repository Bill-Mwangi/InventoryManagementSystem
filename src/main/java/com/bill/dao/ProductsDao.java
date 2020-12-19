package com.bill.dao;

import com.bill.Product;

import java.util.List;

public interface ProductsDao {
    void addProduct(Product product, int supplierID);
    void assignProduct(Product product, int recipientID);
    void updateProduct(Product product);
    List<Product> getAvailableProducts();
    List<Product> getUnavailableProducts();
    List<Product> getAllProducts();

}
