package edu.lk.ijse.farm.dto;

import edu.lk.ijse.farm.dto.tm.CustomerTM;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private String supplierId;
    private String name;
    private String address;
    private String contact;
    private String supplierItems;



}
