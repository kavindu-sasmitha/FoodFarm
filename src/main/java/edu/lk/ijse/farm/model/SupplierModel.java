package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.dto.SupplierDto;
import edu.lk.ijse.farm.dao.SQlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public String saveSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQlUtil.execute("INSERT INTO Supplier (Supplier_Id,Supplier_Name,Contact_Number,Address,Supplied_Items) VALUES (?, ?, ?, ?, ?)",
                supplierDto.getSupplierId(),
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress(),
                supplierDto.getSupplierItems());
        return isSaved ? "Supplier saved successfully" : "Failed to save supplier";
    }

    public String updateSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Supplier SET Supplier_Name =?,Contact_Number = ?,Address = ?,Supplied_Items = ? WHERE Supplier_Id = ?";
        //System.out.println("Updating Supplier");
        return SQlUtil.execute(sql,
                supplierDto.getName(),
                supplierDto.getContact(),
                supplierDto.getAddress(),
                supplierDto.getSupplierItems(),
                supplierDto.getSupplierId()) ? "Supplier updated successfully" : "Failed to update supplier";
    }

    public String deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM Supplier WHERE Supplier_Id=?", supplierId) ? "Supplier deleted successfully" : "Failed to delete supplier";
    }

    public List<SupplierDto> searchSupplier(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Supplier WHERE Supplier_Id LIKE ? OR Supplier_Name LIKE ? OR Contact_Number LIKE ? OR Address LIKE ? OR Supplied_Items LIKE ?",
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
                    rst.getString("Supplied_Items"));
            supplierDtoList.add(supplierDto);
        }
        return supplierDtoList;
    }


    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQlUtil.execute("SELECT * FROM Supplier");
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
        ResultSet resultSet= SQlUtil.execute(sql);
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