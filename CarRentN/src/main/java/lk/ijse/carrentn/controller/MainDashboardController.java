package lk.ijse.carrentn.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.carrentn.App;
import lk.ijse.carrentn.bo.BOFactory;
import lk.ijse.carrentn.bo.custom.DriverBO;
import lk.ijse.carrentn.bo.custom.RentalBO;
import lk.ijse.carrentn.bo.custom.VehicleBO;
import lk.ijse.carrentn.bo.custom.impl.DriverBOimpl;
import lk.ijse.carrentn.bo.custom.impl.RentalBOimpl;
import lk.ijse.carrentn.bo.custom.impl.VehicleBOimpl;
import lk.ijse.carrentn.dao.custom.DriverDAO;
import lk.ijse.carrentn.dao.custom.RentalDAO;
import lk.ijse.carrentn.dao.custom.VehicleDAO;
import lk.ijse.carrentn.dao.custom.impl.DriverDAOImpl;
import lk.ijse.carrentn.dao.custom.impl.RentalDAOImpl;
import lk.ijse.carrentn.dao.custom.impl.VehicleDAOImpl;

public class MainDashboardController implements Initializable {

    @FXML
    private TextField SUVAva;

    @FXML
    private TextField SUVCou;

    @FXML
    private TextField carAva;

    @FXML
    private TextField carCou;

    @FXML
    private Label dateLable;

    @FXML
    private Label driAva;

    @FXML
    private Label driCou;

    @FXML
    private Label timeLable;

    @FXML
    private TextField vanAva;
    @FXML
    private Label lblMonthlyTotal;
    @FXML
    private Label lblMonthlyOngoing;
    @FXML
    private Label lblMonthlyFinished;

    @FXML
    private TextField vanCou;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    private final VehicleBO vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);
    private final DriverBO driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
    private final RentalBO rentalBO = (RentalBO) BOFactory.getInstance().getBO(BOFactory.BOType.RENTAL);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lordDateAndTime();
        lordDriverCount();
        lordVehicleCount();
        loadMonthlyRentCounts();
    }

    @FXML
    private void detailsManage() throws Exception {
            App.setRoot("ManageDetails");
    }

    @FXML
    private void paymentCal() throws Exception {
        App.setRoot("PaymentCalculation");
    }

    @FXML
    private void logOut() throws Exception {
        App.setRoot("Login");
    }

    @FXML
    private void profile() throws Exception {
        App.setRoot("ProfileManage");
    }

    private void lordDateAndTime() {
        dateLable.setText(LocalDate.now().format(dateFormatter));
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> timeLable.setText(LocalTime.now().format(timeFormatter))), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void lordDriverCount(){
        try {
            int driverCount = driverBO.getAllDrivers().size();
            int avaDriverCount = driverBO.getAvailableDrivers(LocalDate.now()).size();
            driCou.setText(Integer.toString(driverCount));
            driAva.setText(Integer.toString(avaDriverCount));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void lordVehicleCount(){
        try {
            // CAR
            carCou.setText(String.valueOf(vehicleBO.getVehiclesCount("CAR")));
            carAva.setText(String.valueOf(vehicleBO.getAvailableVehiclesCount("CAR")));

            // SUV
            SUVCou.setText(String.valueOf(vehicleBO.getVehiclesCount("SUV")));
            SUVAva.setText(String.valueOf(vehicleBO.getAvailableVehiclesCount("SUV")));

            // VAN
            vanCou.setText(String.valueOf(vehicleBO.getVehiclesCount("VAN")));
            vanAva.setText(String.valueOf(vehicleBO.getAvailableVehiclesCount("VAN")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadMonthlyRentCounts() {
        try {
            LocalDate now = LocalDate.now();

            Map<String, Integer> stats =
                    rentalBO.getMonthlyRentStats(
                            now.getYear(), now.getMonthValue()
                    );

            lblMonthlyTotal.setText(stats.get("TOTAL").toString());
            lblMonthlyOngoing.setText(stats.get("ONGOING").toString());
            lblMonthlyFinished.setText(stats.get("FINISHED").toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
