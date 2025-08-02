package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean login(String inputName, String inputPassword) throws SQLException, ClassNotFoundException;
    boolean updatePassword(String inputName, String inputPassword) throws SQLException, ClassNotFoundException;
}
