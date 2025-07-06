package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.ItemsDAO;
import edu.lk.ijse.farm.entity.ItemsEntity;
import edu.lk.ijse.farm.entity.SupplierEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemsDAOImpl implements ItemsDAO {
    @Override
    public boolean reduceqty(ItemsEntity itemsEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "update items set Quantity = quantity-? where Item_Id=?",
                itemsEntity.getQuantity(),
                itemsEntity.getItemId()
        );
    }

    @Override
    public List<ItemsEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=SQlUtil.execute("select * from Items");
        List<ItemsEntity> list=new ArrayList<>();
        while(rst.next()){
            ItemsEntity itemsEntity=new ItemsEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
            list.add(itemsEntity);
        }
        return list;
    }

    @Override
    public Optional<ItemsEntity> getById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQlUtil.execute("SELECT * FROM Items WHERE Item_Id = ?", id);
        if (resultSet.next()) {
            return Optional.of(new ItemsEntity(
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
    public boolean save(ItemsEntity itemsEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO Items (Item_Id,Item_Name,Manufacture_Date,Expire_Date,Price_Per_Unite,Quantity) VALUES (?, ?, ?, ?, ?, ?)",
                itemsEntity.getItemId(),
                itemsEntity.getItemName(),
                itemsEntity.getManufactureDate(),
                itemsEntity.getExpireDate(),
                itemsEntity.getUnitePrice(),
                itemsEntity.getQuantity());
    }

    @Override
    public boolean update(ItemsEntity itemsEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE Items SET Item_Name = ?,Manufacture_Date = ?,Expire_Date = ?,Price_Per_Unite = ?,Quantity = ? WHERE Item_Id = ?",
                itemsEntity.getItemName(),
                itemsEntity.getManufactureDate(),
                itemsEntity.getExpireDate(),
                itemsEntity.getUnitePrice(),
                itemsEntity.getQuantity(),
                itemsEntity.getItemId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SQlUtil.execute("DELETE FROM Items WHERE Item_Id=?",id);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet =SQlUtil.execute("SELECT Supplier_Id FROM Supplier ORDER BY customer_id DESC LIMIT 1");
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
    public List<ItemsEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Items WHERE Item_Id LIKE ? OR Item_Name LIKE ? OR Manufacture_Date LIKE ? OR Expire_Date LIKE ? OR Price_Per_Unite LIKE ? OR Quantity LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<ItemsEntity> list = new ArrayList<>();
        while (rst.next()) {
            ItemsEntity itemsEntity = new ItemsEntity(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
            list.add(itemsEntity);
        }
        return list;
    }
}
