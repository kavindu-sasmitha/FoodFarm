package edu.lk.ijse.farm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantEntity {
    private String plantId;
    private String plantType;
    private int numberOfPlants;
    private String growthStages;
    private int lifeTimeDays;
}
