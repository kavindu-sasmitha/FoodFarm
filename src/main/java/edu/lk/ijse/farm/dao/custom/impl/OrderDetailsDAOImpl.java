package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.OrderDetailsDAO;
import edu.lk.ijse.farm.entity.OrderDetailEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public List<OrderDetailEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Order_Details");
        List<OrderDetailEntity> orderDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetailEntity orderDetails = new OrderDetailEntity(
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
    public Optional<OrderDetailEntity> getById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public boolean save(OrderDetailEntity orderDetailEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Order_Details (orderId, customerId, itemCode, priceOf1KG, quantity, totalPrice) VALUES (?,?,?,?,?,?)",
                orderDetailEntity.getOrderId(),
                orderDetailEntity.getCustomerId(),
                orderDetailEntity.getItemCode(),
                orderDetailEntity.getPriceOf1KG(),
                orderDetailEntity.getQuantity(),
                orderDetailEntity.getTotalPrice());
    }

    @Override
    public boolean update(OrderDetailEntity orderDetailEntity) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT orderId FROM Order_Details ORDER BY orderId DESC LIMIT 1");
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
    public List<OrderDetailEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
