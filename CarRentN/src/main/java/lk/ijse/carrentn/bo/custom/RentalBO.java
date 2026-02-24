package lk.ijse.carrentn.bo.custom;

import lk.ijse.carrentn.dto.FirstPaymentDTO;
import lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.dto.RentalDiscountDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RentalBO {
    public int getGenerateRentalId(RentalDTO rentalDTO) throws SQLException;
    public RentalDTO searchRent(String id) throws SQLException;
    public List<RentalDTO> getAllRents() throws SQLException;
    public String getSaveLastRentalId()throws SQLException;
    public void printBasePayInvoice(int basePaymentId) throws JRException, SQLException;
    public boolean deleteRent(String id) throws SQLException;
    public boolean saveFirstPayment(FirstPaymentDTO firstPaymentDTO)throws SQLException;
    public boolean saveRent(RentalDTO rentalDTO,double basPay,double totalPay,Integer discountId) throws Exception;
    public boolean saveRentalDiscount(RentalDiscountDTO rentalDiscountDTO)throws SQLException;
    public Map<String, Integer> getMonthlyRentStats(int year , int month) throws SQLException;
}
