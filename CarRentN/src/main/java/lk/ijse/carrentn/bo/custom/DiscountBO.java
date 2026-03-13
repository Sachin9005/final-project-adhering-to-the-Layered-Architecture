package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.DiscountDTO;

import java.sql.SQLException;
import java.util.List;

public interface DiscountBO extends SuperBO {

    boolean saveDiscount(DiscountDTO discountDTO) throws SQLException;
    boolean updateDiscount(DiscountDTO discountDTO) throws SQLException;
    boolean deleteDiscount(String id) throws SQLException;
    DiscountDTO searchDiscount(String id) throws SQLException;
    List<DiscountDTO> getAllDiscounts() throws SQLException;
    int searchDiscountId(String description);
}
