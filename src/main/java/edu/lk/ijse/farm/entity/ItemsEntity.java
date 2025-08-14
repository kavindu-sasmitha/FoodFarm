package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsEntity {
    private String itemId;
    private String itemName;
    private String manufactureDate;
    private String expireDate;
    private double unitePrice;
    private int quantity;
}
