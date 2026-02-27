package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.entity.Discount;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {
    public boolean save(Discount discount) throws SQLException {
        return CrudUtil.execute("INSERT INTO Discount (description,percentage) VALUES (?,?)", discount.getDescription(), discount.getPercentage());
    }

    public boolean update(Discount discount) throws SQLException {
        return CrudUtil.execute("UPDATE Discount SET description = ?, percentage = ? WHERE discount_id  = ?", discount.getDescription(), discount.getPercentage(), discount.getDiscount_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental_Discount WHERE discount_id = ?", id);
        return CrudUtil.execute("DELETE FROM Discount WHERE discount_id = ?",id);
    }

    public Discount search(String id) throws SQLException {
        Discount discount = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Discount WHERE discount_id = ?",id);

        if (result.next()) {
            int discountId = result.getInt("discount_id");
            String discountDisc = result.getString("description");
            BigDecimal discountPerc = result.getBigDecimal("percentage");

            discount = new Discount(discountId, discountDisc, discountPerc);
        }
        return discount;
    }

    public List<Discount> getAll() throws SQLException {
        ResultSet rs = CrudUtil.execute( "SELECT * FROM Discount ORDER BY discount_id DESC");

        ArrayList<Discount> discountList = new ArrayList<>();

        while(rs.next()) {
            Discount discount = new Discount(
                    rs.getInt("discount_id"),
                    rs.getString("description"),
                    rs.getBigDecimal("percentage"));
            discountList.add(discount);
        }
        return discountList;
    }

    public Discount searchId(String description)  {
        Discount discount = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Discount WHERE description = ?", description);
            if (result.next()) {
                int discountId = result.getInt("discount_id");
                String discountDisc = result.getString("description");
                BigDecimal discountPerc = result.getBigDecimal("percentage");

                discount = new Discount(discountId, discountDisc, discountPerc);            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return discount;
    }
}
