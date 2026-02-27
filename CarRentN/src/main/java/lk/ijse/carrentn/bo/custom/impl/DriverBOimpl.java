package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.DriverBO;
import lk.ijse.carrentn.dao.DAOFactory;
import lk.ijse.carrentn.dao.custom.DriverDAO;
import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.dto.TM.DriverTM;
import lk.ijse.carrentn.entity.Driver;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverBOimpl implements DriverBO {

    private final DriverDAO driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    @Override
    public boolean saveDriver(DriverDTO driverDTO) throws SQLException {
        Driver driver = new Driver(driverDTO.getName(),driverDTO.getPhone_number(),driverDTO.getLicense_number(), BigDecimal.valueOf(driverDTO.getDriver_rate_per_day()));
        return driverDAO.save(driver);
    }

    @Override
    public boolean updateDriver(DriverDTO driverDTO) throws SQLException {
        Driver driver = new Driver(driverDTO.getDriver_id(),driverDTO.getName(),driverDTO.getPhone_number(),driverDTO.getLicense_number(), BigDecimal.valueOf(driverDTO.getDriver_rate_per_day()));
        return driverDAO.update(driver);
    }

    @Override
    public boolean deleteDriver(String id) throws SQLException {
        return driverDAO.delete(id);
    }

    @Override
    public DriverDTO searchDriver(String id) throws SQLException {
        Driver driver = driverDAO.search(id);
        return new DriverDTO(driver.getDriver_id(),driver.getName(),driver.getPhone_number(),driver.getLicense_number(),driver.getDriver_rate_per_day().doubleValue());
    }

    @Override
    public List<DriverDTO> getAllDrivers() throws SQLException {
        List<Driver> drivers = driverDAO.getAll();
        List<DriverDTO> driverDTOs = new ArrayList<>();
        for (Driver driver : drivers) {
            driverDTOs.add(new DriverDTO(driver.getDriver_id(),driver.getName(),driver.getPhone_number(),driver.getLicense_number(),driver.getDriver_rate_per_day().doubleValue()));
        }
        return driverDTOs;
    }

    @Override
    public String searchDriverId(String name) {
        return driverDAO.searchId(name);
    }

    @Override
    public List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException {
        List<Driver> drivers = driverDAO.getAvailableDrivers(startDate);
        List<DriverTM> driverTMs = new ArrayList<>();
        for (Driver driver : drivers) {
            driverTMs.add(new DriverTM(driver.getDriver_id(),driver.getName(),driver.getPhone_number(),driver.getDriver_rate_per_day().doubleValue()));
        }
        return driverTMs;
    }
}
