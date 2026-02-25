package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface PaymentBO extends SuperBO {
    public boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO)throws Exception;
    public FirstPaymentDTO searchFirstPayment(String rentId)throws SQLException;
    public boolean deleteFirstPayment(String rentId)throws Exception;
    public boolean saveLastPayment(LastPaymentDTO lastPaymentDTO)throws SQLException;
    public String getSaveLastPaymentId()throws SQLException;
    public boolean deleteLastPayment(String rentalId) throws SQLException;
    public void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException;
}
