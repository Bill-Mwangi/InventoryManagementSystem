package com.bill;

import javafx.scene.control.Alert;
import java.sql.*;

public class Database {
    public static PreparedStatement unavailableProducts;
    public static PreparedStatement availableProducts = null;
    public static PreparedStatement supplyQuantity = null;
    public static PreparedStatement updateSupplyQuantity = null;
    public static PreparedStatement updateProductCount = null;
    public static PreparedStatement productCount = null;
    public static PreparedStatement insertNewRecipient = null;
    public static PreparedStatement insertNewSuppliers = null;
    public static PreparedStatement insertNewProducts = null;
    public static PreparedStatement selectAllRecipients = null;
    public static PreparedStatement selectAllProducts = null;
    public static PreparedStatement selectAllSuppliers = null;
    public static ResultSet resultSet = null;
    public static boolean isConnected = false;
    private static final String URL = "jdbc:mysql://root@localhost/inventory";
    private static final String USERNAME = "root@localhost";
    private static final String PASSWORD = "Landscaper77";
    private static Connection con;
    public static int rowsAffected;

    public Database() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            isConnected = true;
            selectAllProducts = con.prepareStatement("SELECT * FROM inventory.product");
            selectAllSuppliers = con.prepareStatement("SELECT * FROM inventory.supplier");
            selectAllRecipients = con.prepareStatement("SELECT * FROM inventory.recipient");
            insertNewProducts = con.prepareStatement("INSERT INTO inventory.product(product_name, Description) VALUES( ?, ?)");
            insertNewSuppliers = con.prepareStatement("INSERT INTO inventory.supplier(supplier_name) VALUES( ?)");
            insertNewRecipient = con.prepareStatement("INSERT INTO inventory.recipient(first_name, last_name) VALUES(?, ?)");
            productCount = con.prepareStatement("SELECT Quantity FROM inventory.product WHERE product_id = ?");
            updateProductCount = con.prepareStatement("UPDATE inventory.product SET Quantity = ? WHERE product_id = ?");
            //supplyQuantity = con.prepareStatement("SELECT Quantity FROM inventory.supplier WHERE supplier_id = ?");
//            updateSupplyQuantity = con.prepareStatement("UPDATE inventory.supplier SET quantity = ? WHERE supplier_id = ?");
//            availableProducts = con.prepareStatement("SELECT * FROM products WHERE Quantity > 0");
//            unavailableProducts = con.prepareStatement("SELECT * FROM products WHERE Quantity = 0");
        } catch (SQLException exception) {
            databaseNotConnectedAlert();
        }
    }
    
    public static Connection getConnection() {return con;}
    
    public static void isConnected() {
        if (!isConnected) {
            databaseNotConnectedAlert();
            System.exit(1);
        }
    }

    public static void databaseNotConnectedAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(null);
        alert.setContentText("Error connecting to database.");
        alert.showAndWait();
    }

    public static void errorAlerts(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}

