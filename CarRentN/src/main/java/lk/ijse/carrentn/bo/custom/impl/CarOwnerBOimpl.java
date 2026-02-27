package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.CarOwnerBO;
import lk.ijse.carrentn.dao.DAOFactory;
import lk.ijse.carrentn.dao.custom.CarOwnerDAO;
import lk.ijse.carrentn.dao.custom.impl.CarOwnerDAOImpl;
import lk.ijse.carrentn.dto.CarOwnerDTO;
import lk.ijse.carrentn.entity.CarOwner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarOwnerBOimpl implements CarOwnerBO {
    private final CarOwnerDAO carOwnerDAO = (CarOwnerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CAR_OWNER);

    @Override
    public boolean saveOwner(CarOwnerDTO carOwnerDTO) throws SQLException {
        CarOwner carOwner = new CarOwner(carOwnerDTO.getName(),carOwnerDTO.getPhone(),carOwnerDTO.getBank_account());
        return carOwnerDAO.save(carOwner);
    }

    @Override
    public boolean updateOwner(CarOwnerDTO carOwnerDTO) throws SQLException {
        CarOwner carOwner = new CarOwner(carOwnerDTO.getOwner_id(),carOwnerDTO.getName(),carOwnerDTO.getPhone(),carOwnerDTO.getBank_account());
        return carOwnerDAO.update(carOwner);
    }

    @Override
    public boolean deleteOwner(String id) throws SQLException {
        return carOwnerDAO.delete(id);
    }

    @Override
    public CarOwnerDTO searchOwner(String id) throws SQLException {
        CarOwner carOwner =  carOwnerDAO.search(id);
        return new CarOwnerDTO(carOwner.getOwner_id(),carOwner.getName(),carOwner.getPhone(),carOwner.getBank_account());
    }

    @Override
    public List<CarOwnerDTO> getAllOwners() throws SQLException {
        List<CarOwner> carOwners =  carOwnerDAO.getAll();
        List<CarOwnerDTO> carOwnerDTOs = new ArrayList<>();
        for (CarOwner carOwner : carOwners) {
            carOwnerDTOs.add(new CarOwnerDTO(carOwner.getOwner_id(),carOwner.getName(),carOwner.getPhone(),carOwner.getBank_account()));
        }
        return carOwnerDTOs;
    }

    @Override
    public String searchOwnerId(String name) throws SQLException {
        return carOwnerDAO.searchId(name);
    }
}
