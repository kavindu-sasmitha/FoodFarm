package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.EmployeeDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<EmployeeEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Employee");
        List<EmployeeEntity> employees = new ArrayList<>();
        while (resultSet.next()) {
            EmployeeEntity employee = new EmployeeEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Optional<EmployeeEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Employee WHERE Employee_Id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new EmployeeEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(EmployeeEntity employeeEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Employee (Employee_Id,Employee_Name,Contact,Join_Date,Email,Position) VALUES (?, ?, ?, ?, ?, ?)",
                employeeEntity.getEmployeeId(),
                employeeEntity.getEmployeeName(),
                employeeEntity.getContact(),
                employeeEntity.getJoinDate(),
                employeeEntity.getEmail(),
                employeeEntity.getPosition());
    }



    @Override
    public boolean update(EmployeeEntity employeeEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE Employee SET Employee_Name = ?, Contact = ?, Join_Date = ?, Email = ?, Position = ? WHERE Employee_Id = ?",
                employeeEntity.getEmployeeName(),
                employeeEntity.getContact(),
                employeeEntity.getJoinDate(),
                employeeEntity.getEmail(),
                employeeEntity.getPosition(),
                employeeEntity.getEmployeeId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM Employee WHERE Employee_Id=?",id);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT Supplier_Id FROM Supplier ORDER BY customer_id DESC LIMIT 1");
        char tableChar = 'E';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    @Override
    public List<EmployeeEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Employee WHERE Employee_Id LIKE ? OR Employee_Name LIKE ? OR Contact LIKE ? OR Join_Date LIKE ? OR Email LIKE ? OR Position LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        while (rst.next()) {
            EmployeeEntity employeeEntity = new EmployeeEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
            employeeEntities.add(employeeEntity);
        }
        return employeeEntities;
    }
}
