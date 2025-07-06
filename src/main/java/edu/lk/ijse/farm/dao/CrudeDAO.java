package edu.lk.ijse.farm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudeDAO<T> extends SuperDAO {
    List<T> getAll() throws SQLException, ClassNotFoundException;
   Optional<T> getById(String id) throws SQLException, ClassNotFoundException;
   boolean save(T t) throws SQLException, ClassNotFoundException;
   boolean update(T t) throws SQLException, ClassNotFoundException;
   boolean delete(String id) throws SQLException, ClassNotFoundException;
   String getNextID() throws SQLException, ClassNotFoundException;
   List<T>search(String keyword) throws SQLException, ClassNotFoundException;
}
