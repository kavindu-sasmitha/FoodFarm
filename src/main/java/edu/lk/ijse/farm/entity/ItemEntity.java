package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    private String itemCode;
    private String itemName;
    private String manufactureDate;
    private String expireDate;
    private double unitPrice;
    private int qtyOnHand;
}
