package edu.lk.ijse.farm.controller;

import edu.lk.ijse.farm.dto.AddSalaryDto;
import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.dto.tm.CustomerTM;
import edu.lk.ijse.farm.dto.tm.SalaryTM;
import edu.lk.ijse.farm.model.AddSalaryModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddSalaryController implements Initializable {

    public ComboBox cmbBox;
    public TableView tblSalary;
    @FXML
    private Label lbPosition;

    @FXML
    private Label lbSsalary;

    @FXML
    private TableColumn<SalaryTM, Double> tblDailySalary;

    @FXML
    private TableColumn<SalaryTM, String> tblPosition;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    AddSalaryModel addSalaryModel = new AddSalaryModel();

    @FXML
    void btnSalaryAddOnAction(ActionEvent event) {
        String selectedPosition = (String) cmbBox.getValue();

        if (selectedPosition != null && !txtSalary.getText().isEmpty()) {
            try {
                double salary = Double.parseDouble(txtSalary.getText());
                AddSalaryDto addSalaryDto = new AddSalaryDto(selectedPosition, salary);
                String result = addSalaryModel.addSalary(addSalaryDto);
                System.out.println(result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input. Please enter a valid number.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error occurred while adding salary: " + e.getMessage());
            }
        } else {
            System.out.println("Please select a position and enter a valid salary.");
        }
    }

    @FXML
    void btnSalaryDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSalaryUpddateOnAction(ActionEvent event) {
        String selectedPosition = txtPosition.getText();
        if (selectedPosition != null && !txtSalary.getText().isEmpty()) {
            try {
                double newSalary = Double.parseDouble(txtSalary.getText());
                AddSalaryDto addSalaryDto = new AddSalaryDto(selectedPosition, newSalary);
                String result = addSalaryModel.updateSalary(addSalaryDto);
                System.out.println(result);
                resetPage();
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary input. Please enter a valid number.");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error occurred while updating salary: " + e.getMessage());
            }
        } else {
            System.out.println("Please enter a valid position and salary to update.");
        }
    }

    public void cmbPositionOnAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbBox.setItems(FXCollections.observableArrayList("Field Worker", "Supervisor", "Manager", "Technician"));
        tblPosition.setCellValueFactory(new PropertyValueFactory<SalaryTM,String>("position"));
        tblDailySalary.setCellValueFactory(new PropertyValueFactory<SalaryTM,Double>("salary"));

        try{
            loardTableData();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loardTableData() {
        try {
            ArrayList<AddSalaryDto> allSalaries = addSalaryModel.getAllSalaryByPosition();
            ObservableList<SalaryTM> salaryTMS = FXCollections.observableArrayList();
            for (AddSalaryDto addSalaryDto : allSalaries) {
                SalaryTM salaryTM = new SalaryTM(
                        addSalaryDto.getPosition(),
                        addSalaryDto.getSalary()
                );
                salaryTMS.add(salaryTM);
            }
            tblSalary.setItems(salaryTMS);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error loading table data: " + e.getMessage());
        }
    }
    public void resetPage(){
        clearInputFields();
        loardTableData();

    }
    public void clearInputFields(){
         cmbBox.getSelectionModel().clearSelection();
         txtSalary.clear();
         txtPosition.clear();
         cmbBox.requestFocus();
         cmbBox.setDisable(false);
         txtSalary.setDisable(false);
         txtPosition.setDisable(false);
         btnSalaryAddOnAction(null);
         cmbBox.setDisable(true);
    }

    public void btnResetPageOnAction(ActionEvent actionEvent) {
        resetPage();
    }
}
