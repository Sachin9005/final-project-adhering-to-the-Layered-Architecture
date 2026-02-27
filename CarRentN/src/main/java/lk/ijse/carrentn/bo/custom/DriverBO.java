package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.dto.TM.DriverTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverBO extends SuperBO {
    boolean saveDriver(DriverDTO driverDTO) throws SQLException;
    boolean updateDriver(DriverDTO driverDTO) throws SQLException;
    boolean deleteDriver(String id) throws SQLException;
    DriverDTO searchDriver(String id) throws SQLException;
    List<DriverDTO> getAllDrivers() throws SQLException;
    String searchDriverId(String name);
    List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException;
}
