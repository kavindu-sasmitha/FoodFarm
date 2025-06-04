package edu.lk.ijse.farm.dto;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddSalaryDto {
    private String position;
    private double salary;

}
