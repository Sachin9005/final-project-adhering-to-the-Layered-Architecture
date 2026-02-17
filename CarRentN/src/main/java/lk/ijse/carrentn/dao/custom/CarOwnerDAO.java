package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.CarOwnerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CarOwnerDAO {
    public boolean save(CarOwnerDTO carOwnerDTO) throws SQLException;
    public boolean update(CarOwnerDTO carOwnerDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public CarOwnerDTO search(String id) throws SQLException;
    public List<CarOwnerDTO> getAllOwners() throws SQLException;
    public List<String> getAllOwnersIds() throws SQLException;
    public String searchId(String name) throws SQLException;
}
