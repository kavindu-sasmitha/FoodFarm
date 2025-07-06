package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.OrderDAO;
import edu.lk.ijse.farm.entity.OrdersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean reduseqty(OrdersEntity ordersEntity) {
        return false;
    }

    @Override
    public List<OrdersEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<OrdersEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(OrdersEntity ordersEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrdersEntity ordersEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT Order_Id FROM Orders ORDER BY Order_Id DESC LIMIT 1");
        char tableChar = 'O';
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
    public List<OrdersEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
