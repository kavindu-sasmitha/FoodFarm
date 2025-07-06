package edu.lk.ijse.farm.dao.custom;

import edu.lk.ijse.farm.dao.CrudeDAO;
import edu.lk.ijse.farm.entity.ItemsEntity;

import java.sql.SQLException;

public interface ItemsDAO extends CrudeDAO<ItemsEntity> {
    boolean reduceqty(ItemsEntity itemsEntity) throws SQLException, ClassNotFoundException;
}
