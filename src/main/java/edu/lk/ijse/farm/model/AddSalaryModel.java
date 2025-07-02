package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.AddSalaryDto;
import edu.lk.ijse.farm.dao.SQlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddSalaryModel {
    public String addSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO addSalary (position,daily_salary) VALUES (?, ?)";
        return SQlUtil.execute(sql, addSalaryDto.getPosition(), addSalaryDto.getSalary()) ? "Salary added successfully" : "Failed to add salary";
    }


    public ArrayList<AddSalaryDto> getAllSalaryByPosition() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM addSalary";
        ResultSet resultSet = SQlUtil.execute(sql);
        ArrayList<AddSalaryDto> salaryList = new ArrayList<>();
        while (resultSet.next()) {
            salaryList.add(new AddSalaryDto(
                    resultSet.getString("position"),
                    resultSet.getDouble("daily_salary")
            ));
        }
        return salaryList;
    }

    public String deleteSalary(String selectedPosition) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM addSalary WHERE position = ?",selectedPosition
        ) ? "Successfully Delete":"Delete Fail";
    }
}
