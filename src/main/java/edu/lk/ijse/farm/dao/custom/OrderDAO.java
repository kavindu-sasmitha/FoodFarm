package edu.lk.ijse.farm.dao.custom;

import edu.lk.ijse.farm.dao.CrudeDAO;
import edu.lk.ijse.farm.entity.OrdersEntity;

public interface OrderDAO extends CrudeDAO <OrdersEntity>{
    String getLastId();
}
