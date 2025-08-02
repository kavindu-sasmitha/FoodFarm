package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.OrderBO;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.*;
import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.dto.OrderDto;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.ItemsEntity;
import edu.lk.ijse.farm.entity.OrderDetailsEntity;
import edu.lk.ijse.farm.entity.OrdersEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderBOImpl implements OrderBO {

   // private final OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDEDAO);
    private final OrderDAO orderDAO=DAOFactory.getInstance().getDAO(DAOType.ORDEDAO);
    private final OrderDetailsDAO orderDetailsDAO = DAOFactory.getInstance().getDAO(DAOType.ORDERDETAILSDAO);
    private final ItemsDAO itemsDAO = DAOFactory.getInstance().getDAO(DAOType.ITEMSDAO);
    private final CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMERDAO);

    @Override
    public String findNameByContact(String selectedCustomerContact) {
        // Placeholder, assuming implementation is pending
        return "";
    }

    @Override
    public boolean placeOrder(OrderDto orderDTO) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            Optional<CustomerEntity> optionalCustomer = customerDAO.findById(orderDTO.getCustomerId());
            if (optionalCustomer.isEmpty()) {
                throw new NotFoundException("Customer not found");
            }

            OrdersEntity order = new OrdersEntity();
            order.setOrderId(orderDTO.getOrderId());
            order.setOrderDate(orderDTO.getDate());
            order.setTotalPrice(String.valueOf(orderDTO.getTotalAmount()));
            order.setCustomerId(orderDTO.getCustomerId());
            order.setOrderStatus(orderDTO.getStatus());

            boolean isOrderSaved = orderDAO.save(order);
            if (isOrderSaved) {
                boolean isSuccess = saveDetailsAndUpdateItem(orderDTO.getOrderDetails());
                if (isSuccess) {
                    connection.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

        return false;
    }

    private boolean saveDetailsAndUpdateItem(ArrayList<OrderDetailDto> orderDetailsList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDto orderDetailDto : orderDetailsList) {
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            orderDetailsEntity.setOrderId(orderDetailDto.getOrderId());
            orderDetailsEntity.setCustomerId(orderDetailDto.getCustomer_Id());
            orderDetailsEntity.setItemId(orderDetailDto.getItemCode());
            orderDetailsEntity.setQuantity(orderDetailDto.getQuantity());
            orderDetailsEntity.setTotalPrice(orderDetailDto.getTotalPrice());
            orderDetailsEntity.setPriceOf1KG(orderDetailDto.getPrice_of_1KG());

            Optional<ItemsEntity> optionalItem = itemsDAO.getById(orderDetailDto.getItemCode());
            if (optionalItem.isEmpty()) {
                throw new NotFoundException("Item not found");
            }

            boolean isDetailSaved = orderDetailsDAO.save(orderDetailsEntity);
            if (!isDetailSaved) {
                return false;
            }

            boolean isItemUpdated = itemsDAO.reduceqty(orderDetailDto.getItemCode(), orderDetailDto.getQuantity());
            if (!isItemUpdated) {
                return false;
            }
        }

        return true;
    }
}
