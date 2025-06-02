package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.UserDto;
import edu.lk.ijse.farm.model.UserModel;
import edu.lk.ijse.farm.util.CrudUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

public class RegisterUserController {

    public TextField txtEmail;
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

    public void btnBackOnAction(ActionEvent actionEvent) {

    }
}
