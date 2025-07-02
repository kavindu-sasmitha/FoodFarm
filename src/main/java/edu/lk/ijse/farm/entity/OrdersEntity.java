package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity {
    private String orderId;
    private String OrderDate;
    private String TotalPrice;
    private String customerId;
    private String orderStatus;
}
