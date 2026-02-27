package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.RentalDiscountDTO;

import java.sql.SQLException;

public interface RentalDiscountBO extends SuperBO {

    RentalDiscountDTO searchRentalDiscount(String rentId) throws SQLException;
    boolean deleteRentalDiscount(String rentId) throws SQLException;
}
