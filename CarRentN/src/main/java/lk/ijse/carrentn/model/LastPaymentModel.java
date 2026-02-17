package lk.ijse.carrentn.model;

import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.dto.LastPaymentDTO;
import lk.ijse.carrentn.dao.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LastPaymentModel {

    public boolean saveFullPayment(LastPaymentDTO lastPaymentDTO)throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO last_Payment (first_payment_id, rental_id, late_days, balance_payment, fine_payment,last_payment , last_payment_date) VALUES (?,?,?,?,?,?,?)",
                lastPaymentDTO.getFirst_payment_id(),
                lastPaymentDTO.getRental_id(),
                lastPaymentDTO.getLate_days(),
                lastPaymentDTO.getBalance_payment(),
                lastPaymentDTO.getFine_payment(),
                lastPaymentDTO.getLast_payment(),
                lastPaymentDTO.getLast_payment_date());
    }

    public void printLastPayInvoice(int finalPaymentId,double vehicleDamage,double customerPay) throws JRException, SQLException {

        Connection conn = DBConnection.getInstance().getConnection();

        // Step 01
        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/carrentn/reports/lastPaymentInvoice.jrxml");
        if (reportObject == null) {
            throw new RuntimeException("lastPaymentInvoice.jrxml not found in /reports folder");
        }

        // Step 02
        JasperReport jr = JasperCompileManager.compileReport(reportObject); // this method thorws JRException

        // Step 03

        Map<String, Object> params = new HashMap<>();
        params.put("FINAL_PAYMENT_ID", finalPaymentId);
        params.put("VEHICLE_DAMAGE_FEE", vehicleDamage);
        params.put("CUSTOMER_PAY", customerPay);


        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn); // fillReport(japerreport, params, connection_obj)

        // Step 04
        JasperViewer.viewReport(jp, false);

    }

    public String getSaveLastPaymentId()throws SQLException{
        String id = null;
        ResultSet result = CrudUtil.execute("SELECT last_payment_id FROM last_Payment ORDER BY last_payment_id DESC LIMIT 1");
        if (result.next()){
            id = String.valueOf(result.getInt("last_payment_id"));
        }
        return id;
    }

}
