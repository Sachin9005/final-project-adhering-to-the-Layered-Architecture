package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.Vehicle;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Vehicle>, SuperDAO {
    Vehicle searchVehicle(String id);
    List<Vehicle> getAvailableVehicles(LocalDate startDate) throws SQLException;
    int getVehiclesCount(String type) throws SQLException;
    int getAvailableVehiclesCount(String type) throws SQLException;
}
