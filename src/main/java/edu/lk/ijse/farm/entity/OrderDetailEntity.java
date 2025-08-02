package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEntity {
    private String orderId;
    private String customerId;
    private String itemCode;
    private double priceOf1KG;
    private int quantity;
    private double totalPrice;
}
