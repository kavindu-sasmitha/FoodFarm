package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.AddSalaryBO;
import edu.lk.ijse.farm.bo.exception.InUseException;
import edu.lk.ijse.farm.bo.exception.NotFoundException;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.AddSalaryDAO;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dto.AddSalaryDto;
import edu.lk.ijse.farm.entity.AddSalaryEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddSalaryBOImpl implements AddSalaryBO {

    private final AddSalaryDAO addSalaryDAO = DAOFactory.getInstance().getDAO(DAOType.ADDSALARYDAO);

    @Override
    public boolean addSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException {
        Optional<AddSalaryEntity> optionalSalary = addSalaryDAO.getById(addSalaryDto.getPosition());
        if (optionalSalary.isPresent()) {
            // If position already exists, update it
            return updateSalary(addSalaryDto);
        }
        
        // Convert DTO to Entity
        AddSalaryEntity entity = new AddSalaryEntity(
                addSalaryDto.getPosition(),
                addSalaryDto.getSalary()
        );
        
        return addSalaryDAO.save(entity);
    }

    @Override
    public boolean updateSalary(AddSalaryDto addSalaryDto) throws SQLException, ClassNotFoundException {
        Optional<AddSalaryEntity> optionalSalary = addSalaryDAO.getById(addSalaryDto.getPosition());
        if (optionalSalary.isEmpty()) {
            throw new NotFoundException("Salary record not found for position: " + addSalaryDto.getPosition());
        }
        
        // Convert DTO to Entity
        AddSalaryEntity entity = new AddSalaryEntity(
                addSalaryDto.getPosition(),
                addSalaryDto.getSalary()
        );
        
        return addSalaryDAO.update(entity);
    }

    @Override
    public String deleteSalary(String position) throws SQLException, ClassNotFoundException {
        Optional<AddSalaryEntity> optionalSalary = addSalaryDAO.getById(position);
        if (optionalSalary.isEmpty()) {
            throw new NotFoundException("Salary record not found for position: " + position);
        }
        
        boolean isDeleted = addSalaryDAO.delete(position);
        if (isDeleted) {
            return "Successfully Delete";
        } else {
            throw new InUseException("Failed to delete salary record");
        }
    }

    @Override
    public ArrayList<AddSalaryDto> getAllSalaryByPosition() throws SQLException, ClassNotFoundException {
        List<AddSalaryEntity> entities = addSalaryDAO.getAll();
        ArrayList<AddSalaryDto> dtos = new ArrayList<>();
        
        for (AddSalaryEntity entity : entities) {
            dtos.add(new AddSalaryDto(
                    entity.getPosition(),
                    entity.getDailySalary()
            ));
        }
        
        return dtos;
    }

    @Override
    public AddSalaryDto searchSalary(String position) throws SQLException, ClassNotFoundException {
        Optional<AddSalaryEntity> optionalSalary = addSalaryDAO.getById(position);
        if (optionalSalary.isEmpty()) {
            throw new NotFoundException("Salary record not found for position: " + position);
        }
        
        AddSalaryEntity entity = optionalSalary.get();
        return new AddSalaryDto(
                entity.getPosition(),
                entity.getDailySalary()
        );
    }
}