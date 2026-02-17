package lk.ijse.carrentn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.App;
import lk.ijse.carrentn.dao.custom.DiscountDAO;
import lk.ijse.carrentn.dao.custom.DriverDAO;
import lk.ijse.carrentn.dao.custom.VehicleDAO;
import lk.ijse.carrentn.dao.impl.DiscountDAOImpl;
import lk.ijse.carrentn.dao.impl.DriverDAOImpl;
import lk.ijse.carrentn.dao.impl.VehicleDAOImpl;
import lk.ijse.carrentn.dto.TM.DriverTM;
import lk.ijse.carrentn.dto.TM.VehicleTM;
import lk.ijse.carrentn.model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class NewRentCalculateController implements Initializable {
    @FXML
    private TableColumn<DriverTM, Integer> colDriverId;
    @FXML
    private TableColumn<DriverTM, String>  colDriverName;
    @FXML
    private TableColumn<DriverTM, Double> colDriverRate;
    @FXML
    private TableColumn<DriverTM, String>  colDriverPhoneNo;

    @FXML
    private TableColumn<VehicleTM, Integer> colVehicleId;
    @FXML
    private TableColumn<VehicleTM, String> colVehicleNo;
    @FXML
    private TableColumn<VehicleTM, String>  colVehicleModel;
    @FXML
    private TableColumn<VehicleTM, String>  colVehicleType;
    @FXML
    private TableColumn<VehicleTM, Double>  colVehicleRate;

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

VehicleDAO vehicleDAO = new VehicleDAOImpl();
DriverDAO driverDAO = new DriverDAOImpl();
DiscountDAO discountDAO = new DiscountDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
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
    void handleRent()throws IOException {
       App.setRoot("ManageDetails");
    }

    @FXML
    private void handleReset() {cleanFileds();}

    @FXML
    private void handleSelectDiscount() {
        String discountDesc = discountCbox.getSelectionModel().getSelectedItem();
        String discoutID = discountDAO.searchId(discountDesc);
        discountIdField.setText(discountDesc);
        discountLable.setText("");
        if (discoutID != null){
            double discountPrec = discountDAO.searchDesForGetPrec(discountDesc);
            double discountedTotal = Double.parseDouble(totalPrice) - ((Double.parseDouble(totalPrice)*discountPrec)/100);
            totalPriceLable.setText(String.valueOf(discountedTotal));
        }
    }

    @FXML
    private void handleSelectDriver() {
        String driverName = driverCbox.getSelectionModel().getSelectedItem();
        driverIdField.setText(driverName);
        driverLable.setText("");
    }

    @FXML
    private void handleSelectVehicle() {
        String vehiclemodel = vehicleCbox.getSelectionModel().getSelectedItem();
        vehicleIdField.setText(vehiclemodel);
        vehicleLable.setText("");
    }

    private void lordVehicleNames(){
        try {
            List<String> vehicleList = vehicleDAO.getAvailableVehiclesNo(LocalDate.now());
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
            List<String> driverList = driverDAO.getAvailableDriverNames(LocalDate.now());
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
            List<String> discountList = discountDAO.getAllDiscountDes();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(discountList);
            discountCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private void calculatetotal(String driverId, String vehicleId, int days){
        //total pay calcuulation
        double total = 0.00;
        try {
            if (driverId.isEmpty()){
                total = vehicleDAO.searchPrioce(vehicleId) * days;
            }else{
                //with vehicle pay,discount,driver payment
                total = (vehicleDAO.searchPrioce(driverId) * days)+(driverDAO.searchRate(driverId)*days);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        total = Math.round(total * 100.0) / 100.0;
        totalPriceLable.setText(String.valueOf(total));
        totalPrice = totalPriceLable.getText();
    }

    @FXML
    private void calculateTotal(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String vehicleId = vehicleDAO.searchId(vehicleIdField.getText());
            String driverID = driverDAO.searchId(driverIdField.getText());
            String days = daysField.getText().trim();

            if (vehicleId.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Select a vehicle first").show();
                return;
            }

            if (!days.matches(DAY_REGEX)) {
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
            List<VehicleTM> vehicleTMS = vehicleDAO.getAvailableVehicles(LocalDate.now());
            ObservableList<VehicleTM> obList =FXCollections.observableArrayList();
            obList.addAll(vehicleTMS);
            tblAvaVehicles.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAvailableDrivers() {
        try {
            List<DriverTM> driverTMs = driverDAO.getAvailableDrivers(LocalDate.now());
            ObservableList<DriverTM> obList =FXCollections.observableArrayList();

            obList.addAll(driverTMs);
            tblAvaDrivers.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
