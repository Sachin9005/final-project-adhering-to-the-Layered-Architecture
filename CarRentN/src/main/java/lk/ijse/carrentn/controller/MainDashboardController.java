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
import lk.ijse.carrentn.model.DriverModel;
import lk.ijse.carrentn.model.RentalModel;
import lk.ijse.carrentn.model.VehicleModel;

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
    private final VehicleModel vehicleModel = new VehicleModel();
    private final DriverModel driverModel = new DriverModel();

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
            int driverCount = driverModel.getDriverCount();
            int avaDriverCount = driverModel.getAvailableDriverCount();
            driCou.setText(Integer.toString(driverCount));
            driAva.setText(Integer.toString(avaDriverCount));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void lordVehicleCount(){
        try {
            // CAR
            carCou.setText(String.valueOf(vehicleModel.getSUVehiclesCount("CAR")));
            carAva.setText(String.valueOf(vehicleModel.getAvailableVehiclesCount("CAR")));

            // SUV
            SUVCou.setText(String.valueOf(vehicleModel.getSUVehiclesCount("SUV")));
            SUVAva.setText(String.valueOf(vehicleModel.getAvailableVehiclesCount("SUV")));

            // VAN
            vanCou.setText(String.valueOf(vehicleModel.getSUVehiclesCount("VAN")));
            vanAva.setText(String.valueOf(vehicleModel.getAvailableVehiclesCount("VAN")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void loadMonthlyRentCounts() {
        try {
            LocalDate now = LocalDate.now();

            Map<String, Integer> stats =
                    RentalModel.getMonthlyRentStats(
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
