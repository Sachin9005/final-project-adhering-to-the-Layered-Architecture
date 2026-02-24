package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.RentalBO;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.dao.custom.RentalDAO;
import lk.ijse.carrentn.dao.custom.RentalDiscountDAO;
import lk.ijse.carrentn.dao.custom.impl.DiscountDAOImpl;
import lk.ijse.carrentn.dao.custom.impl.FirstPaymentDAOImpl;
import lk.ijse.carrentn.dao.custom.impl.RentalDAOImpl;
import lk.ijse.carrentn.dao.custom.impl.RentalDiscountDAOImpl;
import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.dto.RentalDiscountDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RentalBOimpl implements RentalBO {
    RentalDAO rentalDAO =  new RentalDAOImpl();
    FirstPaymentDAO firstPaymentDAO = new FirstPaymentDAOImpl();
    DiscountDAO discountDAO = new DiscountDAOImpl();
    RentalDiscountDAO rentalDiscountDAO = new RentalDiscountDAOImpl();
    @Override
    public int getGenerateRentalId(RentalDTO rentalDTO) throws SQLException {
        return rentalDAO.getSaveId(rentalDTO);
    }

    @Override
    public RentalDTO searchRent(String id) throws SQLException {
        return rentalDAO.search(id);
    }

    @Override
    public List<RentalDTO> getAllRents() throws SQLException {
        return rentalDAO.getAll();
    }

    @Override
    public String getSaveLastRentalId() throws SQLException {
        return rentalDAO.getSaveLastRentalId();
    }

    @Override
    public void printBasePayInvoice(int basePaymentId) throws JRException, SQLException {
        rentalDAO.printBasePayInvoice(basePaymentId);
    }

    @Override
    public boolean deleteRent(String id) throws SQLException {
        return rentalDAO.delete(id);
    }

    @Override
    public boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO) throws SQLException {
        return firstPaymentDAO.save(firstPaymentDTO);
    }

    @Override
    public boolean saveRent(RentalDTO rentalDTO, double basPay, double totalPay, Integer discountId) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            int rentalId = getGenerateRentalId(rentalDTO);

            if (rentalId == 0) {
                throw new Exception("Failed to generate rental id");
            }
            boolean isBasePaySaved = saveFirstPayment(new FirstPaymentDTO(rentalId, basPay, totalPay));

            boolean isDiscountSaved = true;
            if (discountId != null) {
                double disAmount = (totalPay * discountDAO.search(String.valueOf(discountId)).getPercentage())/100;
                isDiscountSaved = saveRentalDiscount(new RentalDiscountDTO(rentalId, discountId, disAmount));
            }

            if (!isBasePaySaved && !isDiscountSaved) {
                throw new Exception("Something went Wrong");
            }
            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean saveRentalDiscount(RentalDiscountDTO rentalDiscountDTO) throws SQLException {
        return rentalDiscountDAO.save(rentalDiscountDTO);
    }

    @Override
    public Map<String, Integer> getMonthlyRentStats(int year, int month) throws SQLException {
        return rentalDAO.getMonthlyRentStats(year, month);
    }
}
