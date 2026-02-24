package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.dto.RentalDiscountDTO;

import java.sql.SQLException;

public interface RentalDiscountBO {

    public RentalDiscountDTO searchRentalDiscount(String rentId) throws SQLException;
    public boolean saveRentalDiscount(RentalDiscountDTO rentalDiscountDTO)throws SQLException;
    public boolean deleteRentalDiscount(String rentId) throws SQLException;
}
