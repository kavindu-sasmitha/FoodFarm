package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.CustomerDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<CustomerEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<CustomerEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(CustomerEntity customerEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws SQLException {
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
    public List<CustomerEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
