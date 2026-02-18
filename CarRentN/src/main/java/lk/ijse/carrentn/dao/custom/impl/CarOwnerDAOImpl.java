package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.CarOwnerDAO;
import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.dto.CarOwnerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarOwnerDAOImpl implements CarOwnerDAO {
    public boolean save(CarOwnerDTO carOwnerDTO) throws SQLException {
        return CrudUtil.execute("INSERT INTO CarOwner (name,phone, bank_account) VALUES (?,?,?)", carOwnerDTO.getName(),carOwnerDTO.getPhone(),carOwnerDTO.getBank_account());
    }

    public boolean update(CarOwnerDTO carOwnerDTO) throws SQLException {
        return CrudUtil.execute("UPDATE CarOwner SET name = ?, phone = ?, bank_account = ? WHERE owner_id  = ?", carOwnerDTO.getName(), carOwnerDTO.getPhone(), carOwnerDTO.getBank_account(),carOwnerDTO.getOwner_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Vehicle WHERE owner_id = ?", id);
        return CrudUtil.execute("DELETE FROM CarOwner WHERE owner_id = ?",id);
    }

    public CarOwnerDTO search(String id) throws SQLException {
        CarOwnerDTO carOwnerDTO = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM CarOwner WHERE owner_id = ?",id);

        if (result.next()) {
            int carOwnerID = result.getInt("owner_id");
            String carOwnerName = result.getString("name");
            String carOwnerPhoneNumber = result.getString("phone");
            String carOwnerBankAccount = result.getString("bank_account");

            carOwnerDTO = new CarOwnerDTO(carOwnerID,carOwnerName,carOwnerPhoneNumber,carOwnerBankAccount);
        }
        return carOwnerDTO;
    }

    public List<CarOwnerDTO> getAll() throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM CarOwner ORDER BY owner_id DESC";

        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        ArrayList<CarOwnerDTO> carOwnerList = new ArrayList<>();

        while(rs.next()) {
            CarOwnerDTO carOwnerDTO = new CarOwnerDTO(
                    rs.getInt("owner_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("bank_account"));

            carOwnerList.add(carOwnerDTO);
        }
        return carOwnerList;
    }

    public List<String> getAllOwnersIds() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM CarOwner ORDER BY owner_id DESC");
        ArrayList<String> carOwnerNameList = new ArrayList<>();
        while(rs.next()) {
            carOwnerNameList.add(rs.getString("name"));
        }
        return carOwnerNameList;
    }

    public String searchId(String name) throws SQLException {
        ResultSet result = CrudUtil.execute("SELECT owner_id FROM CarOwner WHERE name = ?",name);
        String id = null;

        if (result.next()) {
            int carOwnerID = result.getInt("owner_id");
            id = String.valueOf(carOwnerID);
        }
        return id ;
    }
}
