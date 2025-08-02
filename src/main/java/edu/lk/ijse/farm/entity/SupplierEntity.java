package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEntity {
   private String supplierId;
   private String name;
   private String contact;
   private String address;
   private String supplierItems;
}
