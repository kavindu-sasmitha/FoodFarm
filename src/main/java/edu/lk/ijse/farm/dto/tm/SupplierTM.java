package edu.lk.ijse.farm.dto.tm;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierTM {
    private String supplierId;
    private String name;
    private String contact;
    private String address;
    private String items;
}
