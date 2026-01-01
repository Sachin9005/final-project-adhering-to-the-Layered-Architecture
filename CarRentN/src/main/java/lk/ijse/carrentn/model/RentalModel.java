package lk.ijse.carrentn.model;
import lk.ijse.carrentn.db.DBConnection;
import  lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalModel {
    FirstPaymentModel firstPaymentModel = new FirstPaymentModel();
    RentalDiscountModel rentalDiscountModel = new RentalDiscountModel();

    public boolean save(RentalDTO rentalDTO,double basPay,double totalPay,Integer discountId) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
            try {
                conn.setAutoCommit(false);
                int rentalId = CrudUtil.executeInsertAndGetId("INSERT INTO Rental" +
                                " (customer_id, " +
                                "vehicle_id, " +
                                "driver_id, " +
                                "start_DATE, " +
                                "dates_of_rent, " +
                                "return_date ) " +
                                "VALUES (?,?,?,?,?,?)",
                        rentalDTO.getCustomer_id(),
                        rentalDTO.getVehicle_id(),
                        rentalDTO.getDriver_id(),
                        rentalDTO.getStart_date(),
                        rentalDTO.getDates_of_rent(),
                        rentalDTO.getReturn_date());

                if (rentalId == 0) {
                    throw new Exception("Failed to generate rental id");
                }
                boolean isBasePaySaved =
                        firstPaymentModel.saveBasePayment(rentalId, basPay, totalPay);

                boolean isDiscountSaved = true;
                if (discountId != null) {
                    isDiscountSaved =
                            rentalDiscountModel.saveRentalDiscount(rentalId, discountId, totalPay);
                }

                if (!isBasePaySaved && !isDiscountSaved) {
                        throw new Exception("Something went Wrong");
                    }
                conn.commit();
                return true;

            } catch (Exception e) {
                conn.rollback();
                throw e;
            }finally {
                conn.setAutoCommit(true);
            }


    }

    public boolean update(RentalDTO rentalDTO) throws SQLException {

        return CrudUtil.execute("UPDATE Rental SET " +
                        "customer_id = ?, " +
                        "vehicle_id = ? , " +
                        "driver_id = ?, " +
                        "start_DATE = ? , " +
                        "dates_of_rent = ? , " +
                        "return_date = ? " +
                        "WHERE rental_id  = ?",
                rentalDTO.getCustomer_id(),
                rentalDTO.getVehicle_id(),
                rentalDTO.getDriver_id(),
                rentalDTO.getStart_date(),
                rentalDTO.getDates_of_rent(),
                rentalDTO.getReturn_date()
                );
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental_Discount WHERE rental_id = ?", id);
        CrudUtil.execute("DELETE FROM last_Payment WHERE rental_id = ?", id);
        CrudUtil.execute("DELETE FROM First_Payment WHERE rental_id = ?", id);
        return CrudUtil.execute("DELETE FROM Rental WHERE rental_id = ?",id);
    }

    public RentalDTO search(String id) throws SQLException {
        RentalDTO rentalDTO = null;
        double totalPay = 0.0;
        ResultSet resu = CrudUtil.execute("SELECT base_payment FROM First_Payment WHERE rental_id = ?",id);
        ResultSet result = CrudUtil.execute("SELECT * FROM Rental WHERE rental_id = ?",id);

        if (resu.next()) {
            totalPay = resu.getDouble("base_payment");
        }

        if (result.next()) {
            int rentalId = result.getInt("rental_id");
            int cusID = result.getInt("customer_id");
            int vehicleId = result.getInt("vehicle_id");
            int DriverId = result.getInt("driver_id");
            Date sdate = result.getDate("start_DATE");
            int days = result.getInt("dates_of_rent");
            Date eDate = result.getDate("return_date");

            rentalDTO = new RentalDTO(rentalId,cusID,vehicleId,DriverId,sdate.toLocalDate(),days,eDate.toLocalDate(),totalPay);
        }
        return rentalDTO;
    }

    public List<RentalDTO> getAllRentals() throws SQLException {
        ResultSet rs = CrudUtil.execute( "SELECT * FROM Rental ORDER BY rental_id DESC");

        ArrayList<RentalDTO> rentalList = new ArrayList<>();

        while(rs.next()) {
                RentalDTO rentalDTOs = new RentalDTO(
                    rs.getInt("rental_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getDate("start_DATE").toLocalDate(),
                    rs.getInt("dates_of_rent"),
                    rs.getDate("return_date").toLocalDate());
            rentalList.add(rentalDTOs);
        }
        return rentalList;
    }

    public RentalDTO searchRent(String id) throws SQLException {
        RentalDTO rentalDTO = null;

        ResultSet result = CrudUtil.execute("SELECT * FROM Rental r JOIN Customer c ON r.customer_id = c.customer_id LEFT JOIN last_Payment lp ON r.rental_id = lp.rental_id WHERE lp.last_payment_id IS NULL AND r.customer_id = ?",id);

        if (result.next()) {
            int rentalId = result.getInt("rental_id");
            int cusID = result.getInt("customer_id");
            int vehicleId = result.getInt("vehicle_id");
            int DriverId = result.getInt("driver_id");
            Date sdate = result.getDate("start_DATE");
            int days = result.getInt("dates_of_rent");
            Date eDate = result.getDate("return_date");

            rentalDTO = new RentalDTO(rentalId,cusID,vehicleId,DriverId,sdate.toLocalDate(),days,eDate.toLocalDate());
        }
        return rentalDTO;
    }

    public String getSaveLastRentalId()throws SQLException{
        String id = null;
        ResultSet result = CrudUtil.execute("SELECT rental_id FROM Rental ORDER BY rental_id DESC LIMIT 1");
        if (result.next()){
            id = String.valueOf(result.getInt("rental_id"));
        }
        return id;
    }

    public void printBasePayInvoice(int basePaymentId) throws JRException, SQLException {

        Connection conn = DBConnection.getInstance().getConnection();

        // Step 01
        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/carrentn/reports/InvoiceForBasePay.jrxml");
        if (reportObject == null) {
            throw new RuntimeException("InvoiceForBasePay.jrxml not found in /reports folder");
        }

        // Step 02
        JasperReport jr = JasperCompileManager.compileReport(reportObject); // this method thorws JRException

        // Step 03

        Map<String, Object> params = new HashMap<>();
        params.put("PAYMENT_ID", basePaymentId);

        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn); // fillReport(japerreport, params, connection_obj)

        // Step 04
        JasperViewer.viewReport(jp, false);

    }

    public static Map<String, Integer> getMonthlyRentStats(int year , int month) throws SQLException {

        ResultSet rs = CrudUtil.execute(
                "SELECT COUNT(*) AS total, " +
                        "SUM(CASE WHEN return_date IS NOT NULL THEN 1 ELSE 0 END) AS ongoing, " +
                        "SUM(CASE WHEN return_date IS NULL THEN 1 ELSE 0 END) AS finished " +
                        "FROM Rental WHERE YEAR(start_date)=? AND MONTH(start_date)=?",
                year, month
        );

        Map<String, Integer> map = new HashMap<>();

        if (rs.next()) {
            map.put("TOTAL", rs.getInt("total"));
            map.put("ONGOING", rs.getInt("ongoing"));
            map.put("FINISHED", rs.getInt("finished"));
        }
        return map;
    }


}
