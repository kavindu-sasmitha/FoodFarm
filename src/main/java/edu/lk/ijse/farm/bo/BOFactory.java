package edu.lk.ijse.farm.bo;

import edu.lk.ijse.farm.bo.custom.CustomerBO;
import edu.lk.ijse.farm.bo.custom.ItemBO;
import edu.lk.ijse.farm.bo.custom.PlantBO;
import edu.lk.ijse.farm.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public<T extends SuperBO>T getBO(BOTypes boType) {
        switch (boType) {
            case SUPPLIERBO:return (T)new SupplierBOImpl();
            case CUSTOMERBO:return (T)new CustomerBOImpl();
            case ITEMBO:return (T)new ItemBOImpl();
            case PLANTBO:return (T)new PlantBOImpl();
            case ORDERBO:return (T)new OrderBOImpl();
            case LOGINBO:return (T)new LoginBOImpl();
            default:return null;
//            case CUSTOMERBO :return (T) new CustomerBOImpl();
//            case ITEMBO:return (T)new ItemBOImpl();
//            case ORDERBO :return(T) new PlaceOrderBOImpl();
//            default :return null;

        }
    }


    }
