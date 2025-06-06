package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.dto.PlantDto;
import edu.lk.ijse.farm.dto.tm.CustomerTM;
import edu.lk.ijse.farm.dto.tm.PlantTM;
import edu.lk.ijse.farm.model.PlantModel;
import edu.lk.ijse.farm.util.CrudUtil;
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

public class plantController implements Initializable {

    public TableView <PlantTM>tblPlant;
    public TextField txtType;
    public Label lblId;
    @FXML
    private TableColumn<PlantTM,String> colGrowthStages;

    @FXML
    private TableColumn<PlantTM,String> colId;

    @FXML
    private TableColumn<PlantTM,Integer> colLifeTimeDays;

    @FXML
    private TableColumn<PlantTM,String> colName;

    @FXML
    private TableColumn<PlantTM,Integer> colNumberOfPlant;

    @FXML
    private Label lbEXP;

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
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtGrowthSatges;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLifeTimeDays;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumberOfPlant;

    @FXML
    private TextField txtSearch;

    PlantModel plantModel=new PlantModel();

    @FXML
    void btnIPlantUpdateOnAction(ActionEvent event) {
        if (txtType.getText().isEmpty() || txtGrowthSatges.getText().isEmpty() || txtLifeTimeDays.getText().isEmpty() || txtNumberOfPlant.getText().isEmpty()) {
            showError("Input Error", "Please fill in all the required fields.");
            return;
        }

        try {
            String id = lblId.getText();
            String type = txtType.getText();
            int numberOfPlant = Integer.parseInt(txtNumberOfPlant.getText());
            String growthStages = txtGrowthSatges.getText();
            int lifeTimeDays = Integer.parseInt(txtLifeTimeDays.getText());


            PlantDto plantDto = new PlantDto(id, type, numberOfPlant, growthStages, lifeTimeDays);
            String result = plantModel.updatePlant(plantDto);
            boolean isUpdated = result != null && result.equalsIgnoreCase("update");

            if (isUpdated) {
                resetPage();
                loadTableData();
                showSuccess("Plant Updated", "Plant details have been updated successfully!");
            } else {
                showError("Update Failed", "Failed to update plant details. Please try again.");
            }
        } catch (NumberFormatException e) {
            showError("Input Error", "Please ensure that numeric fields are correctly filled.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showError("Database Error", "Unable to update plant details due to a database error.");
        } catch (RuntimeException e) {
            e.printStackTrace();
            showError("Error", "An unexpected error occurred while updating the plant.");
        }
    }

    @FXML
    void btnPlantDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure?",
                ButtonType.YES,
                ButtonType.NO);
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                String plantId = lblId.getText();
                String result = plantModel.deletePlant(plantId);
                if ("Plant deleted successfully".equals(result)) {
                    resetPage();
                    new Alert(Alert.AlertType.INFORMATION, "Plant Deleted").show();
                    loadTableData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete plant details").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error", "Failed to delete plant details.");
            }
        }
    }

    @FXML
    public void btnPlantSaveOnAction(ActionEvent event) {
        String id = lblId.getText();
        String type = txtType.getText();
        String growthStages = txtGrowthSatges.getText();
        int lifeTimeDays = Integer.parseInt(txtLifeTimeDays.getText());
        int numberOfPlant = Integer.parseInt(txtNumberOfPlant.getText());

        PlantDto plantDto = new PlantDto(id, type, numberOfPlant, growthStages, lifeTimeDays);
        try {
            String result = plantModel.addPlant(plantDto);
            boolean isSaved = result != null && result.equalsIgnoreCase("true");
            if (!isSaved) {
                resetPage();
                clearInputFields();
                loadTableData();
                showSuccess("Customer Saved", "Customer details have been saved successfully!");
            }
            for (PlantTM plantTM : tblPlant.getItems()) {
                if (plantTM.getPlantId().equals(plantDto.getPlantId())) {
                    tblPlant.getSelectionModel().select(plantTM);
                    tblPlant.scrollTo(plantTM);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearInputFields() {
        txtType.clear();
        txtGrowthSatges.clear();
        txtLifeTimeDays.clear();
        txtNumberOfPlant.clear();
        loadNextId();
    }

    private void resetPage() {
        txtType.clear();
        txtGrowthSatges.clear();
        txtLifeTimeDays.clear();
        txtNumberOfPlant.clear();
        loadNextId();

    }
    private void loadNextId() {
        try {
            String nextId = plantModel.getNextID();
            if (nextId != null) {
                lblId.setText(nextId);
            }else{
                System.out.println("No customer id found");
            }


        } catch (Exception e) {
            showError("Error", "Failed to load the next customer ID.");
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


    @FXML
    void btnSearchPlantOnAction(ActionEvent event) {
        String searchText = txtSearch.getText().trim();
        if (searchText.isEmpty()) {
            showError("Search Error", "Please enter a value to search.");
            return;
        }
        try{
            PlantDto plant=plantModel.searchPlant(searchText);
            if(plant !=null){
                ObservableList<PlantTM> plantTMS= FXCollections.observableArrayList();
                plantTMS.add(new PlantTM(
                        plant.getPlantId(),
                        plant.getPlantType(),
                        plant.getNumberOfPlant(),
                        plant.getGrowthStage(),
                        plant.getLifeTimeDate()
                ));
                tblPlant.setItems(plantTMS);
            }else {
                showError("No Results", "No plant found for the search term.");
            }
        }catch (Exception e){
            e.printStackTrace();
            showError("Error", "Failed to search for plants.");
        }

    }

    @FXML
    void btsIPlantResetOnAction(ActionEvent event) {
        resetPage();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("plantId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("plantType"));
        colNumberOfPlant.setCellValueFactory(new PropertyValueFactory<>("numberOfPlants"));
        colGrowthStages.setCellValueFactory(new PropertyValueFactory<>("growthStage"));
        colLifeTimeDays.setCellValueFactory(new PropertyValueFactory<>("lifeTimeDays"));

        try {
            loadNextId();
            loadTableData();
        } catch (Exception e) {
            showError("Initialization Error", "Failed to initialize the data.\nPlease try restarting the application.");
        }
    }

    private void loadTableData() {
        try {
            ArrayList<PlantDto> allPlants = (ArrayList<PlantDto>) plantModel.getAllPlants();

            ObservableList<PlantTM> plantTMS = FXCollections.observableArrayList();

            for (PlantDto plantDto : allPlants) {
                PlantTM plantTM = new PlantTM(
                        plantDto.getPlantId(),
                        plantDto.getPlantType(),
                        plantDto.getNumberOfPlant(),
                        plantDto.getGrowthStage(),
                        plantDto.getLifeTimeDate()
                );
                plantTMS.add(plantTM);
            }
            tblPlant.setItems(plantTMS);

        } catch (Exception e) {
            showError("Error", "Failed to load the table data.");
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        PlantTM selectedPlant = tblPlant.getSelectionModel().getSelectedItem();

        if (selectedPlant != null) {
            lblId.setText(selectedPlant.getPlantId());
            txtType.setText(selectedPlant.getPlantType());
            txtNumberOfPlant.setText(String.valueOf(selectedPlant.getNumberOfPlants()));
            txtGrowthSatges.setText(selectedPlant.getGrowthStage());
            txtLifeTimeDays.setText(String.valueOf(selectedPlant.getLifeTimeDays()));
        }
    }
}
