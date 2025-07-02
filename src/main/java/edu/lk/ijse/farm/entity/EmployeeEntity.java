package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity{
    private String employeeId;
    private String employeeName;
    private String Contact;
    private String joinDate;
    private String Email;
    private String position;
}
