package lk.ijse.carrentn.model;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;
import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.util.CrudUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {

    public boolean save(VehicleDTO vehicleDTO) throws SQLException {

        return CrudUtil.execute("INSERT INTO Vehicle (owner_id, model, manufacturer, type, rate_per_day, ownership_percentage, vehicle_No) VALUES (?,?,?,?,?,?,?)",
                vehicleDTO.getOwner_id(),vehicleDTO.getModel(),vehicleDTO.getManufacturer(),vehicleDTO.getType(),vehicleDTO.getRate_per_day(),vehicleDTO.getOwnership_percentage(),vehicleDTO.getVehicleNo());
    }

    public boolean update(VehicleDTO vehicleDTO) throws SQLException {
        return CrudUtil.execute("UPDATE Vehicle SET owner_id = ?, model = ?, manufacturer = ?, type = ? , rate_per_day = ?, ownership_percentage = ? WHERE vehicle_No  = ?",
                vehicleDTO.getOwner_id(),vehicleDTO.getModel(),vehicleDTO.getManufacturer(),vehicleDTO.getType(),vehicleDTO.getRate_per_day(),vehicleDTO.getOwnership_percentage(),vehicleDTO.getVehicleNo());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental WHERE vehicle_id = ?", id);
        return CrudUtil.execute("DELETE FROM Vehicle WHERE vehicle_id = ?",id);
    }

    public VehicleDTO search(String vehicleNo) throws SQLException {
        VehicleDTO vehicleDTO = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle WHERE vehicle_No = ?", vehicleNo);

        if (result.next()) {
            int vehiclId = result.getInt("vehicle_id");
            int vehicleOwnerId = result.getInt("owner_id");
            String vehicleModel = result.getString("model");
            String  vehicleManufac= result.getString("manufacturer");
            String vehicleType = result.getString("type");
            double vehicleRate = result.getDouble("rate_per_day");
            double vehicleOwnerPrec = result.getDouble("ownership_percentage");
            String vehicleNom = result.getString("vehicle_No");


            vehicleDTO = new VehicleDTO(vehiclId, vehicleNom,vehicleOwnerId,vehicleModel,vehicleManufac,vehicleType,vehicleRate,vehicleOwnerPrec);
        }
        return vehicleDTO;
    }

    public List<VehicleDTO> getAllVehicles() throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Vehicle ORDER BY vehicle_id DESC";
        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        ArrayList<VehicleDTO> vehicleList = new ArrayList<>();

        while(rs.next()) {
            VehicleDTO vehicleDTO = new VehicleDTO(
                    rs.getInt("vehicle_id"),
                    rs.getString("vehicle_No"),
                    rs.getInt("owner_id"),
                    rs.getString("model"),
                    rs.getString("manufacturer"),
                    rs.getString("type"),
                    rs.getDouble("rate_per_day"),
                    rs.getDouble("ownership_percentage"));

            vehicleList.add(vehicleDTO);
        }
        return vehicleList;
    }

    public List<String> getAllVehicleNo() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT vehicle_No FROM Vehicle ORDER BY vehicle_id DESC");

        ArrayList<String> vehicleModelList = new ArrayList<>();

        while(rs.next()) {
            vehicleModelList.add(rs.getString("vehicle_No"));
        }
        return vehicleModelList;
    }

    public String searchId(String vehicleNo) {
        String id = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT vehicle_id FROM Vehicle WHERE vehicle_No = ?", vehicleNo);


            if (result.next()) {
                int vehicleId = result.getInt("vehicle_id");
                id = String.valueOf(vehicleId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id ;
    }

    public String searchVehicle(String id) {
        String vehicleNO = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT vehicle_No FROM Vehicle WHERE vehicle_id = ?", id);
            if (result.next()) {
                String vehicleNo = result.getString("vehicle_No");
                vehicleNO = String.valueOf(vehicleNo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vehicleNO;
    }

    public double searchPrioce(String id){
        double ratePerDay =0.0;
        try {
            ResultSet result = CrudUtil.execute("SELECT rate_per_day FROM Vehicle WHERE vehicle_id = ?", id);


            if (result.next()) {
                ratePerDay = result.getDouble("rate_per_day");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ratePerDay ;
    }

    public List<VehicleTM> getAvailableVehicles(LocalDate startDate) throws SQLException {

        String sql = "SELECT DISTINCT v.vehicle_id,v.model,v.type,v.rate_per_day,v.vehicle_No FROM Vehicle v LEFT JOIN Rental r ON v.vehicle_id = r.vehicle_id AND r.return_date >= ? WHERE r.vehicle_id IS NULL";

        ResultSet rs = CrudUtil.execute(
                sql,
                Date.valueOf(startDate)
        );

        List<VehicleTM> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new VehicleTM(
                    rs.getInt("vehicle_id"),
                    rs.getString("vehicle_No"),
                    rs.getString("model"),
                    rs.getString("type"),
                    rs.getDouble("rate_per_day")
            ));
        }
        return list;
    }
    public List<String> getAvailableVehiclesNo(LocalDate startDate) throws SQLException {

        String sql = "SELECT DISTINCT v.vehicle_No FROM Vehicle v LEFT JOIN Rental r ON v.vehicle_id = r.vehicle_id AND r.return_date >= ? WHERE r.vehicle_id IS NULL";

        ResultSet rs = CrudUtil.execute(sql, Date.valueOf(startDate) );

        List<String> list = new ArrayList<>();

        while (rs.next()) {
            list.add(
                    rs.getString("vehicle_No")
            );
        }
        return list;
    }

}