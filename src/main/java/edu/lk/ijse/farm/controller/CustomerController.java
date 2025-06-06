package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.dto.tm.CustomerTM;
import edu.lk.ijse.farm.model.CustomerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CustomerController implements Initializable {

    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    @FXML
    private Label lbSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label lbName;
    @FXML
    private TextField txtName;
    @FXML
    private Label lbContact;
    @FXML
    private TextField txtContact;
    @FXML
    private Label lbEmail;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lbAddress;
    @FXML
    private TextField txtAddress;
    @FXML
    private TableView<CustomerTM> tblCustomer;
    @FXML
    private TableColumn<CustomerTM,String> colId;
    @FXML
    private TableColumn<CustomerTM,String> colName;
    @FXML
    private TableColumn<CustomerTM,String> colContact;
    @FXML
    private TableColumn<CustomerTM,String> colEmail;
    @FXML
    private TableColumn<CustomerTM,String> colAddress;
  
    @FXML
    private Label lbCustomId;

    private final CustomerModel customerModel = new CustomerModel();


    public CustomerController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<CustomerTM, String>("address"));

        try {
            loadNextId();
            loadTableData();
        } catch (Exception e) {
            showError("Initialization Error", "Failed to initialize the data.\nPlease try restarting the application.");
        }
    }

    @FXML
    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {

        String customerId = lbCustomId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (isInputValid(name,contact, email,address)) {
            CustomerDto customerDto = new CustomerDto(customerId, name,contact, email,address);

            try {
                String result = customerModel.saveCustomer(customerDto);
                boolean isSaved = result != null && result.equalsIgnoreCase("true");
                if (!isSaved) {
                    resetPage();
                    clearInputFields();
                    loadTableData();
                    showSuccess("Customer Saved", "Customer details have been saved successfully!");


                    for (CustomerTM customer : tblCustomer.getItems()) {
                        if (customer.getCustomerId().equals(customerDto.getCustomerId())) {
                            tblCustomer.getSelectionModel().select(customer);
                            tblCustomer.scrollTo(customer);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            showError("Search Error", "Please enter a value to search.");
            return;
        }

        try {
            CustomerDto customer = customerModel.searchCustomer(searchText);
            if (customer != null) {
                ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();
                customerTMS.add(new CustomerTM(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getContact(),
                        customer.getEmail(),
                        customer.getAddress()
                ));
                tblCustomer.setItems(customerTMS);
            } else {
                showError("No Results", "No customer found for the search term.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error", "Failed to search for customers.");
        }
    }

    @FXML
    public void btnGenerateReportOnAction(ActionEvent actionEvent) {
        try{
            JasperReport jasperReport=JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/CustomerReport.jrxml")
            );
            Connection connection=DBConnection.getInstance().getConnection();

            Map<String,Object> parameters=new HashMap<>();

            parameters.put("p_date",LocalDate.now().toString());

          JasperPrint jasperPrint= JasperFillManager.fillReport(
                   jasperReport,
                  parameters,
                  connection

            );

            JasperViewer.viewReport(jasperPrint,false);




        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();

    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure",
                ButtonType.YES,
                ButtonType.NO);
        Optional<ButtonType> respons = alert.showAndWait();

        if (respons.isPresent() && respons.get() == ButtonType.YES) {
            try {
                String customerId = lbCustomId.getText();
                String result = customerModel.deleteCustomer(customerId);
                boolean isDeleted = result != null && result.equalsIgnoreCase("Successfully Delete");
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Deleted").show();
                    resetPage();
                    loadTableData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer details").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                showError("Error", "Failed to delete customer details.");
            }
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String customerId = lbCustomId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        if (isInputValid(name, contact, email, address)) {
            CustomerDto customerDto = new CustomerDto(customerId, name, contact, email, address);

            try {
                String result = customerModel.updateCustomer(customerDto);
                boolean isUpdated = result != null && result.equalsIgnoreCase("Customer updated successfully");
                if (isUpdated) {
                    resetPage();
                    clearInputFields();
                    showSuccess("Customer Updated", "Customer details have been updated successfully!");
                } else {
                    showError("Update Failed", "Failed to update customer details.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Database Error", "Unable to update customer details due to a database error.");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error", "An unexpected error occurred.\nPlease contact support.");
            }
        }
    }




    private void clearInputFields() {
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        txtSearch.clear();
        txtSearch.requestFocus();
        txtSearch.selectAll();
        tblCustomer.getSelectionModel().clearSelection();
        tblCustomer.refresh();
    }

    private boolean isInputValid(String name,String contact, String email,String address) {
        if (name.isEmpty()  || contact.isEmpty() || email.isEmpty()|| address.isEmpty()) {
            showError("Input Error", "All input fields are required.\nPlease fill in all fields.");
            return false;
        }

        if (!contact.matches("\\d{10}")) {
            showError("Input Error", "Contact number must be 10 digits.");
            return false;
        }

        if (!email.contains("@") || !email.contains(".")) {
            showError("Input Error", "Please enter a valid email address.");
            return false;
        }

        return true;
    }


    private void loadNextId() {
        try {
            String nextId = customerModel.getNextID();
            if (nextId != null) {
                lbCustomId.setText(nextId);
            }else{
                System.out.println("No customer id found");
            }


        } catch (Exception e) {
            showError("Error", "Failed to load the next customer ID.");
        }
    }


    private void loadTableData() {
        try {
            ArrayList<CustomerDto> allCustomers = customerModel.getAllCustomers();

            ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

            for (CustomerDto customerDto : allCustomers) {
                CustomerTM customerTM = new CustomerTM(
                        customerDto.getCustomerId(),
                        customerDto.getName(),
                        customerDto.getContact(),
                        customerDto.getEmail(),
                        customerDto.getAddress()
                );
                customerTMS.add(customerTM);
            }
            tblCustomer.setItems(customerTMS);

        } catch (Exception e) {
            showError("Error", "Failed to load the table data.");
        }
    }



    private void showSuccess(String title, String message) {
        new Alert(Alert.AlertType.INFORMATION, message).setTitle(title);
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle(title);
        alert.show();
    }

    public void onClickTable(MouseEvent mouseEvent) {
       CustomerTM selectedCustomer= tblCustomer.getSelectionModel().getSelectedItem();

       if (selectedCustomer != null) {
           btnDelete.setDisable(false);
           btnUpdate.setDisable(false);
           btnSave.setDisable(true);
          lbCustomId.setText(selectedCustomer.getCustomerId());
           txtName.setText(selectedCustomer.getName());
           txtContact.setText(selectedCustomer.getContact());
           txtEmail.setText(selectedCustomer.getEmail());
           txtAddress.setText(selectedCustomer.getAddress());


       }


    }
    public void resetPage(){
        loadNextId();
        loadTableData();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        clearInputFields();
    }

}