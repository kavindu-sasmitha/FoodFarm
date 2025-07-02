package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.AddSalaryDAO;
import edu.lk.ijse.farm.entity.AddSalaryEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AddSalaryDAOImpl implements AddSalaryDAO {
    @Override
    public List<AddSalaryEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<AddSalaryEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(AddSalaryEntity addSalaryEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(AddSalaryEntity addSalaryEntity) throws SQLException {
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
    public List<AddSalaryEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
