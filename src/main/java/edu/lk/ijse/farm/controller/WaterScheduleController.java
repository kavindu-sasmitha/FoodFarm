package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.WaterScheduleDto;
import edu.lk.ijse.farm.dto.tm.WaterScheduleTM;
import edu.lk.ijse.farm.model.WaterScheduleModel;
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

public class WaterScheduleController {

    @FXML
    private TextField txtScheduleID;

    @FXML
    private TextField txtWateringDays;

    @FXML
    private TextField txtPlantType;

    @FXML
    private TextField txtGrowthStages;

    @FXML
    private TextArea txtNote;

    @FXML
    private TableView<WaterScheduleTM> tblWaterSchedule;

    @FXML
    private TableColumn<WaterScheduleTM, String> colScheduleID;

    @FXML
    private TableColumn<WaterScheduleTM, String> colWateringDays;

    @FXML
    private TableColumn<WaterScheduleTM, String> colPlantType;

    @FXML
    private TableColumn<WaterScheduleTM, String> colGrowthStages;

    @FXML
    private TableColumn<WaterScheduleTM, String> colNote;

    private WaterScheduleModel waterScheduleModel = new WaterScheduleModel();

    public void initialize() {
        setCellValueFactory();
        loadAllSchedules();

        // Add listener to table selection
        tblWaterSchedule.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtScheduleID.setText(newSelection.getScheduleId());
                txtPlantType.setText(newSelection.getPlantType());
                txtWateringDays.setText(newSelection.getWateringDays());
                txtGrowthStages.setText(newSelection.getGrowthStages());
                txtNote.setText(newSelection.getNote());
            }
        });
    }

    private void setCellValueFactory() {
        colScheduleID.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        colWateringDays.setCellValueFactory(new PropertyValueFactory<>("wateringDays"));
        colPlantType.setCellValueFactory(new PropertyValueFactory<>("plantType"));
        colGrowthStages.setCellValueFactory(new PropertyValueFactory<>("growthStages"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
    }

    private void loadAllSchedules() {
        ObservableList<WaterScheduleTM> tmList = FXCollections.observableArrayList();
        try {
            List<WaterScheduleDto> dtoList = waterScheduleModel.getAllSchedules();
            for (WaterScheduleDto dto : dtoList) {
                tmList.add(new WaterScheduleTM(
                        dto.getScheduleId(),
                        dto.getWateringDays(),
                        dto.getPlantType(),
                        dto.getGrowthStages(),
                        dto.getNote()
                ));
            }
            tblWaterSchedule.setItems(tmList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load water schedules: " + e.getMessage()).show();
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
        if (txtWateringDays.getText().isEmpty() || !Pattern.matches("^\d+$",txtWateringDays.getText())) {
            new Alert(Alert.AlertType.ERROR, "Watering Days must be a number and not empty!").show();
            return false;
        }
        if (txtGrowthStages.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Growth Stages must not be empty!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnSaveSchedule(ActionEvent event) { // Assuming fx:id is btnSaveSchedule in FXML for save
        if (!validateFields()) {
            return;
        }
        String scheduleId = txtScheduleID.getText();
        String plantType = txtPlantType.getText();
        String wateringDays = txtWateringDays.getText();
        String growthStages = txtGrowthStages.getText();
        String note = txtNote.getText();

        WaterScheduleDto dto = new WaterScheduleDto(scheduleId, plantType, wateringDays, growthStages, note);

        try {
            boolean isSaved = waterScheduleModel.saveSchedule(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Water schedule saved successfully!").show();
                loadAllSchedules();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save water schedule.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error saving water schedule: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateScheduleOnAction(ActionEvent event) { // Assuming an update button with this handler
        if (!validateFields()) {
            return;
        }
        String scheduleId = txtScheduleID.getText();
        String plantType = txtPlantType.getText();
        String wateringDays = txtWateringDays.getText();
        String growthStages = txtGrowthStages.getText();
        String note = txtNote.getText();

        WaterScheduleDto dto = new WaterScheduleDto(scheduleId, plantType, wateringDays, growthStages, note);

        try {
            boolean isUpdated = waterScheduleModel.updateSchedule(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Water schedule updated successfully!").show();
                loadAllSchedules();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update water schedule. Make sure Schedule ID exists.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error updating water schedule: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteSchedule(ActionEvent event) { // Assuming fx:id is btnDeleteSchedule
        String scheduleId = txtScheduleID.getText();
         if (scheduleId.isEmpty() || !Pattern.matches("^[S]\d{3,}$",scheduleId)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid Schedule ID (e.g S001) to delete!").show();
            return;
        }

        try {
            boolean isDeleted = waterScheduleModel.deleteSchedule(scheduleId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Water schedule deleted successfully!").show();
                loadAllSchedules();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete water schedule. Make sure Schedule ID exists.").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error deleting water schedule: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnResetSchedule(ActionEvent event) { // Assuming fx:id is btnResetSchedule
       clearFields();
    }

    private void clearFields() {
        txtScheduleID.clear();
        txtPlantType.clear();
        txtWateringDays.clear();
        txtGrowthStages.clear();
        txtNote.clear();
        tblWaterSchedule.getSelectionModel().clearSelection();
    }
}
