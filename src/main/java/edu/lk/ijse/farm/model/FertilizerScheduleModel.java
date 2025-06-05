package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.FertilizerScheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerScheduleModel {

    public boolean saveSchedule(FertilizerScheduleDto dto) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            // Assuming a table named 'FertilizerSchedule' with columns matching FertilizerScheduleDto
            String sql = "INSERT INTO FertilizerSchedule (scheduleId, plantType, fertilizerName, fertilizerDays, quantityPerPlant, growthStages, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getScheduleId());
            pstm.setString(2, dto.getPlantType());
            pstm.setString(3, dto.getFertilizerName());
            pstm.setString(4, dto.getFertilizerDays());
            pstm.setString(5, dto.getQuantityPerPlant());
            pstm.setString(6, dto.getGrowthStages());
            pstm.setString(7, dto.getNote());
            return pstm.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver class not found", e);
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            // Connection is managed by DBConnection, typically not closed here
        }
    }

    public boolean updateSchedule(FertilizerScheduleDto dto) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE FertilizerSchedule SET plantType = ?, fertilizerName = ?, fertilizerDays = ?, quantityPerPlant = ?, growthStages = ?, note = ? WHERE scheduleId = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getPlantType());
            pstm.setString(2, dto.getFertilizerName());
            pstm.setString(3, dto.getFertilizerDays());
            pstm.setString(4, dto.getQuantityPerPlant());
            pstm.setString(5, dto.getGrowthStages());
            pstm.setString(6, dto.getNote());
            pstm.setString(7, dto.getScheduleId());
            return pstm.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver class not found", e);
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public boolean deleteSchedule(String scheduleId) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "DELETE FROM FertilizerSchedule WHERE scheduleId = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, scheduleId);
            return pstm.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver class not found", e);
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public FertilizerScheduleDto getSchedule(String scheduleId) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM FertilizerSchedule WHERE scheduleId = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, scheduleId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new FertilizerScheduleDto(
                        rs.getString("scheduleId"),
                        rs.getString("plantType"),
                        rs.getString("fertilizerName"),
                        rs.getString("fertilizerDays"),
                        rs.getString("quantityPerPlant"),
                        rs.getString("growthStages"),
                        rs.getString("note")
                );
            }
            return null;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver class not found", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public List<FertilizerScheduleDto> getAllSchedules() throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<FertilizerScheduleDto> scheduleList = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM FertilizerSchedule";
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                scheduleList.add(new FertilizerScheduleDto(
                        rs.getString("scheduleId"),
                        rs.getString("plantType"),
                        rs.getString("fertilizerName"),
                        rs.getString("fertilizerDays"),
                        rs.getString("quantityPerPlant"),
                        rs.getString("growthStages"),
                        rs.getString("note")
                ));
            }
            return scheduleList;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver class not found", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        }
    }
}
