package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.UserDto;
import edu.lk.ijse.farm.model.UserModel;
import edu.lk.ijse.farm.util.CrudUtil;
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
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterUserController {

    public TextField txtEmail;
    public AnchorPane ancPage;
    @FXML
    private Button button;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPassword;

    @FXML
    private Label lbPassword1;

    @FXML
    private Label lbTital;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPassword1;

    @FXML
    UserModel userModel = new UserModel();

    @FXML
    void btnRegisterUserOnAction(ActionEvent event) {
        try {
            String userName = txtName.getText();
            String password = txtPassword.getText();
            String Email = txtEmail.getText();


            UserDto userDto = new UserDto(0, userName, password,Email); // Placeholder for email
            String result = userModel.saveUser(userDto);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 LoginController loginController = new LoginController();


    public void btnBackOnAction(ActionEvent actionEvent) {
        loadView("/view/login/Loginview.fxml",loginController.ancMainContainer);

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
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
