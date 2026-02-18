package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.LastPaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface LastPaymentDAO {
    public boolean saveFullPayment(LastPaymentDTO lastPaymentDTO)throws SQLException;
    public String getSaveLastPaymentId()throws SQLException;
    public void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException;
    public boolean deleteLastPayment(int rentalId) throws SQLException;
}
