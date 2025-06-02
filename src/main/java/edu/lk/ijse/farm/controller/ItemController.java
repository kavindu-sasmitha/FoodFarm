package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.ItemDto;
import edu.lk.ijse.farm.dto.tm.ItemTm;
import edu.lk.ijse.farm.model.ItemModel;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;
    public TableColumn colExpireDate;
    public TableColumn colUnitePrice;
    public TableColumn colCode;
    public TableColumn colQuentity;
    public Label lblId;

    @FXML
    private Label lbSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label lbItemCode;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtMFD;
    @FXML
    private TextField txtExpireDate;
    @FXML
    private TextField txtQuantity;
    @FXML
    private TableView<ItemTm> tblItem;
    @FXML
    private TableColumn<ItemTm, String> colItemId;
    @FXML
    private TableColumn<ItemTm, String> colName;
    @FXML
    private TableColumn<ItemTm,Double> colPrice;
    @FXML
    private TableColumn<ItemTm, String> colMFD;
    @FXML
    private TableColumn<ItemTm, String> colEXP;
    @FXML
    private TableColumn<ItemTm, Integer> colQuantity;

    private final ItemModel itemModel = new ItemModel();

    public ItemController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colMFD.setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        colEXP.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadTableData();
        try {
            loadNextId();
            loadTableData();
        } catch (Exception e) {
            showError("Initialization Error", "Failed to initialize data. Please restart the application.");
        }
    }

    @FXML
    public void btnItemSaveOnAction(ActionEvent actionEvent) {
        String itemId = lblId.getText();
        String name = txtName.getText();
        String mfd = txtMFD.getText();
        String exp = txtExpireDate.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();


        if (isInputValid(name,mfd, exp, price, quantity)) {
            ItemDto itemDto = new ItemDto(itemId, name, mfd, exp, Double.parseDouble(price), Integer.parseInt(quantity));

            try {
                String saveResult = itemModel.saveItem(itemDto);
                if ("Item added successfully".equals(saveResult)) {
                    resetPage();
                    showSuccess("Item Saved", "Item details have been saved successfully!");
                } else {
                    showError("Save Failed", "Failed to save item details.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Database Error", "Unable to save item details due to a database error.");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error", "An unexpected error occurred. Please contact support.");
            }
        }
    }

    @FXML
    public void btnItemDeleteOnAction(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure",
                ButtonType.YES,
                ButtonType.NO);
        Optional<ButtonType> respons=alert.showAndWait();

        if(respons.isPresent()&&respons.get()==ButtonType.YES){
            try{
                String itemId=lblId.getText();
                boolean isDeleted = Boolean.parseBoolean(itemModel.deleteItems(itemId));
                if(isDeleted){
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION,"Item Deleted").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Failed to delete Item details").show();
                }

            }catch (Exception e){
                e.printStackTrace();
                showError("Error", "Failed to delete Item details.");
            }

        }
    }

    @FXML
    public void btnItemUpdateOnAction(ActionEvent actionEvent) {
        String itemId = lblId.getText();
        String name = txtName.getText();
        String mfd = txtMFD.getText();
        String exp = txtExpireDate.getText();
        String price = txtPrice.getText();
        String quantity = txtQuantity.getText();
        if (isInputValid(name, mfd, exp, price, quantity)) {
            ItemDto itemDto = new ItemDto(itemId, name, mfd, exp, Double.parseDouble(price), Integer.parseInt(quantity));

            try {
                boolean isUpdated = Boolean.parseBoolean(itemModel.updateItem(itemDto));
                if (isUpdated) {
                    resetPage();
                    showSuccess("Item Updated", "Item details have been updated successfully!");
                } else {
                    showError("Update Failed", "Failed to update item details.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showError("Database Error", "Unable to update item details due to a database error.");
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error", "An unexpected error occurred. Please contact support.");
            }
        }
    }

    @FXML
    public void onClickTable(MouseEvent mouseEvent) {
        ItemTm selectedItem = tblItem.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnSave.setDisable(true);
            lblId.setText(selectedItem.getItemId());
            txtName.setText(selectedItem.getItemName());
            txtMFD.setText(selectedItem.getManufacturerDate());
            txtExpireDate.setText(selectedItem.getExpireDate());
            txtPrice.setText(selectedItem.getPricePerUnit());
            txtQuantity.setText(String.valueOf(selectedItem.getQuantity()));
        }
    }



    private boolean isInputValid(String name, String mfd, String exp, String price, String quantity) {
        if (name.isEmpty() || price.isEmpty() || mfd.isEmpty() || exp.isEmpty() || quantity.isEmpty()) {
            showError("Input Error", "All input fields are required. Please fill in all fields.");
            return false;
        }

        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            showError("Input Error", "Price must be a numeric value.");
            return false;
        }

        try {
            Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            showError("Input Error", "Quantity must be an integer value.");
            return false;
        }

        if (!mfd.matches("\\d{4}-\\d{2}-\\d{2}") || !exp.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showError("Input Error", "Dates must be in the format YYYY-MM-DD.");
            return false;
        }

        return true;
    }



    private void loadTableData() {
        try {
            ArrayList<ItemDto> allItems = itemModel.getAllItems();
            ObservableList<ItemTm> itemTMS = FXCollections.observableArrayList();
            

            for (ItemDto itemDto : allItems) {
                ItemTm itemTM = new ItemTm(
                        itemDto.getItemCode(),
                        itemDto.getItemName(),
                        itemDto.getManufactureDate(),
                        itemDto.getExpireDate(),
                        String.valueOf(itemDto.getUnitPrice()),
                        String.valueOf(itemDto.getQtyOnHand())
                );
                //System.out.println("after fore each");
                itemTMS.add(itemTM);
                //System.out.println("item.add(itemTm) is called");
            }
            //System.out.println("before tblItem.setItems(itemTMS)");
            tblItem.setItems(itemTMS);
            //System.out.println("after tblItem.setItems(itemTMS)");
        } catch (Exception e) {
            System.out.println(e);
            showError("Error", "Failed to load the table data.");
        }
    }



    public void btnSearchItemOnAction(ActionEvent actionEvent) {
        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            showError("Search Error", "Please enter a value to search.");
            return;
        }

        try {
            ItemDto itemDto = itemModel.searchItems(searchText);
            if (itemDto != null) {
                ObservableList<ItemTm> itemTMS = FXCollections.observableArrayList();
                itemTMS.add(new ItemTm(
                        itemDto.getItemCode(),
                        itemDto.getItemName(),
                        itemDto.getManufactureDate(),
                        itemDto.getExpireDate(),
                        String.valueOf(itemDto.getUnitPrice()),
                        String.valueOf(itemDto.getQtyOnHand())
                       
                ));
                tblItem.setItems(itemTMS);
            } else {
                showError("No Results", "No Item found for the search term.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Error", "Failed to search for Items.");
        }

    }
    @FXML
    public void btsItemResetOnAction(ActionEvent actionEvent) {

        resetPage();
    }

    private void resetPage() {
        loadNextId();
        loadTableData();
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        clearInputFields();
    }

    private void clearInputFields() {
        txtName.clear();
        txtPrice.clear();
        txtMFD.clear();
        txtExpireDate.clear();
        txtQuantity.clear();
    }
    private void loadNextId() {
        try {
            String nextId = itemModel.getNextID();
            if (nextId != null) {
                lblId.setText(nextId);
            }else{
                System.out.println("No item id found");
            }


        } catch (Exception e) {
            showError("Error", "Failed to load the next item ID.");
        }
    }
    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle(title);
        alert.show();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle(title);
        alert.show();
    }

}