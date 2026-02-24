package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.DriverBO;
import lk.ijse.carrentn.dao.custom.DriverDAO;
import lk.ijse.carrentn.dao.custom.impl.DriverDAOImpl;
import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.dto.TM.DriverTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DriverBOimpl implements DriverBO {

    DriverDAO driverDAO = new DriverDAOImpl();
    @Override
    public boolean saveDriver(DriverDTO driverDTO) throws SQLException {
        return driverDAO.save(driverDTO);
    }

    @Override
    public boolean updateDriver(DriverDTO driverDTO) throws SQLException {
        return driverDAO.update(driverDTO);
    }

    @Override
    public boolean deleteDriver(String id) throws SQLException {
        return driverDAO.delete(id);
    }

    @Override
    public DriverDTO searchDriver(String id) throws SQLException {
        return driverDAO.search(id);
    }

    @Override
    public List<DriverDTO> getAllDrivers() throws SQLException {
        return driverDAO.getAll();
    }

    @Override
    public String searchDriverId(String name) {
        return driverDAO.searchId(name);
    }

    @Override
    public List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException {
        return driverDAO.getAvailableDrivers(startDate);
    }
}
