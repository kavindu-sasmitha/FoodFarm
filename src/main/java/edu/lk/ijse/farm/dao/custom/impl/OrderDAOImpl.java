package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.OrderDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;
import edu.lk.ijse.farm.entity.OrdersEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {


    @Override
    public String getLastId() {
        try {
            ResultSet rs = SQlUtil.execute("SELECT Order_Id FROM orders ORDER BY Order_Id DESC LIMIT 1");
            if (rs.next()) {
                return rs.getString("Order_Id");
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
        return SQlUtil.execute("DELETE FROM orders WHERE Order_Id = ?", id);
    }

    @Override
    public List<OrdersEntity> getAll() throws SQLException {
        return List.of(); // You can build this out based on resultSet parsing
    }

    @Override
    public Optional<OrdersEntity> getById(String id) throws SQLException {
        return Optional.empty(); // Same here — can expand logic later
    }

    @Override
    public boolean save(OrdersEntity ordersEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "INSERT INTO orders (Order_Id, Order_Date, Total_Amount, Customer_Id, Order_States) VALUES (?, ?, ?, ?, ?)",
                ordersEntity.getOrderId(),
                ordersEntity.getOrderDate(),
                ordersEntity.getTotalPrice(),
                ordersEntity.getCustomerId(),
                ordersEntity.getOrderStatus()
        );
    }

    @Override
    public boolean update(OrdersEntity ordersEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "UPDATE orders SET Order_Date = ?, Total_Amount = ?, Customer_Id = ?, Order_States = ? WHERE Order_Id = ?",
                ordersEntity.getOrderDate(),
                ordersEntity.getTotalPrice(),
                ordersEntity.getCustomerId(),
                ordersEntity.getOrderStatus(),
                ordersEntity.getOrderId()
        );
    }

    @Override
    public List<OrdersEntity> search(String keyword) throws SQLException {
        return List.of(); // Placeholder
    }
}
