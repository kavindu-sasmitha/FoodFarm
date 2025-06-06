package edu.lk.ijse.farm.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@Getter
public class UserDto {
    private int id;
    private String UserName;
    private String Password;
    private String UserEmail;


}
