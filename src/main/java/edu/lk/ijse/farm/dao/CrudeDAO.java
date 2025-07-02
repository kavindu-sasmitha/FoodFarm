package edu.lk.ijse.farm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudeDAO<T> extends SuperDAO {
    List<T> getAll()throws SQLException;
   Optional<T> getById(String id)throws SQLException;
   boolean save(T t)throws SQLException;
   boolean update(T t)throws SQLException;
   boolean delete(String id)throws SQLException;
   String getNextID() throws SQLException;
   List<T>search(String keyword)throws SQLException;
}
