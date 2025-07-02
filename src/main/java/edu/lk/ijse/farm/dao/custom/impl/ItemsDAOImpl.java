package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.ItemsDAO;
import edu.lk.ijse.farm.entity.ItemsEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemsDAOImpl implements ItemsDAO {
    @Override
    public boolean reduceqty(ItemsEntity itemsEntity) {
        return false;
    }

    @Override
    public List<ItemsEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<ItemsEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(ItemsEntity itemsEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ItemsEntity itemsEntity) throws SQLException {
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
    public List<ItemsEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
