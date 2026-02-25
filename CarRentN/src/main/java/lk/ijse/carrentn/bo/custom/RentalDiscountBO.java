package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.RentalDiscountDTO;

import java.sql.SQLException;

public interface RentalDiscountBO extends SuperBO {

    public RentalDiscountDTO searchRentalDiscount(String rentId) throws SQLException;
    public boolean deleteRentalDiscount(String rentId) throws SQLException;
}
