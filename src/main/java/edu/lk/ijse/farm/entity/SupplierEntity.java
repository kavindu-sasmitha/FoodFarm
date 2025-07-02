package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEntity {
   private String supplierId;
   private String supplierName;
   private String contactNumber;
   private String address;
   private String supplierItem;
}
