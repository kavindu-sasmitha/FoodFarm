package edu.lk.ijse.farm.dao;

import edu.lk.ijse.farm.dao.custom.DAOType;
import edu.lk.ijse.farm.dao.custom.LoginDAO;
import edu.lk.ijse.farm.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory ;
    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public <T extends SuperDAO>T getDAO(DAOType daoType){
        switch(daoType){
            case CUSTOMERDAO :return (T) new CustomerDAOImpl();
            case EMPLOYEEDAO:return (T) new EmployeeDAOImpl();
            case ITEMSDAO:return (T)new ItemsDAOImpl();
            case LOGINDAO:return (T)new LoginDAOImpl();
            case ORDEDAO:return (T)new LoginDAOImpl();
            case ORDERDETAILSDAO:return (T) new OrderDetailsDAOImpl();
            case PLANTDAO:return (T) new PlantDAOImpl();
            case SUPPLIERDAO:return (T) new SupplierDAOImpl();
            case USERDAO:return (T) new UserDAOImpl();
            default :return null;
        }

    }
}
