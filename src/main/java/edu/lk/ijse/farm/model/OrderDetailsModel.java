package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsModel {

    private final ItemModel itemModel;

    public OrderDetailsModel() {
        this.itemModel = new ItemModel();
    }

    public static ArrayList<OrderDetailDto> getAllOrders() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Order_Details";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();
        ArrayList<OrderDetailDto> orders = new ArrayList<>();
        while(rst.next()){
            OrderDetailDto orderDetailDto = new OrderDetailDto(
                    rst.getString("Order_Id"),
                    rst.getString("Customer_Id"),
                    rst.getString("Item_id"),
                    rst.getDouble("Price_Of_1KG"),
                    rst.getInt("Quantity"),
                    rst.getDouble("Total_Price"));
            orders.add(orderDetailDto);

//
        }
        return orders;

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
