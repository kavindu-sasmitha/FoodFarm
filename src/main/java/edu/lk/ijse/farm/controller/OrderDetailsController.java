package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.bo.BOFactory;
import edu.lk.ijse.farm.bo.BOTypes;
import edu.lk.ijse.farm.bo.custom.OrderDetailsBO;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.dto.tm.OrderDetailsTM;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderDetailsController implements Initializable {
    private final OrderDetailsBO orderDetailsBO= BOFactory.getInstance().getBO(BOTypes.ORDERDEATAILS);

    @FXML
    private TableColumn<OrderDetailsTM, String> colCustomerId;

    @FXML
    private TableColumn<OrderDetailsTM, String> colItemId;

    @FXML
    private TableColumn<OrderDetailsTM, String> colOrderId;

    @FXML
    private TableColumn<OrderDetailsTM, Double> colPriceOf1KG;

    @FXML
    private TableColumn<OrderDetailsTM, Integer> colQuantity;

    @FXML
    private TableColumn<OrderDetailsTM, Double> colTotalPrice;

    @FXML
    private TableView<OrderDetailsTM> tblOrderDetails;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,String>("OrderId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,String>("customerId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,String>("ItemId"));
        colPriceOf1KG.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,Double>("PriceOf1KG"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,Integer>("Quantity"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<OrderDetailsTM,Double>("TotalPrice"));

        try {
            loadTableData();
        } catch (Exception e) {
            showError("Initialization Error", "Failed to initialize the data.\nPlease try restarting the application.");
        }
    }

private void showError(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR, message);
    alert.setTitle(title);
    alert.show();
}
    private void loadTableData() {
        try {
            ArrayList<OrderDetailDto> allOrders = OrderDetailsBO.getAllOrders();

            ObservableList<OrderDetailsTM> orderTms = FXCollections.observableArrayList();

            for (OrderDetailDto orderDetailDto : allOrders) {
                    OrderDetailsTM orderDetailsTM = new OrderDetailsTM(
                       orderDetailDto.getOrderId(),
                            orderDetailDto.getCustomer_Id(),
                            orderDetailDto.getItemCode(),
                            orderDetailDto.getPrice_of_1KG(),
                            orderDetailDto.getQuantity(),
                            orderDetailDto.getTotalPrice()
                );
                orderTms.add(orderDetailsTM);
            }
            tblOrderDetails.setItems(orderTms);

        } catch (Exception e) {
            showError("Error", "Failed to load the table data.");
        }
    }


}
