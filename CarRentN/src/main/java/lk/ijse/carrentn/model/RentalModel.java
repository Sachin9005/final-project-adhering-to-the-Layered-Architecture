package lk.ijse.carrentn.model;
import javafx.scene.control.Alert;
import lk.ijse.carrentn.db.DBConnection;
import  lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.util.CrudUtil;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class RentalModel {
    FirstPaymentModel firstPaymentModel = new FirstPaymentModel();
    LastPaymentModel lastPaymentModel = new LastPaymentModel();
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

        boolean result = CrudUtil.execute("UPDATE Rental SET " +
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

        return result;
    }

    public boolean delete(String id) throws SQLException {
        CrudUtil.execute("DELETE FROM Rental_Discount WHERE rental_id = ?", id);
        CrudUtil.execute("DELETE FROM last_Payment WHERE rental_id = ?", id);
        CrudUtil.execute("DELETE FROM First_Payment WHERE rental_id = ?", id);
        boolean result = CrudUtil.execute("DELETE FROM Rental WHERE rental_id = ?",id);
        return result;
    }

    public RentalDTO search(String id) throws SQLException {
        RentalDTO rentalDTO = null;
        double totalPay = 0.0;
        ResultSet resu = CrudUtil.execute("SELECT base_payment FROM First_Payment WHERE rental_id = ?",id);
        ResultSet result = CrudUtil.execute("SELECT * FROM Rental WHERE rental_id = ?",id);

        if (resu.next()) {
            double total = resu.getDouble("base_payment");
            totalPay = total;
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

        ArrayList<RentalDTO> rentalList = new ArrayList();

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

    public List<String> getAllRentalIds() throws SQLException {
        ResultSet rs = CrudUtil.execute("SELECT rental_id FROM Rental ORDER BY rental_id DESC");

        ArrayList<String> rebtalIdList = new ArrayList();

        while(rs.next()) {
            rebtalIdList.add(String.valueOf(rs.getInt("rental_id")));
        }
        return rebtalIdList;
    }

    public RentalDTO searchRent(String id) throws SQLException {
        RentalDTO rentalDTO = null;

        ResultSet result = CrudUtil.execute("SELECT * FROM Rental WHERE customer_id = ? ORDER BY rental_id DESC LIMIT 1",id);

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

}
