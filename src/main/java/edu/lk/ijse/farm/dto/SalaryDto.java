package edu.lk.ijse.farm.dto;

public class SalaryDto {
    private String salaryId;
    private String month;
    private double salary;
    private String employeeId;
    private String employeeName;
    private String day;


    public SalaryDto() {
    }
    public SalaryDto(String salaryId, String month, double salary, String employeeId, String employeeName, String day) {
        this.salaryId = salaryId;
        this.month = month;
        this.salary = salary;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.day = day;
    }
    public String getSalaryId() {
        return salaryId;
    }
    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public double getSalary() {
        return salary;
    }



    public void setSalary(double salary) {
        this.salary = salary;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "SalaryDto{" +
                "salaryId='" + salaryId + '\'' +
                ", month='" + month + '\'' +
                ", salary=" + salary +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", day='" + day + '\'' +
                '}';
    }

}
