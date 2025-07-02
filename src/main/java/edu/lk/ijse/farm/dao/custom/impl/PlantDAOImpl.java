package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.PlantDAO;
import edu.lk.ijse.farm.entity.PlantEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PlantDAOImpl implements PlantDAO {

    @Override
    public List<PlantEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<PlantEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(PlantEntity plantEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(PlantEntity plantEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String getNextID() throws SQLException {
        return "";
    }

    @Override
    public List<PlantEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
