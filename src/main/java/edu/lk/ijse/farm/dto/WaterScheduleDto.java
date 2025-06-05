package edu.lk.ijse.farm.dto;

public class WaterScheduleDto {
    private String scheduleId;
    private String plantType;
    private String wateringDays;
    private String growthStages;
    private String note;

    public WaterScheduleDto() {
    }

    public WaterScheduleDto(String scheduleId, String plantType, String wateringDays, String growthStages, String note) {
        this.scheduleId = scheduleId;
        this.plantType = plantType;
        this.wateringDays = wateringDays;
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

    public String getWateringDays() {
        return wateringDays;
    }

    public void setWateringDays(String wateringDays) {
        this.wateringDays = wateringDays;
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
        return "WaterScheduleDto{" +
                "scheduleId='" + scheduleId + ''' +
                ", plantType='" + plantType + ''' +
                ", wateringDays='" + wateringDays + ''' +
                ", growthStages='" + growthStages + ''' +
                ", note='" + note + ''' +
                '}';
    }
}
