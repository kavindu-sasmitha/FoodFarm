package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    String saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;

    List<SupplierDto> searchSupplier(String searchText) throws SQLException, ClassNotFoundException;

    String deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    String updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;

    ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
}
