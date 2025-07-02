package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.UserDAO;
import edu.lk.ijse.farm.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    @Override
    public List getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(UserEntity userEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(UserEntity userEntity) throws SQLException {
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
    public List search(String keyword) throws SQLException {
        return List.of();
    }
}
