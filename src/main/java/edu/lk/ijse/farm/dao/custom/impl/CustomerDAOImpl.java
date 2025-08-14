package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.CustomerDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<CustomerEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQlUtil.execute("SELECT * FROM Customer");
        List<CustomerEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            CustomerEntity customerEntity = new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(customerEntity);
        }
        return list;
    }

    @Override
    public Optional<CustomerEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Customer WHERE Customer_Id =?", id);
        if (resultSet.next()) {
            return Optional.of(new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(CustomerEntity customerEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Customer (Customer_Id,Customer_Name,Contact_Number,Email,Address) VALUES (?, ?, ?, ?, ?)",
                customerEntity.getCustomerId(),
                customerEntity.getCustomerName(),
                customerEntity.getContact(),
                customerEntity.getEmail(),
                customerEntity.getAddress());
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE Customer SET Customer_Name=?, Contact_Number=?, Email=?, Address=? WHERE Customer_Id=?",
                customerEntity.getCustomerName(),
                customerEntity.getContact(),
                customerEntity.getEmail(),
                customerEntity.getAddress(),
                customerEntity.getCustomerId());
    }



    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM Customer WHERE Customer_Id=?",id);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT Customer_Id FROM Customer ORDER BY customer_id DESC LIMIT 1");
        char tableChar = 'C';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
           return lastId ;
        }
        return null;
    }

    @Override
    public List<CustomerEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Customer WHERE Customer_Id LIKE ? OR Customer_Name LIKE ? OR Contact_Number LIKE ? OR Email LIKE ? OR Address LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<CustomerEntity> list = new ArrayList<>();
        while (rst.next()) {
            CustomerEntity customerEntity = new CustomerEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            list.add(customerEntity);
        }
        return list;
    }

    @Override
    public Optional<CustomerEntity> findById(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Customer WHERE Contact_Number = ?",customerId);
        if (resultSet.next()) {
            return Optional.of(new CustomerEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }
}
