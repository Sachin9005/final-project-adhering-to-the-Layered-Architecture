package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.RentalDiscountBO;
import lk.ijse.carrentn.dao.custom.RentalDiscountDAO;
import lk.ijse.carrentn.dao.custom.impl.RentalDiscountDAOImpl;
import lk.ijse.carrentn.dto.RentalDiscountDTO;

import java.sql.SQLException;

public class RentalDiscountBOimpl implements RentalDiscountBO {

    RentalDiscountDAO rentalDiscountDAO =  new RentalDiscountDAOImpl();
    @Override
    public RentalDiscountDTO searchRentalDiscount(String rentId) throws SQLException {
        return rentalDiscountDAO.search(rentId);
    }

    @Override
    public boolean saveRentalDiscount(RentalDiscountDTO rentalDiscountDTO) throws SQLException {
        return rentalDiscountDAO.save(rentalDiscountDTO);
    }

    @Override
    public boolean deleteRentalDiscount(String rentId) throws SQLException {
        return rentalDiscountDAO.delete(rentId);
    }
}
