package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.custom.LoginDAO;
import edu.lk.ijse.farm.entity.LoginEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public List<LoginEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<LoginEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(LoginEntity loginEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean update(LoginEntity loginEntity) throws SQLException {
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
    public List<LoginEntity> search(String keyword) throws SQLException {
        return List.of();
    }
}
