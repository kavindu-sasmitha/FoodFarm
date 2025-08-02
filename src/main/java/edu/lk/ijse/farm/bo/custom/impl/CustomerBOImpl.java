package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.CustomerBO;
import edu.lk.ijse.farm.bo.exception.InUseException;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.bo.util.EntityDTOConverter;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.CustomerDAO;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.entity.CustomerEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerBOImpl implements CustomerBO {

    private final CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMERDAO);
    private final EntityDTOConverter convertor = new EntityDTOConverter();

    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        Optional<CustomerEntity> optionalCustomer = customerDAO.findById(customerDto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            throw new InUseException("Customer already exists.");
        }
        CustomerEntity customerEntity = convertor.getCustomer(customerDto);
        return customerDAO.save(customerEntity);
    }

    @Override
    public CustomerDto searchCustomer(String searchText) throws SQLException, ClassNotFoundException {
        List<CustomerEntity> result = customerDAO.search(searchText);
        if (!result.isEmpty()) {
            return convertor.getCustomerDTO(result.get(0));
        }
        throw new NotFoundException("No customer found for keyword: " + searchText);
    }

    @Override
    public String deleteCustomer(String customerId) throws SQLException, ClassNotFoundException {
        Optional<CustomerEntity> optionalCustomer = customerDAO.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer not found.");
        }

        boolean isDeleted = customerDAO.delete(customerId);
        if (isDeleted) {
            return "Deleted successfully.";
        } else {
            throw new InUseException("Customer cannot be deleted — record might be in use.");
        }
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        Optional<CustomerEntity> optionalCustomer = customerDAO.findById(customerDto.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer not found.");
        }

        CustomerEntity customerEntity = convertor.getCustomer(customerDto);
        return customerDAO.update(customerEntity);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        String lastId = customerDAO.getNextID();
        char tableChar = 'C';
        if (lastId != null && lastId.matches("C\\d{3}")) {
            int number = Integer.parseInt(lastId.substring(1));
            System.out.println("Last ID: " + lastId);
            return String.format("%s%03d", tableChar, number + 1);

        }
        return tableChar + "001";
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        List<CustomerEntity> entities = customerDAO.getAll();
        ArrayList<CustomerDto> dtos = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            dtos.add(convertor.getCustomerDTO(entity));
        }
        return dtos;
    }

    @Override
    public String findNameByContact(String contact) throws SQLException, ClassNotFoundException {
        List<CustomerEntity> results = customerDAO.search(contact);
        for (CustomerEntity entity : results) {
            if (entity.getContact().equals(contact)) {
                return entity.getName();
            }
        }
        throw new NotFoundException("Customer name not found for contact: " + contact);
    }
}
