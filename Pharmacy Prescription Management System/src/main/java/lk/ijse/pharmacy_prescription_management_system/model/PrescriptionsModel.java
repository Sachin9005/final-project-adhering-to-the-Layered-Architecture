package lk.ijse.pharmacy_prescription_management_system.model;

import lk.ijse.pharmacy_prescription_management_system.DTO.MedicinesDTO;
import lk.ijse.pharmacy_prescription_management_system.DTO.PrescriptionsDTO;
import lk.ijse.pharmacy_prescription_management_system.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class PrescriptionsModel {
    private final MedicinesModel medicinesModel = new MedicinesModel();

    public boolean savePrescription(PrescriptionsDTO prescriptionsDTO)throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();

        try {
            conn.setAutoCommit(false);
            MedicinesDTO medicinesDTO = medicinesModel.searchMedicine(String.valueOf(prescriptionsDTO.getMedicineId()));

            if (medicinesDTO.getQuantity() <= 0){
                conn.rollback();
               throw new SQLException("Medicine out of quntity");
            }

            MedicinesDTO newMedicinesDTO = new MedicinesDTO(prescriptionsDTO.getMedicineId(),medicinesDTO.getName(),medicinesDTO.getUnitPrice(),(medicinesDTO.getQuantity()-1));
            medicinesModel.updateMedicine(newMedicinesDTO);

            String sql = "INSERT INTO prescriptions(patient_id,medicine_id,prescribed_date) VALUES (?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, prescriptionsDTO.getPatientId());
            pstm.setInt(2, prescriptionsDTO.getMedicineId());
            pstm.setDate(3, Date.valueOf(LocalDate.now()));
            pstm.executeUpdate();
            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public boolean deletePrescription(int prescriptionId,int medicineId )throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            String sql1 = "UPDATE medicines SET stock_quantity=stock_quantity+1  where id=?";
            PreparedStatement pstm1 = conn.prepareStatement(sql1);
            pstm1.setInt(1, medicineId);
            pstm1.executeUpdate();
            String sql2 = "DELETE FROM prescriptions WHERE id = ?";
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            pstm2.setInt(1, prescriptionId);
            pstm2.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return false;
        }finally {
            conn.setAutoCommit(true);
        }
    }

    public PrescriptionsDTO getPrescription(int prescriptionId) throws SQLException {
        Connection conn = DBConnection.getInstance().getConnection();
        PrescriptionsDTO prescriptionsDTO = new PrescriptionsDTO();
        String sql = "SELECT * FROM prescriptions WHERE id = ?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, prescriptionId);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            prescriptionsDTO.setMedicineId(rs.getInt("medicine_id"));
            prescriptionsDTO.setPatientId(rs.getInt("patient_id"));

        }
        return prescriptionsDTO;
    }


}
