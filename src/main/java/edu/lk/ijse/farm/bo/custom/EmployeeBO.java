package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    String pdateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;

    String saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;

    String deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException;

    int searchEmployee(String searchText) throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException;
}
