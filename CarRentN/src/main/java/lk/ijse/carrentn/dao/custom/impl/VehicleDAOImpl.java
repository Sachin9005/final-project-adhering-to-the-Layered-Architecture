package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.VehicleDAO;
import lk.ijse.carrentn.entity.Vehicle;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    public boolean save(Vehicle vehicle) throws SQLException {
        return CrudUtil.execute("INSERT INTO Vehicle (owner_id, model, manufacturer, type, rate_per_day, ownership_percentage, vehicle_No) VALUES (?,?,?,?,?,?,?)",
                vehicle.getOwner_id(), vehicle.getModel(), vehicle.getManufacturer(), vehicle.getType(), vehicle.getRate_per_day(), vehicle.getOwnership_percentage(), vehicle.getVehicle_No());
    }

    public boolean update(Vehicle vehicle) throws SQLException {
        return CrudUtil.execute("UPDATE Vehicle SET owner_id = ?, model = ?, manufacturer = ?, type = ? , rate_per_day = ?, ownership_percentage = ? WHERE vehicle_No  = ?",
                vehicle.getOwner_id(), vehicle.getModel(), vehicle.getManufacturer(), vehicle.getType(), vehicle.getRate_per_day(), vehicle.getOwnership_percentage(), vehicle.getVehicle_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental WHERE vehicle_id = ?", id);
        return CrudUtil.execute("DELETE FROM Vehicle WHERE vehicle_id = ?",id);
    }

    public Vehicle search(String vehicleNo) throws SQLException {
        Vehicle vehicle = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle WHERE vehicle_No = ?", vehicleNo);

        if (result.next()) {
            int vehiclId = result.getInt("vehicle_id");
            int vehicleOwnerId = result.getInt("owner_id");
            String vehicleModel = result.getString("model");
            String  vehicleManu= result.getString("manufacturer");
            String vehicleType = result.getString("type");
            BigDecimal vehicleRate = result.getBigDecimal("rate_per_day");
            BigDecimal vehicleOwnerPrec = result.getBigDecimal("ownership_percentage");
            String vehicleNum = result.getString("vehicle_No");


            vehicle = new Vehicle(vehiclId,vehicleOwnerId,vehicleModel,vehicleManu,vehicleType,vehicleRate,vehicleOwnerPrec,vehicleNum);
        }
        return vehicle;
    }

    public List<Vehicle> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Vehicle ORDER BY vehicle_id DESC");

        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        while(rs.next()) {
            Vehicle vehicle = new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getInt("owner_id"),
                    rs.getString("model"),
                    rs.getString("manufacturer"),
                    rs.getString("type"),
                    rs.getBigDecimal("rate_per_day"),
                    rs.getBigDecimal("ownership_percentage"),
                    rs.getString("vehicle_No"));

            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public Vehicle searchVehicle(String id) {
        Vehicle vehicle = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle WHERE vehicle_id = ?", id);
            if (result.next()) {
                int vehiclId = result.getInt("vehicle_id");
                int vehicleOwnerId = result.getInt("owner_id");
                String vehicleModel = result.getString("model");
                String  vehicleManufac= result.getString("manufacturer");
                String vehicleType = result.getString("type");
                BigDecimal vehicleRate = result.getBigDecimal("rate_per_day");
                BigDecimal vehicleOwnerPrec = result.getBigDecimal("ownership_percentage");
                String vehicleNom = result.getString("vehicle_No");
            vehicle = new Vehicle(vehiclId,vehicleOwnerId,vehicleModel,vehicleManufac,vehicleType,vehicleRate,vehicleOwnerPrec,vehicleNom);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<Vehicle> getAvailableVehicles(LocalDate startDate) throws SQLException {

        String sql = "SELECT DISTINCT * FROM Vehicle v LEFT JOIN Rental r ON v.vehicle_id = r.vehicle_id AND r.return_date >= ? WHERE r.vehicle_id IS NULL";

        ResultSet rs = CrudUtil.execute(sql, Date.valueOf(startDate));

        List<Vehicle> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getInt("owner_id"),
                    rs.getString("model"),
                    rs.getString("manufacturer"),
                    rs.getString("type"),
                    rs.getBigDecimal("rate_per_day"),
                    rs.getBigDecimal("ownership_percentage"),
                    rs.getString("vehicle_No")));
        }
        return list;
    }


    public int getVehiclesCount(String type) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Vehicle WHERE type = ?";
        ResultSet resultSet = CrudUtil.execute(sql,type);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public int getAvailableVehiclesCount(String type) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT v.vehicle_id) FROM Vehicle v LEFT JOIN Rental r ON v.vehicle_id = r.vehicle_id AND r.return_date >= ? WHERE r.vehicle_id IS NULL AND type = ?";

        ResultSet rs = CrudUtil.execute(sql,LocalDate.now(),type);
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;


    }
}
