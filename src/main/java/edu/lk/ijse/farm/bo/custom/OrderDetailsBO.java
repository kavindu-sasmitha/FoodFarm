package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.dto.tm.OrderDetailsTM;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface OrderDetailsBO {
    static ArrayList<OrderDetailDto> getAllOrders() {
        ArrayList<OrderDetailDto> allOrders = new ArrayList<>();
        return allOrders;
    }


    ObservableList<OrderDetailsTM> getOrderDetails();
}
