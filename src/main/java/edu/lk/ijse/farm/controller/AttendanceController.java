package edu.lk.ijse.farm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AttendanceController {

    @FXML
    private TableColumn<?, ?> colAttendanceId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colEmployeeName;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label lbAttendanceId;

    @FXML
    private Label lbDate;

    @FXML
    private Label lbEmployeeId;

    @FXML
    private Label lbName;

    @FXML
    private Label lbSearch;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtEmployeId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnEmployeAbsentOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeePresentOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {

    }

}
