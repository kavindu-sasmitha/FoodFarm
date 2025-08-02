package edu.lk.ijse.farm.bo.custom;

import edu.lk.ijse.farm.bo.BOTypes;
import edu.lk.ijse.farm.bo.SuperBO;
import edu.lk.ijse.farm.dto.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    String saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;

    String deleteItems(String itemId) throws SQLException, ClassNotFoundException;

    String updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException;

    ItemDto searchItems(String searchText) throws SQLException, ClassNotFoundException;

    String getNextID() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;

    ItemDto findById(String selectedItemId) throws SQLException, ClassNotFoundException;
}
