package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.EmployeeBO;
import edu.lk.ijse.farm.bo.exception.DuplicateException;
import edu.lk.ijse.farm.bo.exception.InUseException;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.bo.util.EntitiyDTOConvertor;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.EmployeeDAO;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dto.EmployeeDto;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeBOImpl implements EmployeeBO {

    private final EmployeeDAO employeeDAO = DAOFactory.getInstance().getDAO(DAOType.EMPLOYEEDAO);
    private final EntitiyDTOConvertor convertor = new EntitiyDTOConvertor();

    @Override
    public String saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Optional<EmployeeEntity> optionalEmployee = employeeDAO.getById(employeeDto.getEmployeeId());
        if (optionalEmployee.isPresent()) {
            throw new InUseException("Employee already exists.");
        }

        EmployeeEntity employeeEntity = convertor.getEmployeeDto(employeeDto);
        boolean isSaved = employeeDAO.save(employeeEntity);
        return isSaved ? "Saved successfully." : "Failed to save.";
    }

    @Override
    public String pdateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        Optional<EmployeeEntity> optionalEmployee = employeeDAO.getById(employeeDto.getEmployeeId());
        if (optionalEmployee.isEmpty()) {
            throw new NotFoundException("Employee not found.");
        }

        EmployeeEntity employeeEntity = convertor.getEmployeeDto(employeeDto);
        boolean isUpdated = employeeDAO.update(employeeEntity);
        return isUpdated ? "Updated successfully." : "Failed to update.";
    }

    @Override
    public String deleteEmployee(String employeeId) throws SQLException, ClassNotFoundException {
        Optional<EmployeeEntity> optionalEmployee = employeeDAO.getById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new NotFoundException("Employee not found.");
        }

        boolean isDeleted = employeeDAO.delete(employeeId);
        return isDeleted ? "Deleted successfully." : "Failed to delete.";
    }

    @Override
    public int searchEmployee(String searchText) throws SQLException, ClassNotFoundException {
        List<EmployeeEntity> result = employeeDAO.search(searchText);
        return result.size();
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        String lastId = employeeDAO.getNextID();
        char prefix = 'E';
        if (lastId != null && lastId.matches("E\\d{3}")) {
            int number = Integer.parseInt(lastId.substring(1));
            return String.format("%s%03d", prefix, number + 1);
        }
        return prefix + "001";
    }

    @Override
    public ArrayList<EmployeeDto> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<EmployeeEntity> entities = employeeDAO.getAll();
        ArrayList<EmployeeDto> dtos = new ArrayList<>();
        for (EmployeeEntity entity : entities) {
            dtos.add(convertor.getEmployee(entity));
        }
        return dtos;
    }
}
