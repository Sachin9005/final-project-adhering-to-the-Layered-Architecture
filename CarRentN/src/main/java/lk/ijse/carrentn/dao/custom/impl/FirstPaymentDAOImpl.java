package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.entity.FirstPayment;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FirstPaymentDAOImpl implements FirstPaymentDAO {
    public boolean save(FirstPayment firstPayment)throws SQLException{
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO First_Payment (rental_id, base_payment, final_payment, base_payment_date) VALUES (?,?,?,?)",
                firstPayment.getRental_id(),
                firstPayment.getBase_payment(),
                firstPayment.getFinal_payment(),
                LocalDate.now().toString());
        if (isSaved) {
            System.out.println("Base Payment Saved Successfully");
        }
        return isSaved;
    }

    public FirstPayment search(int rentId)throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM First_Payment WHERE rental_id = ?", id);
    }

    @Override
    public boolean update(FirstPayment cusDTO) throws SQLException {
        return false;
    }

    @Override
    public List<FirstPayment> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public FirstPayment search(String id) throws SQLException {
        return null;
    }

    @Override
    public FirstPayment getFirstPaymentByRentalId(int rentalId) throws SQLException {
        FirstPayment firstPayment = null ;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM First_Payment WHERE rental_id = ?", rentalId);
        if (resultSet.next()) {
            int firstPayId = resultSet.getInt("first_payment_id");
            int rentId = resultSet.getInt("rental_id");
            BigDecimal basePay = resultSet.getBigDecimal("base_payment");
            BigDecimal finalPay = resultSet.getBigDecimal("final_payment");
            LocalDate basePaymentDate = resultSet.getDate("base_payment_date").toLocalDate();

            firstPayment = new FirstPayment(firstPayId,rentId,basePay,finalPay,basePaymentDate);
        }
        return firstPayment ;
    }
}
