package edu.lk.ijse.farm.dto;

public class AttandanceDto {
    private String attandanceId;
    private String date;
    private String Month;
    private String year;
    private String employeeId;

    public AttandanceDto() {
    }
    public AttandanceDto(String attandanceId, String date, String month, String year, String employeeId) {
        this.attandanceId = attandanceId;
        this.date = date;
        Month = month;
        this.year = year;
        this.employeeId = employeeId;
    }
    public String getAttandanceId() {
        return attandanceId;
    }
    public void setAttandanceId(String attandanceId) {
        this.attandanceId = attandanceId;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMonth() {
        return Month;
    }
    public void setMonth(String month) {
        Month = month;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    @Override
    public String toString() {
        return "AttandanceDto{" +
                "attandanceId='" + attandanceId + '\'' +
                ", date='" + date + '\'' +
                ", Month='" + Month + '\'' +
                ", year='" + year + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';

    }


}
