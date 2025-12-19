package lk.ijse.carrentn.model;
import lk.ijse.carrentn.dto.VehicleDTO;
import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {

    public boolean save(VehicleDTO vehicleDTO) throws SQLException {

        boolean result = CrudUtil.execute("INSERT INTO Vehicle (owner_id, model, manufacturer, type, rate_per_day, ownership_percentage) VALUES (?,?,?,?,?,?)",
                vehicleDTO.getOwner_id(),vehicleDTO.getModel(),vehicleDTO.getManufacturer(),vehicleDTO.getType(),vehicleDTO.getRate_per_day(),vehicleDTO.getOwnership_percentage());
        return result;
    }

    public boolean update(VehicleDTO vehicleDTO) throws SQLException {

        boolean result = CrudUtil.execute("UPDATE Vehicle owner_id = ?, model = ?, manufacturer = ?, type = ? , rate_per_day = ? , ownership_percentage = ?  WHERE vehicle_id  = ?",
                vehicleDTO.getOwner_id(),vehicleDTO.getModel(),vehicleDTO.getManufacturer(),vehicleDTO.getType(),vehicleDTO.getRate_per_day(),vehicleDTO.getOwnership_percentage(),vehicleDTO.getVehicle_id());
        return result;
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental WHERE vehicle_id = ?", id);
        boolean result = CrudUtil.execute("DELETE FROM Vehicle WHERE vehicle_id = ?",id);
        return result;
    }

    public VehicleDTO search(String id) throws SQLException {
        VehicleDTO vehicleDTO = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle WHERE vehicle_id = ?",id);

        if (result.next()) {
            int vehiclId = result.getInt("vehicle_id");
            int vehicleOwnerId = result.getInt("owner_id");
            String vehicleModel = result.getString("model");
            String  vehicleManufac= result.getString("manufacturer");
            String vehicleType = result.getString("type");
            double vehicleRate = result.getDouble("rate_per_day");
            double vehicleOwnerPrec = result.getDouble("ownership_percentage");


            vehicleDTO = new VehicleDTO(vehiclId,vehicleOwnerId,vehicleModel,vehicleManufac,vehicleType,vehicleRate,vehicleOwnerPrec);
        }
        return vehicleDTO;
    }

    public List<VehicleDTO> getAllVehicles() throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Vehicle ORDER BY vehicle_id DESC";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        ArrayList<VehicleDTO> vehicleList = new ArrayList();

        while(rs.next()) {
            VehicleDTO vehicleDTO = new VehicleDTO(
                    rs.getInt("vehicle_id"),
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

    public List<String> getAllVehicleModels() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT model FROM Vehicle ORDER BY vehicle_id DESC");

        ArrayList<String> vehicleModelList = new ArrayList();

        while(rs.next()) {
            vehicleModelList.add(rs.getString("model"));
        }
        return vehicleModelList;
    }

    public String searchId(String model) {
        String id = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT vehicle_id FROM Vehicle WHERE model = ?", model);


            if (result.next()) {
                int vehicleId = result.getInt("vehicle_id");
                id = String.valueOf(vehicleId);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id ;
    }

    public double searchPrioce(String id) throws SQLException {
        double ratePerDay =0.0;
        try {
            ResultSet result = CrudUtil.execute("SELECT rate_per_day FROM Vehicle WHERE vehicle_id = ?", id);


            if (result.next()) {
                double vehicleRate = result.getDouble("rate_per_day");
                ratePerDay = vehicleRate;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ratePerDay ;
    }


}
