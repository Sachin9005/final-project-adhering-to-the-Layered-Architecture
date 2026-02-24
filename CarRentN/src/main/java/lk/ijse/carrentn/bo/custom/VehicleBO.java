package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VehicleBO {
    public boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException;
    public boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException;
    public boolean deleteVehicle(String id) throws SQLException;
    public VehicleDTO searchVehicleNo(String vehicleNo) throws SQLException;
    public List<VehicleDTO> getAllVehicle() throws SQLException;
    public VehicleDTO searchVehicle(String id);
    public List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException;
    public int getVehiclesCount(String type) throws SQLException;
    public int getAvailableVehiclesCount(String type) throws SQLException;

}
