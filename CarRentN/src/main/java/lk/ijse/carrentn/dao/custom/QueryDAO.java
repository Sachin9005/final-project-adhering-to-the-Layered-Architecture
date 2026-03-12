package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.dto.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<CustomDTO> getOrderDetails() throws SQLException;

}