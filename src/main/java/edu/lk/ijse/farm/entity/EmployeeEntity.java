package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity{
    private String employeeId;
    private String name;
    private String contact;
    private String joiningDate;
    private String email;
    private String position;
}
