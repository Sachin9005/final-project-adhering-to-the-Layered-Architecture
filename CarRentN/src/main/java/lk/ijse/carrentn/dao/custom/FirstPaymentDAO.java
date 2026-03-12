package lk.ijse.carrentn.dao.custom;

import lk.ijse.carrentn.dao.CrudDAO;
import lk.ijse.carrentn.dao.SuperDAO;
import lk.ijse.carrentn.entity.FirstPayment;

import java.sql.SQLException;

public interface FirstPaymentDAO extends CrudDAO<FirstPayment>, SuperDAO {

 FirstPayment getFirstPaymentByRentalId(int rentalId) throws SQLException;

}
