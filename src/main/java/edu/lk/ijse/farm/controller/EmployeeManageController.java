package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.dto.EmployeeDto;
import edu.lk.ijse.farm.dto.tm.CustomerTM;
import edu.lk.ijse.farm.dto.tm.EmployeeTM;
import edu.lk.ijse.farm.model.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


public class EmployeeManageController implements Initializable {

    public TableView tblEmployee;
    public Button btnReset;
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;

    @FXML
    private TableColumn<EmployeeTM, String> colContact;

    @FXML
    private TableColumn<EmployeeTM, String> colEmail;

    @FXML
    private TableColumn<EmployeeTM, String> colId;

    @FXML
    private TableColumn<EmployeeTM, String> colJoinDate;

    @FXML
    private TableColumn<EmployeeTM, String> colName;
    @FXML
    private TableColumn<EmployeeTM, String> colPosition;
    
 

    @FXML
    private Label lbEXP;

    @FXML
    private Label lbItemCode;

    @FXML
    private Label lbItemName;

    @FXML
    private Label lbMFD;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbQuantity;

    @FXML
    private Label lbSearch;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSearch;

    private final EmployeeModel employeeModel = new EmployeeModel();

    @FXML


    private void resetPage() {
        txtContact.clear();
        txtDate.clear();
        txtEmail.clear();
        txtName.clear();
        txtPosition.clear();
        txtSearch.clear();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle(title);
        alert.show();
    }

    public void btnEmpResetOnAction(ActionEvent actionEvent) {

        resetPage();

        loadTableData();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        lblId.setText("");
        txtName.clear();
        txtContact.clear();
        txtDate.clear();
        loadNextId();
    }


    public void btnEmpUpdateOnAction(ActionEvent actionEvent) {
        String employeeId = lblId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String date = txtDate.getText();
        String email = txtEmail.getText();
        String position = txtPosition.getText();
        if (isInputValid(name, contact, email, date)) {
            EmployeeDto employeeDto = new EmployeeDto(employeeId, name, contact, date, email, position);

            try {
                String result = employeeModel.updateEmployee(employeeDto);
                boolean isUpdated = result != null && result.equalsIgnoreCase("Employee updated successfully");
                if (!isUpdated) {
                    resetPage();
                    loadNextId();  
                    loadTableData();  
                    new Alert(Alert.AlertType.INFORMATION, "Employee Updated").show();
                } else {
                    showError("Update Failed", "Failed to update employee details.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showError("Database Error", "Unable to update employee details due to a database error.");
            }
        }
    }

    public void btnEmpSaveOnAction(ActionEvent actionEvent) {
        String employeeId = lblId.getText();
        String name = txtName.getText();
        String contact = txtContact.getText();
        String date = txtDate.getText();
        String email = txtEmail.getText();
        String position = txtPosition.getText();
        if (isInputValid(name, contact, email, date)) {
            EmployeeDto employeeDto = new EmployeeDto(employeeId, name, contact, date, email, position);

            try {
                String result = employeeModel.saveEmployee(employeeDto);
                if ("Employee added successfully".equalsIgnoreCase(result)) {
                    resetPage();
                    loadTableData();
                    new Alert(Alert.AlertType.INFORMATION, "Employee Saved").show();
                } else {
                    showError("Save Failed", "Failed to save employee details.");
                }
            } catch (Exception e) {
                showError("Database Error", "Unable to save employee details due to a database error.");
            }

        }


    }


    public void btnEmpDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                String employeeId = lblId.getText();
                boolean isDeleted = Boolean.parseBoolean(employeeModel.deleteEmployee(employeeId));
                if (isDeleted) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Customer Deleted").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer details").show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                showError("Error", "Failed to delete customer details.");
            }

        }


    }

    public void btnSearchEmployeeOnAction(ActionEvent actionEvent) {
        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            showError("Search Error", "Please enter a value to search.");
            return;
        }

        try {
            ArrayList<EmployeeDto> searchResult = new ArrayList<>(employeeModel.searchEmployee(searchText));
            ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

            for (EmployeeDto employeeDto : searchResult) {
                EmployeeTM employeeTM = new EmployeeTM(
                       employeeDto.getEmployeeId(),
                        employeeDto.getName(),
                        employeeDto.getContact(),
                        employeeDto.getJoiningDate(),
                        employeeDto.getEmail(),
                        employeeDto.getPosition()
                );
                employeeTMS.add(employeeTM);
            }
            tblEmployee.setItems(employeeTMS);
        } catch (Exception e) {
            e.printStackTrace();
            showError("Search Error", "Failed to search employee details.");
        }
    }

    private boolean isInputValid(String name, String contact, String email, String address) {
        if (name.isEmpty() || contact.isEmpty() || email.isEmpty() || address.isEmpty()) {
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
            String nextId = employeeModel.getNextID();
            if (nextId != null) {
                lblId.setText(nextId);
            } else {
                System.out.println("No employee id found");
            }


        } catch (Exception e) {
            showError("Error", "Failed to load the next customer ID.");
        }
    }

    private void loadTableData() {
        try {
            ArrayList<EmployeeDto> allEmployee = employeeModel.getAllEmployee();

            ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

            if (allEmployee != null && !allEmployee.isEmpty()) {
                for (EmployeeDto employeeDto : allEmployee) {
                    EmployeeTM employeeTM = new EmployeeTM(
                            employeeDto.getEmployeeId() != null ? employeeDto.getEmployeeId() : "",
                            employeeDto.getName() != null ? employeeDto.getName() : "",
                            employeeDto.getContact() != null ? employeeDto.getContact() : "",
                            employeeDto.getJoiningDate() != null ? employeeDto.getJoiningDate() : "",
                            employeeDto.getEmail() != null ? employeeDto.getEmail() : "",
                            employeeDto.getPosition() != null ? employeeDto.getPosition() : ""
                    );
                    employeeTMS.add(employeeTM);
                }
                tblEmployee.setItems(employeeTMS);
            } else {
                tblEmployee.setItems(FXCollections.observableArrayList());
            }
        } catch (Exception e) {
            showError("Error", "Failed to load the table data.");
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        EmployeeTM selectedEmployee = (EmployeeTM) tblEmployee.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            lblId.setText(selectedEmployee.getEmployeeId());
            txtName.setText(selectedEmployee.getName());
            txtContact.setText(selectedEmployee.getContact());
            txtDate.setText(selectedEmployee.getJoiningDate());
            txtEmail.setText(selectedEmployee.getEmail());
            txtPosition.setText(selectedEmployee.getPosition());
        } else {
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnSave.setDisable(false);
            resetPage();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("contact"));
        colJoinDate.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("joinDate"));
        colEmail.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("email"));
        colPosition.setCellValueFactory(new PropertyValueFactory<EmployeeTM, String>("position"));


        try {
            // Initial setup like loading next ID or table data
            loadNextId();
            loadTableData();
        } catch (Exception e) {
            showError("Initialization Error", "Failed to initialize the data.\nPlease try restarting the application.");
        }
    }
}
