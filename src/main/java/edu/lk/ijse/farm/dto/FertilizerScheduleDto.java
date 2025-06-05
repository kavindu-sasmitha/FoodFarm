package edu.lk.ijse.farm.dto;

public class FertilizerScheduleDto {
    private String scheduleId;
    private String plantType;
    private String fertilizerName;
    private String fertilizerDays;
    private String quantityPerPlant;
    private String growthStages;
    private String note;

    public FertilizerScheduleDto() {
    }

    public FertilizerScheduleDto(String scheduleId, String plantType, String fertilizerName, String fertilizerDays, String quantityPerPlant, String growthStages, String note) {
        this.scheduleId = scheduleId;
        this.plantType = plantType;
        this.fertilizerName = fertilizerName;
        this.fertilizerDays = fertilizerDays;
        this.quantityPerPlant = quantityPerPlant;
        this.growthStages = growthStages;
        this.note = note;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
    }

    public String getFertilizerName() {
        return fertilizerName;
    }

    public void setFertilizerName(String fertilizerName) {
        this.fertilizerName = fertilizerName;
    }

    public String getFertilizerDays() {
        return fertilizerDays;
    }

    public void setFertilizerDays(String fertilizerDays) {
        this.fertilizerDays = fertilizerDays;
    }

    public String getQuantityPerPlant() {
        return quantityPerPlant;
    }

    public void setQuantityPerPlant(String quantityPerPlant) {
        this.quantityPerPlant = quantityPerPlant;
    }

    public String getGrowthStages() {
        return growthStages;
    }

    public void setGrowthStages(String growthStages) {
        this.growthStages = growthStages;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "FertilizerScheduleDto{" +
                "scheduleId='" + scheduleId + ''' +
                ", plantType='" + plantType + ''' +
                ", fertilizerName='" + fertilizerName + ''' +
                ", fertilizerDays='" + fertilizerDays + ''' +
                ", quantityPerPlant='" + quantityPerPlant + ''' +
                ", growthStages='" + growthStages + ''' +
                ", note='" + note + ''' +
                '}';
    }
}
