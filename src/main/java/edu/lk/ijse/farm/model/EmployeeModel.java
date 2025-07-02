package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.EmployeeDto;
import edu.lk.ijse.farm.dao.SQlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    public String saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Employee (Employee_Id,Employee_Name,Contact,Join_Date,Email,Position) VALUES (?, ?, ?, ?, ?, ?)",
                employeeDto.getEmployeeId(),
                employeeDto.getName(),
                employeeDto.getContact(),
                employeeDto.getJoiningDate(),
                employeeDto.getEmail(),
                employeeDto.getPosition()) ? "Employee added successfully" : "Employee addition failed";
    }

    public String updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Employee SET Employee_Name = ?, Contact = ?, Join_Date = ?, Email = ?, Position = ? WHERE Employee_Id = ?";
        return SQlUtil.execute(sql,
                employeeDto.getName(),
                employeeDto.getContact(),
                employeeDto.getJoiningDate(),
                employeeDto.getEmail(),
                employeeDto.getPosition(),
                employeeDto.getEmployeeId()
        ) ? "Employee updated successfully" : "Failed to update employee";
    }

    public String deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM Employee WHERE Employee_Id=?", employeeId) ? "Employee deleted successfully" : "Failed to delete employee";
    }

    public List<EmployeeDto> searchEmployee(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Employee WHERE Employee_Id LIKE ? OR Employee_Name LIKE ? OR Contact LIKE ? OR Join_Date LIKE ? OR Email LIKE ? OR Position LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        while (rst.next()) {
            EmployeeDto employeeDto = new EmployeeDto(
                    rst.getString("Employee_Id"),
                    rst.getString("Employee_Name"),
                    rst.getString("Contact"),
                    rst.getString("Join_Date"),
                    rst.getString("Email"),
                    rst.getString("Position"));
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;
    }


    public String getNextID() throws SQLException, ClassNotFoundException {
        String sql = "select Employee.Employee_Id from Employee order by Employee_Id desc LIMIT 1";
        ResultSet resultSet = SQlUtil.execute(sql);
        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String numericPart = id.replaceAll("[^0-9]", "");
            int nextid = Integer.parseInt(numericPart);
            nextid++;
            return String.format("E" + "%03d", nextid);
        } else {
            return "E001";
        }
    }

    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Employee");
        ArrayList<EmployeeDto> employees = new ArrayList<>();
        while (rst.next()) {
            EmployeeDto employeeDto = new EmployeeDto(
                    rst.getString("Employee_Id"),
                    rst.getString("Employee_Name"),
                    rst.getString("Contact"),
                    rst.getString("Join_Date"),
                    rst.getString("Email"),
                    rst.getString("Position")
            );
            employees.add(employeeDto);
        }
        return employees;
    }
}
