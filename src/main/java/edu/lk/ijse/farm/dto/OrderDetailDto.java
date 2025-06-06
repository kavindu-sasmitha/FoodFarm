package edu.lk.ijse.farm.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDto {
    private String OrderId;
    private String Customer_Id;
    private String ItemCode;
    private double price_of_1KG;
    private int quantity;
    private  double totalPrice;
}
