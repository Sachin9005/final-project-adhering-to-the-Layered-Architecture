package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.RentalDiscountBO;
import lk.ijse.carrentn.dao.custom.RentalDiscountDAO;
import lk.ijse.carrentn.dao.custom.impl.RentalDiscountDAOImpl;
import lk.ijse.carrentn.dto.RentalDiscountDTO;
import lk.ijse.carrentn.entity.RentalDiscount;

import java.sql.SQLException;

public class RentalDiscountBOimpl implements RentalDiscountBO {

    RentalDiscountDAO rentalDiscountDAO =  new RentalDiscountDAOImpl();
    @Override
    public RentalDiscountDTO searchRentalDiscount(String rentId) throws SQLException {
        RentalDiscount rentalDiscount = rentalDiscountDAO.search(rentId);
        return new RentalDiscountDTO(rentalDiscount.getRental_discount_id(),rentalDiscount.getRental_id(),rentalDiscount.getDiscount_id(),rentalDiscount.getDiscount_amount_applied().doubleValue());
    }

    @Override
    public boolean deleteRentalDiscount(String rentId) throws SQLException {
        return rentalDiscountDAO.delete(rentId);
    }
}
