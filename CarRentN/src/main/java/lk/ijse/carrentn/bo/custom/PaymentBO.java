package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO)throws Exception;
    FirstPaymentDTO searchFirstPayment(String rentId)throws SQLException;
    boolean deleteFirstPayment(String rentId)throws Exception;
    boolean saveLastPayment(LastPaymentDTO lastPaymentDTO)throws SQLException;
    String getSaveLastPaymentId()throws SQLException;
    boolean deleteLastPayment(String rentalId) throws SQLException;
    void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException;
}
