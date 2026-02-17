package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.dto.TM.DriverTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DriverDAO {
    public boolean save(DriverDTO driverDTO) throws SQLException;
    public boolean update(DriverDTO driverDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public DriverDTO search(String id) throws SQLException;
    public List<DriverDTO> getAllDrivers() throws SQLException;
    public String searchId(String name);
    public double searchRate(String id);
    public List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException;
    public List<String> getAvailableDriverNames(LocalDate startDate) throws SQLException;
    public int getDriverCount() throws SQLException;
    public int getAvailableDriverCount() throws SQLException;
}
