package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.dto.FirstPaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FirstPaymentDAOImpl implements FirstPaymentDAO {
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
            throw new Exception("Something went Wrong");

        }
        return true;
    }

    public FirstPaymentDTO getFirstPayment(int rentId)throws SQLException {
        FirstPaymentDTO firstPaymentDTO = null ;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM First_Payment WHERE rental_id = ?", rentId);
        if (resultSet.next()) {
            int firstPayId = resultSet.getInt("first_payment_id");
            int rentalId = resultSet.getInt("rental_id");
            double basePay = resultSet.getDouble("base_payment");
            double finalPay = resultSet.getDouble("final_payment");
            LocalDate basePaymentDate = resultSet.getDate("base_payment_date").toLocalDate();

            firstPaymentDTO = new FirstPaymentDTO(firstPayId,rentalId,basePay,finalPay,basePaymentDate);
        }
        return firstPaymentDTO ;
    }

    public boolean deleteFirstPayment(int rentId)throws Exception{
        return CrudUtil.execute("DELETE FROM First_Payment WHERE rental_id = ?", rentId);
    }

    public double searchTotalPay(String rentalId)throws Exception{
        double totalPay = 0.0;
        ResultSet resu = CrudUtil.execute("SELECT base_payment FROM First_Payment WHERE rental_id = ?",rentalId);
        if (resu.next()) {
            totalPay = resu.getDouble("base_payment");
        }
        return totalPay;
    }
}
