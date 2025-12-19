package lk.ijse.carrentn.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.carrentn.App;

public class MainDashboardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
}
