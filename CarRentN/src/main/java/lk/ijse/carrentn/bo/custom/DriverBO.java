package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.viwe.tdm.DriverTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverBO extends SuperBO {
    public boolean saveDriver(DriverDTO driverDTO) throws SQLException;
    public boolean updateDriver(DriverDTO driverDTO) throws SQLException;
    public boolean deleteDriver(String id) throws SQLException;
    public DriverDTO searchDriver(String id) throws SQLException;
    public List<DriverDTO> getAllDrivers() throws SQLException;
    public String searchDriverId(String name);
    public List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException;
}
