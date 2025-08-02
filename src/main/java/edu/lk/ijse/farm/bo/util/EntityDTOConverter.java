package edu.lk.ijse.farm.bo.util;

import edu.lk.ijse.farm.dto.*;
import edu.lk.ijse.farm.entity.*;

public class EntityDTOConverter {
    public CustomerDto getCustomerDTO(CustomerEntity customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getContact(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    public CustomerEntity getCustomer(CustomerDto dto) {
        return new CustomerEntity(
                dto.getCustomerId(),
                dto.getName(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress()
        );
    }

    public EmployeeDto getEmployee(EmployeeEntity employee) {
        return new EmployeeDto(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getContact(),
                employee.getJoiningDate(),
                employee.getEmail(),
                employee.getPosition()
        );
    }

    public EmployeeEntity getEmployeeDto(EmployeeDto employeeDto) {
        return new EmployeeEntity(
                employeeDto.getEmployeeId(),
                employeeDto.getName(),
                employeeDto.getContact(),
                employeeDto.getJoiningDate(),
                employeeDto.getEmail(),
                employeeDto.getPosition()
        );
    }

    public ItemDto getItemDto(ItemEntity itemEntity) {
        return new ItemDto(
                itemEntity.getItemCode(),
                itemEntity.getItemName(),
                itemEntity.getManufactureDate(),
                itemEntity.getExpireDate(),
                itemEntity.getUnitPrice(),
                itemEntity.getQtyOnHand()
        );
    }
    public ItemEntity getItemEntity(ItemDto dto) {
        return new ItemEntity(
                dto.getItemCode(),
                dto.getItemName(),
                dto.getManufactureDate(),
                dto.getExpireDate(),
                dto.getUnitPrice(),
                dto.getQtyOnHand()
        );
    }
    public PlantEntity getPlantEntity(PlantDto dto) {
        return new PlantEntity(
                dto.getPlantId(),
                dto.getPlantType(),
                dto.getNumberOfPlant(),
                dto.getGrowthStage(),
                dto.getLifeTimeDate()
        );
    }
    public PlantDto getPlantDto(PlantEntity entity) {
        return new PlantDto(
                entity.getPlantId(),
                entity.getPlantType(),
                entity.getNumberOfPlant(),
                entity.getGrowthStage(),
                entity.getLifeTimeDate()
        );
    }
    public SupplierEntity getSupplierEntity(SupplierDto supplierDto) {
        return new SupplierEntity(
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress(),
                supplierDto.getSupplierItems()
        );

    }
    public SupplierDto getSupplierDto(SupplierEntity supplierEntity) {
        return new SupplierDto(
                supplierEntity.getSupplierId(),
                supplierEntity.getName(),
                supplierEntity.getContact(),
                supplierEntity.getAddress(),
                supplierEntity.getSupplierItems()
        );
    }
}
