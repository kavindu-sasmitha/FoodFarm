package edu.lk.ijse.farm.dto;

import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDto {
   private String orderId;
   private String Customer_Id;
   private String itemCode;
   private double price_of_1KG;
   private int quantity;
   private double totalPrice;



}
