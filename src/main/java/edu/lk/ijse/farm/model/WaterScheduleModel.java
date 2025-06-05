package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.WaterScheduleDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WaterScheduleModel {

    public boolean saveSchedule(WaterScheduleDto dto) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            // Assuming a table named 'WaterSchedule' with columns matching WaterScheduleDto
            String sql = "INSERT INTO WaterSchedule (scheduleId, plantType, wateringDays, growthStages, note) VALUES (?, ?, ?, ?, ?)";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getScheduleId());
            pstm.setString(2, dto.getPlantType());
            pstm.setString(3, dto.getWateringDays());
            pstm.setString(4, dto.getGrowthStages());
            pstm.setString(5, dto.getNote());
            return pstm.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver class not found", e);
        } finally {
            if (pstm != null) {
                pstm.close();
            }
        }
    }

    public boolean updateSchedule(WaterScheduleDto dto) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "UPDATE WaterSchedule SET plantType = ?, wateringDays = ?, growthStages = ?, note = ? WHERE scheduleId = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, dto.getPlantType());
            pstm.setString(2, dto.getWateringDays());
            pstm.setString(3, dto.getGrowthStages());
            pstm.setString(4, dto.getNote());
            pstm.setString(5, dto.getScheduleId());
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
            String sql = "DELETE FROM WaterSchedule WHERE scheduleId = ?";
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

    public WaterScheduleDto getSchedule(String scheduleId) throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM WaterSchedule WHERE scheduleId = ?";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, scheduleId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new WaterScheduleDto(
                        rs.getString("scheduleId"),
                        rs.getString("plantType"),
                        rs.getString("wateringDays"),
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

    public List<WaterScheduleDto> getAllSchedules() throws SQLException {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<WaterScheduleDto> scheduleList = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT * FROM WaterSchedule";
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                scheduleList.add(new WaterScheduleDto(
                        rs.getString("scheduleId"),
                        rs.getString("plantType"),
                        rs.getString("wateringDays"),
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
