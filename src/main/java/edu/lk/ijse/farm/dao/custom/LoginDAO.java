package edu.lk.ijse.farm.dao.custom;

import edu.lk.ijse.farm.dao.CrudeDAO;
import edu.lk.ijse.farm.entity.LoginEntity;

import java.sql.SQLException;

public interface LoginDAO extends CrudeDAO<LoginEntity> {
    public boolean login(String name,String password) throws SQLException, ClassNotFoundException;
    public boolean updatePassword(String name,String newPassword) throws SQLException, ClassNotFoundException;
}
