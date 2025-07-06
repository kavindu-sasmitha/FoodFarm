package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.SupplierDAO;
import edu.lk.ijse.farm.entity.SupplierEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<SupplierEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("select * from Supplier");
        List<SupplierEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            SupplierEntity supplierEntity = new SupplierEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            list.add(supplierEntity);
        }
        return list;
    }

    @Override
    public Optional<SupplierEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Supplier WHERE Supplier_Id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new SupplierEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                   resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(SupplierEntity supplierEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Supplier (Supplier_Id,Supplier_Name,Contact_Number,Address,Supplied_Items) VALUES (?, ?, ?, ?, ?)",
                supplierEntity.getSupplierId(),
                supplierEntity.getSupplierName(),
                supplierEntity.getContactNumber(),
                supplierEntity.getAddress(),
                supplierEntity.getSupplierItem());
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE Supplier SET Supplier_Name =?,Contact_Number = ?,Address = ?,Supplied_Items = ? WHERE Supplier_Id = ?",
                supplierEntity.getSupplierName(),
                supplierEntity.getContactNumber(),
                supplierEntity.getAddress(),
                supplierEntity.getSupplierItem(),
                supplierEntity.getSupplierId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM Supplier WHERE Supplier_Id=?",id);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT Supplier_Id FROM Supplier ORDER BY customer_id DESC LIMIT 1");
        char tableChar = 'S';
        if (resultSet.next()) {
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableChar + "%03d", nextIdNumber);
            return nextIdString;
        }
        return tableChar + "001";
    }

    @Override
    public List<SupplierEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Supplier WHERE Supplier_Id LIKE ? OR Supplier_Name LIKE ? OR Contact_Number LIKE ? OR Address LIKE ? OR Supplied_Items LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<SupplierEntity> list = new ArrayList<>();
        while (rst.next()) {
            SupplierEntity supplierEntity = new SupplierEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            list.add(supplierEntity);
        }
        return list;

    }
}
