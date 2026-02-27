package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.Rental;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.Map;

public interface RentalDAO extends CrudDAO<Rental> , SuperDAO {
    String getSaveLastRentalId()throws SQLException;
    Map<String, Integer> getMonthlyRentStats(int year , int month) throws SQLException;
    void printBasePayInvoice(int basePaymentId) throws JRException, SQLException;
    int getSaveId(Rental rental) throws SQLException;
}
