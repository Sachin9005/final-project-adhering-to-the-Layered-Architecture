package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VehicleDAO extends CrudDAO<VehicleDTO> {
    public VehicleDTO searchVehicle(String id);
    public List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException;
    public int getVehiclesCount(String type) throws SQLException;
    public int getAvailableVehiclesCount(String type) throws SQLException;
}
