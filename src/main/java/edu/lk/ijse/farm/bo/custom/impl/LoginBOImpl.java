package edu.lk.ijse.farm.bo.custom.impl;

import edu.lk.ijse.farm.bo.custom.LoginBO;
import edu.lk.ijse.farm.dao.DAOFactory;
import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    private final LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOType.LOGINDAO);
    @Override
    public boolean login(String inputName, String inputPassword) throws SQLException, ClassNotFoundException {
        boolean isLogin=loginDAO.login(inputName,inputPassword);
        return isLogin;
    }

    @Override
    public boolean updatePassword(String inputName, String inputPassword) throws SQLException, ClassNotFoundException {
        boolean isUpdatePassword=loginDAO.updatePassword(inputName,inputPassword);
        return isUpdatePassword;
    }
}
