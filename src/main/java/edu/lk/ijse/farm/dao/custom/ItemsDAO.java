package edu.lk.ijse.farm.dao.custom;

import edu.lk.ijse.farm.dao.CrudeDAO;
import edu.lk.ijse.farm.entity.ItemEntity;

import java.sql.SQLException;

public interface ItemsDAO extends CrudeDAO<ItemEntity> {
    boolean reduceqty(String id,int qty) throws SQLException, ClassNotFoundException;
}
