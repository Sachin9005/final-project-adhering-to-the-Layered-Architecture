package lk.ijse.carrentn.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carrentn.App;

public class PaymentCalculationController implements Initializable {
    @FXML
    private AnchorPane mainContentPayment;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {clickNewRentalNav();}catch (Exception e){e.printStackTrace();}
    }

    @FXML
    private void clickNewRentalNav() throws IOException {
        Parent customerFXML = App.loadFXML("NewRentCalculation");
        mainContentPayment.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickCurrentRentNav()throws IOException {
        Parent customerFXML = App.loadFXML("CurrentRentCalculation");
        mainContentPayment.getChildren().setAll(customerFXML);
    }

    @FXML
    private void back()throws IOException {
        App.setRoot("MainDashboard");
    }
}