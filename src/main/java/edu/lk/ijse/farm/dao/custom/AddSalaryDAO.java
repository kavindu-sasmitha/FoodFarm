package edu.lk.ijse.farm.dao.custom;

import edu.lk.ijse.farm.dao.CrudeDAO;
import edu.lk.ijse.farm.entity.AddSalaryEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddSalaryDAO extends CrudeDAO<AddSalaryEntity> {
    public ArrayList<AddSalaryEntity> getAddSalaryByPosition(String position) throws SQLException, ClassNotFoundException;
}
