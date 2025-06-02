package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.ItemDto;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {

    private final ItemModel itemModel;

    public OrderDetailsModel() {
        this.itemModel = new ItemModel();
    }

    public boolean saveOrderDetailsList(ArrayList<OrderDetailDto> cartList) throws SQLException, ClassNotFoundException {
        for (OrderDetailDto orderDetailsDTO : cartList) {
            if (!saveOrderDetails(orderDetailsDTO) || !itemModel.reduceQty(orderDetailsDTO)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveOrderDetails(OrderDetailDto dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO Order_Details (Order_Id,Customer_Id,Item_Id,Price_Of_1KG,Quantity,Total_Price) VALUES (?, ?, ?, ?, ?, ?)",
                dto.getOrderId(),
                dto.getCustomer_Id(),
                dto.getItemCode(),
                dto.getPrice_of_1KG(),
                dto.getQuantity(),
                dto.getTotalPrice()
        );
    }
}
