package lk.ijse.pharmacy_prescription_management_system.model;

import lk.ijse.pharmacy_prescription_management_system.DTO.MedicinesDTO;
import lk.ijse.pharmacy_prescription_management_system.DTO.PrescriptionsDTO;
import lk.ijse.pharmacy_prescription_management_system.db.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
