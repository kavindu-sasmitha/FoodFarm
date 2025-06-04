package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.model.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import edu.lk.ijse.farm.controller.LoginController;

public class FrogetPasswordController {


    public AnchorPane ancePane;
    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnSendCode;

    @FXML
    private Button btnVerifyCode;

    @FXML
    private Label lbCode;

    @FXML
    private Label lbConfirmPassword;

    @FXML
    private Label lbEmail;

    @FXML
    private Label lbNewPassword;

    @FXML
    private Label lbTitle;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtVerificationCode;

    private final String otp = "2004";

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if (!txtNewPassword.getText().equals(txtConfirmPassword.getText())) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match").showAndWait();
            return;
        }

        LoginModel loginModel = new LoginModel();
        boolean isUpdated = loginModel.updateUserPassword(txtEmail.getText(), txtNewPassword.getText());

        if (!isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Password reset successful!").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to reset password! Please try again.").showAndWait();
        }
    }

    @FXML
    void btnSendCodeOnAction(ActionEvent event) {
        if(txtEmail == null || txtEmail.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter an email").showAndWait();
            return;
        }

        // Check for valid email format
        if (!txtEmail.getText().contains("@") || !txtEmail.getText().contains(".")) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid email").showAndWait();
            return;
        }

        String subject = "Password Reset Code";
        String text = "Your verification code is: " + otp;
        String email = txtEmail.getText();
        String myEmail = "kavindusasmitha20@gmail.com"; // Correct the email format here
        String myPassword = "mnen eqxh ojek pves"; // Correct application-specific password

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail)); // Validate this email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            new Alert(Alert.AlertType.INFORMATION, "Verification code sent to your email").showAndWait();
            txtVerificationCode.setText(otp);
            btnVerifyCode.setDisable(false);
            btnSendCode.setDisable(true);
            btnConfirm.setDisable(false);
            lbCode.setVisible(true);
        } catch (AddressException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address: " + myEmail).showAndWait();
        } catch (MessagingException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to send email. Please check your internet connection and email credentials").showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void btnVerifyCodeOnAction(ActionEvent event) {
        if (txtVerificationCode.getText().equals(otp)) {
            new Alert(Alert.AlertType.INFORMATION, "Verification successful!").showAndWait();
            btnConfirm.setDisable(false);
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid verification code!").showAndWait();
        }
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



    public void btnBackOnAction(ActionEvent actionEvent) {
        loadView("/view/login/Loginview.fxml",ancePane);

    }
}
