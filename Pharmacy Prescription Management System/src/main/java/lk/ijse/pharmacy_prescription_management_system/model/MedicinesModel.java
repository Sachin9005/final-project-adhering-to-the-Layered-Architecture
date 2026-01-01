package lk.ijse.pharmacy_prescription_management_system.model;

import lk.ijse.pharmacy_prescription_management_system.DTO.MedicinesDTO;
import lk.ijse.pharmacy_prescription_management_system.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicinesModel {
    public boolean saveMedicine(MedicinesDTO medicinesDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO medicines(name,unit_price,stock_quantity) VALUES (?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, medicinesDTO.getName());
        pstmt.setDouble(2 , medicinesDTO.getUnitPrice());
        pstmt.setInt(3, medicinesDTO.getQuantity());
        return pstmt.executeUpdate() > 0;
    }

    public boolean updateMedicine(MedicinesDTO medicinesDTO)throws SQLException {

        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE medicines SET name=?,unit_price=?,stock_quantity=? where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, medicinesDTO.getName());
        pstmt.setDouble(2 , medicinesDTO.getUnitPrice());
        pstmt.setInt(3, medicinesDTO.getQuantity());
        pstmt.setInt(4, medicinesDTO.getId());
        return pstmt.executeUpdate() > 0;
    }

    public boolean deleteMedicine(String medicineID)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM medicines where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(medicineID));
        return pstmt.executeUpdate() > 0;
    }

    public MedicinesDTO searchMedicine(String medicineID)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        MedicinesDTO medicinesDTO = null;
        String sql = "SELECT * FROM medicines where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,Integer.parseInt(medicineID));
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            medicinesDTO = new MedicinesDTO(
                    rs.getString("name"),
                    rs.getDouble("unit_price"),
                    rs.getInt("stock_quantity")
            );
        }
        return medicinesDTO;
    }
    public List<MedicinesDTO> getAllMedicines()throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM medicines";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<MedicinesDTO> medicinesDTOList = new ArrayList<>();
        if (rs.next()) {
            medicinesDTOList.add(new MedicinesDTO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("unit_price"),
                    rs.getInt("stock_quantity")
            ));
        }
        return medicinesDTOList;
    }
}
