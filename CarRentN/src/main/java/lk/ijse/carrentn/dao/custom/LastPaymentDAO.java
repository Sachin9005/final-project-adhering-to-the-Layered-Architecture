package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface LastPaymentDAO extends CrudDAO <LastPaymentDTO>{
    String getSaveLastPaymentId()throws SQLException;
    void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException;

}
