package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.OrderDAO;
import edu.lk.ijse.farm.entity.OrderEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public String getLastId() {
        try {
            ResultSet rs = SQlUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
            if (rs.next()) {
                return rs.getString("orderId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        String lastId = getLastId();
        char prefix = 'O';
        if (lastId != null) {
            int number = Integer.parseInt(lastId.substring(1));
            return String.format(prefix + "%03d", number + 1);
        }
        return prefix + "001";
    }





    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM orders WHERE orderId = ?", id);
    }

    @Override
    public List<OrderEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM orders");
        List<OrderEntity> list = new ArrayList<>();
        while (rst.next()) {
            OrderEntity orderEntity = new OrderEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            list.add(orderEntity);
        }
        return list;
    }

    @Override
    public Optional<OrderEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM orders WHERE orderId = ?", id);
        if (rst.next()) {
            return Optional.of(new OrderEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(OrderEntity orderEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "INSERT INTO orders (orderId, date, totalAmount, customerId, status) VALUES (?, ?, ?, ?, ?)",
                orderEntity.getOrderId(),
                orderEntity.getDate(),
                orderEntity.getTotalAmount(),
                orderEntity.getCustomerId(),
                orderEntity.getStatus()
        );
    }

    @Override
    public boolean update(OrderEntity orderEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "UPDATE orders SET date = ?, totalAmount = ?, customerId = ?, status = ? WHERE orderId = ?",
                orderEntity.getDate(),
                orderEntity.getTotalAmount(),
                orderEntity.getCustomerId(),
                orderEntity.getStatus(),
                orderEntity.getOrderId()
        );
    }

    @Override
    public List<OrderEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        return List.of(); // Placeholder
    }
}
