/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package lk.ijse.carrentn.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import lk.ijse.carrentn.App;
import javafx.scene.layout.AnchorPane;

public class ManageDetailsController implements Initializable {

    @FXML
    public AnchorPane mainContent;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            clickRentalNav();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void clickCustomerNav() throws IOException {
        Parent customerFXML = App.loadFXML("CustomerManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickDriverNav() throws IOException {
        Parent customerFXML = App.loadFXML("DriverManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickDiscountNav() throws IOException {
        Parent customerFXML = App.loadFXML("DiscountManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickCarOwnerNav() throws IOException {
        Parent customerFXML = App.loadFXML("CarOwnerManage");
        mainContent.getChildren().setAll(customerFXML);
    }

   @FXML
    private void clickVehicleNav() throws IOException {
        Parent customerFXML = App.loadFXML("VehicleManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void clickRentalNav() throws IOException {
        Parent customerFXML = App.loadFXML("RentalManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void back() throws Exception {
        App.setRoot("MainDashboard");
    }



    
}
