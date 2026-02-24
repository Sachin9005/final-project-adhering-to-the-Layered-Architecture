package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.DiscountBO;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dao.custom.impl.DiscountDAOImpl;
import lk.ijse.carrentn.dto.DiscountDTO;

import java.sql.SQLException;
import java.util.List;

public class DiscountBOimpl implements DiscountBO {

    DiscountDAO discountDAO = new DiscountDAOImpl();

    @Override
    public boolean saveDiscount(DiscountDTO discountDTO) throws SQLException {
        return discountDAO.save(discountDTO);
    }

    @Override
    public boolean updateDiscount(DiscountDTO discountDTO) throws SQLException {
        return discountDAO.update(discountDTO);
    }

    @Override
    public boolean deleteDiscount(String id) throws SQLException {
        return discountDAO.delete(id);
    }

    @Override
    public DiscountDTO searchDiscount(String id) throws SQLException {
        return discountDAO.search(id);
    }

    @Override
    public List<DiscountDTO> getAllDiscounts() throws SQLException {
        return discountDAO.getAll();
    }

    @Override
    public DiscountDTO searchDiscountId(String description) {
        return discountDAO.searchId(description);
    }
}
