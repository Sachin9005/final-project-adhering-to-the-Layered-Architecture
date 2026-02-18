package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dto.RentalDTO;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RentalDAO {
    public boolean delete(String id) throws SQLException;
    public RentalDTO search(String id) throws SQLException;
    public List<RentalDTO> getAllRentals() throws SQLException;
    public RentalDTO searchRent(String id) throws SQLException;
    public String getSaveLastRentalId()throws SQLException;
    public Map<String, Integer> getMonthlyRentStats(int year , int month) throws SQLException;
    public void printBasePayInvoice(int basePaymentId) throws JRException, SQLException;
    public int getSaveId(RentalDTO rentalDTO) throws SQLException;
}
