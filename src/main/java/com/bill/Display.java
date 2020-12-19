//package com.bill;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import java.util.List;
//
//public class Display extends Application {
//
//    Scene mainScene, productScene, supplierScene, receiverScene, allProductsScene, availableProductsScene,
//            unavailableProductsScene, allSuppliersScene, allRecipientsScene;
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        DatabaseAccess access = new DatabaseAccess();
//        VBox mainMenu = new VBox(7);
//        mainMenu.setAlignment(Pos.CENTER);
//
//        Button productsMenuButton = new Button("Product Menu");
//        productsMenuButton.setAlignment(Pos.CENTER);
//        Button suppliersMenuButton = new Button("Suppliers Menu");
//        suppliersMenuButton.setAlignment(Pos.CENTER);
//        Button recipientsMenuButton = new Button("Recipients Menu");
//        recipientsMenuButton.setAlignment(Pos.CENTER);
//        mainMenu.getChildren().addAll(productsMenuButton, suppliersMenuButton, recipientsMenuButton);
//        mainScene = new Scene(mainMenu, 300, 300);
//        primaryStage.setScene(mainScene);
//        primaryStage.setTitle("Inventory Management System");
//        primaryStage.show();
//
//        productsMenuButton.setOnAction(e -> {
//            VBox productMenu = new VBox(7);
//            productMenu.setAlignment(Pos.CENTER);
//
//            Button addProduct = new Button("Add Product");
//            addProduct.setAlignment(Pos.CENTER);
//            addProduct.setOnAction(e1 -> {
//                GridPane addMenu = new GridPane();
//                addMenu.setHgap(10);
//                addMenu.setVgap(7);
//                addMenu.setAlignment(Pos.CENTER);
//
//                Label productNameLbl = new Label("Product name:");
//                TextArea productNameText = new TextArea();
//                productNameText.setPrefRowCount(1);
//                productNameLbl.setLabelFor(productNameText);
//
//                Label descriptionLbl = new Label("Description:");
//                TextArea descriptionText = new TextArea();
//                descriptionText.setPrefRowCount(1);
//                descriptionLbl.setLabelFor(descriptionText);
//
//                Label quantityLbl = new Label("Quantity:");
//                TextArea quantityText = new TextArea();
//                quantityText.setPrefRowCount(1);
//                quantityLbl.setLabelFor(quantityText);
//
//                Label supplierIDLbl = new Label("Supplier ID:");
//                TextArea supplierIDText = new TextArea();
//                supplierIDText.setPrefRowCount(1);
//                supplierIDLbl.setLabelFor(supplierIDText);
//
//                Button submit = new Button("Submit");
//                submit.setAlignment(Pos.CENTER);
//                submit.setOnAction(e2 -> {
//                    int quantity = Integer.parseInt(quantityText.getText());
//                    int supplierID = Integer.parseInt(supplierIDText.getText());
//                    access.addProduct(new Product(productNameText.getText(),
//                            descriptionText.getText(), quantity), supplierID);
//                });
//
//                Button back = new Button("Back");
//                back.setAlignment(Pos.CENTER_RIGHT);
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(productScene);
//                    primaryStage.setTitle("Product Menu");
//                    primaryStage.show();
//                });
//                addMenu.addRow(0, productNameLbl, productNameText);
//                addMenu.addRow(1, descriptionLbl, descriptionText);
//                addMenu.addRow(2, quantityLbl, quantityText);
//                addMenu.addRow(3, supplierIDLbl, supplierIDText);
//                addMenu.addRow(4, submit, back);
//                primaryStage.setScene(new Scene(addMenu, 700, 400));
//                primaryStage.setTitle("Add New Product");
//                primaryStage.show();
//            });
//
//            Button updateProducts = new Button("Update Product");
//            updateProducts.setAlignment(Pos.CENTER);
//            updateProducts.setOnAction(e3 -> {
//                GridPane updateMenu = new GridPane();
//                updateMenu.setHgap(10);
//                updateMenu.setVgap(7);
//                updateMenu.setAlignment(Pos.CENTER);
//
//                Label info = new Label("Adding to existing products");
//                Label productIDLbl = new Label("Product ID:");
//                TextArea productIDText = new TextArea();
//                productIDLbl.setLabelFor(productIDText);
//                productIDText.setPrefRowCount(1);
//
//                Label quantityLbl = new Label("Quantity:");
//                TextArea quantityText = new TextArea();
//                quantityText.setPrefRowCount(1);
//                quantityLbl.setLabelFor(quantityText);
//
//                Button submit = new Button("Submit");
//                submit.setAlignment(Pos.CENTER);
//                submit.setOnAction(e2 -> {
//                    int quantity = Integer.parseInt(quantityText.getText());
//                    int productID = Integer.parseInt(productIDText.getText());
//                    access.updateProducts(productID, quantity);
//                });
//                Button back = new Button("Back");
//                back.setAlignment(Pos.CENTER_RIGHT);
//                back.setOnAction(e4 -> {
//                    primaryStage.setScene(productScene);
//                    primaryStage.setTitle("Product Menu");
//                    primaryStage.show();
//                });
//                updateMenu.addRow(0, info);
//                updateMenu.addRow(1, productIDLbl, productIDText);
//                updateMenu.addRow(2, quantityLbl, quantityText);
//                updateMenu.addRow(3, submit, back);
//                primaryStage.setScene(new Scene(updateMenu, 700, 400));
//                primaryStage.setTitle("Updating Product Count");
//                primaryStage.show();
//            });
//
//            Button assignProduct = new Button("Assign Product");
//            assignProduct.setAlignment(Pos.CENTER);
//            assignProduct.setOnAction(e4 -> {
//                GridPane assignMenu = new GridPane();
//                assignMenu.setHgap(10);
//                assignMenu.setVgap(7);
//                assignMenu.setAlignment(Pos.CENTER);
//
//                Label info = new Label("Assigning a product");
//                Label productIDLbl = new Label("Product ID:");
//                TextArea productIDText = new TextArea();
//                productIDLbl.setLabelFor(productIDText);
//                productIDText.setPrefRowCount(1);
//
//                Label recipientIDLbl = new Label("Recipient ID:");
//                TextArea recipientIDText = new TextArea();
//                recipientIDLbl.setLabelFor(recipientIDText);
//                recipientIDText.setPrefRowCount(1);
//
//                Label quantityLbl = new Label("Quantity:");
//                TextArea quantityText = new TextArea();
//                quantityText.setPrefRowCount(1);
//                quantityLbl.setLabelFor(quantityText);
//
//                Button submit = new Button("Submit");
//                submit.setAlignment(Pos.CENTER);
//                submit.setOnAction(e2 -> {
//                    int quantity = Integer.parseInt(quantityText.getText());
//                    int productID = Integer.parseInt(productIDText.getText());
//                    int recipientID = Integer.parseInt(recipientIDText.getText());
//                    access.assignProduct(productID, recipientID, quantity);
//                });
//                Button back = new Button("Back");
//                back.setAlignment(Pos.CENTER_RIGHT);
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(productScene);
//                    primaryStage.setTitle("Product Menu");
//                    primaryStage.show();
//                });
//                assignMenu.addRow(0, info);
//                assignMenu.addRow(1, productIDLbl, productIDText);
//                assignMenu.addRow(2, recipientIDLbl, recipientIDText);
//                assignMenu.addRow(3, quantityLbl, quantityText);
//                assignMenu.addRow(4, submit, back);
//                primaryStage.setScene(new Scene(assignMenu, 700, 400));
//                primaryStage.setTitle("Assigning Product");
//                primaryStage.show();
//            });
//
//            Button showAvailableProducts = new Button("Available Product");
//            showAvailableProducts.setAlignment(Pos.CENTER);
//            showAvailableProducts.setOnAction(e5 -> {
//                VBox root = new VBox();
//                TableView<Product> tableView = new TableView<>();
//                TableColumn<Product, Integer> productId = new TableColumn<>("Product ID");
//                productId.setMinWidth(100);
//                productId.setCellValueFactory(new PropertyValueFactory<>("productID"));
//                TableColumn<Product, String> productName = new TableColumn<>("Product Name");
//                productName.setMinWidth(100);
//                productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
//                TableColumn<Product, String> description = new TableColumn<>("Description");
//                description.setMinWidth(100);
//                description.setCellValueFactory(new PropertyValueFactory<>("description"));
//                TableColumn<Product, Integer> quantity = new TableColumn<>("Quantity");
//                quantity.setMinWidth(100);
//                quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//                ObservableList<Product> data = FXCollections.observableArrayList();
//                List<Product> availableProducts = access.showAvailableProducts();
//                data.addAll(availableProducts);
//                tableView.setItems(data);
//                tableView.getColumns().addAll(productId, productName, description, quantity);
//
//                Button back = new Button("Back");
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(productScene);
//                    primaryStage.setTitle("Product Menu");
//                    primaryStage.show();
//                });
//
//                root.getChildren().addAll(tableView, back);
//                availableProductsScene = new Scene(root, 400, 400);
//                primaryStage.setScene(availableProductsScene);
//                primaryStage.setTitle("Available Product");
//                primaryStage.show();
//            });
//
//            Button showUnavailableProducts = new Button("Unavailable Product");
//            showUnavailableProducts.setAlignment(Pos.CENTER);
//            showUnavailableProducts.setOnAction(e6 -> {
//                VBox root = new VBox();
//                TableView<Product> tableView = new TableView<>();
//                TableColumn<Product, Integer> productId = new TableColumn<>("Product ID");
//                productId.setMinWidth(100);
//                productId.setCellValueFactory(new PropertyValueFactory<>("productID"));
//                TableColumn<Product, String> productName = new TableColumn<>("Product Name");
//                productName.setMinWidth(100);
//                productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
//                TableColumn<Product, String> description = new TableColumn<>("Description");
//                description.setMinWidth(100);
//                description.setCellValueFactory(new PropertyValueFactory<>("description"));
//                TableColumn<Product, Integer> quantity = new TableColumn<>("Quantity");
//                quantity.setMinWidth(100);
//                quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//                ObservableList<Product> data = FXCollections.observableArrayList();
//                List<Product> unavailableProducts = access.showUnavailableProducts();
//                data.addAll(unavailableProducts);
//                tableView.setItems(data);
//                tableView.getColumns().addAll(productId, productName, description, quantity);
//
//                Button back = new Button("Back");
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(productScene);
//                    primaryStage.setTitle("Product Menu");
//                    primaryStage.show();
//                });
//
//                root.getChildren().addAll(tableView, back);
//                unavailableProductsScene = new Scene(root, 400, 400);
//                primaryStage.setScene(unavailableProductsScene);
//                primaryStage.setTitle("Unavailable Product");
//                primaryStage.show();
//
//            });
//
//            Button showAllProducts = new Button("All Product");
//            showAllProducts.setAlignment(Pos.CENTER);
//            showAllProducts.setOnAction(e2 -> {
//                VBox root = new VBox();
//                TableView<Product> tableView = new TableView<>();
//                TableColumn<Product, Integer> productId = new TableColumn<>("Product ID");
//                productId.setMinWidth(100);
//                productId.setCellValueFactory(new PropertyValueFactory<>("productID"));
//                TableColumn<Product, String> productName = new TableColumn<>("Product Name");
//                productName.setMinWidth(100);
//                productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
//                TableColumn<Product, String> description = new TableColumn<>("Description");
//                description.setMinWidth(100);
//                description.setCellValueFactory(new PropertyValueFactory<>("description"));
//                TableColumn<Product, Integer> quantity = new TableColumn<>("Quantity");
//                quantity.setMinWidth(100);
//                quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//                List<Product> allProducts = access.getAllProducts();
//                ObservableList<Product> data = FXCollections.observableArrayList();
//                data.addAll(allProducts);
//                tableView.setItems(data);
//                tableView.getColumns().addAll(productId, productName, description, quantity);
//
//                Button back = new Button("Back");
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(productScene);
//                    primaryStage.setTitle("Product Menu");
//                    primaryStage.show();
//                });
//
//                root.getChildren().addAll(tableView, back);
//                allProductsScene = new Scene(root, 400, 400);
//                primaryStage.setScene(allProductsScene);
//                primaryStage.setTitle("All Product");
//                primaryStage.show();
//            });
//
//            Button back = new Button("Back");
//            back.setAlignment(Pos.BOTTOM_RIGHT);
//            back.setOnAction(e7 -> {
//                primaryStage.setScene(mainScene);
//                primaryStage.setTitle("Inventory Management System");
//                primaryStage.show();
//            });
//            productMenu.getChildren().addAll(addProduct, showAllProducts, updateProducts,
//                    assignProduct, showAvailableProducts, showUnavailableProducts, back
//            );
//            productScene = new Scene(productMenu, 250, 250);
//            primaryStage.setScene(productScene);
//            primaryStage.setTitle("Product Menu");
//            primaryStage.show();
//
//        });
//        suppliersMenuButton.setOnAction(e1 -> {
//            VBox supplerMenu = new VBox(7);
//            supplerMenu.setAlignment(Pos.CENTER);
//            Button addSupplier = new Button("Add Supplier");
//            addSupplier.setAlignment(Pos.CENTER);
//            addSupplier.setOnAction(e2 -> {
//                GridPane addMenu = new GridPane();
//                addMenu.setHgap(10);
//                addMenu.setVgap(7);
//                addMenu.setAlignment(Pos.CENTER);
//
//                Label supplierIDLbl = new Label("Supplier ID:");
//                TextArea supplierIDText = new TextArea();
//                supplierIDText.setPrefRowCount(1);
//                supplierIDLbl.setLabelFor(supplierIDText);
//
//                Label supplierNameLbl = new Label("Supplier name:");
//                TextArea supplierNameText = new TextArea();
//                supplierNameText.setPrefRowCount(1);
//                supplierNameLbl.setLabelFor(supplierNameText);
//
////                Label quantityLbl = new Label("Quantity:");
////                TextArea quantityText = new TextArea();
////                quantityText.setPrefRowCount(1);
////                quantityLbl.setLabelFor(quantityText);
//
//                Button submit = new Button("Submit");
//                submit.setAlignment(Pos.CENTER);
//                submit.setOnAction(ee -> {
//                    //int quantity = Integer.parseInt(quantityText.getText());
//                    int supplierID = Integer.parseInt(supplierIDText.getText());
//                    access.addSupplier(new Suppliers(supplierID, supplierNameText.getText()));
//                });
//
//                Button back = new Button("Back");
//                back.setAlignment(Pos.CENTER_RIGHT);
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(supplierScene);
//                    primaryStage.setTitle("Suppliers Menu");
//                    primaryStage.show();
//                });
//                addMenu.addRow(0, supplierIDLbl, supplierIDText);
//                addMenu.addRow(1, supplierNameLbl, supplierNameText);
//                //addMenu.addRow(2, quantityLbl, quantityText);
//
//                addMenu.addRow(2, submit, back);
//                primaryStage.setScene(new Scene(addMenu, 700, 400));
//                primaryStage.setTitle("Add New Supplier");
//                primaryStage.show();
//
//            });
//            Button showAllSuppliers = new Button("All Suppliers");
//            showAllSuppliers.setAlignment(Pos.CENTER);
//            showAllSuppliers.setOnAction(e2 -> {
//                VBox root = new VBox();
//                TableView<Suppliers> tableView = new TableView<>();
//                TableColumn<Suppliers, Integer> supplierId = new TableColumn<>("Supplier ID");
//                supplierId.setMinWidth(100);
//                supplierId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
//                TableColumn<Suppliers, String> supplierName = new TableColumn<>("Supplier Name");
//                supplierName.setMinWidth(100);
//                supplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
//                TableColumn<Suppliers, Integer> quantity = new TableColumn<>("Quantity");
//                quantity.setMinWidth(100);
//                quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//
//                ObservableList<Suppliers> data = FXCollections.observableArrayList();
//                List<Suppliers> allSuppliers = access.getAllSuppliers();
//                data.addAll(allSuppliers);
//                tableView.setItems(data);
//                tableView.getColumns().addAll(supplierId, supplierName, quantity);
//
//                Button back = new Button("Back");
//                back.setOnAction(e3 -> {
//                    primaryStage.setScene(supplierScene);
//                    primaryStage.setTitle("Suppliers Menu");
//                    primaryStage.show();
//                });
//                root.getChildren().addAll(tableView, back);
//                allSuppliersScene = new Scene(root, 300, 300);
//                primaryStage.setScene(allSuppliersScene);
//                primaryStage.setTitle("All Suppliers");
//                primaryStage.show();
//            });
//            Button back = new Button("Back");
//            back.setAlignment(Pos.BOTTOM_RIGHT);
//            back.setOnAction(e7 -> {
//                primaryStage.setScene(mainScene);
//                primaryStage.setTitle("Inventory Management System");
//                primaryStage.show();
//            });
//            supplerMenu.getChildren().addAll(addSupplier, showAllSuppliers, back);
//            supplierScene = new Scene(supplerMenu, 250, 250);
//            primaryStage.setScene(supplierScene);
//            primaryStage.setTitle("Suppliers Menu");
//            primaryStage.show();
//        });
//
//        recipientsMenuButton.setOnAction(e2 -> {
//            VBox receiverMenu = new VBox(7);
//            receiverMenu.setAlignment(Pos.CENTER);
//            Button addSupplier = new Button("Add Recipient");
//            addSupplier.setAlignment(Pos.CENTER);
//            addSupplier.setOnAction(e3 -> {
//                GridPane addMenu = new GridPane();
//                addMenu.setHgap(10);
//                addMenu.setVgap(7);
//                addMenu.setAlignment(Pos.CENTER);
//
//                Label firstNameLbl = new Label("First name:");
//                TextArea firstNameText = new TextArea();
//                firstNameText.setPrefRowCount(1);
//                firstNameLbl.setLabelFor(firstNameText);
//
//                Label lastNameLbl = new Label("Last name:");
//                TextArea lastNameText = new TextArea();
//                lastNameText.setPrefRowCount(1);
//                lastNameLbl.setLabelFor(lastNameText);
//
//                Button submit = new Button("Submit");
//                submit.setAlignment(Pos.CENTER);
//                submit.setOnAction(ee -> access.addAssignees(new Assignee(firstNameText.getText(), lastNameText.getText())));
//
//                Button back = new Button("Back");
//                back.setAlignment(Pos.CENTER_RIGHT);
//                back.setOnAction(e4 -> {
//                    primaryStage.setScene(receiverScene);
//                    primaryStage.setTitle("Recipients Menu");
//                    primaryStage.show();
//                });
//                addMenu.addRow(0, firstNameLbl, firstNameText);
//                addMenu.addRow(1, lastNameLbl, lastNameText);
//                addMenu.addRow(2, submit, back);
//                primaryStage.setScene(new Scene(addMenu, 700, 400));
//                primaryStage.setTitle("Add New Recipient");
//                primaryStage.show();
//
//            });
//            Button showAllRecipients = new Button("All Recipients");
//            showAllRecipients.setAlignment(Pos.CENTER);
//            showAllRecipients.setOnAction(e4 -> {
//                VBox root = new VBox();
//                TableView<Assignee> tableView = new TableView<>();
//                TableColumn<Assignee, Integer> recipientId = new TableColumn<>("Recipient ID");
//                recipientId.setMinWidth(100);
//                recipientId.setCellValueFactory(new PropertyValueFactory<>("assigneeID"));
//                TableColumn<Assignee, String> firstName = new TableColumn<>("First Name");
//                firstName.setMinWidth(100);
//                firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//                TableColumn<Assignee, String> lastName = new TableColumn<>("Last Name");
//                lastName.setMinWidth(100);
//                lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//
//                ObservableList<Assignee> data = FXCollections.observableArrayList();
//                List<Assignee> allAssignee = access.getAllAssignees();
//                data.addAll(allAssignee);
//                tableView.setItems(data);
//                tableView.getColumns().addAll(recipientId, firstName, lastName);
//
//                Button back = new Button("Back");
//                back.setOnAction(e5 -> {
//                    primaryStage.setScene(receiverScene);
//                    primaryStage.setTitle("Recipient Menu");
//                    primaryStage.show();
//                });
//
//                root.getChildren().addAll(tableView, back);
//                allRecipientsScene = new Scene(root, 300, 300);
//                primaryStage.setScene(allRecipientsScene);
//                primaryStage.setTitle("All Recipients");
//                primaryStage.show();
//            });
//            Button back = new Button("Back");
//            back.setAlignment(Pos.BOTTOM_RIGHT);
//            back.setOnAction(e7 -> {
//                primaryStage.setScene(mainScene);
//                primaryStage.setTitle("Inventory Management System");
//                primaryStage.show();
//            });
//            receiverMenu.getChildren().addAll(addSupplier, showAllRecipients, back);
//            receiverScene = new Scene(receiverMenu, 250, 250);
//            primaryStage.setScene(receiverScene);
//            primaryStage.setTitle("Recipients Menu");
//            primaryStage.show();
//        });
//    }
//}