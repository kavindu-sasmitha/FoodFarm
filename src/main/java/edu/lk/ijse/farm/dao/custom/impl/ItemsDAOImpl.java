package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.ItemsDAO;
import edu.lk.ijse.farm.entity.ItemEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemsDAOImpl implements ItemsDAO {
    @Override
    public boolean reduceqty(String id, int qty) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "update items set qtyOnHand = qtyOnHand-? where itemCode=?",
                qty,id
        );
    }

    @Override
    public List<ItemEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQlUtil.execute("SELECT * FROM items");
        List<ItemEntity> list=new ArrayList<>();
        while(rst.next()){
            ItemEntity itemEntity =new ItemEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
            list.add(itemEntity);
        }
        return list;
    }

    @Override
    public Optional<ItemEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Items WHERE itemCode=?", id);
        if (resultSet.next()) {
            return Optional.of(new ItemEntity(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5),
                    resultSet.getInt(6)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(ItemEntity itemEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Items (itemCode,itemName,manufactureDate,expireDate,unitPrice,qtyOnHand) VALUES (?, ?, ?, ?, ?, ?)",
                itemEntity.getItemCode(),
                itemEntity.getItemName(),
                itemEntity.getManufactureDate(),
                itemEntity.getExpireDate(),
                itemEntity.getUnitPrice(),
                itemEntity.getQtyOnHand());
    }

    @Override
    public boolean update(ItemEntity itemEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE Items SET itemName = ?,manufactureDate = ?,expireDate = ?,unitPrice = ?,qtyOnHand = ? WHERE itemCode = ?",
                itemEntity.getItemName(),
                itemEntity.getManufactureDate(),
                itemEntity.getExpireDate(),
                itemEntity.getUnitPrice(),
                itemEntity.getQtyOnHand(),
                itemEntity.getItemCode());
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQlUtil.execute("DELETE FROM Items WHERE itemCode=?",id);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT itemCode FROM Items ORDER BY itemCode DESC LIMIT 1");
        char tableChar = 'I';
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
    public List<ItemEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Items WHERE itemCode LIKE ? OR itemName LIKE ? OR manufactureDate LIKE ? OR expireDate LIKE ? OR unitPrice LIKE ? OR qtyOnHand LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<ItemEntity> list = new ArrayList<>();
        while (rst.next()) {
            ItemEntity itemEntity = new ItemEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
            list.add(itemEntity);
        }
        return list;
    }
}
