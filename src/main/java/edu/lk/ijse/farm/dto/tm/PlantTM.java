package edu.lk.ijse.farm.dto.tm;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlantTM {
    private String plantId;
    private String plantType;
    private int numberOfPlants;
    private String growthStage;
    private int lifeTimeDays;
}
