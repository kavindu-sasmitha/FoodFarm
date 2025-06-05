package edu.lk.ijse.farm.dto.tm;

public class WaterScheduleTM {
    private String scheduleId;
    private String wateringDays;
    private String plantType;
    private String growthStages;
    private String note;

    public WaterScheduleTM() {
    }

    public WaterScheduleTM(String scheduleId, String wateringDays, String plantType, String growthStages, String note) {
        this.scheduleId = scheduleId;
        this.wateringDays = wateringDays;
        this.plantType = plantType;
        this.growthStages = growthStages;
        this.note = note;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getWateringDays() {
        return wateringDays;
    }

    public void setWateringDays(String wateringDays) {
        this.wateringDays = wateringDays;
    }

    public String getPlantType() {
        return plantType;
    }

    public void setPlantType(String plantType) {
        this.plantType = plantType;
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
        return "WaterScheduleTM{" +
                "scheduleId='" + scheduleId + ''' +
                ", wateringDays='" + wateringDays + ''' +
                ", plantType='" + plantType + ''' +
                ", growthStages='" + growthStages + ''' +
                ", note='" + note + ''' +
                '}';
    }
}
