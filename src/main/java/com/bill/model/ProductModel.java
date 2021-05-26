package com.bill.model;

import com.bill.Database;
import com.bill.Product;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductModel extends Database {
   // @Override
    public static void addProduct(Product product, int supplierID) throws IllegalStateException {
        isConnected();
        try {
            insertNewProducts.setString(1, product.getProductName());
            insertNewProducts.setInt(2, product.getQuantity());
            insertNewProducts.setString(3, product.getDescription());
            rowsAffected = insertNewProducts.executeUpdate();
            JOptionPane.showMessageDialog(null, rowsAffected +" product" +((rowsAffected > 1)? "s": "") + "added.",
                    "Add products", JOptionPane.PLAIN_MESSAGE);
            supplyQuantity.setInt(1, supplierID);
            resultSet = supplyQuantity.executeQuery();
            if (resultSet.next()) {
                int supplyCount = resultSet.getInt("Quantity");
                int newQuantity = supplyCount + product.getQuantity();
                updateSupplyQuantity.setInt(1, newQuantity);
                updateSupplyQuantity.setInt(2, supplierID);
                rowsAffected = updateSupplyQuantity.executeUpdate();
                JOptionPane.showMessageDialog(null, rowsAffected + " rows affected",
                        "Update Supplier Quantity", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new product\n"+
                    exception.getMessage());
            System.exit(1);
        }
    }

  //  @Override
    public static void assignProduct(Product product, int recipientID) {
        isConnected();
        try {
            productCount.setInt(1, product.getProductID());
            resultSet = productCount.executeQuery();
            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("Quantity");
                if ((availableQuantity - product.getQuantity()) >= 0) {
                    JOptionPane.showMessageDialog(null, recipientID + " has been assigned " + product.getQuantity()
                            + " products", "Assigning Product", JOptionPane.PLAIN_MESSAGE);
                    int newQuantity = availableQuantity - product.getQuantity();
                    updateProductCount.setInt(1, newQuantity);
                } else {
                    JOptionPane.showMessageDialog(null, recipientID + " has been assigned "
                            + availableQuantity + " products", "Assigning Product", JOptionPane.PLAIN_MESSAGE);
                    int newQuantity = 0;
                    updateProductCount.setInt(1, newQuantity);
                }
                updateProductCount.setInt(2, product.getProductID());
                rowsAffected = updateProductCount.executeUpdate();
                JOptionPane.showMessageDialog(null, rowsAffected + " rows affected",
                        "Assign Product", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException exception) {
            errorAlerts("Assign Product", "Error assigning products\n"+
                    exception.getMessage());
            System.exit(1);
        }
    }

   // @Override
    public static List<Product> getAllProducts() {
        isConnected();

        List<Product> results = new ArrayList<>();
        try {

            resultSet = selectAllProducts.executeQuery();
            while (resultSet.next()) {
                results.add(new Product(resultSet.getInt("ProductID"),
                        resultSet.getString("ProductName"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Quantity")));
            }
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error in getting list of products\n"+
                    exception.getMessage());
            System.exit(1);
        }
        return results;
    }

   // @Override
    public static List<Product> getAvailableProducts() {
        isConnected();
        List<Product> products = new ArrayList<>();
        try {
            resultSet = availableProducts.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("ProductID"), resultSet.getString("ProductName"),
                        resultSet.getString("Description"), resultSet.getInt("Quantity")));
            }
        } catch (SQLException exception) {
            errorAlerts("Available Product", "Error showing available products\n"+
                    exception.getMessage());
            System.exit(1);
        }
        return products;
    }

   // @Override
    public static List<Product> getUnavailableProducts() {
        isConnected();
        List<Product> products = new ArrayList<>();
        try {
            resultSet = unavailableProducts.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("ProductID"), resultSet.getString("ProductName"),
                        resultSet.getString("Description"), resultSet.getInt("Quantity")));
            }
        } catch (SQLException exception) {
            errorAlerts("Unavailable Product", "Error showing Unavailable products\n"+
                    exception.getMessage());
            System.exit(1);
        }
        return products;
    }
}
