package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.dto.TM.DriverTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverDAO extends CrudDAO<DriverDTO> {
    public String searchId(String name);
    public List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException;
}
