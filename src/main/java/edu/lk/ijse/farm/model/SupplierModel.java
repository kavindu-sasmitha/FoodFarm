package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.EmployeeDto;
import edu.lk.ijse.farm.dto.SupplierDto;
import edu.lk.ijse.farm.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public String saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute("INSERT INTO Supplier (Supplier_Id,Supplier_Name,Contact_Number,Address,Supplied_Items) VALUES (?, ?, ?, ?, ?)",
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress(),
                supplierDto.getSupplierItems());
        return isSaved ? "Supplier saved successfully" : "Failed to save supplier";
    }

    public String updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET Supplier_Name = ?,Contact_Number = ?,Address = ?,Supplied_Items = ? WHERE Supplier_Id = ?";
        return CrudUtil.execute(sql,
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress(),
                supplierDto.getSupplierItems(),
                supplierDto.getSupplierId()) ? "Supplier updated successfully" : "Failed to update supplier";
    }

    public String deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Supplier WHERE Supplier_Id=?", supplierId) ? "Supplier deleted successfully" : "Failed to delete supplier";
    }

    public List<SupplierDto> searchSupplier(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Supplier WHERE Supplier_Id LIKE ? OR Supplier_Name LIKE ? OR Contact_Number LIKE ? OR Address LIKE ? OR Supplier_Items LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%",
                "%" + keyword + "%");
        List<SupplierDto> supplierDtoList = new ArrayList<>();
        while (rst.next()) {
            SupplierDto supplierDto = new SupplierDto(
                    rst.getString("Supplier_Id"),
                    rst.getString("Supplier_Name"),
                    rst.getString("Contact_Number"),
                    rst.getString("Address"),
                    rst.getString("Supplier_Items"));
            supplierDtoList.add(supplierDto);
        }
        return supplierDtoList;
    }


    public List<String> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select Supplier_Id from Supplier");
        List<String> list = new ArrayList<>();
        while (rst.next()) {
            String id = rst.getString(1);
            list.add(id);
        }
        return list;
    }


    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Supplier");
        ArrayList<SupplierDto> suppliers = new ArrayList<>();
        while (rst.next()) {
            SupplierDto supplierDto = new SupplierDto(
                    rst.getString("Supplier_Id"),
                    rst.getString("Supplier_Name"),
                    rst.getString("Contact_Number"),
                    rst.getString("Address"),
                    rst.getString("Supplied_Items")
            );
            suppliers.add(supplierDto);
        }
        return suppliers;
    }
    public String getNextID() throws SQLException, ClassNotFoundException {
        String sql= "select Supplier.Supplier_Id from Supplier order by Supplier_Id desc LIMIT 1";
        ResultSet resultSet=CrudUtil.execute(sql);
        if(resultSet.next()){
            String id=resultSet.getString(1);
            String numericPart = id.replaceAll("[^0-9]", "");
            int nextid = Integer.parseInt(numericPart);
            nextid++;
            return String.format("S"+"%03d",nextid);
        }else {
            return "S00-001";
        }
    }
}