package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.AddSalaryDto;
import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddSalaryModel {
    public String addSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO addSalary (position,daily_salary) VALUES (?, ?)";
        return CrudUtil.execute(sql, addSalaryDto.getPosition(), addSalaryDto.getSalary()) ? "Salary added successfully" : "Failed to add salary";
    }

    public String updateSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE addSalary SET daily_salary = ? WHERE position = ?";
        return CrudUtil.execute(sql,addSalaryDto.getSalary(),addSalaryDto.getPosition())
                ? "Salary updated successfully"
                : "Failed to update salary";
    }


    public ArrayList<AddSalaryDto> getAllSalaryByPosition() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM addSalary";
        ResultSet resultSet = CrudUtil.execute(sql);
        ArrayList<AddSalaryDto> salaryList = new ArrayList<>();
        while (resultSet.next()) {
            salaryList.add(new AddSalaryDto(
                    resultSet.getString("position"),
                    resultSet.getDouble("daily_salary")
            ));
        }
        return salaryList;
    }
}
