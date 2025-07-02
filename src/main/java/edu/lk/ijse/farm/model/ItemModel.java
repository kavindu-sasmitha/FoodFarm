package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.ItemDto;
import edu.lk.ijse.farm.dto.OrderDetailDto;
import edu.lk.ijse.farm.dao.SQlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {
    public String saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Items (Item_Id,Item_Name,Manufacture_Date,Expire_Date,Price_Per_Unite,Quantity) VALUES (?, ?, ?, ?, ?, ?)";
        return SQlUtil.execute(sql,
                itemDto.getItemCode(),
                itemDto.getItemName(),
                itemDto.getManufactureDate(),
                itemDto.getExpireDate(),
                itemDto.getUnitPrice(),
                itemDto.getQtyOnHand()) ? "Item added successfully" : "Item addition failed";
    }

    public String deleteItems(String itemCode) throws SQLException, ClassNotFoundException {
       return SQlUtil.execute("DELETE FROM Items WHERE Item_Id=?", itemCode) ? "Item deleted successfully" : "Item deletion failed";
    }

    public String updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Items SET Item_Name = ?,Manufacture_Date = ?,Expire_Date = ?,Price_Per_Unite = ?,Quantity = ? WHERE Item_Id = ?";
        boolean isUpdated = SQlUtil.execute(sql,
                itemDto.getItemName(),
                itemDto.getManufactureDate(),
                itemDto.getExpireDate(),
                itemDto.getUnitPrice(),
                itemDto.getQtyOnHand(),
                itemDto.getItemCode());
        return isUpdated ? "Item updated successfully" : "Item update failed";
    }

    public ItemDto searchItems(String item_id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Items WHERE Item_Id LIKE ? OR Item_Name LIKE ? OR Manufacture_Date LIKE ? OR Expire_Date LIKE ? OR Price_Per_Unite LIKE ? OR Quantity LIKE ?";
        ResultSet rst = SQlUtil.execute(sql,
                "%" + item_id + "%",
                "%" + item_id + "%",
                "%" + item_id + "%",
                "%" + item_id + "%",
                "%" + item_id + "%",
                "%" + item_id + "%");
        if (rst.next()) {
            return new ItemDto(
                    rst.getString("Item_Id"),
                    rst.getString("Item_Name"),
                    rst.getString("Manufacture_Date"),
                    rst.getString("Expire_Date"),
                    rst.getDouble("Price_Per_Unite"),
                    rst.getInt("Quantity")
            );
        }
        return null;
    }
   
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst= SQlUtil.execute("select Item_Id from Items");
        ArrayList<String> list=new ArrayList<>();
        while(rst.next()){
            String id=rst.getString(1);
            list.add(id);
        }
        return list;

    }

    public ItemDto findById(String selectItemDetails) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Items WHERE Item_Id = ?", selectItemDetails);
        if (rst.next()) {
            return new ItemDto(
                    rst.getString("Item_Id"),
                    rst.getString("Item_Name"),
                    rst.getString("Manufacture_Date"),
                    rst.getString("Expire_Date"),
                    rst.getDouble("Price_Per_Unite"),
                    rst.getInt("Quantity")
            );
        }
        return null;

    }
    public boolean reduseQty(OrderDetailDto orderDetailDto) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("update Items set Quantity=Quantity-? where Item_Id=?",orderDetailDto.getQuantity(),orderDetailDto.getItemCode());
    }

    public String getNextID() throws SQLException, ClassNotFoundException {
        String sql= "select Items.Item_Id from Items order by Item_Id desc LIMIT 1";
        ResultSet resultSet= SQlUtil.execute(sql);
        if(resultSet.next()){
            String id=resultSet.getString(1);
            String numericPart = id.replaceAll("[^0-9]", "");
            int nextid = Integer.parseInt(numericPart);
            nextid++;
            return String.format("FRM"+"%03d",nextid);
        }else {
            return "FRM00-001";
        }
    }

    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        //System.out.println("getAllItems called in ItemModel.java");
        ResultSet resultSet = SQlUtil.execute("select * from Items");
       // System.out.println("query ok");
        ArrayList<ItemDto> list = new ArrayList<>();
       // System.out.println("list created");

        while (resultSet.next()) {
            //System.out.println("while loop called");
            ItemDto itemDto = new ItemDto(
                    resultSet.getString("Item_Id"),
                    resultSet.getString("Item_Name"),
                    resultSet.getString("Manufacture_Date"),
                    resultSet.getString("Expire_Date"),
                    resultSet.getDouble("Price_Per_Unite"),
                    resultSet.getInt("Quantity")
            );
           // System.out.println("itemDto created");
            list.add(itemDto);
        }
        //System.out.println("after while loop");
        return list;
    }

    public boolean reduceQty(OrderDetailDto orderDetailsDTO) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute(
                "update items set Quantity = quantity-? where Item_Id=?",
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getItemCode()
        );
    }
}
