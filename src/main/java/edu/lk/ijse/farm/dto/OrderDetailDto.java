package edu.lk.ijse.farm.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDto {
    private String orderId;
    private String customerId;
    private String itemCode;
    private double priceOf1KG;
    private int quantity;
    private  double totalPrice;
}
