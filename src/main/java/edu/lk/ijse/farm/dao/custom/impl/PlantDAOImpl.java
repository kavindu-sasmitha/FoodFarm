package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.PlantDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;
import edu.lk.ijse.farm.entity.PlantEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlantDAOImpl implements PlantDAO {

    @Override
    public List<PlantEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Plant");
        List<PlantEntity> list = new ArrayList<>();
        while (rst.next()) {
            PlantEntity plantEntity = new PlantEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
            list.add(plantEntity);
        }
        return list;
    }

    @Override
    public Optional<PlantEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Plant WHERE Plant_Id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new PlantEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(PlantEntity plantEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("insert into Plant(Plant_Id,Plant_Type,Number_Of_Plant,Growth_Stages,Life_Time_Days) values (?, ?, ?, ?, ?)",
                plantEntity.getPlantId(),
                plantEntity.getPlantType(),
                plantEntity.getNumberOfPlants(),
                plantEntity.getGrowthStages(),
                plantEntity.getLifeTimeDays());
    }

    @Override
    public boolean update(PlantEntity plantEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("update Plant set Plant_Type=?,Number_Of_Plant=?,Growth_Stages=?,Life_Time_Days=? where Plant_Id=?",
                plantEntity.getPlantType(),
                plantEntity.getNumberOfPlants(),
                plantEntity.getGrowthStages(),
                plantEntity.getLifeTimeDays(),
                plantEntity.getPlantId());
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM Plant WHERE Plant_Id = ?",id);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT Plant_Id FROM Plant ORDER BY plant_Id DESC LIMIT 1");
        char tableChar = 'P';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    @Override
    public List<PlantEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Plant WHERE Plant_Id LIKE ? OR Plant_Type LIKE ? OR Number_Of_Plant LIKE ? OR Growth_Stages LIKE ? OR Life_Time_Days LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<PlantEntity> list = new ArrayList<>();
        while (rst.next()) {
            PlantEntity plantEntity = new PlantEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5)
            );
            list.add(plantEntity);
        }
        return list;
    }
}
