package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.dto.PlantDto;

import java.sql.SQLException;

public interface PlantBO {
    String updatePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException;

    String deletePlant(String plantId) throws SQLException, ClassNotFoundException;

    String addPlant(PlantDto plantDto) throws SQLException, ClassNotFoundException;

    PlantDto searchPlant(String searchText) throws SQLException, ClassNotFoundException;

    Object getAllPlants() throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;
}
