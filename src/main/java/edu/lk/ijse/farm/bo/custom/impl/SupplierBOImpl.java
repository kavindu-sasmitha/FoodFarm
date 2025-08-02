package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.SupplierBO;
import edu.lk.ijse.farm.bo.exception.InUseException;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.bo.util.EntitiyDTOConvertor;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dao.custom.SupplierDAO;
import edu.lk.ijse.farm.dto.SupplierDto;
import edu.lk.ijse.farm.entity.SupplierEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierBOImpl implements SupplierBO {

    private final SupplierDAO supplierDAO = DAOFactory.getInstance().getDAO(DAOType.SUPPLIERDAO);
    private final EntitiyDTOConvertor convertor = new EntitiyDTOConvertor();

    @Override
    public String saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierDAO.getById(supplierDto.getSupplierId());
        if (optionalSupplier.isPresent()) {
            throw new InUseException("Supplier already exists.");
        }

        boolean isSaved = supplierDAO.save(convertor.getSupplierEntity(supplierDto));
        return isSaved ? "Saved successfully." : "Failed to save supplier.";
    }

    @Override
    public List<SupplierDto> searchSupplier(String searchText) throws SQLException, ClassNotFoundException {
        List<SupplierEntity> entities = supplierDAO.search(searchText);
        List<SupplierDto> dtoList = new ArrayList<>();
        for (SupplierEntity entity : entities) {
            dtoList.add(convertor.getSupplierDto(entity));
        }
        return dtoList;
    }

    @Override
    public String deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierDAO.getById(id);
        if (optionalSupplier.isEmpty()) {
            throw new NotFoundException("Supplier not found.");
        }

        boolean isDeleted = supplierDAO.delete(id);
        return isDeleted ? "Deleted successfully." : "Failed to delete supplier.";
    }

    @Override
    public String updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        Optional<SupplierEntity> optionalSupplier = supplierDAO.getById(supplierDto.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            throw new NotFoundException("Supplier not found.");
        }

        boolean isUpdated = supplierDAO.update(convertor.getSupplierEntity(supplierDto));
        return isUpdated ? "Updated successfully." : "Failed to update supplier.";
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        String lastId = supplierDAO.getNextID();
        char prefix = 'S';
        if (lastId != null && lastId.matches("S\\d{3}")) {
            int number = Integer.parseInt(lastId.substring(1));
            return String.format("%s%03d", prefix, number + 1);
        }
        return prefix + "001";
    }

    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<SupplierEntity> entities = supplierDAO.getAll();
        ArrayList<SupplierDto> dtos = new ArrayList<>();
        for (SupplierEntity entity : entities) {
            dtos.add(convertor.getSupplierDto(entity));
        }
        return dtos;
    }
}
