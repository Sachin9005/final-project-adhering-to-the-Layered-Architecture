package lk.ijse.carrentn.model;

import lk.ijse.carrentn.dto.RentalDiscountDTO;
import lk.ijse.carrentn.dao.CrudUtil;
import java.sql.ResultSet;

public class RentalDiscountModel {
    private final DiscountModel discountModel = new DiscountModel();
    public boolean saveRentalDiscount(int rentId , Integer discountId ,double totalPay)throws Exception{
        double prec = discountModel.searchIdtoGetPrec(String.valueOf(discountId));
        double disAmount = (totalPay * prec)/100;
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO Rental_Discount (rental_id, discount_id, discount_amount_applied ) VALUES (?,?,?)",
                rentId,
                discountId,
                disAmount);

        if (isSaved) {
            System.out.println("Base Payment Saved Successfully");
        } else {
            throw new Exception("Something went Wrong");
        }
        return true;
    }

    public RentalDiscountDTO searchRentalById(String rentId) throws Exception{
        RentalDiscountDTO rentalDiscountDTO = null;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Rental_Discount WHERE rental_id = ?", rentId);

        if (resultSet.next()) {
            int rentalId = resultSet.getInt("rental_id");
            int discountId = resultSet.getInt("discount_id");
            double discount_amount_applied = resultSet.getDouble("discount_amount_applied");

            rentalDiscountDTO = new RentalDiscountDTO(rentalId, discountId, discount_amount_applied);
        }
        return rentalDiscountDTO;
    }
}
