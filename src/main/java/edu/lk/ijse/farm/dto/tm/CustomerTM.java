package edu.lk.ijse.farm.dto.tm;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerTM {
    private String customerId;
    private String name;
    private String contact;
    private String email;
    private String address;
}
