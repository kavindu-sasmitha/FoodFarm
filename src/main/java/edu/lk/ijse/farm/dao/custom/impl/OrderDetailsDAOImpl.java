package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.OrderDetailsDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;
import edu.lk.ijse.farm.entity.OrderDetailsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public List<OrderDetailsEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Order_Details");
        List<OrderDetailsEntity> orderDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetailsEntity orderDetails = new OrderDetailsEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6)
            );
            orderDetailsList.add(orderDetails);

        }
        return orderDetailsList;
    }

    @Override
    public Optional<OrderDetailsEntity> getById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public boolean save(OrderDetailsEntity orderDetailsEntity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT Order_Id FROM Order_Details ORDER BY Order_Id DESC LIMIT 1");
        char tableChar = 'D';
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
    public List<OrderDetailsEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
