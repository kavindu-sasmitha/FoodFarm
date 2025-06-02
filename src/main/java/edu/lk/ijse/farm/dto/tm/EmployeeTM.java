package edu.lk.ijse.farm.dto.tm;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class EmployeeTM extends CustomerTM {
    private String employeeId;
    private String name;
    private String contact;
    private String joiningDate;
    private String email;
    private String position;
}
