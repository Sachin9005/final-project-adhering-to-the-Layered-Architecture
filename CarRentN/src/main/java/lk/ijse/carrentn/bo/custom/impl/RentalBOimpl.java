package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.RentalBO;
import lk.ijse.carrentn.dao.DAOFactory;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.dao.custom.RentalDAO;
import lk.ijse.carrentn.dao.custom.RentalDiscountDAO;
import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.dto.RentalDiscountDTO;
import lk.ijse.carrentn.entity.FirstPayment;
import lk.ijse.carrentn.entity.Rental;
import lk.ijse.carrentn.entity.RentalDiscount;
import net.sf.jasperreports.engine.JRException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RentalBOimpl implements RentalBO {
    private final RentalDAO rentalDAO = (RentalDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RENTAL);
    private final FirstPaymentDAO firstPaymentDAO = (FirstPaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FIRST_PAYMENT);
    private final DiscountDAO discountDAO = (DiscountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DISCOUNT);
    private final RentalDiscountDAO rentalDiscountDAO = (RentalDiscountDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RENTAL_DISCOUNT);

    @Override
    public int getGenerateRentalId(RentalDTO rentalDTO) throws SQLException {
        Rental rental = new Rental(rentalDTO.getCustomer_id(),rentalDTO.getVehicle_id(),rentalDTO.getDriver_id(),rentalDTO.getStart_date(),rentalDTO.getDates_of_rent(),rentalDTO.getReturn_date());
        return rentalDAO.getSaveId(rental);
    }

    @Override
    public RentalDTO searchRent(String id) throws SQLException {
        Rental rental = rentalDAO.search(id);
        return new RentalDTO(rental.getRental_id(),rental.getCustomer_id(),rental.getVehicle_id(),rental.getDriver_id(),rental.getStart_DATE(),rental.getDates_of_rent(),rental.getReturn_date());
    }

    @Override
    public List<RentalDTO> getAllRents() throws SQLException {
        List<Rental> rentals =  rentalDAO.getAll();
        List<RentalDTO> rentalDTOs = new ArrayList<>();
        for (Rental rental : rentals) {
            rentalDTOs.add(new RentalDTO(rental.getRental_id(),rental.getCustomer_id(),rental.getVehicle_id(),rental.getDriver_id(),rental.getStart_DATE(),rental.getDates_of_rent(),rental.getReturn_date()));
        }
        return rentalDTOs;
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
        FirstPayment firstPayment = new FirstPayment(firstPaymentDTO.getRental_id(), BigDecimal.valueOf(firstPaymentDTO.getBase_payment()),BigDecimal.valueOf(firstPaymentDTO.getFinal_payment()),firstPaymentDTO.getBase_payment_date());
        return firstPaymentDAO.save(firstPayment);
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
                double disAmount = (totalPay * discountDAO.search(String.valueOf(discountId)).getPercentage().doubleValue())/100;
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
        RentalDiscount rentalDiscount = new RentalDiscount(rentalDiscountDTO.getRental_id(),rentalDiscountDTO.getDiscount_id(),BigDecimal.valueOf(rentalDiscountDTO.getDiscount_amount_applied()));
        return rentalDiscountDAO.save(rentalDiscount);
    }

    @Override
    public Map<String, Integer> getMonthlyRentStats(int year, int month) throws SQLException {
        return rentalDAO.getMonthlyRentStats(year, month);
    }
}
