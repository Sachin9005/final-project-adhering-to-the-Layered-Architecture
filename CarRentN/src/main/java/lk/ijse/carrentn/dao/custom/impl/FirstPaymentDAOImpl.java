package lk.ijse.carrentn.dao.custom.impl;

import lk.ijse.carrentn.dao.CrudUtil;
import lk.ijse.carrentn.dao.custom.FirstPaymentDAO;
import lk.ijse.carrentn.dto.FirstPaymentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FirstPaymentDAOImpl implements FirstPaymentDAO {
    public boolean save(FirstPaymentDTO firstPaymentDTO)throws SQLException{
        boolean isSaved = CrudUtil.execute(
                "INSERT INTO First_Payment (rental_id, base_payment, final_payment, base_payment_date) VALUES (?,?,?,?)",
                firstPaymentDTO.getRental_id(),
                firstPaymentDTO.getBase_payment(),
                firstPaymentDTO.getFinal_payment(),
                LocalDate.now().toString());
        if (isSaved) {
            System.out.println("Base Payment Saved Successfully");
        }
        return true;
    }

    public FirstPaymentDTO search(int rentId)throws SQLException {
        FirstPaymentDTO firstPaymentDTO = null ;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM First_Payment WHERE rental_id = ?", rentId);
        if (resultSet.next()) {
            int firstPayId = resultSet.getInt("first_payment_id");
            int rentalId = resultSet.getInt("rental_id");
            double basePay = resultSet.getDouble("base_payment");
            double finalPay = resultSet.getDouble("final_payment");
            LocalDate basePaymentDate = resultSet.getDate("base_payment_date").toLocalDate();

            firstPaymentDTO = new FirstPaymentDTO(firstPayId,rentalId,basePay,finalPay,basePaymentDate);
        }
        return firstPaymentDTO ;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM First_Payment WHERE rental_id = ?", id);
    }

    @Override
    public boolean update(FirstPaymentDTO cusDTO) throws SQLException {
        return false;
    }

    @Override
    public List<FirstPaymentDTO> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public FirstPaymentDTO search(String id) throws SQLException {
        return null;
    }
}
