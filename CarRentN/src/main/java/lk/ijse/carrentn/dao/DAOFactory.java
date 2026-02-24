package lk.ijse.carrentn.dao;

import lk.ijse.carrentn.dao.custom.CarOwnerDAO;
import lk.ijse.carrentn.dao.custom.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return  daoFactory ==  null ? new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        CAR_OWNER,CUSTOMER,VEHICLE,DISCOUNT,RENTAL,USER,FIRST_PAYMENT,LAST_PAYMENT,DRIVER,RENTAL_DISCOUNT;
    }

    public SuperDAO getDAO(DAOType daoType){
        switch(daoType){
            case CAR_OWNER:return new CarOwnerDAOImpl();
            case CUSTOMER:return new CustomerDAOImpl();
            case VEHICLE:return new VehicleDAOImpl();
            case DISCOUNT:return new DiscountDAOImpl();
            case RENTAL:return new RentalDAOImpl();
            case USER: return new UserDAOImpl();
            case FIRST_PAYMENT:return new FirstPaymentDAOImpl();
            case LAST_PAYMENT:return new LastPaymentDAOImpl();
            case DRIVER:return new DriverDAOImpl();
            case RENTAL_DISCOUNT: return new RentalDiscountDAOImpl();
            default: return null;
        }
    }

}
