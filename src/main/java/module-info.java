module edu.lk.ijse.farm {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires static lombok;
    requires mysql.connector.j;
    requires jdk.httpserver;
    requires java.mail;
    requires net.sf.jasperreports.core;


    opens edu.lk.ijse.farm.controller to javafx.fxml;
    exports edu.lk.ijse.farm.controller;
    exports edu.lk.ijse.farm.dto;
    exports edu.lk.ijse.farm;
    opens edu.lk.ijse.farm.dto to javafx.base;
    opens edu.lk.ijse.farm.dto.tm to javafx.base;
    //opens edu.lk.ijse.farm.util to javafx.base;
    exports edu.lk.ijse.farm.db;
    //exports edu.lk.ijse.farm.util;
    exports edu.lk.ijse.farm.dto.tm;
    exports edu.lk.ijse.farm.dao;
    opens edu.lk.ijse.farm.dao to javafx.base;


}
