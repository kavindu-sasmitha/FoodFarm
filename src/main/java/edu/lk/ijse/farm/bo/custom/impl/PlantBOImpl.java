package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.PlantBO;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.bo.util.EntitiyDTOConvertor;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dao.custom.PlantDAO;
import edu.lk.ijse.farm.dto.PlantDto;
import edu.lk.ijse.farm.entity.PlantEntity;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlantBOImpl implements PlantBO {

    private final PlantDAO plantDAO = DAOFactory.getInstance().getDAO(DAOType.PLANTDAO);
    private final EntitiyDTOConvertor convertor = new EntitiyDTOConvertor();

    @Override
    public String updatePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException {
        Optional<PlantEntity> optionalPlant = plantDAO.getById(plantDto.getPlantId());
        if (optionalPlant.isEmpty()) {
            throw new NotFoundException("Plant not found.");
        }

        boolean isUpdated = plantDAO.update(convertor.getPlantEntity(plantDto));
        return isUpdated ? "Plant updated successfully." : "Failed to update plant.";
    }

    @Override
    public String deletePlant(String plantId) throws SQLException, ClassNotFoundException {
        Optional<PlantEntity> optionalPlant = plantDAO.getById(plantId);
        if (optionalPlant.isEmpty()) {
            throw new NotFoundException("Plant not found.");
        }

        boolean isDeleted = plantDAO.delete(plantId);
        return isDeleted ? "Plant deleted successfully." : "Failed to delete plant.";
    }

    @Override
    public String addPlant(PlantDto plantDto) throws SQLException, ClassNotFoundException {
        Optional<PlantEntity> optionalPlant = plantDAO.getById(plantDto.getPlantId());
        if (optionalPlant.isPresent()) {
            return "Plant already exists.";
        }

        boolean isSaved = plantDAO.save(convertor.getPlantEntity(plantDto));
        return isSaved ? "Plant added successfully." : "Failed to add plant.";
    }

    @Override
    public PlantDto searchPlant(String searchText) throws SQLException, ClassNotFoundException {
        List<PlantEntity> optionalPlant = plantDAO.search(searchText);
        return null;
    }

    @Override
    public List<PlantDto> getAllPlants() throws SQLException, ClassNotFoundException {
        List<PlantEntity> entities = plantDAO.getAll();
        List<PlantDto> dtoList = new ArrayList<>();
        for (PlantEntity entity : entities) {
            dtoList.add(convertor.getPlantDto(entity));
        }
        return dtoList;
    }


    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        return plantDAO.getNextID();
    }
}
