package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.custom.QueryDAO;
import lk.ijse.carrentn.dto.CustomDTO;

import java.sql.SQLException;
import java.util.List;

public class QueryDAOimpl implements QueryDAO {
    @Override
    public List<CustomDTO> getOrderDetails() throws SQLException {
        return List.of();
    }
}
