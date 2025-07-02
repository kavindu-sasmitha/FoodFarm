package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    private String CustomerId;
    private String CustomerName;
    private String Contact;
    private String email;
    private String address;
}
