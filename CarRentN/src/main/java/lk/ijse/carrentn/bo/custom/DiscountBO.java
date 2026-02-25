package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dao.custom.impl.DiscountDAOImpl;
import lk.ijse.carrentn.dto.DiscountDTO;

import java.sql.SQLException;
import java.util.List;

public interface DiscountBO extends SuperBO {

    public boolean saveDiscount(DiscountDTO discountDTO) throws SQLException;
    public boolean updateDiscount(DiscountDTO discountDTO) throws SQLException;
    public boolean deleteDiscount(String id) throws SQLException;
    public DiscountDTO searchDiscount(String id) throws SQLException;
    public List<DiscountDTO> getAllDiscounts() throws SQLException;
    public DiscountDTO searchDiscountId(String description);
}
