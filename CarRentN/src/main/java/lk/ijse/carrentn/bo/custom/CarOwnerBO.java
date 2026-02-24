package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.dto.CarOwnerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CarOwnerBO {
    boolean saveOwner(CarOwnerDTO carOwnerDTO) throws SQLException;
    boolean updateOwner(CarOwnerDTO carOwnerDTO) throws SQLException;
    boolean deleteOwner(String id) throws SQLException;
    CarOwnerDTO searchOwner(String id) throws SQLException;
    List<CarOwnerDTO> getAllOwners() throws SQLException;
    String searchOwnerId(String name) throws SQLException;
}
