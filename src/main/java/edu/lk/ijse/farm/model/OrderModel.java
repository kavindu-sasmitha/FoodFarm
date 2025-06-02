/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.dto.OrderDto;
import edu.lk.ijse.farm.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {

    private final OrderDetailsModel orderDetailsModel = new OrderDetailsModel();
    private final ItemModel itemModel = new ItemModel();

    public boolean placeOrder(OrderDto orderDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isOrderSaved = CrudUtil.execute(
                    "INSERT INTO orders (Order_Id, Order_Date, Total_Amount, Customer_Id, Order_States) VALUES (?, ?, ?, ?, ?)",
                    orderDTO.getOrderId(),
                    orderDTO.getDate(),
                    orderDTO.getTotalAmount(),
                    orderDTO.getCustomerId(),
                    orderDTO.getStatus()
            );

            if (!isOrderSaved) {
                connection.rollback();
                return false;
            }

            ArrayList<OrderDetailDto> orderDetailsList = orderDTO.getOrderDetails();
            boolean areOrderDetailsSaved = orderDetailsModel.saveOrderDetailsList(orderDetailsList);

            if (!areOrderDetailsSaved) {
                connection.rollback();
                return false;
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT Order_Id FROM orders ORDER BY Order_Id DESC LIMIT 1");
        char tableChar = 'O';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "O004"
            String lastIdNumberString = lastId.substring(1); // "004"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 4
            int nextIdNumber = lastIdNumber + 1; // 5
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber); // "O005"
            return nextIdString;
        }
        return tableChar + "001";
    }

    public boolean reduceQty(OrderDetailDto orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "update item set quantity = quantity-? where item_id=?",
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getItemCode()
        );
    }
}