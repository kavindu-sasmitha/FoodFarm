package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.FertilizerDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FertilizerModel  {


    public String addFertilizer(FertilizerDto fertilizerDto) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getInstance().getConnection();
        String sql="INSERT INTO Fertilizer VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,fertilizerDto.getFertilizerId());
        statement.setString(2,fertilizerDto.getFertilizerName());
        statement.setString(3,fertilizerDto.getExpectedDate());
        statement.setInt(4,fertilizerDto.getQuantity());

        return statement.executeUpdate() >0 ? " Successfully":"Faild";

    }
    public String deleteFertilizer(String fertilizerId) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getInstance().getConnection();
        String sql="DELETE FROM Fertilizer WHERE FertilizerId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,fertilizerId);
        return statement.executeUpdate() >0 ? "Delete Successfully":"Faild to Delete";

    }
    public String updateFertilizer(FertilizerDto fertilizerDto) throws SQLException, ClassNotFoundException {
        Connection connection =DBConnection.getInstance().getConnection();
        String sql="UPDATE Fertilizer Fertilizer_Name=?,Expire_Date=?,Quantity=?  WHERE Fertilizer_Id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,fertilizerDto.getFertilizerName());
        statement.setString(2,fertilizerDto.getExpectedDate());
        statement.setInt(3,fertilizerDto.getQuantity());
        statement.setString(4,fertilizerDto.getFertilizerId());
        return statement.executeUpdate() >0 ? " Successfully":"Faild to Update";


    }
    public FertilizerDto getFertilizer(String fertilizerId) throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        String sql="SELECT * FROM Fertilizer WHERE Fertilizer_Id = ? || Fertilizer_Name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,fertilizerId);
        ResultSet rst = statement.executeQuery();
        if(rst.next()){
            FertilizerDto fertilizerDto = new FertilizerDto(rst.getString("Fertilizer_Id"),rst.getString("Fertilizer_Name"),rst.getString("Expire_Date"),
                    rst.getInt("Quantity"));
        }
        return null;
    }
    public List<FertilizerDto> getFertilizerList() throws SQLException, ClassNotFoundException {
        Connection connection=DBConnection.getInstance().getConnection();
        String sql="SELECT * FROM Fertilizer";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();
        List<FertilizerDto> fertilizerDtoList = new ArrayList<>();
        while(rst.next()){
            FertilizerDto fertilizerDto = new FertilizerDto(rst.getString("Fertilizer_Id"),rst.getString("Fertilizer_Name"),rst.getString("Expire_Date"),
                    rst.getInt("Quantity"));
        }
        return fertilizerDtoList;
    }

}
