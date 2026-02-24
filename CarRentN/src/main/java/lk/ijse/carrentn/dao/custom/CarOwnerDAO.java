package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.dto.CarOwnerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CarOwnerDAO extends CrudDAO<CarOwnerDTO> , SuperDAO {
    String searchId(String name) throws SQLException;
}
