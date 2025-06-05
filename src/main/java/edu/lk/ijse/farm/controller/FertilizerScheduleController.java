package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.FertilizerScheduleDto;
import edu.lk.ijse.farm.dto.tm.FertilizerScheduleTM;
import edu.lk.ijse.farm.model.FertilizerScheduleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class FertilizerScheduleController {

    @FXML
    private TextField txtScheduleID;

    @FXML
    private TextField txtFertilizerDays;

    @FXML
    private TextField txtFertilizerPerPlant;

    @FXML
    private TextField txtGrowthStages;

    @FXML
    private TextField txtPlantType;

    @FXML
    private TextField txtFertilizerName;

    @FXML
    private TextArea txtNote;

    @FXML
    private TableView<FertilizerScheduleTM> tblFertilizerSchedule; // Updated fx:id needed in FXML

    @FXML
    private TableColumn<FertilizerScheduleTM, String> colPlantType; // Updated fx:id needed in FXML

    @FXML
    private TableColumn<FertilizerScheduleTM, String> colFertilizerName; // Updated fx:id needed in FXML

    @FXML
    private TableColumn<FertilizerScheduleTM, String> colFertilizerQuantityPerPlant; // Updated fx:id needed in FXML

    @FXML
    private TableColumn<FertilizerScheduleTM, String> colGrowthStages; // Updated fx:id needed in FXML

    @FXML
    private TableColumn<FertilizerScheduleTM, String> colNote; // Updated fx:id needed in FXML

    private FertilizerScheduleModel fertilizerScheduleModel = new FertilizerScheduleModel();

    public void initialize() {
        setCellValueFactory();
        loadAllSchedules();
        // Add listener to table selection
        tblFertilizerSchedule.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Assuming FertilizerScheduleTM has a method to get the original DTO or enough data
                // For simplicity, we'll assume schedule ID is not directly in TM but could be retrieved if TM stored it
                // Or, we re-fetch based on some unique combination if ID isn't in TM.
                // For now, let's populate fields based on TM. A full DTO retrieval might be better.
                // This part requires that FertilizerScheduleTM has a scheduleId or we fetch the DTO.
                // For now, we will assume the DTO needs to be fetched if we were to populate txtScheduleID.
                // txtScheduleID.setText(newSelection.getScheduleId()); // If scheduleId was in TM
                txtPlantType.setText(newSelection.getPlantType());
                txtFertilizerName.setText(newSelection.getFertilizerName());
                txtFertilizerPerPlant.setText(newSelection.getQuantityPerPlant());
                txtGrowthStages.setText(newSelection.getGrowthStages());
                txtNote.setText(newSelection.getNote());
                // txtFertilizerDays would need to be part of TM or fetched from DTO.
                // For simplicity, we'll leave it. If editing, user might need to re-enter or it's fetched.
            }
        });
    }

    private void setCellValueFactory() {
        // These fx:ids must match the FXML file after step 9
        colPlantType.setCellValueFactory(new PropertyValueFactory<>("plantType"));
        colFertilizerName.setCellValueFactory(new PropertyValueFactory<>("fertilizerName"));
        colFertilizerQuantityPerPlant.setCellValueFactory(new PropertyValueFactory<>("quantityPerPlant"));
        colGrowthStages.setCellValueFactory(new PropertyValueFactory<>("growthStages"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
    }

    private void loadAllSchedules() {
        ObservableList<FertilizerScheduleTM> tmList = FXCollections.observableArrayList();
        try {
            List<FertilizerScheduleDto> dtoList = fertilizerScheduleModel.getAllSchedules();
            for (FertilizerScheduleDto dto : dtoList) {
                tmList.add(new FertilizerScheduleTM(
                        dto.getPlantType(),
                        dto.getFertilizerName(),
                        dto.getQuantityPerPlant(),
                        dto.getGrowthStages(),
                        dto.getNote()
                        // Note: scheduleId and fertilizerDays are not in FertilizerScheduleTM as defined in step 3
                        // This means the table won't show them, and clicking won't populate them unless TM is changed
                        // or DTO is fetched separately.
                ));
            }
            tblFertilizerSchedule.setItems(tmList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load schedules: " + e.getMessage()).show();
        }
    }

    private boolean validateFields() {
        if (txtScheduleID.getText().isEmpty() || !Pattern.matches("^[S]\d{3,}$",txtScheduleID.getText())) {
            new Alert(Alert.AlertType.ERROR, "Schedule ID must not be empty and match format S001!").show();
            return false;
        }
        if (txtPlantType.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Plant Type must not be empty!").show();
            return false;
        }
        if (txtFertilizerName.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Fertilizer Name must not be empty!").show();
            return false;
        }
         if (txtFertilizerDays.getText().isEmpty() || !Pattern.matches("^\d+$",txtFertilizerDays.getText())) {
            new Alert(Alert.AlertType.ERROR, "Fertilizer Days must be a number and not empty!").show();
            return false;
        }
        if (txtFertilizerPerPlant.getText().isEmpty() || !Pattern.matches("^\d+(\.\d+)?$", txtFertilizerPerPlant.getText())) {
             new Alert(Alert.AlertType.ERROR, "Fertilizer Quantity Per Plant must be a number and not empty!").show();
            return false;
        }
        if (txtGrowthStages.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Growth Stages must not be empty!").show();
            return false;
        }
        return true;
    }


    @FXML
    void btnSaveSchedule(ActionEvent event) {
        if (!validateFields()) {
            return;
        }

        String scheduleId = txtScheduleID.getText();
        String plantType = txtPlantType.getText();
        String fertilizerName = txtFertilizerName.getText();
        String fertilizerDays = txtFertilizerDays.getText();
        String quantityPerPlant = txtFertilizerPerPlant.getText();
        String growthStages = txtGrowthStages.getText();
        String note = txtNote.getText();

        FertilizerScheduleDto dto = new FertilizerScheduleDto(scheduleId, plantType, fertilizerName, fertilizerDays, quantityPerPlant, growthStages, note);

        try {
            boolean isSaved = fertilizerScheduleModel.saveSchedule(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Schedule saved successfully!").show();
                loadAllSchedules();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save schedule.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error saving schedule: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateSceduleOnAction(ActionEvent event) {
        if (!validateFields()) {
            return;
        }
        String scheduleId = txtScheduleID.getText();
        String plantType = txtPlantType.getText();
        String fertilizerName = txtFertilizerName.getText();
        String fertilizerDays = txtFertilizerDays.getText();
        String quantityPerPlant = txtFertilizerPerPlant.getText();
        String growthStages = txtGrowthStages.getText();
        String note = txtNote.getText();

        FertilizerScheduleDto dto = new FertilizerScheduleDto(scheduleId, plantType, fertilizerName, fertilizerDays, quantityPerPlant, growthStages, note);

        try {
            boolean isUpdated = fertilizerScheduleModel.updateSchedule(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Schedule updated successfully!").show();
                loadAllSchedules();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update schedule. Make sure Schedule ID exists.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error updating schedule: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnDeleteScheduleOnAction(ActionEvent event) {
        String scheduleId = txtScheduleID.getText();
        if (scheduleId.isEmpty() || !Pattern.matches("^[S]\d{3,}$",scheduleId)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid Schedule ID (e.g S001) to delete!").show();
            return;
        }

        try {
            boolean isDeleted = fertilizerScheduleModel.deleteSchedule(scheduleId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Schedule deleted successfully!").show();
                loadAllSchedules();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete schedule. Make sure Schedule ID exists.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error deleting schedule: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnResetScheduleOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtScheduleID.clear();
        txtPlantType.clear();
        txtFertilizerName.clear();
        txtFertilizerDays.clear();
        txtFertilizerPerPlant.clear();
        txtGrowthStages.clear();
        txtNote.clear();
        tblFertilizerSchedule.getSelectionModel().clearSelection();
    }
}
