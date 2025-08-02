package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.OrderDetailsBO;
import edu.lk.ijse.farm.dto.tm.OrderDetailsTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderDetailsBOImpl implements OrderDetailsBO {

    @Override
    public ObservableList<OrderDetailsTM> getOrderDetails() {
        ObservableList<OrderDetailsTM> orderDetails = FXCollections.observableArrayList();
        return orderDetails;

    }
}
