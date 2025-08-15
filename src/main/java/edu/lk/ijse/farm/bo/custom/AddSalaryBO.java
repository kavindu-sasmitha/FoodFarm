package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dto.AddSalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddSalaryBO extends SuperBO {

    boolean addSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException;

    boolean updateSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException;

    String deleteSalary(String position) throws SQLException, ClassNotFoundException;

    ArrayList<AddSalaryDto> getAllSalaryByPosition() throws SQLException, ClassNotFoundException;

    AddSalaryDto searchSalary(String position) throws SQLException, ClassNotFoundException;
}