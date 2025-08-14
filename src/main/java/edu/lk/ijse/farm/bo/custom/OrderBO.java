package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dao.custom.OrderDAO;
import edu.lk.ijse.farm.dao.custom.impl.OrderDAOImpl;
import edu.lk.ijse.farm.dto.OrderDto;

import java.sql.SQLException;

public interface OrderBO extends SuperBO {

    String getNextOrderId() throws SQLException, ClassNotFoundException;


    String findNameByContact(String selectedCustomerContact);

    boolean placeOrder(OrderDto orderDTO) throws SQLException;
}
