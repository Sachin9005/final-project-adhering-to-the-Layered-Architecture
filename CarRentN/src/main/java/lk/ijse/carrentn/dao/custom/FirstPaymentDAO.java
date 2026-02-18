package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.FirstPaymentDTO;

import java.sql.SQLException;

public interface FirstPaymentDAO {
    public boolean saveBasePayment(int rentId , double basePay , double totalPay)throws Exception;
    public FirstPaymentDTO getFirstPayment(int rentId)throws SQLException;
    public double searchTotalPay(String rentalId)throws Exception;
    public boolean deleteFirstPayment(int rentId)throws Exception;
}
