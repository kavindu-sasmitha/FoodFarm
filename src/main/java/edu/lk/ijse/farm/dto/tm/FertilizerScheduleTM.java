package edu.lk.ijse.farm.dto.tm;

public class FertilizerScheduleTM {
    private String plantType;
    private String fertilizerName;
    private String quantityPerPlant;
    private String growthStages;
    private String note;

    public FertilizerScheduleTM() {
    }

    public FertilizerScheduleTM(String plantType, String fertilizerName, String quantityPerPlant, String growthStages, String note) {
        this.plantType = plantType;
        this.fertilizerName = fertilizerName;
        this.quantityPerPlant = quantityPerPlant;
        this.growthStages = growthStages;
        this.note = note;
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
        return "FertilizerScheduleTM{" +
                "plantType='" + plantType + ''' +
                ", fertilizerName='" + fertilizerName + ''' +
                ", quantityPerPlant='" + quantityPerPlant + ''' +
                ", growthStages='" + growthStages + ''' +
                ", note='" + note + ''' +
                '}';
    }
}
