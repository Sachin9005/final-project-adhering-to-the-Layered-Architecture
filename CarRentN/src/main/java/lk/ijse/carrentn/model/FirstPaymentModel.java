package lk.ijse.carrentn.model;

import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FirstPaymentModel {
    public boolean saveBasePayment(int rentId , double basePay , double totalPay)throws Exception{
        boolean isSaved = CrudUtil.execute(
                    "INSERT INTO First_Payment (rental_id, base_payment, final_payment, base_payment_date) VALUES (?,?,?,?)",
                    rentId,
                    basePay,
                    totalPay,
                    LocalDate.now().toString());

            if (isSaved) {
                System.out.println("Base Payment Saved Successfully");
            } else {
                throw new Exception("Somethin went Wrong");

            }
        return true;
    }
    public FirstPaymentDTO getFirstPayment(String rentId)throws Exception{
        FirstPaymentDTO firstPaymentDTO = null ;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM First_Payment WHERE rental_id = ?", rentId);

        if (resultSet.next()) {
            int rentalId = resultSet.getInt("rental_id");
            double basePay = resultSet.getDouble("base_payment");
            double finalPay = resultSet.getDouble("final_payment");
            LocalDate basePaymentDate = resultSet.getDate("base_payment_date").toLocalDate();

            firstPaymentDTO = new FirstPaymentDTO(rentalId,basePay,finalPay,basePaymentDate);
        }
        return firstPaymentDTO ;
    }
    
}
