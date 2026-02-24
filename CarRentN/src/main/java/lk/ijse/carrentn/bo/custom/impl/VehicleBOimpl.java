package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.VehicleBO;
import lk.ijse.carrentn.dao.custom.VehicleDAO;
import lk.ijse.carrentn.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class VehicleBOimpl implements VehicleBO {

    VehicleDAO vehicleDAO =  new VehicleDAOImpl();
    @Override
    public boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException {
        return vehicleDAO.save(vehicleDTO);
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException {
        return vehicleDAO.update(vehicleDTO);
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException {
        return vehicleDAO.delete(id);
    }

    @Override
    public VehicleDTO searchVehicleNo(String vehicleNo) throws SQLException {
        return vehicleDAO.search(vehicleNo);
    }

    @Override
    public List<VehicleDTO> getAllVehicle() throws SQLException {
        return vehicleDAO.getAll();
    }

    @Override
    public VehicleDTO searchVehicle(String id) {
        return vehicleDAO.searchVehicle(id);
    }

    @Override
    public List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException {
        return vehicleDAO.getAvailableVehicles(startDate);
    }

    @Override
    public int getVehiclesCount(String type) throws SQLException {
        return vehicleDAO.getVehiclesCount(type);
    }

    @Override
    public int getAvailableVehiclesCount(String type) throws SQLException {
        return vehicleDAO.getAvailableVehiclesCount(type);
    }
}
