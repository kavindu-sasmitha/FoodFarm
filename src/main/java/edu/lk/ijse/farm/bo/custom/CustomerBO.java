package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String searchText) throws SQLException, ClassNotFoundException;

    String deleteCustomer(String customerId) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException;

    String findNameByContact(String searchText) throws SQLException, ClassNotFoundException;
}
