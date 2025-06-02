package edu.lk.ijse.farm.dto;

public class FertilizerDto {
    private String fertilizerId;
    private String fertilizerName;
    private  String ExpectedDate;
    private int quantity;

    public FertilizerDto() {
    }
    public FertilizerDto(String fertilizerId, String fertilizerName, String expectedDate, int quantity) {
        this.fertilizerId = fertilizerId;
        this.fertilizerName = fertilizerName;
        ExpectedDate = expectedDate;
        this.quantity = quantity;
    }
    public String getFertilizerId() {
        return fertilizerId;
    }
    public void setFertilizerId(String fertilizerId) {
        this.fertilizerId = fertilizerId;
    }
    public String getFertilizerName() {
        return fertilizerName;
    }
    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }
    public String getExpectedDate() {
        return ExpectedDate;
    }
    public  void setExpectedDate(String expectedDate) {
        ExpectedDate = expectedDate;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "FertilizerDto{" +
                "fertilizerId='" + fertilizerId + '\'' +
                ", fertilizerName='" + fertilizerName + '\'' +
                ", ExpectedDate='" + ExpectedDate + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
