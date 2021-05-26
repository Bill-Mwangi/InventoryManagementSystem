package com.bill;

import com.bill.interfaces.Connect;
import javafx.scene.control.Alert;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess implements Connect {
    private static PreparedStatement unavailableProducts = null;
    private static PreparedStatement availableProducts = null;
    private static PreparedStatement updateSupplyQuantity = null;
    private static CallableStatement updateProduct = null;
    private static PreparedStatement productCount = null;
    private static PreparedStatement insertNewRecipient = null;
    private static PreparedStatement insertNewSuppliers = null;
    private static CallableStatement insertNewProducts = null;
    private static PreparedStatement selectAllRecipients = null;
    private static Connection con = null;
    private static PreparedStatement selectAssignedProducts = null;
    private static PreparedStatement selectAllSuppliers = null;
    private static CallableStatement assignProcedure = null;
    private static ResultSet resultSet = null;
    private static boolean isConnected = false;
    private static int rowsAffected;
    private static String message;
    private static PreparedStatement selectSuppliedProducts;

    public static void startConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            isConnected = true;
            con.createStatement();
            selectAssignedProducts = con.prepareStatement("SELECT * FROM inventory.assigned_view");
            selectSuppliedProducts = con.prepareStatement("SELECT * FROM inventory.supplied_view");
            selectAllSuppliers = con.prepareStatement("SELECT * FROM inventory.supplier");
            selectAllRecipients = con.prepareStatement("SELECT * FROM inventory.recipient");
            insertNewProducts = con.prepareCall("{CALL add_product(?, ?, ?, ?)}");
            insertNewSuppliers = con.prepareStatement("INSERT INTO inventory.supplier(supplier_name, description, products) VALUES( ?,?,?)");
            insertNewRecipient = con.prepareStatement("INSERT INTO inventory.recipient(first_name, last_name, department) VALUES(?, ?, ?)");
            productCount = con.prepareStatement("SELECT quantity FROM inventory.product WHERE product_name = ?");
            updateProduct = con.prepareCall("{CALL update_product(?, ?, ?, ?)}");
            updateSupplyQuantity = con.prepareStatement("UPDATE inventory.supplier SET quantity = ? WHERE supplier_id = ?");
            availableProducts = con.prepareStatement("SELECT * FROM product WHERE quantity > 0");
            unavailableProducts = con.prepareStatement("SELECT * FROM product WHERE quantity = 0");
            assignProcedure = con.prepareCall("{CALL assign_product(?, ?, ?, ?, ?)}");
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error connecting to database\n"+
                    exception.getMessage());
        }
    }

     public static List<AssignedProduct> getAssignedProducts() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        List<AssignedProduct> results = new ArrayList<>();
        try {
            resultSet = selectAssignedProducts.executeQuery();
            while (resultSet.next()) {
                results.add(new AssignedProduct(resultSet.getString("product_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("date_assigned")));
            }
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error in getting list of assigned products\n"+
                    exception.getMessage());
        }
        return results;
    }
    public static List<Product> getAvailableProducts() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        List<Product> products = new ArrayList<>();
        try {
            resultSet = availableProducts.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("product_id"), resultSet.getString("product_name")));
            }
        } catch(SQLException exception) {
            errorAlerts("Database error", "Error in getting list of assigned products\n"+
                    exception.getMessage());
        }
    return products;
    }
    public static List<SuppliedProduct> getSuppliedProducts() {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        List<SuppliedProduct> results = new ArrayList<>();
        try {
            resultSet = selectSuppliedProducts.executeQuery();
            while (resultSet.next()) {
                results.add(new SuppliedProduct(resultSet.getString("product_name"),
                        resultSet.getString("supplier_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("date_supplied")));
            }
        } catch (SQLException exception) {
            errorAlerts("Database error", "Error in getting list of assigned products\n" +
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

    public static List <String> getSupplierNames() {
        List<Suppliers> suppliers = getAllSuppliers();
        List <String> names = new ArrayList<>();
        for (Suppliers supplier : suppliers) {
            names.add(supplier.getSupplierName());
        }
        return names;
    }
    public static List<String> getProductNames(){
        List<Product> products = getAvailableProducts();
        List <String> names = new ArrayList<>();
        for (Product product : products) {
            names.add(product.getProductName());
        }
        return names;
    }public static List<String> getRecipientNames() {
        List<Recipient> recipients = getAllRecipients();
        List<String> names = new ArrayList<>();
        for (Recipient recipient : recipients) {
            String firstName = recipient.getFirstName();
            String lastName = recipient.getLastName();
            names.add(firstName +" " +lastName);
        }
        return names;
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
    public static String addProduct(Product product, String supplierName) throws IllegalStateException {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            insertNewProducts.setString(1, product.getProductName());
            insertNewProducts.setString(2, product.getDescription());
            insertNewProducts.setInt(3, product.getQuantity());
            insertNewProducts.setString(4, supplierName);
            rowsAffected = insertNewProducts.executeUpdate();
            message = "Product added";
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new product\n"+
                    exception.getMessage());
        }
        return message;
    }
    public static String addSupplier(Suppliers supplier) throws IllegalStateException {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            insertNewSuppliers.setString(1, supplier.getSupplierName());
            insertNewSuppliers.setString(2,supplier.getDescription());
            insertNewSuppliers.setString(3,supplier.getProducts());
            rowsAffected = insertNewSuppliers.executeUpdate();
            message = "Supplier added";
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new supplier\n"+
                    exception.getMessage());
            
        }
        return message;
    }
    public static String addRecipients(Recipient recipient) throws IllegalStateException {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            insertNewRecipient.setString(1, recipient.getFirstName());
            insertNewRecipient.setString(2, recipient.getLastName());
            insertNewRecipient.setString(3, recipient.getDepartment());
            rowsAffected = insertNewRecipient.executeUpdate();
            message = "Recipient added";
        } catch (SQLException exception){
            errorAlerts("Database error", "Error adding new recipient\n"+
                    exception.getMessage());
        }
        return message;
    }
    public static String updateProducts(String productName, int quantity, String supplierName) {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            productCount.setString(1, productName);
            resultSet = productCount.executeQuery();
            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("quantity");
                int newQuantity = quantity + availableQuantity;
                updateProduct.setString(1, productName);
                updateProduct.setString(2, supplierName);
                updateProduct.setInt(3, quantity);
                updateProduct.setInt(4, newQuantity);
                rowsAffected = updateProduct.executeUpdate();
                message = quantity +" product" +(quantity>1?"s":"") +" has been added.";
            }
        } catch (SQLException exception) {
            errorAlerts("Database Error", "Error updating products\n"+
                    exception.getMessage());
        }
        return message;
    }
    public static String assignProduct(String productName, String recipientName, int quantity) {
        if (!isConnected) {
            databaseNotConnectedAlert();
        }
        try {
            productCount.setString(1, productName);
            resultSet = productCount.executeQuery();
            String[] names = recipientName.split(" ");
            assignProcedure.setString(1, productName);
            assignProcedure.setString(2, names[0]);
            assignProcedure.setString(3, names[1]);

            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("quantity");
                if ((availableQuantity - quantity) >= 0) {
                    assignProcedure.setInt(4, quantity);
                    int newQuantity = availableQuantity - quantity;
                    assignProcedure.setInt(5, newQuantity);
                    rowsAffected = assignProcedure.executeUpdate();
                    message =  (recipientName + " has been assigned " +quantity +" products");
                } else {
                    assignProcedure.setInt(4, availableQuantity);
                    assignProcedure.setInt(5, 0);
                    rowsAffected = assignProcedure.executeUpdate();
                    message = (recipientName + " has been assigned " + availableQuantity + " products");
                }
            }
        } catch (SQLException exception) {
            errorAlerts("Assign Product", "Error assigning products\n"+ exception.getCause() +
                    exception.getMessage());
        }
        return message;
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
                updateSupplyQuantity.close();
                updateProduct.close();
                productCount.close();
                insertNewRecipient.close();
                insertNewSuppliers.close();
                insertNewProducts.close();
                selectAllRecipients.close();
                selectAllSuppliers.close();
                if (resultSet != null) resultSet.close();
                con.close();
            } catch(SQLException exception) {
                JOptionPane.showMessageDialog(null,"Error in closing the database",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
