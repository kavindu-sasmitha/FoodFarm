package edu.lk.ijse.farm.model;


import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.UserDto;
import edu.lk.ijse.farm.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public boolean logIn(String inputName, String inputPassword) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM User WHERE User_Name = ? AND User_Password = ?", inputName, inputPassword);
        if (rst.next()) {
            return true;
        } else {
            return false;
        }
    }

    public String saveUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO User VALUES(?,?,?,?)",
                userDto.getId(),
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getUserEmail());
    }

    public boolean updateUserPassword(String userName, String newPassword) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean result = CrudUtil.execute("UPDATE User SET User_Password=? WHERE User_Id=1", newPassword);
            connection.commit();
            return result;
        } catch (SQLException | ClassNotFoundException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}


