package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VehicleDAO {
    public boolean save(VehicleDTO vehicleDTO) throws SQLException;
    public boolean update(VehicleDTO vehicleDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public VehicleDTO search(String vehicleNo) throws SQLException;
    public List<VehicleDTO> getAllVehicles() throws SQLException;
    public String searchId(String vehicleNo);
    public String searchVehicle(String id);
    public double searchPrioce(String id);
    public List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException;
    public List<String> getAvailableVehiclesNo(LocalDate startDate) throws SQLException;
    public int getSUVehiclesCount(String type) throws SQLException;
    public int getAvailableVehiclesCount(String type) throws SQLException;
}
