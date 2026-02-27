package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.Driver;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverDAO extends CrudDAO<Driver> , SuperDAO {
    String searchId(String name);
    List<Driver> getAvailableDrivers(LocalDate startDate) throws SQLException;
}
