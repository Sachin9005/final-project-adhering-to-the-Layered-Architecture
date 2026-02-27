package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.bo.SuperBO;
import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.dto.RentalDiscountDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RentalBO extends SuperBO {
    int getGenerateRentalId(RentalDTO rentalDTO) throws SQLException;
    RentalDTO searchRent(String id) throws SQLException;
    List<RentalDTO> getAllRents() throws SQLException;
    String getSaveLastRentalId()throws SQLException;
    void printBasePayInvoice(int basePaymentId) throws JRException, SQLException;
    boolean deleteRent(String id) throws SQLException;
    boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO)throws SQLException;
    boolean saveRent(RentalDTO rentalDTO,double basPay,double totalPay,Integer discountId) throws Exception;
    boolean saveRentalDiscount(RentalDiscountDTO rentalDiscountDTO)throws SQLException;
    Map<String, Integer> getMonthlyRentStats(int year , int month) throws SQLException;
}
