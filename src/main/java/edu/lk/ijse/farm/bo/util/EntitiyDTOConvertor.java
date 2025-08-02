package edu.lk.ijse.farm.bo.util;

import edu.lk.ijse.farm.dto.*;
import edu.lk.ijse.farm.entity.*;

public class EntitiyDTOConvertor {
    public CustomerDto getCustomerDTO(CustomerEntity customer) {
        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setName(customer.getCustomerName());
        dto.setContact(customer.getContact());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    public CustomerEntity getCustomer(CustomerDto dto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(dto.getCustomerId());
        customerEntity.setCustomerName(dto.getName());
        customerEntity.setContact(dto.getContact());
        customerEntity.setEmail(dto.getEmail());
        customerEntity.setAddress(dto.getAddress());
        return customerEntity;
    }

    public EmployeeDto getEmployee(EmployeeEntity employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setName(employee.getEmployeeName());
        employeeDto.setContact(employee.getContact());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPosition(employee.getPosition());
        return employeeDto;
    }

    public EmployeeEntity getEmployeeDto(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(employeeDto.getEmployeeId());
        employeeEntity.setEmployeeName(employeeDto.getName());
        employeeEntity.setContact(employeeDto.getContact());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setPosition(employeeDto.getPosition());
        return employeeEntity;
    }

    public ItemDto getIemDto(ItemsEntity itemsEntity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemCode(itemDto.getItemCode());
        itemDto.setItemName(itemDto.getItemName());
        itemDto.setManufactureDate(itemDto.getManufactureDate());
        itemDto.setExpireDate(itemDto.getExpireDate());
        itemDto.setUnitPrice(itemDto.getUnitPrice());
        itemDto.setQtyOnHand(itemDto.getQtyOnHand());
        return itemDto;
    }
    public ItemsEntity getItemsEntity(ItemDto dto) {
        ItemsEntity entity = new ItemsEntity();
        entity.setItemId(dto.getItemCode());
        entity.setItemName(dto.getItemName());
        entity.setManufactureDate(dto.getManufactureDate());
        entity.setExpireDate(dto.getExpireDate());
        entity.setUnitePrice(dto.getUnitPrice());
        entity.setQuantity(dto.getQtyOnHand());
        return entity;
    }
    public PlantEntity getPlantEntity(PlantDto dto) {
        PlantEntity entity = new PlantEntity();
        entity.setPlantId(dto.getPlantId());
        entity.setPlantType(dto.getPlantType());
        entity.setNumberOfPlants(dto.getNumberOfPlant());
        entity.setGrowthStages(dto.getGrowthStage());
        entity.setLifeTimeDays(dto.getLifeTimeDate());
        return entity;
    }
    public PlantDto getPlantDto(PlantEntity entity) {
        PlantDto plantDto = new PlantDto();
        plantDto.setPlantId(entity.getPlantId());
        plantDto.setPlantType(entity.getPlantType());
        plantDto.setNumberOfPlant(entity.getNumberOfPlants());
        plantDto.setGrowthStage(entity.getGrowthStages());
        plantDto.setLifeTimeDate(entity.getLifeTimeDays());
        return plantDto;
    }
    public SupplierEntity getSupplierEntity(SupplierDto supplierDto) {
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setSupplierId(supplierDto.getSupplierId());
        supplierEntity.setSupplierName(supplierDto.getName());
        supplierEntity.setContactNumber(supplierDto.getContact());
        supplierEntity.setAddress(supplierDto.getAddress());
        supplierEntity.setSupplierItem(supplierDto.getSupplierItems());
        return supplierEntity;

    }
    public SupplierDto getSupplierDto(SupplierEntity supplierEntity) {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierId(supplierEntity.getSupplierId());
        supplierDto.setName(supplierEntity.getSupplierName());
        supplierDto.setContact(supplierEntity.getContactNumber());
        supplierDto.setAddress(String.valueOf(supplierEntity.getAddress()));
        supplierDto.setSupplierItems(String.valueOf(supplierEntity.getSupplierItem()));
        return supplierDto;
    }


}
