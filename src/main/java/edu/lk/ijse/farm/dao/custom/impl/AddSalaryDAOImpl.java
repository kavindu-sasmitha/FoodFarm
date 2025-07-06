package edu.lk.ijse.farm.dao.custom.impl;

import edu.lk.ijse.farm.dao.SQlUtil;
import edu.lk.ijse.farm.dao.custom.AddSalaryDAO;
import edu.lk.ijse.farm.entity.AddSalaryEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddSalaryDAOImpl implements AddSalaryDAO {
    @Override
    public List<AddSalaryEntity> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public Optional<AddSalaryEntity> getById(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public boolean save(AddSalaryEntity addSalaryEntity) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("INSERT INTO addSalary (position,daily_salary) VALUES (?, ?)",
                addSalaryEntity.getPosition(),
                addSalaryEntity.getDailySalary());
    }

    @Override
    public boolean update(AddSalaryEntity addSalaryEntity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQlUtil.execute("DELETE FROM addSalary WHERE position = ?",id);
    }

    @Override
    public String getNextID() throws SQLException {
        return "";
    }

    @Override
    public List<AddSalaryEntity> search(String keyword) throws SQLException {
        return List.of();
    }

    @Override
    public ArrayList<AddSalaryEntity> getAddSalaryByPosition(String position) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQlUtil.execute("SELECT * FROM addSalary WHERE position = ?",position);
        ArrayList<AddSalaryEntity> list = new ArrayList<>();
        while (rs.next()) {
            AddSalaryEntity addSalaryEntity = new AddSalaryEntity(
                    rs.getString(1),
                    rs.getDouble(2)
            );
            list.add(addSalaryEntity);
        }
        return list;
    }
}
