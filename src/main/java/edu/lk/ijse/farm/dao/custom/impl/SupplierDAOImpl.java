package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.SupplierDAO;
import edu.lk.ijse.farm.entity.SupplierEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<SupplierEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<SupplierEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(SupplierEntity supplierEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) throws SQLException {
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
    public List<SupplierEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
