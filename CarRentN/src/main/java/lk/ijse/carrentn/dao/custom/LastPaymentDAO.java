package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import lk.ijse.carrentn.entity.LastPayment;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface LastPaymentDAO extends CrudDAO <LastPayment>, SuperDAO {
    String getSaveLastPaymentId()throws SQLException;
    void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException;

}
