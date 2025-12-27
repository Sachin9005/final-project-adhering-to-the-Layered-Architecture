package lk.ijse.carrentn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.App;
import lk.ijse.carrentn.dto.TM.DriverTM;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.dto.VehicleDTO;
import lk.ijse.carrentn.model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class NewRentCalculateController implements Initializable {
    @FXML
    private TableColumn<?, ?> colDriverId;
    @FXML
    private TableColumn<?, ?> colDriverName;
    @FXML
    private TableColumn<?, ?> colDriverRate;
    @FXML
    private TableColumn<?, ?> colDriverPhoneNo;

    @FXML
    private TableColumn<?, ?> colVehicleId;
    @FXML
    private TableColumn<?, ?> colVehicleModel;
    @FXML
    private TableColumn<?, ?> colVehicleType;
    @FXML
    private TableColumn<?, ?> colVehicleRate;

    @FXML
    private TextField daysField;
    @FXML
    private ComboBox<String> discountCbox;
    @FXML
    private TextField discountIdField;
    @FXML
    private Label discountLable;
    @FXML
    private ComboBox<String> driverCbox;
    @FXML
    private TextField driverIdField;
    @FXML
    private Label driverLable;

    @FXML
    private TableView<DriverTM> tblAvaDrivers;
    @FXML
    private TableView<VehicleTM> tblAvaVehicles;

    @FXML
    private Label totalPriceLable;
    @FXML
    private ComboBox<String> vehicleCbox;
    @FXML
    private TextField vehicleIdField;
    @FXML
    private Label vehicleLable;

    String totalPrice;

    private final String DAY_REGEX = "^[0-9]+$";


VehicleModel vehicleModel = new VehicleModel();
DriverModel driverModel = new DriverModel();
DiscountModel discountModel = new DiscountModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVehicleModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colVehicleRate.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));

        colDriverId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDriverPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNO"));
        colDriverRate.setCellValueFactory(new PropertyValueFactory<>("dailyRate"));


        lordDriverNames();
        lordVehicleNames();
        lordDiscountDes();
        lordAvailableVehicleTable();
        loadAvailableDrivers();
    }

    @FXML
    void handleRent(ActionEvent event)throws IOException {
        String vehicleId = vehicleIdField.getText();
        String driverId = driverIdField.getText();
        String days = daysField.getText();
        String discount = discountIdField.getText();

        App.setRoot("ManageDetails");
    }

    @FXML
    private void handleReset(ActionEvent event) {cleanFileds();}

    @FXML
    private void handleSelectDiscount(ActionEvent event) {
        String discountDesc = discountCbox.getSelectionModel().getSelectedItem();
        String discoutID = discountModel.searchId(discountDesc);
        discountIdField.setText(discoutID);
        discountLable.setText("");

        if (discoutID != null){
            double discountPrec = discountModel.searchDesForGetPrec(discountDesc);
            double discountedTotal = Double.parseDouble(totalPrice) - ((Double.parseDouble(totalPrice)*discountPrec)/100);
            totalPriceLable.setText(String.valueOf(discountedTotal));
        }

    }

    @FXML
    private void handleSelectDriver(ActionEvent event) {
        String driverName = driverCbox.getSelectionModel().getSelectedItem();
        String driverIr = driverModel.searchId(driverName);
        driverIdField.setText(driverIr);
        driverLable.setText("");

    }

    @FXML
    private void handleSelectVehicle(ActionEvent event) {
        String vehiclemodel = vehicleCbox.getSelectionModel().getSelectedItem();
        String vehicleId = vehicleModel.searchId(vehiclemodel);
        vehicleIdField.setText(vehicleId);
        vehicleLable.setText("");
    }

    private void lordVehicleNames(){
        try {
            List<String> vehicleList = vehicleModel.getAllVehicleModels();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(vehicleList);
            vehicleCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private void lordDriverNames(){
        try {
            List<String> driverList = driverModel.getAllDriverNames();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(driverList);
            driverCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private void lordDiscountDes(){
        try {
            List<String> discountList = discountModel.getAllDiscountDes();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(discountList);
            discountCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private double calculatetotal(String driverId,String vehicleId,int days){
        //total pay calcuulation
        double total = 0.00;
        try {
            if (driverId.isEmpty()){
                total = vehicleModel.searchPrioce(vehicleId) * days;
            }else{
                //with vehicle pay,discount,driver payment
                total = (vehicleModel.searchPrioce(driverId) * days)+(driverModel.searchRate(driverId)*days);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        total = Math.round(total * 100.0) / 100.0;
        totalPriceLable.setText(String.valueOf(total));
        totalPrice = totalPriceLable.getText();
        return total;
    }

    @FXML
    private void calculateTotal(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String vehicleId = vehicleIdField.getText().trim();
            String driverID = driverIdField.getText();
            String days = daysField.getText().trim();

            if (vehicleId == null || vehicleId.isBlank()) {
                new Alert(Alert.AlertType.ERROR, "Select a vehicle first").show();
                return;
            }

            if (days == null || !days.matches(DAY_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Enter valid rent days").show();
                return;
            }
            String driverIdr;

            // driver is OPTIONAL
            driverIdr = (driverID == null) ? "" : driverID.trim();

            calculatetotal(driverIdr, vehicleId.trim(), Integer.parseInt(days.trim()));
        }

    }

    private void cleanFileds () {

        vehicleIdField.setText("");
        driverIdField.setText("");
        daysField.setText("");
        vehicleCbox.getSelectionModel().clearSelection();
        vehicleLable.setText("Select Vehicle");
        driverCbox.getSelectionModel().clearSelection();
        driverLable.setText("Select Driver");
        discountCbox.getSelectionModel().clearSelection();
        discountLable.setText("Select Discount");
        totalPriceLable.setText("-");

    }

    private void lordAvailableVehicleTable(){
        try {
            List<VehicleTM> vehicleTMS = vehicleModel.getAvailableVehicles(LocalDate.now());
            ObservableList<VehicleTM> obList =FXCollections.observableArrayList();
            for (VehicleTM vehicleTM : vehicleTMS) {
                obList.add(vehicleTM);
            }
            tblAvaVehicles.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAvailableDrivers() {
        try {
            List<DriverTM> driverTMs = DriverModel.getAvailableDrivers(LocalDate.now());

            ObservableList<DriverTM> obList =FXCollections.observableArrayList();

            for (DriverTM driverTM : driverTMs){
                obList.add(driverTM);
            }
            tblAvaDrivers.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
