package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.OrderDAO;
import edu.lk.ijse.farm.entity.OrdersEntity;

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
    public String getNextID() throws SQLException {
        return "";
    }

    @Override
    public List<OrdersEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
