package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    private String orderId;
    private String date;
    private double totalAmount;
    private String customerId;
    private String status;
}
