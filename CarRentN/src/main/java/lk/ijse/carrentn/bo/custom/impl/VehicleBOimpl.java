package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.VehicleBO;
import lk.ijse.carrentn.dao.DAOFactory;
import lk.ijse.carrentn.dao.custom.VehicleDAO;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;
import lk.ijse.carrentn.entity.Vehicle;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOimpl implements VehicleBO {

    private final VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException {
        Vehicle vehicle = new Vehicle(vehicleDTO.getOwner_id(),vehicleDTO.getModel(), vehicleDTO.getManufacturer(), vehicleDTO.getType(), BigDecimal.valueOf(vehicleDTO.getRate_per_day()),BigDecimal.valueOf(vehicleDTO.getOwnership_percentage()),vehicleDTO.getVehicleNo());
        return vehicleDAO.save(vehicle);
    }

    @Override
    public boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException {
        Vehicle vehicle = new Vehicle(vehicleDTO.getVehicle_id(),vehicleDTO.getOwner_id(),vehicleDTO.getModel(), vehicleDTO.getManufacturer(), vehicleDTO.getType(), BigDecimal.valueOf(vehicleDTO.getRate_per_day()),BigDecimal.valueOf(vehicleDTO.getOwnership_percentage()),vehicleDTO.getVehicleNo());
        return vehicleDAO.update(vehicle);
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException {
        return vehicleDAO.delete(id);
    }

    @Override
    public VehicleDTO searchVehicleNo(String vehicleNo) throws SQLException {
        Vehicle vehicle = vehicleDAO.search(vehicleNo);
        return new VehicleDTO(vehicle.getVehicle_id(),vehicle.getVehicle_No(),vehicle.getOwner_id(),vehicle.getModel(),vehicle.getManufacturer(),vehicle.getType(),vehicle.getRate_per_day().doubleValue(),vehicle.getOwnership_percentage().doubleValue());
    }

    @Override
    public List<VehicleDTO> getAllVehicle() throws SQLException {
        List<Vehicle> vehicles =  vehicleDAO.getAll();
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOs.add(new VehicleDTO(vehicle.getVehicle_id(),vehicle.getVehicle_No(),vehicle.getOwner_id(),vehicle.getModel(),vehicle.getManufacturer(),vehicle.getType(),vehicle.getRate_per_day().doubleValue(),vehicle.getOwnership_percentage().doubleValue()));
        }
        return vehicleDTOs;
    }

    @Override
    public VehicleDTO searchVehicle(String id) {
        Vehicle vehicle = vehicleDAO.searchVehicle(id);
        return new VehicleDTO(vehicle.getVehicle_id(),vehicle.getVehicle_No(),vehicle.getOwner_id(),vehicle.getModel(),vehicle.getManufacturer(),vehicle.getType(),vehicle.getRate_per_day().doubleValue(),vehicle.getOwnership_percentage().doubleValue());
    }

    @Override
    public List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException {
        List<Vehicle>  vehicles = vehicleDAO.getAvailableVehicles(startDate);
        List<VehicleTM> vehicleTMS = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleTMS.add(new VehicleTM(vehicle.getVehicle_id(),vehicle.getVehicle_No(),vehicle.getModel(),vehicle.getType(),vehicle.getRate_per_day().doubleValue()));
        }
        return vehicleTMS;
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
