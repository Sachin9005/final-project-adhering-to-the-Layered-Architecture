package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VehicleBO extends SuperBO {
    boolean saveVehicle(VehicleDTO vehicleDTO) throws SQLException;
    boolean updateVehicle(VehicleDTO vehicleDTO) throws SQLException;
    boolean deleteVehicle(String id) throws SQLException;
    VehicleDTO searchVehicleNo(String vehicleNo) throws SQLException;
    List<VehicleDTO> getAllVehicle() throws SQLException;
    VehicleDTO searchVehicle(String id);
    List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException;
    int getVehiclesCount(String type) throws SQLException;
    int getAvailableVehiclesCount(String type) throws SQLException;

}
