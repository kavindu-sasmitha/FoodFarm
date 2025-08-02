package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.ItemBO;
import edu.lk.ijse.farm.bo.exception.InUseException;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.bo.util.EntitiyDTOConvertor;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dao.custom.ItemsDAO;
import edu.lk.ijse.farm.dto.ItemDto;
import edu.lk.ijse.farm.entity.ItemsEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class  ItemBOImpl implements ItemBO {

    private final ItemsDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEMSDAO);
    private final EntitiyDTOConvertor convertor = new EntitiyDTOConvertor();

    @Override
    public String saveItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        Optional<ItemsEntity> optionalItem = itemDAO.getById(itemDto.getItemCode());
        if (optionalItem.isPresent()) {
            throw new InUseException("Item already exists.");
        }

        boolean isSaved = itemDAO.save(convertor.getItemsEntity(itemDto));
        return isSaved ? "Saved successfully." : "Failed to save.";
    }

    @Override
    public String updateItem(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        Optional<ItemsEntity> optionalItem = itemDAO.getById(itemDto.getItemCode());
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Item not found.");
        }

        boolean isUpdated = itemDAO.update(convertor.getItemsEntity(itemDto));
        return isUpdated ? "Updated successfully." : "Failed to update.";
    }

    @Override
    public String deleteItems(String itemId) throws SQLException, ClassNotFoundException {
        Optional<ItemsEntity> optionalItem = itemDAO.getById(itemId);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Item not found.");
        }

        boolean isDeleted = itemDAO.delete(itemId);
        return isDeleted ? "Deleted successfully." : "Failed to delete.";
    }

    @Override
    public ArrayList<ItemDto> getAllItems() throws SQLException, ClassNotFoundException {
        List<ItemsEntity> entities = itemDAO.getAll();
        ArrayList<ItemDto> dtos = new ArrayList<>();
        for (ItemsEntity entity : entities) {
            dtos.add(convertor.getIemDto(entity));
        }
        return dtos;
    }

    @Override
    public ItemDto searchItems(String searchText) throws SQLException, ClassNotFoundException {
        List<ItemsEntity> results = itemDAO.search(searchText);
        if (!results.isEmpty()) {
            return convertor.getIemDto(results.get(0));
        }
        throw new NotFoundException("No item found for keyword: " + searchText);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        String lastId = itemDAO.getNextID();
        char prefix = 'I';
        if (lastId != null && lastId.matches("I\\d{3}")) {
            int number = Integer.parseInt(lastId.substring(1));
            return String.format("%s%03d", prefix, number + 1);
        }
        return prefix + "001";
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        List<ItemsEntity> items = itemDAO.getAll();
        ArrayList<String> ids = new ArrayList<>();
        for (ItemsEntity item : items) {
            ids.add(item.getItemId());
        }
        return ids;
    }

    @Override
    public ItemDto findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        Optional<ItemsEntity> optionalItem = itemDAO.getById(selectedItemId);
        if (optionalItem.isPresent()) {
            return convertor.getIemDto(optionalItem.get());
        }
        throw new NotFoundException("Item not found.");
    }
}
