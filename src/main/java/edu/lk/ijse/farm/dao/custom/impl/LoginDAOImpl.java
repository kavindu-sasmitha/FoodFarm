package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.LoginDAO;
import edu.lk.ijse.farm.entity.LoginEntity;

import java.sql.ResultSet;
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

    @Override
    public boolean login(String inputName, String inputPassword) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQlUtil.execute("SELECT * FROM User WHERE User_Name = ? AND User_Password = ?",
                inputName, inputPassword);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(String name, String newPassword) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE User SET User_Password=? WHERE User_Id=1", newPassword);
    }
}
