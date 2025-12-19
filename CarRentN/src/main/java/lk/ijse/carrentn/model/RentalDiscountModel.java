package lk.ijse.carrentn.model;

import lk.ijse.carrentn.util.CrudUtil;

import java.time.LocalDate;

public class RentalDiscountModel {
    private DiscountModel discountModel = new DiscountModel();
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
            throw new Exception("Somethin went Wrong");
        }

        return true;

    }
}
