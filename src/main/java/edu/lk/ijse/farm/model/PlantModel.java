package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.PlantDto;
import edu.lk.ijse.farm.dao.SQlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class
PlantModel {
    public String addPlant(PlantDto plantDto) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("insert into Plant(Plant_Id,Plant_Type,Number_Of_Plant,Growth_Stages,Life_Time_Days) values (?, ?, ?, ?, ?)",
                plantDto.getPlantId(),
                plantDto.getPlantType(),
                plantDto.getNumberOfPlant(),
                plantDto.getGrowthStage(),
                plantDto.getLifeTimeDate()) ? "add" : "not add";
    }
   public String updatePlant(PlantDto plantDto) throws SQLException, ClassNotFoundException {
      return SQlUtil.execute("update Plant set Plant_Type=?,Number_Of_Plant=?,Growth_Stages=?,Life_Time_Days=? where Plant_Id=?",
              plantDto.getPlantType(),
              plantDto.getNumberOfPlant(),
              plantDto.getGrowthStage(),
              plantDto.getLifeTimeDate(),
              plantDto.getPlantId()) ? "update" :"not update";
   }

    public String deletePlant(String plantId) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "DELETE FROM Plant WHERE Plant_Id = ?", plantId
        ) ? "Plant deleted successfully" : "Failed to delete plant";
    }
   public List<PlantDto> getAllPlants() throws SQLException, ClassNotFoundException {
       Connection connection= DBConnection.getInstance().getConnection();
       String sql="SELECT * FROM Plant";
       PreparedStatement statement=connection.prepareStatement(sql);
       ResultSet rst = statement.executeQuery();
       List<PlantDto> plantDtoList = new ArrayList<>();
       while(rst.next()){
           PlantDto plantDto = new PlantDto(rst.getString("Plant_Id"),rst.getString("Plant_Type"),
                   rst.getInt("Number_Of_Plant"),rst.getString("Growth_Stages"),rst.getInt("Life_Time_Days"));
           plantDtoList.add(plantDto);

       }
       return plantDtoList;
   }

    public PlantDto searchPlant(String plantId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Plant WHERE Plant_Id LIKE ? OR Plant_Type LIKE ? OR Growth_Stages LIKE ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        String searchPattern = "%" + plantId + "%";
        statement.setString(1, searchPattern);
        statement.setString(2, searchPattern);
        statement.setString(3, searchPattern);
        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
            return new PlantDto(
                    rst.getString("Plant_Id"),
                    rst.getString("Plant_Type"),
                    rst.getInt("Number_Of_Plant"),
                    rst.getString("Growth_Stages"),
                    rst.getInt("Life_Time_Days")
            );
        }
        return null;
    }

    public String getNextID() throws SQLException, ClassNotFoundException {
        String sql="select Plant.Plant_id from Plant order by Plant_Id desc LIMIT 1";
        ResultSet resultSet= SQlUtil.execute(sql);
        if(resultSet.next()){
            String id=resultSet.getString(1);
            String numericPart = id.replaceAll("[^0-9]", "");
            int nextid = Integer.parseInt(numericPart);
            nextid++;
            return String.format("P"+"%03d",nextid);
        }else {
            return "P00-001";
        }
    }
}
