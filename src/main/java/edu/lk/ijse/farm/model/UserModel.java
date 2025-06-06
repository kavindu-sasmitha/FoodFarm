package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserModel {
   public String saveUser(UserDto userDto) throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        String sql="INSERT INTO User VALUES(?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1, userDto.getId());
        statement.setString(2, userDto.getUserName());
        statement.setString(3, userDto.getPassword());
        statement.setString(4, userDto.getUserEmail());
        
        return statement.executeUpdate()>0?"save":"fail to save user";

   }


}
