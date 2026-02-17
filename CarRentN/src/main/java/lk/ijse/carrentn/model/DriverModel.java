package lk.ijse.carrentn.model;

import lk.ijse.carrentn.dto.DriverDTO;
import lk.ijse.carrentn.dto.TM.DriverTM;
import lk.ijse.carrentn.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DriverModel {

    public boolean save(DriverDTO driverDTO) throws SQLException {

        return CrudUtil.execute("INSERT INTO Driver (name,phone_number, license_number, driver_rate_per_day) VALUES (?,?,?,?)",
                driverDTO.getName(),
                driverDTO.getPhone_number(),
                driverDTO.getLicense_number(),
                driverDTO.getDriver_rate_per_day());
    }

    public boolean update(DriverDTO driverDTO) throws SQLException {

        return CrudUtil.execute("UPDATE Driver SET name = ?, phone_number = ?, license_number = ? , driver_rate_per_day = ? WHERE driver_id  = ?",
                driverDTO.getName(),
                driverDTO.getPhone_number(),
                driverDTO.getLicense_number(),
                driverDTO.getDriver_rate_per_day(),
                driverDTO.getDriver_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental WHERE driver_id = ?", id);
        return CrudUtil.execute("DELETE FROM Driver WHERE driver_id = ?",id);
    }

    public DriverDTO search(String id) throws SQLException {
        DriverDTO driverDTO = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Driver WHERE driver_id = ?",id);

        if (result.next()) {
            int driverId = result.getInt("driver_id");
            String driverName = result.getString("name");
            String driverPhoneNumber = result.getString("phone_number");
            String driverLiceNo = result.getString("license_number");
            double driverRatePerDay = result.getDouble("driver_rate_per_day");


            driverDTO = new DriverDTO(driverId, driverName, driverPhoneNumber,driverLiceNo,driverRatePerDay);
        }
        return driverDTO;
    }

    public List<DriverDTO> getAllDrivers() throws SQLException {
       ResultSet rs = CrudUtil.execute("SELECT * FROM Driver ORDER BY driver_id DESC");

        ArrayList<DriverDTO> driverDTOList = new ArrayList<>();

        while(rs.next()) {
            DriverDTO driverDTO = new DriverDTO(
                    rs.getInt("driver_id"),
                    rs.getString("name"),
                    rs.getString("phone_number"),
                    rs.getString("license_number"),
                    rs.getDouble("driver_rate_per_day"));

            driverDTOList.add(driverDTO);
        }
        return driverDTOList;
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

    public double searchRate(String id)  {
        double  driverRatePerDay = 0.0;
        try {
            ResultSet result = CrudUtil.execute("SELECT driver_rate_per_day FROM Driver WHERE driver_id = ?", id);
            if (result.next()) {
                driverRatePerDay = result.getInt("driver_rate_per_day");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return driverRatePerDay ;
    }

     public static List<DriverTM> getAvailableDrivers(LocalDate startDate) throws SQLException {

            String sql = "SELECT DISTINCT d.driver_id, d.name, d.phone_number, d.driver_rate_per_day FROM Driver d LEFT JOIN Rental r ON d.driver_id = r.driver_id AND r.return_date >= ? WHERE r.driver_id IS NULL";

            ResultSet rs = CrudUtil.execute(sql,Date.valueOf(startDate));

            List<DriverTM> list = new ArrayList<>();

            while (rs.next()) {
                list.add(new DriverTM(
                        rs.getInt("driver_id"),
                        rs.getString("name"),
                        rs.getString("phone_number"),
                        rs.getDouble("driver_rate_per_day")));
            }
            return list;
        }

    public List<String> getAvailableDriverNames(LocalDate startDate) throws SQLException {

        String sql = "SELECT DISTINCT d.name FROM Driver d LEFT JOIN Rental r ON d.driver_id = r.driver_id AND r.return_date >= ? WHERE r.driver_id IS NULL";

        ResultSet rs = CrudUtil.execute(sql,Date.valueOf(startDate));

        List<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(
                    rs.getString("name")
            );
        }
        return list;
    }
    public int getDriverCount() throws SQLException {
        String sql = "SELECT COUNT(DISTINCT d.driver_id) FROM Driver d";
        ResultSet rs = CrudUtil.execute(sql);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int getAvailableDriverCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Driver d LEFT JOIN Rental r ON d.driver_id = r.driver_id AND r.return_date >= ? WHERE r.driver_id IS NULL";
        ResultSet rs = CrudUtil.execute(sql,LocalDate.now());
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }



}
