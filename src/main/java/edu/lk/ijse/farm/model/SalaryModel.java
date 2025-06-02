package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryModel {
    public String addSalary(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO Salary VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salaryDto.getSalaryId());
        statement.setString(2, salaryDto.getEmployeeId());
        statement.setDouble(3, salaryDto.getSalary());
        statement.setString(4,salaryDto.getMonth());
        statement.setString(5,salaryDto.getEmployeeName());

        return statement.executeUpdate() > 0 ? "success": "fail";
    }
    public String updateSalary(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql="UPDATE TABLE SET Month=?,Employee_Id=?,Day=?,Total_Salary=?,Employee_Name=? WHERE SalaryId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salaryDto.getMonth());
        statement.setString(2, salaryDto.getEmployeeId());
        statement.setDouble(3, salaryDto.getSalary());
        statement.setString(4, salaryDto.getMonth());
        statement.setString(5, salaryDto.getEmployeeName());
        statement.setString(6, salaryDto.getSalaryId());
        return statement.executeUpdate() > 0 ? "success": "fail";
    }

    public String deleteSalary(String salayId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM Salary WHERE SalaryId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salayId);
        return statement.executeUpdate() > 0 ? "success": "fail";
    }
    public SalaryDto searchEmployeeSalary(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Salary WHERE SalaryId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salaryDto.getSalaryId());
        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
           SalaryDto salaryDto1 = new SalaryDto(rst.getString("Salary_Id"),rst.getString("Month"),
                   rst.getDouble(3),rst.getString(4),
                   rst.getString(5),rst.getString(6));
           return salaryDto1;
        }
        return null;
    }
    public List<SalaryDto> searchAllSalary(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Salary WHERE SalaryId=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, salaryDto.getSalaryId());
        ResultSet rst = statement.executeQuery();
        List<SalaryDto> salaryDtoList = new ArrayList<>();
        while (rst.next()) {
            SalaryDto salaryDto1=new SalaryDto(rst.getString("Salary_Id"),rst.getString("Month"),
                    rst.getDouble(3),rst.getString(4),rst.getString(5),
                    rst.getString(6));
            salaryDtoList.add(salaryDto1);

        }
        return salaryDtoList;
    }


}
