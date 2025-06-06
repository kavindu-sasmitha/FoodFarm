package edu.lk.ijse.farm.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class DashBoardController {

    @FXML
    private AnchorPane ancMainContainer;

    @FXML
    private AnchorPane ancPage1;

    @FXML
    private Label lbDateTime;

    @FXML
    private Label lbTime;

    @FXML
    private Circle oxygenStatus;

    @FXML
    private Label oxygenValue;

    @FXML
    private LineChart<?, ?> phChart;

    @FXML
    private Circle phStatus;

    @FXML
    private Label phValue;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private LineChart<?, ?> tempChart;

    @FXML
    private Circle tempStatus;

    @FXML
    private Label tempValue;

    @FXML
    private Label timestampValue;

    @FXML
    private Circle turbidityStatus;

    @FXML
    private Label turbidityValue;

    private void loadView(String resourcePath, AnchorPane container) {
        try {
            if (container != null) {
                container.getChildren().clear(); // Clear existing content
                Parent root = FXMLLoader.load(getClass().getResource(resourcePath));
                if (root != null) {
                    container.getChildren().add(root); // Add the new content
                } else {
                    showAlert("Error", "Failed to load the requested view! Resource not found.");
                }
            } else {
                showAlert("Error", "Target container is null!");
            }
        } catch (IOException e) {
            showAlert("Error", "An error occurred while loading the view: " + e.getMessage());
            e.printStackTrace();
        }
        lbDateTime.setText(LocalDate.now().toString());
        lbTime.setText(LocalTime.now().withNano(0).toString());
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    void btnCustomerViewOnAction(ActionEvent event) {
        loadView("/view/customer/CustomerView.fxml", ancMainContainer);

    }

    @FXML
    void btnDashBoardOnAction(ActionEvent event) {
        loadView("/view/dashboard/DashBoardview.fxml", ancPage1);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        loadView("/view/employee/EmployeeDashboard.fxml", ancMainContainer);

    }

    @FXML
    void btnItemViewOnAction(ActionEvent event) {
        loadView("/view/inventory/ItemView.fxml", ancMainContainer);

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        loadView("/view/login/LoginView.fxml", ancPage1);
    }

    @FXML
    void btnOrderViewOnAction(ActionEvent event) {
        loadView("/view/order/OrderView.fxml", ancMainContainer);

    }

    @FXML
    void btnPlantOnAction(ActionEvent event) {
        loadView("/view/plant/AddPlant.fxml", ancMainContainer);

    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        loadView("/view/supplier/SupplierView.fxml",ancMainContainer);

    }

    public void btnOrderDetailsOnAction(ActionEvent actionEvent) {
        loadView("/view/order/OrderDetailsView.fxml",ancMainContainer);
    }
}
