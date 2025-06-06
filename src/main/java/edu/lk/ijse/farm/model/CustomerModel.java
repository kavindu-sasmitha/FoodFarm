package edu.lk.ijse.farm.model;

import edu.lk.ijse.farm.db.DBConnection;
import edu.lk.ijse.farm.dto.CustomerDto;
import edu.lk.ijse.farm.util.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    private Connection connection;

    public  CustomerModel() throws Exception{
        connection = DBConnection.getInstance().getConnection();
    }
    public String getNextID() throws SQLException, ClassNotFoundException {
        String sql="select Customer.Customer_Id from Customer order by Customer_Id desc LIMIT 1";
        ResultSet resultSet=CrudUtil.execute(sql);
        if(resultSet.next()){
            String id=resultSet.getString(1);
            String numericPart = id.replaceAll("[^0-9]", "");
            int nextid = Integer.parseInt(numericPart);
            nextid++;
            return String.format("C"+"%03d",nextid);
        }else {
            return "C00-001";
        }
    }
    public String saveCustomer(CustomerDto customerDto) throws Exception {
        return CrudUtil.execute(
                "INSERT INTO Customer (Customer_Id,Customer_Name,Contact_Number,Email,Address) VALUES (?, ?, ?, ?, ?)",
                customerDto.getCustomerId(),
                customerDto.getName(),
                customerDto.getContact(),
                customerDto.getEmail(),
                customerDto.getAddress()
        ) ? "Customer saved successfully" : "Failed to save customer";


    }

    public CustomerDto searchCustomer(String custId) throws Exception {
        String sql = "SELECT * FROM Customer WHERE Customer_Id LIKE ? OR Customer_Name LIKE ? OR Contact_Number LIKE ? OR Email LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        String searchPattern = "%" + custId + "%";
        statement.setString(1, searchPattern);
        statement.setString(2, searchPattern);
        statement.setString(3, searchPattern);
        statement.setString(4, searchPattern);
        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
            return new CustomerDto(
                    rst.getString("Customer_Id"),
                    rst.getString("Customer_Name"),
                    rst.getString("Contact_Number"),
                    rst.getString("Email"),
                    rst.getString("Address")
            );
        }
        return null;
    }

    public String updateCustomer(CustomerDto customerDto) throws Exception {
        return CrudUtil.execute(
                "UPDATE Customer SET Customer_Name=?, Contact_Number=?, Email=?, Address=? WHERE Customer_Id=?",
                customerDto.getName(),
                customerDto.getContact(),
                customerDto.getEmail(),
                customerDto.getAddress(),
                customerDto.getCustomerId()
        ) ? "Customer updated successfully" : "Failed to update customer";
    }
    public String deleteCustomer(String Id) throws Exception {
        return CrudUtil.execute("DELETE FROM Customer WHERE Customer_Id=?",
                Id)? "Successfully Delete":"Delete Fail";
    }
    public ArrayList<CustomerDto> getAllCustomers() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rst = statement.executeQuery();
        ArrayList<CustomerDto> customers = new ArrayList<>();
        while(rst.next()){
            CustomerDto CustomerDto = new CustomerDto(rst.getString("Customer_Id"), rst.getString("Customer_Name"
            ), rst.getString("Contact_Number"), rst.getString("Email"), rst.getString("Address"));
            customers.add(CustomerDto);
//            System.out.println(CustomerDto);
        }
        return customers;
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("select Customer_Id from Customer");
        ArrayList<String> list=new ArrayList<>();
        while(rst.next()){
           String id=rst.getString(1);
           list.add(id);
        }
        return list;
    }

    public String findNameByContact(String selectedCustomerContact) throws SQLException, ClassNotFoundException {
        //System.out.println("hiContact");
        ResultSet rst = CrudUtil.execute(

                "SELECT Customer_Name FROM Customer WHERE Contact_Number=?",
                selectedCustomerContact
        );
        return rst.next() ? rst.getString("Customer_Name") : "No customer found for the provided contact number.";
    }
}
