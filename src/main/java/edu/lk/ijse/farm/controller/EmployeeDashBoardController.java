package edu.lk.ijse.farm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class EmployeeDashBoardController {

    @FXML
    private AnchorPane ancPaneEmployee;

    @FXML
    void btnManageEmployeeOnAction(ActionEvent event) throws IOException {
        loadView("/view/employee/EmployeeManage.fxml",ancPaneEmployee);
    }

    private void loadView(String s, AnchorPane ancPaneEmployee) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(s));
        ancPaneEmployee.getChildren().setAll(pane);
        AnchorPane.setBottomAnchor(pane,0.0);
        AnchorPane.setTopAnchor(pane,0.0);

    }

    @FXML
    void btnManageSalaryOnAction(ActionEvent event) {

    }

}
