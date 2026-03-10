package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.DriverDAO;
import lk.ijse.carrentn.entity.Driver;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {
    public boolean save(Driver driver) throws SQLException {

        return CrudUtil.execute("INSERT INTO Driver (name,phone_number, license_number, driver_rate_per_day) VALUES (?,?,?,?)",
                driver.getName(),
                driver.getPhone_number(),
                driver.getLicense_number(),
                driver.getDriver_rate_per_day());
    }

    public boolean update(Driver driver) throws SQLException {

        return CrudUtil.execute("UPDATE Driver SET name = ?, phone_number = ?, license_number = ? , driver_rate_per_day = ? WHERE driver_id  = ?",
                driver.getName(),
                driver.getPhone_number(),
                driver.getLicense_number(),
                driver.getDriver_rate_per_day(),
                driver.getDriver_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental WHERE driver_id = ?", id);
        return CrudUtil.execute("DELETE FROM Driver WHERE driver_id = ?",id);
    }

    public Driver search(String id) throws SQLException {
        Driver driver = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Driver WHERE driver_id = ?",id);

        if (result.next()) {
            int driverId = result.getInt("driver_id");
            String driverName = result.getString("name");
            String driverPhoneNumber = result.getString("phone_number");
            String driverLiceNo = result.getString("license_number");
            BigDecimal driverRatePerDay = result.getBigDecimal("driver_rate_per_day");


            driver = new Driver(driverId, driverName, driverPhoneNumber,driverLiceNo,driverRatePerDay);
        }
        return driver;
    }

    public List<Driver> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Driver ORDER BY driver_id DESC");

        ArrayList<Driver> driverList = new ArrayList<>();

        while(rs.next()) {
            Driver driver = new Driver(
                    rs.getInt("driver_id"),
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("license_number"),
                    rs.getBigDecimal("driver_rate_per_day"));

            driverList.add(driver);
        }
        return driverList;
    }

    public String searchId(String name) {
        String id = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT driver_id FROM Driver WHERE name = ?",name);
            if (result.next()) {
                int driverId = result.getInt("driver_id");
                id = String.valueOf(driverId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id ;
    }

    public List<Driver> getAvailableDrivers(LocalDate startDate) throws SQLException {

        String sql = "SELECT DISTINCT * FROM Driver d LEFT JOIN Rental r ON d.driver_id = r.driver_id AND r.return_date >= ? WHERE r.driver_id IS NULL";

        ResultSet rs = CrudUtil.execute(sql, Date.valueOf(startDate));

        List<Driver> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Driver(
                    rs.getInt("driver_id"),
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("license_number"),
                    rs.getBigDecimal("driver_rate_per_day")));
        }
        return list;
    }
}
