package lk.ijse.carrentn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.dto.RentalDTO;
import lk.ijse.carrentn.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RentalManageController implements Initializable {

    @FXML
    private TextField basePayField;
    @FXML
    private TableColumn<?, ?> colCusId;
    @FXML
    private TableColumn<?, ?> colDays;
    @FXML
    private TableColumn<?, ?> colDriverId;
    @FXML
    private TableColumn<?, ?> colEDate;
    @FXML
    private TableColumn<?, ?> colRentalId;
    @FXML
    private TableColumn<?, ?> colSDate;
    @FXML
    private TableColumn<?, ?> colVehicleId;
    @FXML
    private TableView<RentalDTO> tblRent;
    @FXML
    private ComboBox<String> comboDiscountId;
    @FXML
    private ComboBox<String> customeCbox;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField daysField;
    @FXML
    private TextField driverIdField;
    @FXML
    private ComboBox<String> driveerCbox;
    @FXML
    private TextField eDateField;
    @FXML
    private TextField retalIDField;
    @FXML
    private TextField sDateField;
    @FXML
    private Label totalPriceLable;
    @FXML
    private ComboBox<String> vehicleCbox;
    @FXML
    private TextField vehicleIDField;
    @FXML
    private Label customerLable;
    @FXML
    private Label vehicleLable;
    @FXML
    private Label driverLable;
    @FXML
    private Label discountLable;

    private String totalPrice;


    private final String RENTAL_ID_REGEX = "^[0-9]+$";
    private final String CUSTOMER_ID_REGEX = "^[0-9]+$";
    private final String VEHICLE_ID_REGEX = "^[0-9]+$";
    private final String DRIVER_ID_REGEX = "^$|^[0-9]+$";
    private final String START_DATE_REGEX = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private final String DAY_REGEX = "^[0-9]+$";
    private final String RETURN_DATE_REGEX = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    private final String BASE_PAYMENT_REGEX = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";
    private final String DISCOUNT_PERCENTAGE_REGEX = "^(100(\\.0{1,2})?|[0-9]{1,2}(\\.[0-9]{1,2})?)$";


    RentalModel rentalModel =  new RentalModel();
    CustomerModel customerModel = new CustomerModel();
    VehicleModel vehicleModel = new VehicleModel();
    DriverModel driverModel = new DriverModel();
    DiscountModel discountModel = new DiscountModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("RentalManage is loaded");
        colRentalId.setCellValueFactory(new PropertyValueFactory<>("rental_id"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colVehicleId.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
        colSDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("dates_of_rent"));
        colEDate.setCellValueFactory(new PropertyValueFactory<>("return_date"));
        sDateField.setText(String.valueOf(LocalDate.now()).toString());
        driverIdField.setText("");

        lordCustomerNames();
        lordDriverNames();
        lordVehicleNames();
        lordDiscountDes();
        lordRentalTable();

    }

    public void setData(String vehicleId , String driverId ,String rentDays , String disId){
        vehicleIDField.setText(vehicleId);
        driverIdField.setText(driverId);
        daysField.setText(rentDays);
    }

    private void lordCustomerNames(){
        try {
            List<String> customerList = customerModel.getAllOCustomerNames();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(customerList);
            customeCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void lordVehicleNames(){
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

    public void lordDriverNames(){
        try {
            List<String> driverList = driverModel.getAllDriverNames();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(driverList);
            driveerCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void lordDiscountDes(){
        try {
            List<String> discountList = discountModel.getAllDiscountDes();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(discountList);
            comboDiscountId.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }


    @FXML
    private void handleSaveRental(ActionEvent event) {

        String customerId = customerIdField.getText().trim();
        String vehicleId = vehicleIDField.getText().trim();
        String driverId = driverIdField.getText().trim();
        String sDate = sDateField.getText().trim();
        String days = daysField.getText().trim();
        String basePay = basePayField.getText().trim();
        String edate = eDateField.getText().trim();
        String discountDesc = comboDiscountId.getSelectionModel().getSelectedItem();


        if (!customerId.matches(CUSTOMER_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Id").show();
        }else if(!vehicleId.matches(VEHICLE_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Id").show();
        }else if(!driverId.matches(DRIVER_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Driver Id").show();
        }else if(!sDate.matches(START_DATE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Start Date").show();
        }else if(!days.matches(DAY_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Rent Days").show();
        }else if(!basePay.matches(BASE_PAYMENT_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Base Payment").show();
        }else{
            String total = totalPriceLable.getText();



            Integer driverIdValue = null;
            if (!driverId.isEmpty()) {
                driverIdValue = Integer.parseInt(driverId);
            }

            Integer discountId = null;

            if (discountDesc != null) {
                String discIdStr = discountModel.searchId(discountDesc);
                if (discIdStr != null) {
                    discountId = Integer.parseInt(discIdStr);
                }
            }
            try {

                RentalDTO rentalDTO = new RentalDTO(
                        Integer.parseInt(customerId),
                        Integer.parseInt(vehicleId),
                        driverIdValue,
                        LocalDate.parse(sDate),
                        Integer.parseInt(days),
                        LocalDate.parse(edate));

                boolean result = rentalModel.save(rentalDTO,Double.parseDouble(basePay),Double.parseDouble(total),discountId);
                cleanFileds();
                lordRentalTable();
                sDateField.setText(String.valueOf(LocalDate.now()));


                if(result) {
                    System.out.println("Rental saved successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Rental saved successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleSearchCustomer() {
        try {
            String id = retalIDField.getText();
                if (id.matches(RENTAL_ID_REGEX)) {
                    RentalDTO rentalDTO = rentalModel.search(id);

                    if (rentalDTO != null) {
                        customerIdField.setText(String.valueOf(rentalDTO.getCustomer_id()));
                        vehicleIDField.setText(String.valueOf(rentalDTO.getVehicle_id()));

                        if (rentalDTO.getDriver_id() != null) {
                            driverIdField.setText(String.valueOf(rentalDTO.getDriver_id()));
                        } else {
                            driverIdField.setText("");
                        };
                        sDateField.setText(String.valueOf(rentalDTO.getStart_date()));
                        daysField.setText(String.valueOf(rentalDTO.getDates_of_rent()));
                        eDateField.setText(String.valueOf(rentalDTO.getReturn_date()));
                        basePayField.setText(String.valueOf(rentalDTO.getTotal()));
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Rental not found").show();
                    }
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdateRental(ActionEvent event) {

    }

    private void cleanFileds () {
        retalIDField.setText("");
        customerIdField.setText("");
        vehicleIDField.setText("");
        driverIdField.setText("");
        sDateField.setText("");
        daysField.setText("");
        eDateField.setText("");
        customeCbox.getSelectionModel().clearSelection();
        customerLable.setText("Select Customer");
        vehicleCbox.getSelectionModel().clearSelection();
        vehicleLable.setText("Select Vehicle");
        driveerCbox.getSelectionModel().clearSelection();
        driverLable.setText("Select Driver");
        comboDiscountId.getSelectionModel().clearSelection();
        discountLable.setText("Select Discount");
        basePayField.setText("");
        totalPriceLable.setText("-");
        sDateField.setText(String.valueOf(LocalDate.now()).toString());

    }

    @FXML
    private void handleDeleteRental() {
        try {
            String id = retalIDField.getText();
            if (id.matches(RENTAL_ID_REGEX)) {
                boolean result = rentalModel.delete(id);
                lordRentalTable();
                if(result) {
                    System.out.println("Rental Delete successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Rental Delete successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void handleResetlFields() {cleanFileds();}

    @FXML
    private void handleSelectCustomer(ActionEvent event) {
        String cusName = customeCbox.getSelectionModel().getSelectedItem();
        String cusId = customerModel.searchId(cusName);
        customerIdField.setText(cusId);
        customerLable.setText("");
    }
    @FXML
    private void handleSelectDriver(ActionEvent event) {
        String driverName = driveerCbox.getSelectionModel().getSelectedItem();
        String driverID = driverModel.searchId(driverName);
        driverIdField.setText(driverID);
        driverLable.setText("");

    }
    @FXML
    private void handleSelectVehicle(ActionEvent event) {
        String vehiclemodel = vehicleCbox.getSelectionModel().getSelectedItem();
        String vehicleId = vehicleModel.searchId(vehiclemodel);
        vehicleIDField.setText(vehicleId);
        vehicleLable.setText("");

    }

    @FXML
    private void handleSelectDiscountId(ActionEvent event) {
        String discountDesc = comboDiscountId.getSelectionModel().getSelectedItem();
        String discoutID = discountModel.searchId(discountDesc);
        discountLable.setText("");

        if (discoutID != null){
            double discountPrec = discountModel.searchDesForGetPrec(discountDesc);
            double discountedTotal = Double.parseDouble(totalPrice) - ((Double.parseDouble(totalPrice)*discountPrec)/100);
            totalPriceLable.setText(String.valueOf(discountedTotal));
        }

    }
    private void lordRentalTable(){
        try {
            List<RentalDTO> rentalDTOS = rentalModel.getAllRentals();

            ObservableList<RentalDTO> obList = FXCollections.observableArrayList();

            for (RentalDTO rentalDTO : rentalDTOS) {
                obList.add(rentalDTO);
            }
            tblRent.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private double calculatetotal(String driverId,String vehicleId,int days){
        //total pay calcuulation
        double total = 0.0;
        String strdate = sDateField.getText();

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
        totalPriceLable.setText(String.valueOf(total));
        totalPrice = String.valueOf(total);

        //date calculation
        LocalDate startDate = LocalDate.parse(strdate);
        LocalDate endDate = startDate.plusDays(days);
        eDateField.setText(String.valueOf(endDate));

        return total;
    }

    @FXML
    private void calculateTotal(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String vehicleId = vehicleIDField.getText().trim();
            String driverId = driverIdField.getText().trim();
            String days = daysField.getText().trim();

            if (vehicleId == null || vehicleId.isBlank()) {
                new Alert(Alert.AlertType.ERROR, "Select a vehicle first").show();
                return;
            }

            if (days == null || !days.matches(DAY_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Enter valid rent days").show();
                return;
            }

            // driver is OPTIONAL
            driverId = (driverId == null) ? "" : driverId.trim();

            calculatetotal(driverId, vehicleId.trim(), Integer.parseInt(days.trim()));
        }

    }

    }
