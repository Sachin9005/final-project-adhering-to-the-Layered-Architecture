package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.CarOwnerDAO;
import lk.ijse.carrentn.entity.CarOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarOwnerDAOImpl implements CarOwnerDAO {
    public boolean save(CarOwner carOwner) throws SQLException {
        return CrudUtil.execute("INSERT INTO CarOwner (name,phone, bank_account) VALUES (?,?,?)", carOwner.getName(), carOwner.getPhone(), carOwner.getBank_account());
    }

    public boolean update(CarOwner carOwner) throws SQLException {
        return CrudUtil.execute("UPDATE CarOwner SET name = ?, phone = ?, bank_account = ? WHERE owner_id  = ?", carOwner.getName(), carOwner.getPhone(), carOwner.getBank_account(), carOwner.getOwner_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Vehicle WHERE owner_id = ?", id);
        return CrudUtil.execute("DELETE FROM CarOwner WHERE owner_id = ?",id);
    }

    public CarOwner search(String id) throws SQLException {
        CarOwner carOwner = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM CarOwner WHERE owner_id = ?",id);

        if (result.next()) {
            int carOwnerID = result.getInt("owner_id");
            String carOwnerName = result.getString("name");
            String carOwnerPhoneNumber = result.getString("phone");
            String carOwnerBankAccount = result.getString("bank_account");

            carOwner = new CarOwner(carOwnerID,carOwnerName,carOwnerPhoneNumber,carOwnerBankAccount);
        }
        return carOwner;
    }

    public List<CarOwner> getAll() throws SQLException {
        String sql = "SELECT * FROM CarOwner ORDER BY owner_id DESC";
        ResultSet rs = CrudUtil.execute(sql);
        ArrayList<CarOwner> carOwnerList = new ArrayList<>();

        while(rs.next()) {
            CarOwner carOwner = new CarOwner(
                    rs.getInt("owner_id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("bank_account"));

            carOwnerList.add(carOwner);
        }
        return carOwnerList;
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
