package lk.ijse.pharmacy_prescription_management_system.model;

import lk.ijse.pharmacy_prescription_management_system.DTO.PatientsDTO;
import lk.ijse.pharmacy_prescription_management_system.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientsModel {
    public boolean save(PatientsDTO patientsDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO patients(name,contact) VALUES (?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,patientsDTO.getName());
        ps.setString(2,patientsDTO.getContact());
        return ps.executeUpdate() > 0 ;
    }

    public boolean update(PatientsDTO patientsDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE patients SET name=?,contact=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,patientsDTO.getName());
        ps.setString(2,patientsDTO.getContact());
        ps.setInt(3,patientsDTO.getId());
        return ps.executeUpdate()>0;
    }
    public boolean delete(String id)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "DELETE FROM patients WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,Integer.parseInt(id));
        return  ps.executeUpdate()>0 ;
    }
    public PatientsDTO search(String patientID)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PatientsDTO patientsDTO = null;
        String sql = "SELECT * FROM patients WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,Integer.parseInt(patientID));
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            patientsDTO = new PatientsDTO(
                    rs.getString("name"),
                    rs.getString("contact")
            );
        }
        return patientsDTO;
    }
}
