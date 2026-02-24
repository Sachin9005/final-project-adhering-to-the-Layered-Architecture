package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.CarOwnerBO;
import lk.ijse.carrentn.dao.custom.CarOwnerDAO;
import lk.ijse.carrentn.dao.custom.impl.CarOwnerDAOImpl;
import lk.ijse.carrentn.dto.CarOwnerDTO;

import java.sql.SQLException;
import java.util.List;

public class CarOwnerBOimpl implements CarOwnerBO {
    CarOwnerDAO carOwnerDAO =  new CarOwnerDAOImpl();

    @Override
    public boolean saveOwner(CarOwnerDTO carOwnerDTO) throws SQLException {
        return carOwnerDAO.save(carOwnerDTO);
    }

    @Override
    public boolean updateOwner(CarOwnerDTO carOwnerDTO) throws SQLException {
        return carOwnerDAO.update(carOwnerDTO);
    }

    @Override
    public boolean deleteOwner(String id) throws SQLException {
        return carOwnerDAO.delete(id);
    }

    @Override
    public CarOwnerDTO searchOwner(String id) throws SQLException {
        return carOwnerDAO.search(id);
    }

    @Override
    public List<CarOwnerDTO> getAllOwners() throws SQLException {
        return carOwnerDAO.getAll();
    }

    @Override
    public String searchOwnerId(String name) throws SQLException {
        return carOwnerDAO.searchId(name);
    }
}
