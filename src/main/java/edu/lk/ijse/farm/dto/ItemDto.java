package edu.lk.ijse.farm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ItemDto {
    private String itemCode;
    private String itemName;
    private String manufactureDate;
    private String expireDate;
    private double unitPrice;
    private int qtyOnHand;



}
