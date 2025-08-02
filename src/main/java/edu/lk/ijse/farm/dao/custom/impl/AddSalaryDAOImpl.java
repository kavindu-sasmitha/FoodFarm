package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.AddSalaryDAO;
import edu.lk.ijse.farm.entity.AddSalaryEntity;
import edu.lk.ijse.farm.entity.CustomerEntity;
import edu.lk.ijse.farm.entity.EmployeeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddSalaryDAOImpl implements AddSalaryDAO {

    @Override
    public List<AddSalaryEntity> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQlUtil.execute("SELECT * FROM addSalary");
        List<AddSalaryEntity> list = new ArrayList<>();
        while (rs.next()) {
            AddSalaryEntity salary = new AddSalaryEntity(
                    rs.getString("position"),
                    rs.getDouble("daily_salary")
            );
            list.add(salary);
        }
        return list;
    }

    @Override
    public Optional<AddSalaryEntity> getById(String position) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQlUtil.execute("SELECT * FROM addSalary WHERE position = ?", position);
        if (rs.next()) {
            return Optional.of(new AddSalaryEntity(
                    rs.getString("position"),
                    rs.getDouble("daily_salary")
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean save(AddSalaryEntity addSalaryEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO addSalary (position, daily_salary) VALUES (?, ?)",
                addSalaryEntity.getPosition(),
                addSalaryEntity.getDailySalary());
    }

    @Override
    public boolean update(AddSalaryEntity addSalaryEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("UPDATE addSalary SET daily_salary = ? WHERE position = ?",
                addSalaryEntity.getDailySalary(),
                addSalaryEntity.getPosition());
    }




    @Override
    public boolean delete(String position) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM addSalary WHERE position = ?", position);
    }

    @Override
    public String getNextID() throws SQLException, ClassNotFoundException {
        ResultSet rs = SQlUtil.execute("SELECT position FROM addSalary ORDER BY position DESC LIMIT 1");
        if (rs.next()) {
            String lastId = rs.getString("position");
            // You could apply custom logic here like extracting a number
            return lastId; // or generate new based on format
        }
        return "SAL001";
    }

    @Override
    public List<AddSalaryEntity> search(String keyword) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQlUtil.execute("SELECT * FROM addSalary WHERE position LIKE ? OR daily_salary LIKE ?",
                "%" + keyword + "%",
                "%" + keyword + "%");

        List<AddSalaryEntity> list = new ArrayList<>();
        while (rs.next()) {
            AddSalaryEntity salary = new AddSalaryEntity(
                    rs.getString("position"),
                    rs.getDouble("daily_salary")
            );
            list.add(salary);
        }
        return list;
    }

    @Override
    public ArrayList<AddSalaryEntity> getAddSalaryByPosition(String position) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQlUtil.execute("SELECT * FROM addSalary WHERE position = ?", position);
        ArrayList<AddSalaryEntity> list = new ArrayList<>();
        while (rs.next()) {
            AddSalaryEntity salary = new AddSalaryEntity(
                    rs.getString("position"),
                    rs.getDouble("daily_salary")
            );
            list.add(salary);
        }
        return list;
    }
}
