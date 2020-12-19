package com.bill;

import com.bill.controllers.AddProductController;
import com.bill.controllers.AddRecipientController;
import com.bill.controllers.AddSupplierController;
import com.bill.interfaces.Connect;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess implements Connect {
    private static PreparedStatement unavailableProducts = null;
    private static PreparedStatement availableProducts = null;
    private static PreparedStatement supplyQuantity = null;
    private static PreparedStatement updateSupplyQuantity = null;
    private static PreparedStatement updateProductCount = null;
    private static PreparedStatement productCount = null;
    private static PreparedStatement insertNewRecipient = null;
    private static PreparedStatement insertNewSuppliers = null;
    private static PreparedStatement insertNewProducts = null;
    private static PreparedStatement selectAllRecipients = null;
    private static Connection con = null;
    private static PreparedStatement selectAllProducts = null;
    private static PreparedStatement selectAllSuppliers = null;
    private static ResultSet resultSet = null;
    private static boolean isConnected = false;
    private static int rowsAffected;

    public DatabaseAccess() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            isConnected = true;
            con.createStatement();
            selectAllProducts = con.prepareStatement("SELECT * FROM inventory.product");
            selectAllSuppliers = con.prepareStatement("SELECT * FROM inventory.supplier");
            selectAllRecipients = con.prepareStatement("SELECT * FROM inventory.recipient");
            insertNewProducts = con.prepareStatement("INSERT INTO inventory.product(product_id, product_name, quantity, description, supplier_id) VALUES(?, ?, ?, ?, ?)");
            insertNewSuppliers = con.prepareStatement("INSERT INTO inventory.supplier(supplier_name, description, products) VALUES( ?,?,?)");
            insertNewRecipient = con.prepareStatement("INSERT INTO inventory.recipient(first_name, last_name, department) VALUES(?, ?, ?)");
            productCount = con.prepareStatement("SELECT quantity FROM inventory.product WHERE product_id = ?");
            updateProductCount = con.prepareStatement("UPDATE inventory.product SET quantity = ? WHERE product_id = ?");
            //supplyQuantity = con.prepareStatement("SELECT quantity FROM inventory.supplier WHERE supplier_id = ?");
            updateSupplyQuantity = con.prepareStatement("UPDATE inventory.supplier SET quantity = ? WHERE supplier_id = ?");
            availableProducts = con.prepareStatement("SELECT * FROM product WHERE quantity > 0");
            unavailableProducts = con.prepareStatement("SELECT * FROM product WHERE quantity = 0");
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error connecting to database\n"+
                    exception.getMessage());
        }
    }

     public static List<Product> getAllProducts() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }

        List<Product> results = new ArrayList<>();
        try {
            resultSet = selectAllProducts.executeQuery();
            while (resultSet.next()) {
                results.add(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity")));
            }
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error in getting list of products\n"+
                    exception.getMessage());
            
        }
        return results;
    }
    public static List<Suppliers> getAllSuppliers() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        List<Suppliers> results = new ArrayList<>();
        try {
            resultSet = selectAllSuppliers.executeQuery();
            while (resultSet.next()) {
                results.add(new Suppliers(resultSet.getInt("supplier_id"),
                        resultSet.getString("supplier_name"), resultSet.getString("description"),
                        resultSet.getString("products")));
            }
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error in getting list of suppliers\n"+
                    exception.getMessage());
            
        }
        return results;
    }
    public static List<Recipient> getAllRecipients() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }

        List<Recipient> results = new ArrayList<>();
        try {
            resultSet = selectAllRecipients.executeQuery();
            while (resultSet.next()) {
                results.add(new Recipient(resultSet.getInt("recipient_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("department")));
            }
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error in getting list of recipients\n"+
                    exception.getMessage());
            
        }
        return results;
    }
    public static void addProduct(Product product, int supplierID) throws IllegalStateException {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            insertNewProducts.setString(1, null);
            insertNewProducts.setString(2, product.getProductName());
            insertNewProducts.setInt(3, product.getQuantity());
            insertNewProducts.setString(4, product.getDescription());
            insertNewProducts.setInt(5, supplierID);
            rowsAffected = insertNewProducts.executeUpdate();
            AddProductController.actionTarget.setText("Product added");
            supplyQuantity.setInt(1, supplierID);
            resultSet = supplyQuantity.executeQuery();
//            if (resultSet.next()) {
//                int supplyCount = resultSet.getInt("quantity");
//                int newQuantity = supplyCount + product.getQuantity();
//                updateSupplyQuantity.setInt(1, newQuantity);
//                updateSupplyQuantity.setInt(2, supplierID);
//                rowsAffected = updateSupplyQuantity.executeUpdate();
//                JOptionPane.showMessageDialog(null, rowsAffected + " rows affected",
//                        "Update Supplier Quantity", JOptionPane.PLAIN_MESSAGE);
//            }
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new product\n"+
                    exception.getMessage());
        }
    }
    public static void addSupplier(Suppliers supplier) throws IllegalStateException {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            insertNewSuppliers.setString(1, supplier.getSupplierName());
            insertNewSuppliers.setString(2,supplier.getDescription());
            insertNewSuppliers.setString(3,supplier.getProducts());
            rowsAffected = insertNewSuppliers.executeUpdate();
            AddSupplierController.actionTarget.setText("Supplier added");
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new supplier\n"+
                    exception.getMessage());
            
        }
    }
    public static void addRecipients(Recipient recipient) throws IllegalStateException {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            insertNewRecipient.setString(1, recipient.getFirstName());
            insertNewRecipient.setString(2, recipient.getLastName());
            insertNewRecipient.setString(3, recipient.getDepartment());
            rowsAffected = insertNewRecipient.executeUpdate();
            AddRecipientController.actionTarget.setText("Recipient added");
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new recipient\n"+
                    exception.getMessage());
        }
    }
    public static void updateProducts(int productID, int quantity) {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            productCount.setInt(1, productID);
            resultSet = productCount.executeQuery();
            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("quantity");
                int newQuantity = quantity + availableQuantity;
                updateProductCount.setInt(1, newQuantity);
                updateProductCount.setInt(2, productID);
                rowsAffected = updateProductCount.executeUpdate();
                JOptionPane.showMessageDialog(null, rowsAffected + " rows affected",
                        "Update Product", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException exception) {
            errorAlerts("Database Error", "Error updating products\n"+
                    exception.getMessage());
        }
    }
    public static void assignProduct(int productID, int recipientID, int quantity) {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            productCount.setInt(1, productID);
            resultSet = productCount.executeQuery();
            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("quantity");
                if ((availableQuantity - quantity) >= 0) {
                    JOptionPane.showMessageDialog(null, recipientID + " has been assigned " + quantity
                            + " products", "Assigning Product", JOptionPane.PLAIN_MESSAGE);
                    int newQuantity = availableQuantity - quantity;
                    updateProductCount.setInt(1, newQuantity);
                } else {
                    JOptionPane.showMessageDialog(null, recipientID + " has been assigned "
                            + availableQuantity + " products", "Assigning Product", JOptionPane.PLAIN_MESSAGE);
                    int newQuantity = 0;
                    updateProductCount.setInt(1, newQuantity);
                }
                updateProductCount.setInt(2, productID);
                rowsAffected = updateProductCount.executeUpdate();
                JOptionPane.showMessageDialog(null, rowsAffected + " rows affected",
                        "Assign Product", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (SQLException exception) {
            errorAlerts("Assign Product", "Error assigning products\n"+
                    exception.getMessage());
        }
    }
    public static List<Product> showAvailableProducts() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        List<Product> products = new ArrayList<>();
        try {
            resultSet = availableProducts.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("product_id"), resultSet.getString("product_name"),
                        resultSet.getString("description"), resultSet.getInt("quantity")));
            }
        } catch (SQLException exception) {
            errorAlerts("Available Product", "Error showing available products\n"+
                    exception.getMessage());
        }
        return products;
    }
    public static List<Product> showUnavailableProducts() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        List<Product> products = new ArrayList<>();
        try {
            resultSet = unavailableProducts.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("product_id"), resultSet.getString("product_name"),
                        resultSet.getString("description"), resultSet.getInt("quantity")));
            }
        } catch (SQLException exception) {
            errorAlerts("Unavailable Product", "Error showing Unavailable products\n"+
                    exception.getMessage());
        }
        return products;
    }
    private static void databaseNotConnectedAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(null);
        alert.setContentText("Database not connected");
        alert.showAndWait();
    }
    private static void errorAlerts(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void plainMessageAlerts(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void closeConnection() {
        if (isConnected) {
            try {
                unavailableProducts.close();
                availableProducts.close();
                supplyQuantity.close();
                updateSupplyQuantity.close();
                updateProductCount.close();
                productCount.close();
                insertNewRecipient.close();
                insertNewSuppliers.close();
                insertNewProducts.close();
                selectAllRecipients.close();
                selectAllProducts.close();
                selectAllSuppliers.close();
                resultSet.close();
                con.close();
            } catch(SQLException exception) {
                JOptionPane.showMessageDialog(null,"Error in closing the database",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
