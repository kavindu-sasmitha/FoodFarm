package edu.lk.ijse.farm.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsTM {
    private String orderId;
    private String customerId;
    private String  itemId;
    private  double priceOf1KG;
    private  int quantity;
    private  double totalPrice;

}
