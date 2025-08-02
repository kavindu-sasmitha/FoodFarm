package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.dao.custom.OrderDAO;
import edu.lk.ijse.farm.dao.custom.impl.OrderDAOImpl;
import edu.lk.ijse.farm.dto.OrderDto;

import java.sql.SQLException;

public interface OrderBO {

    static String getNextOrderId() {
        return null;
    }


    String findNameByContact(String selectedCustomerContact);

    boolean placeOrder(OrderDto orderDTO) throws SQLException;
}
