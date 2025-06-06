package edu.lk.ijse.farm.controller;


import edu.lk.ijse.farm.dto.SupplierDto;
import edu.lk.ijse.farm.dto.tm.EmployeeTM;
import edu.lk.ijse.farm.dto.tm.SupplierTM;
import edu.lk.ijse.farm.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class SupplierController implements Initializable {

    public Label lbId;
    public TableView<SupplierTM> tblSupplier;
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<SupplierTM, String> colAddress;

    @FXML
    private TableColumn<SupplierTM,String> colContact;

    @FXML
    private TableColumn<SupplierTM, String> colId;

    @FXML
    private TableColumn<SupplierTM,String> colItem;

    @FXML
    private TableColumn<SupplierTM,String> colName;

    @FXML
    private Label lbAddress;

    @FXML
    private Label lbContact;

    @FXML
    private Label lbSearch;

    @FXML
    private Label lbSupplierId;

    @FXML
    private Label lbSupplierItem;

    @FXML
    private Label lbSupplierName;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

  private final SupplierModel supplierModel=new SupplierModel();

    public SupplierController() throws Exception {
    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String searchText = txtSearch.getText();
        if (searchText.isEmpty()) {
            showError("Search Error", "Please enter a value to search.");
            return;
        }
        try {
            List<SupplierDto> searchResults = supplierModel.searchSupplier(searchText);
            if (searchResults.isEmpty()) {
                showError("No Results", "No matching suppliers found.");
                return;
            }
            ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();
            for (SupplierDto supplierDto : searchResults) {
                SupplierTM supplierTM = new SupplierTM(
                        supplierDto.getSupplierId(),
                        supplierDto.getName(),
                        supplierDto.getContact(),
                        supplierDto.getAddress(),
                        supplierDto.getSupplierItems()
                );
                supplierTMS.add(supplierTM);
            }
            tblSupplier.setItems(supplierTMS);
        } catch (Exception e) {
            showError("Error", "An error occurred while searching for suppliers.");
            e.printStackTrace();
        }
    }

    @FXML
    void btnSuplierSaveOnAction(ActionEvent event) {
        String id = lbId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String item = txtItem.getText();

        if (isInputValid(name, contact, address)) {
            SupplierDto supplierDto = new SupplierDto(id, name, address, contact, item);

            try {
                String result = supplierModel.saveSupplier(supplierDto);
                if (result.equalsIgnoreCase("Supplier saved successfully")) {
                    resetPage();
                    clearInputFields();
                    showSuccess("Supplier Saved", "Supplier details have been saved successfully!");

                    // Reload table data to include the newly saved supplier
                    loadTableData();
                } else {
                    showError("Save Error", result);
                }
            } catch (Exception e) {
                showError("Error", "An error occurred while saving the supplier.");
                e.printStackTrace();
            }
        }
    }

    private void showSuccess(String customerSaved, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, s);
        alert.setTitle(customerSaved);
        alert.show();
    }

    private void resetPage() {
        clearInputFields();

    }

    private void clearInputFields() {
        txtAddress.clear();
        txtContact.clear();
        txtItem.clear();
        txtName.clear();
        txtSearch.clear();
    }

    @FXML
    void btnSupplierDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure",
                ButtonType.YES,
                ButtonType.NO
        );
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                String id = lblId.getText();
                if (!id.isEmpty()) {
                    String result = supplierModel.deleteSupplier(id);
                    if (result.equalsIgnoreCase("Supplier deleted successfully")) {
                        showSuccess("Supplier Deleted", "Supplier details have been deleted successfully!");
                        resetPage();
                        loadTableData();
                    } else {
                        showError("Delete Error", result);
                    }
                } else {
                    showError("Delete Error", "No supplier selected for deletion.");
                }
            } catch (Exception e) {
                showError("Error", "An error occurred while deleting the supplier.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnSupplierResetOnAction(ActionEvent event) {
        loadNextId();
        resetPage();
        loadTableData();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        lblId.setText("");
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();

    }

    @FXML
    void btnSupplierUpdateOnAction(ActionEvent event) {
        String id=lblId.getText();
        String name=txtName.getText();
        String address=txtAddress.getText();
        String contact=txtContact.getText();
        String item=txtItem.getText();
        if (isInputValid(name,contact,address)) {
            SupplierDto supplierDto=new SupplierDto(id,name,address,contact,item);
        }

    }
    private boolean isInputValid(String name,String contact,String address) {
        if (name.isEmpty()  || contact.isEmpty() ||  address.isEmpty()) {
            showError("Input Error", "All input fields are required.\nPlease fill in all fields.");
            return false;
        }

        if (!contact.matches("\\d{10}")) {
            showError("Input Error", "Contact number must be 10 digits.");
            return false;
        }


        return true;
    }

    private void showError(String inputError, String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR, s);
        alert.setTitle(inputError);
        alert.show();
    }


    private void loadNextId() {
        try {
            String nextId = supplierModel.getNextID();
            if (nextId != null) {
                lbId.setText(nextId);
            }else{
                System.out.println("No customer id found");
            }


        } catch (Exception e) {
            showError("Error", "Failed to load the next customer ID.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<SupplierTM, String>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<SupplierTM, String>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<SupplierTM, String>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<SupplierTM, String>("address"));
        colItem.setCellValueFactory(new PropertyValueFactory<SupplierTM, String>("items"));

        try {
            loadNextId();
            loadTableData();
        } catch (Exception e) {
            showError("Initialization Error", "Failed to initialize the data.\nPlease try restarting the application.");
        }
    }
    private void loadTableData() {

        try {
            ArrayList<SupplierDto> allSupplier = supplierModel.getAllSupplier();


           ObservableList<SupplierTM> supplierTMS = FXCollections.observableArrayList();


            for (SupplierDto supplierDto : allSupplier) {

                SupplierTM supplierTM = new SupplierTM(

                       supplierDto.getSupplierId(),
                        supplierDto.getName(),
                        supplierDto.getAddress(),
                        supplierDto.getContact(),
                        supplierDto.getSupplierItems()
                );
                supplierTMS.add(supplierTM);

            }
            tblSupplier.setItems(supplierTMS);

        } catch (Exception e) {
            showError("Error", "Failed to load the table data.");
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        SupplierTM supplierTM = (SupplierTM) tblSupplier.getSelectionModel().getSelectedItem();

        if (supplierTM != null) {
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            lbId.setText(supplierTM.getSupplierId());
            txtName.setText(supplierTM.getName());
            txtContact.setText(supplierTM.getContact());
            txtAddress.setText(supplierTM.getAddress());
            txtItem.setText(supplierTM.getItems());
        } else {
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
            btnSave.setDisable(false);
            resetPage();
        }
    }

}
