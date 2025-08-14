package edu.lk.ijse.farm.controller;


import edu.lk.ijse.farm.bo.BOFactory;
import edu.lk.ijse.farm.bo.BOTypes;
import edu.lk.ijse.farm.bo.custom.CustomerBO;
import edu.lk.ijse.farm.bo.custom.ItemBO;
import edu.lk.ijse.farm.bo.custom.OrderBO;
import edu.lk.ijse.farm.dto.ItemDto;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.dto.OrderDto;
import edu.lk.ijse.farm.dto.tm.CartTm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    public TextField txtCustomerContact;


    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn<CartTm, Button> colAction;

    @FXML
    private TableColumn<CartTm, String> colItemId;

    @FXML
    private TableColumn<CartTm, String> colName;

    @FXML
    private TableColumn<CartTm, Double> colPrice;

    @FXML
    private TableColumn<CartTm, Integer> colQuantity;

    @FXML
    private TableColumn<CartTm, Double> colTotal;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQty;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label orderDate;

    @FXML
    private TableView<CartTm> tblCart;

    @FXML
    private TextField txtAddToCartQty;
    @FXML
    private ComboBox<String> cmbPaymentMethod;

    //private final OrderModel orderModel = new OrderModel();
    private final CustomerBO customerBO=BOFactory.getInstance().getBO(BOTypes.CUSTOMERBO);
    //private final CustomerModel customerModel = new CustomerModel();
    private final ItemBO itemBO=BOFactory.getInstance().getBO(BOTypes.ITEMBO);
    //private final ItemModel itemModel = new ItemModel();
    private final OrderBO orderBO= BOFactory.getInstance().getBO(BOTypes.ORDERBO);

    private final ObservableList<CartTm> cartData = FXCollections.observableArrayList();

    public OrderController() throws Exception {

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        try {
            String selectedItemId = cmbItemId.getValue();
            String cartQtyString = txtAddToCartQty.getText();

            if (selectedItemId == null) {
                showAlert(Alert.AlertType.WARNING, "Please select an item");
                return;
            }

            if (!cartQtyString.matches("^[0-9]+$") || cartQtyString.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Please enter valid quantity");
                return;
            }

            int cartQty = Integer.parseInt(cartQtyString);
            int currentAvailableQty = Integer.parseInt(lblItemQty.getText());
            String itemName = lblItemName.getText();
            double unitPrice = Double.parseDouble(lblItemPrice.getText());
            double total = unitPrice * cartQty;

            if (cartQty <= 0) {
                showAlert(Alert.AlertType.WARNING, "Quantity must be positive");
                return;
            }

            for (CartTm existingItem : cartData) {
                if (existingItem.getItemId().equals(selectedItemId)) {
                    int newQty = existingItem.getCartQty() + cartQty;
                    int originalStock = currentAvailableQty + existingItem.getCartQty();

                    if (newQty > originalStock) {
                        showAlert(Alert.AlertType.WARNING,
                                "Cannot add more than available stock. Available: " +
                                        (originalStock - existingItem.getCartQty()));
                        return;
                    }

                    existingItem.setCartQty(newQty);
                    existingItem.setTotal(newQty * unitPrice);
                    lblItemQty.setText(String.valueOf(originalStock - newQty));
                    txtAddToCartQty.clear();
                    tblCart.refresh();
                    return;
                }
            }
 // For new items
            if (cartQty > currentAvailableQty) {
                showAlert(Alert.AlertType.WARNING,
                        "Not enough quantity! Available: " + currentAvailableQty);
                return;
            }

            Button removeBtn = new Button("Remove");
            CartTm newItem = new CartTm(
                    selectedItemId,
                    itemName,
                    cartQty,
                    unitPrice,
                    total,
                    removeBtn
            );

            removeBtn.setOnAction(eventRemove -> {
                int currentStock = Integer.parseInt(lblItemQty.getText());
                lblItemQty.setText(String.valueOf(currentStock + newItem.getCartQty()));
                cartData.remove(newItem);
                tblCart.refresh();
            });

            lblItemQty.setText(String.valueOf(currentAvailableQty - cartQty));
            cartData.add(newItem);
            txtAddToCartQty.clear();
            tblCart.refresh();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid input for quantity or price.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            if (tblCart.getItems().isEmpty()) {
                showAlert(
                        Alert.AlertType.WARNING,
                        "Please add items to cart..!"
                );
                return;
            }

            if (txtCustomerContact.getText() == null || txtCustomerContact.getText().isEmpty()) {
                showAlert(
                        Alert.AlertType.WARNING,
                        "Please select customer for place order..!"
                );
                return;
            }

            String selectedCustomerId = txtCustomerContact.getText();
            String orderId = lblOrderId.getText();
            Date date = Date.valueOf(orderDate.getText());

            ArrayList<OrderDetailDto> cartList = new ArrayList<>();

            for (CartTm cartTM : cartData) {
                OrderDetailDto orderDetailsDTO = new OrderDetailDto(
                        orderId,
                        selectedCustomerId,
                        cartTM.getItemId(),
                        cartTM.getUnitPrice(),
                        cartTM.getCartQty(),
                        cartTM.getTotal()
                );
                cartList.add(orderDetailsDTO);
            }

            OrderDto orderDTO = new OrderDto(
                    orderId,
                    selectedCustomerId,
                    date.toString(),
                    cartList.stream().mapToDouble(OrderDetailDto::getTotalPrice).sum(),
                    "Pending",
                    cartList
            );
           // boolean isPlaced = orderModel.placeOrder(orderDTO);
            boolean isPlaced=orderBO.placeOrder(orderDTO);

            if (isPlaced) {
                resetPage();
                showAlert(Alert.AlertType.INFORMATION, "Order placed successfully..!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Fail to place order..!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Class not found error: " + e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        try {
            resetPage();
            cartData.clear();
            tblCart.refresh();
            lblCustomerName.setText("");
            lblItemName.setText("");
            lblItemPrice.setText("");
            lblItemQty.setText("");
            txtAddToCartQty.clear();
            txtCustomerContact.getText();
            cmbItemId.setValue(null);
        } catch (SQLException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error resetting page: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String selectedCustomerContact = txtCustomerContact.getText();
        if (selectedCustomerContact != null) {
            String name = orderBO.findNameByContact(selectedCustomerContact);
            lblCustomerName.setText(name);
        } else {
            lblCustomerName.setText("");
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        if (selectedItemId != null) {
            ItemDto itemDto = itemBO.findById(selectedItemId);

            if (itemDto != null) {
                lblItemName.setText(itemDto.getItemName());
                lblItemQty.setText(String.valueOf(itemDto.getQtyOnHand()));
                lblItemPrice.setText(String.valueOf(itemDto.getUnitPrice()));
            } else {
                lblItemName.setText("");
                lblItemQty.setText("");
                lblItemPrice.setText("");
            }
        } else {
            lblItemName.setText("");
            lblItemQty.setText("");
            lblItemPrice.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        tblCart.setItems(cartData);
        cmbPaymentMethod.getItems().addAll(
                "Cash",
                "Credit Card",
                "Debit Card",
                "Mobile Payment",
                "Bank Transfer"
        );


        try {
            resetPage();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert(
                    Alert.AlertType.ERROR,
                    "Failed to load data from the database! Please try again later."
            );
        }
    }

    public void resetPage() throws SQLException, ClassNotFoundException {
        lblOrderId.setText(orderBO.getNextOrderId());
        orderDate.setText(LocalDate.now().toString());
        loadItemIds();
    }



    private void loadItemIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> itemIdsList = itemBO.getAllItemIds();
        ObservableList<String> itemIds = FXCollections.observableArrayList(itemIdsList);
        cmbItemId.setItems(itemIds);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type.toString());
        alert.setHeaderText(null);
        alert.setContentText(message);

        Window ownerWindow = txtAddToCartQty.getScene().getWindow();
        if (ownerWindow != null && ownerWindow.isShowing()) {
            alert.initOwner(ownerWindow);
        }

        alert.showAndWait();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String searchText = txtCustomerContact.getText();
        if (searchText == null || searchText.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please enter a customer contact");
            return;
        }

        try {
            String customerName = customerBO.findNameByContact(searchText);
            lblCustomerName.setText(customerName);
        } catch (Exception e) {
            lblCustomerName.setText("New Customer");
            e.printStackTrace();
        }
    }


}
