package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.bo.BOFactory;
import edu.lk.ijse.farm.bo.BOTypes;
import edu.lk.ijse.farm.bo.custom.AddSalaryBO;
import edu.lk.ijse.farm.dto.AddSalaryDto;
import edu.lk.ijse.farm.dto.tm.SalaryTM;

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

public class AddSalaryController implements Initializable {

    public ComboBox<String> cmbBox;
    public TableView<SalaryTM> tblSalary;
    @FXML
    private Label lbPosition;
    @FXML
    private Label lbSsalary;
    @FXML
    private TableColumn<SalaryTM, Double> tblDailySalary;
    @FXML
    private TableColumn<SalaryTM, String> tblPosition;
    @FXML
    private TextField txtSalary;

    private final AddSalaryBO addSalaryBO = BOFactory.getInstance().getBO(BOTypes.ADDSALARYBO);

    @FXML
    void btnSalaryAddOnAction(ActionEvent event) {
        String selectedPosition = cmbBox.getValue();

        if (selectedPosition != null && !txtSalary.getText().isEmpty()) {
            try {
                double salary = Double.parseDouble(txtSalary.getText());
                AddSalaryDto addSalaryDto = new AddSalaryDto(selectedPosition, salary);
                boolean result = addSalaryBO.addSalary(addSalaryDto);
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Salary Added Successfully").show();
                    loadTableData();
                    clearInputFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add salary").show();
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Invalid salary input. Please enter a valid number.").show();
            } catch (ClassNotFoundException | SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Error occurred while adding salary: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a position and enter a valid salary.").show();
        }
    }

    @FXML
    public void btnSalaryDeleteOnAction(ActionEvent actionEvent) {
        SalaryTM selectedSalary = tblSalary.getSelectionModel().getSelectedItem();

        if (selectedSalary == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a salary record to delete").show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                String position = selectedSalary.getPosition();
                String result = addSalaryBO.deleteSalary(position);
                boolean isDeleted = result != null && result.equalsIgnoreCase("Successfully Delete");
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Salary Deleted Successfully").show();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete salary details").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete salary details: " + e.getMessage()).show();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbBox.setItems(FXCollections.observableArrayList("Field Worker", "Supervisor", "Manager", "Technician"));
        tblPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        tblDailySalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        try {
            loadTableData();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to initialize: " + e.getMessage()).show();
        }
    }

    private void loadTableData() {
        try {
            ArrayList<AddSalaryDto> allSalaries = addSalaryBO.getAllSalaryByPosition();
            ObservableList<SalaryTM> salaryTMS = FXCollections.observableArrayList();
            for (AddSalaryDto addSalaryDto : allSalaries) {
                salaryTMS.add(new SalaryTM(addSalaryDto.getPosition(), addSalaryDto.getSalary()));
            }
            tblSalary.setItems(salaryTMS);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error loading table data: " + e.getMessage()).show();
        }
    }

    public void resetPage() {
        clearInputFields();
        loadTableData();
    }

    public void clearInputFields() {
        cmbBox.getSelectionModel().clearSelection();
        txtSalary.clear();
        cmbBox.requestFocus();
        cmbBox.setDisable(false);
        txtSalary.setDisable(false);
    }

    public void btnResetPageOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent) {
        SalaryTM selectedSalary = tblSalary.getSelectionModel().getSelectedItem();

        if (selectedSalary != null) {
            txtSalary.setText(String.valueOf(selectedSalary.getSalary()));
            cmbBox.getSelectionModel().select(selectedSalary.getPosition());
            cmbBox.setDisable(true);
            txtSalary.setDisable(false);
        }
    }
}
