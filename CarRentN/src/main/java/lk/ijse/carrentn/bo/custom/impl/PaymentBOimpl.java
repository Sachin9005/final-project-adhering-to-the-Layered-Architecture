package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.PaymentBO;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.dao.custom.LastPaymentDAO;
import lk.ijse.carrentn.dao.custom.impl.FirstPaymentDAOImpl;
import lk.ijse.carrentn.dao.custom.impl.LastPaymentDAOImpl;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public class PaymentBOimpl implements PaymentBO {

    FirstPaymentDAO firstPaymentDAO =  new FirstPaymentDAOImpl();
    LastPaymentDAO lastPaymentDAO = new LastPaymentDAOImpl();

    @Override
    public boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO) throws Exception {
        return firstPaymentDAO.save(firstPaymentDTO);
    }

    @Override
    public FirstPaymentDTO searchFirstPayment(String rentId) throws SQLException {
        return firstPaymentDAO.search(rentId);
    }

    @Override
    public boolean deleteFirstPayment(String rentId) throws Exception {
        return firstPaymentDAO.delete(rentId);
    }

    @Override
    public boolean saveLastPayment(LastPaymentDTO lastPaymentDTO) throws SQLException {
        return lastPaymentDAO.save(lastPaymentDTO);
    }

    @Override
    public String getSaveLastPaymentId() throws SQLException {
        return lastPaymentDAO.getSaveLastPaymentId();
    }

    @Override
    public boolean deleteLastPayment(String rentalId) throws SQLException {
        return lastPaymentDAO.delete(rentalId);
    }

    @Override
    public void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException {
        lastPaymentDAO.printLastPayInvoice(finalPaymentId,vehicleDamage,customerPay);
    }

}
