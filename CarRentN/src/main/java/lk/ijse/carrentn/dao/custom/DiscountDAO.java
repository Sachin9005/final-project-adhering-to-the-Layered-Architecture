package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.DiscountDTO;

import java.sql.SQLException;
import java.util.List;

public interface DiscountDAO {
    public boolean save(DiscountDTO discountDTO) throws SQLException;
    public boolean update(DiscountDTO discountDTO) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public DiscountDTO search(String id) throws SQLException;
    public List<DiscountDTO> getAllDiscounts() throws SQLException;
    public List<String> getAllDiscountDes() throws SQLException;
    public String searchId(String description);
    public Double searchDesForGetPrec (String description);
    public Double searchIdtoGetPrec(String id);
}
