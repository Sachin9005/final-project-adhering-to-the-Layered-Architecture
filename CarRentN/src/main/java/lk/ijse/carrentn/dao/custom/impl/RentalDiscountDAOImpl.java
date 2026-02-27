package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.RentalDiscountDAO;
import lk.ijse.carrentn.entity.RentalDiscount;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RentalDiscountDAOImpl implements RentalDiscountDAO {
    public RentalDiscount search(String rentId) throws SQLException{
        RentalDiscount rentalDiscount = null;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Rental_Discount WHERE rental_id = ?", rentId);

        if (resultSet.next()) {
            int rentalId = resultSet.getInt("rental_id");
            int discountId = resultSet.getInt("discount_id");
            BigDecimal discount_amount_applied = resultSet.getBigDecimal("discount_amount_applied");

            rentalDiscount = new RentalDiscount(rentalId, discountId, discount_amount_applied);
        }
        return rentalDiscount;
    }

    public boolean save(RentalDiscount rentalDiscount)throws SQLException{
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO Rental_Discount (rental_id, discount_id, discount_amount_applied ) VALUES (?,?,?)",
                rentalDiscount.getRental_id(),
                rentalDiscount.getDiscount_id(),
                rentalDiscount.getDiscount_amount_applied());

        if (isSaved) {
            System.out.println("Base Payment Saved Successfully");
        }
        return true;
    }

    @Override
    public boolean update(RentalDiscount cusDTO) throws SQLException {
        return false;
    }

    public boolean delete(String rentId) throws SQLException{
        return CrudUtil.execute("DELETE FROM Rental_Discount WHERE rental_id = ?", rentId);
    }

    @Override
    public List<RentalDiscount> getAll() throws SQLException{
        return List.of();
    }
}
