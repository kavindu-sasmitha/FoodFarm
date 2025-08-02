package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.UserDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;
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
    public boolean save(UserEntity userEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO User VALUES(?,?,?,?)",
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getUserPaassword(),
                userEntity.getUserEmail());
    }

    @Override
    public boolean update(UserEntity userEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE User SET User_Name=?,User_Password=?,Email_Address=? WHERE User_ID=?",
                userEntity.getUserName(),
                userEntity.getUserPaassword(),
                userEntity.getUserEmail(),
                userEntity.getUserId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("delete from User where User_ID=?",id);
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
