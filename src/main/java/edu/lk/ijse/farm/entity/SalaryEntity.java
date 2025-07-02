package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryEntity {
    private String salaryId;
    private String Month;
    private String employeeId;
    private  double totalSalary;
    private String day;
    private String name;
}
