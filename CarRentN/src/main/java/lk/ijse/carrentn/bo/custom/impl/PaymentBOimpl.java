package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.PaymentBO;
import lk.ijse.carrentn.dao.DAOFactory;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.dao.custom.LastPaymentDAO;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import lk.ijse.carrentn.entity.FirstPayment;
import lk.ijse.carrentn.entity.LastPayment;
import net.sf.jasperreports.engine.JRException;

import java.math.BigDecimal;
import java.sql.SQLException;

public class PaymentBOimpl implements PaymentBO {

    private final FirstPaymentDAO firstPaymentDAO = (FirstPaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FIRST_PAYMENT);
    private final LastPaymentDAO lastPaymentDAO = (LastPaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LAST_PAYMENT);

    @Override
    public boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO) throws Exception {
        FirstPayment firstPayment = new FirstPayment(firstPaymentDTO.getRental_id(), BigDecimal.valueOf(firstPaymentDTO.getBase_payment()),BigDecimal.valueOf(firstPaymentDTO.getFinal_payment()),firstPaymentDTO.getBase_payment_date());
        return firstPaymentDAO.save(firstPayment);
    }

    @Override
    public FirstPaymentDTO searchFirstPayment(String rentId) throws SQLException {
        FirstPayment firstPayment = firstPaymentDAO.search(rentId);
        return new FirstPaymentDTO(firstPayment.getFirst_payment_id(),firstPayment.getRental_id(),firstPayment.getFinal_payment().doubleValue(),firstPayment.getFinal_payment().doubleValue(),firstPayment.getBase_payment_date());
    }

    @Override
    public boolean deleteFirstPayment(String rentId) throws Exception {
        return firstPaymentDAO.delete(rentId);
    }

    @Override
    public boolean saveLastPayment(LastPaymentDTO lastPaymentDTO) throws SQLException {
        LastPayment lastPayment = new LastPayment(lastPaymentDTO.getFirst_payment_id(),lastPaymentDTO.getRental_id(),lastPaymentDTO.getLate_days(),BigDecimal.valueOf(lastPaymentDTO.getBalance_payment()),BigDecimal.valueOf(lastPaymentDTO.getFine_payment()),BigDecimal.valueOf(lastPaymentDTO.getLast_payment()),lastPaymentDTO.getLast_payment_date());
        return lastPaymentDAO.save(lastPayment);
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
