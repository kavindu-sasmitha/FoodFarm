package edu.lk.ijse.farm.dao.custom;

import edu.lk.ijse.farm.dao.CrudeDAO;
import edu.lk.ijse.farm.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerDAO extends CrudeDAO<CustomerEntity> {
    Optional<CustomerEntity> findById(String customerId) throws SQLException, ClassNotFoundException;
}
