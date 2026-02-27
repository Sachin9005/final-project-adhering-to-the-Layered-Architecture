package lk.ijse.carrentn.bo.custom.impl;

import lk.ijse.carrentn.bo.custom.DiscountBO;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dao.custom.impl.DiscountDAOImpl;
import lk.ijse.carrentn.dto.DiscountDTO;
import lk.ijse.carrentn.entity.Discount;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountBOimpl implements DiscountBO {

    DiscountDAO discountDAO = new DiscountDAOImpl();

    @Override
    public boolean saveDiscount(DiscountDTO discountDTO) throws SQLException {
        Discount discount = new Discount(discountDTO.getDescription(), BigDecimal.valueOf(discountDTO.getPercentage()));
        return discountDAO.save(discount);
    }

    @Override
    public boolean updateDiscount(DiscountDTO discountDTO) throws SQLException {
        Discount discount = new Discount(discountDTO.getDiscount_id(),discountDTO.getDescription(), BigDecimal.valueOf(discountDTO.getPercentage()));
        return discountDAO.update(discount);
    }

    @Override
    public boolean deleteDiscount(String id) throws SQLException {
        return discountDAO.delete(id);
    }

    @Override
    public DiscountDTO searchDiscount(String id) throws SQLException {
        Discount discount = discountDAO.search(id);
        return new DiscountDTO(discount.getDiscount_id(),discount.getDescription(),discount.getPercentage().doubleValue());
    }

    @Override
    public List<DiscountDTO> getAllDiscounts() throws SQLException {
        List<Discount> discounts = discountDAO.getAll();
        List<DiscountDTO> dtos = new ArrayList<>();
        for (Discount discount : discounts) {
            dtos.add(new DiscountDTO(discount.getDiscount_id(),discount.getDescription(),discount.getPercentage().doubleValue()));
        }
        return dtos;
    }

    @Override
    public DiscountDTO searchDiscountId(String description) {
        Discount discount= discountDAO.searchId(description);
        return new DiscountDTO(discount.getDiscount_id(),discount.getDescription(),discount.getPercentage().doubleValue());
    }
}
