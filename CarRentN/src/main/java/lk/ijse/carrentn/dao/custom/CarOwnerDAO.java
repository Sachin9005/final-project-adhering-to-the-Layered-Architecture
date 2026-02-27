package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.CarOwner;

import java.sql.SQLException;

public interface CarOwnerDAO extends CrudDAO<CarOwner> , SuperDAO {
    String searchId(String name) throws SQLException;
}
