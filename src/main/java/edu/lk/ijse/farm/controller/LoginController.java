package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.bo.BOFactory;
import edu.lk.ijse.farm.bo.BOTypes;
import edu.lk.ijse.farm.bo.custom.LoginBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private AnchorPane ancForgetPassword;

    @FXML
    public AnchorPane ancMainContainer;

    @FXML
    private AnchorPane ancPage1;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPassword;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnRegisterUserOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login/RegisterUser.fxml")));
            ancMainContainer.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUserFrogetPasswordOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login/ForgetPasswordView.fxml")));
            ancPage1.getChildren().setAll(root);
            ancForgetPassword.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

   private final LoginBO loginBO=BOFactory.getInstance().getBO(BOTypes.LOGINBO);


    @FXML
    void btnUserLoginOnAction(ActionEvent event) {
        try {
            boolean isLoggedIn = loginBO.login(txtName.getText(), txtPassword.getText());
            if (isLoggedIn) {
                loadView("/view/dashboard/DashBoardview.fxml",ancPage1);
            } else {
                showAlert("Login Failed", "Invalid username or password. Please try again.");
            }
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }


    }
    private void showAlert(String error, String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(error);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();

    }
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
    }



}
