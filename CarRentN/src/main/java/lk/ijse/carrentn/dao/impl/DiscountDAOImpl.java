package lk.ijse.carrentn.dao.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dto.DiscountDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAOImpl implements DiscountDAO {
    public boolean save(DiscountDTO discountDTO) throws SQLException {
        return CrudUtil.execute("INSERT INTO Discount (description,percentage) VALUES (?,?)", discountDTO.getDescription(), discountDTO.getPercentage());
    }

    public boolean update(DiscountDTO discountDTO) throws SQLException {
        return CrudUtil.execute("UPDATE Discount SET description = ?, percentage = ? WHERE discount_id  = ?", discountDTO.getDescription(), discountDTO.getPercentage(), discountDTO.getDiscount_id());
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental_Discount WHERE discount_id = ?", id);
        return CrudUtil.execute("DELETE FROM Discount WHERE discount_id = ?",id);
    }

    public DiscountDTO search(String id) throws SQLException {
        DiscountDTO discountDTO = null;
        ResultSet result = CrudUtil.execute("SELECT * FROM Discount WHERE discount_id = ?",id);

        if (result.next()) {
            int discountId = result.getInt("discount_id");
            String discountDisc = result.getString("description");
            double discountPerc = result.getDouble("percentage");

            discountDTO = new DiscountDTO(discountId, discountDisc, discountPerc);
        }
        return discountDTO;
    }

    public List<DiscountDTO> getAllDiscounts() throws SQLException {
        ResultSet rs = CrudUtil.execute( "SELECT * FROM Discount ORDER BY discount_id DESC");

        ArrayList<DiscountDTO> discountList = new ArrayList<>();

        while(rs.next()) {
            DiscountDTO discountDTO = new DiscountDTO(
                    rs.getInt("discount_id"),
                    rs.getString("description"),
                    rs.getDouble("percentage"));

            discountList.add(discountDTO);
        }
        return discountList;
    }

    public List<String> getAllDiscountDes() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT description FROM Discount ORDER BY discount_id DESC");

        ArrayList<String> carOwnerNameList = new ArrayList<>();

        while(rs.next()) {
            carOwnerNameList.add(rs.getString("description"));
        }
        return carOwnerNameList;
    }

    public String searchId(String description)  {
        String id = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT discount_id FROM Discount WHERE description = ?", description);
            if (result.next()) {
                int carOwnerID = result.getInt("discount_id");
                id = String.valueOf(carOwnerID);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return id ;
    }

    public Double searchDesForGetPrec (String description) {
        Double prec = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT percentage FROM Discount WHERE description = ?", description);
            if (result.next()) {
                prec = result.getDouble("percentage");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return prec ;
    }

    public Double searchIdtoGetPrec(String id) {
        Double prec = null;
        try {
            ResultSet result = CrudUtil.execute("SELECT percentage FROM Discount WHERE discount_id = ?", id);
            if (result.next()) {
                prec = result.getDouble("percentage");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return prec ;
    }
}
