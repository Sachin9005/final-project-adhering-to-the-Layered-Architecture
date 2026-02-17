package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.RentalDiscountDTO;

public interface RentalDiscountDAO {
    public RentalDiscountDTO searchRentalById(String rentId) throws Exception;
    public boolean saveRentalDiscount(int rentId , Integer discountId ,double totalPay,double prec)throws Exception;
}
