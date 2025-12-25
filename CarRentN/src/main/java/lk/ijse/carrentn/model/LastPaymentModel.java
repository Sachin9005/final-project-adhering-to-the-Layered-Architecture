package lk.ijse.carrentn.model;

import lk.ijse.carrentn.dto.LastPaymentDTO;
import lk.ijse.carrentn.util.CrudUtil;

import java.sql.SQLException;

public class LastPaymentModel {

    public boolean saveFullPayment(LastPaymentDTO lastPaymentDTO)throws SQLException {
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO last_Payment (first_payment_id, rental_id, late_days, balance_payment, fine_payment,last_payment , last_payment_date) VALUES (?,?,?,?,?,?,?)",
                lastPaymentDTO.getFirst_payment_id(),
                lastPaymentDTO.getRental_id(),
                lastPaymentDTO.getLate_days(),
                lastPaymentDTO.getBalance_payment(),
                lastPaymentDTO.getFine_payment(),
                lastPaymentDTO.getLast_payment(),
                lastPaymentDTO.getLast_payment_date());
        return isSaved;
    }


}
