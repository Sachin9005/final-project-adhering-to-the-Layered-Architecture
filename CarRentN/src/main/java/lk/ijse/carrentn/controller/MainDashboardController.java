package lk.ijse.carrentn.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import lk.ijse.carrentn.App;

public class MainDashboardController implements Initializable {

    @FXML
    private Label dateLable;

    @FXML
    private Label timeLable;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lordDateAndTime();
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
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            timeLable.setText(LocalTime.now().format(timeFormatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
}
