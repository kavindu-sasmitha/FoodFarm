package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.EmployeeDAO;
import edu.lk.ijse.farm.entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<EmployeeEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<EmployeeEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(EmployeeEntity employeeEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) throws SQLException {
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
    public List<EmployeeEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
