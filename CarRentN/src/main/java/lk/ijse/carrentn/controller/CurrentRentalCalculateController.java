package lk.ijse.carrentn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.dto.*;
import lk.ijse.carrentn.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;


public class CurrentRentalCalculateController implements Initializable {

    @FXML
    private Label balanceLable;

    @FXML
    private Label basePayField;

    @FXML
    private TextField customeNICField;

    @FXML
    private ComboBox<String> customerCbox;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerPaidAmountField;

    @FXML
    private Label daysLable;

    @FXML
    private Label discountLable;

    @FXML
    private Label driverNameField;

    @FXML
    private Label driverRateField;

    @FXML
    private Label driverTotalLable;

    @FXML
    private Label eDateLable;

    @FXML
    private TextField invoiceIDField;

    @FXML
    private Label lateDatesLable;

    @FXML
    private Label lateFeeLable;

    @FXML
    private Label modelLable;

    @FXML
    private Label remainAmountLable;

    @FXML
    private Label returnedDateLable;

    @FXML
    private Label sDateLable;

    @FXML
    private Label selectCustomerLable;

    @FXML
    private Label totalLable;

    @FXML
    private TextField vehicleDFField;

    @FXML
    private Label vehicleRateLable;

    @FXML
    private Label vehicleTotalLable;

    private int firstPayId;
    private int rentalId;
    private int lateDays;
    private String cusId;
    private double basePay;//first payment
    private double totalPay;//with fine and vehicle
    private double total;
    private double lateFee;
    private double discount;
    private double vehicleTotal;
    private double driverTotal;
    private double remainPay;

    private final String PAYMENT_REGEX = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";


    private CustomerModel customerModel = new CustomerModel();
    private RentalModel rentalModel = new RentalModel();
    private VehicleModel vehicleModel = new VehicleModel();
    private DriverModel driverModel = new DriverModel();
    private FirstPaymentModel firstPaymentModel = new FirstPaymentModel();
    private RentalDiscountModel rentalDiscountModel = new RentalDiscountModel();
    private LastPaymentModel lastPaymentModel = new LastPaymentModel();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        lordCustomerNames();
    }

    @FXML
    private void handleConfirmPay() {

        //vehicle and driver total - base payment
        double vdTotal = total - lateFee - basePay;

        //fine payment = late fee + vehicle damage fee
        double finePay = totalPay -(vehicleTotal+driverTotal);
        LastPaymentDTO lastPaymentDTO = new LastPaymentDTO(firstPayId,rentalId,lateDays,vdTotal,finePay,(totalPay-discount),LocalDate.now());
        try {
            boolean isSaved = lastPaymentModel.saveFullPayment(lastPaymentDTO);
            cleanFields();

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Payment Successful!").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Something Went Wrong!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void handleReset(ActionEvent event) {cleanFields();
    }

    @FXML
    private void handleSelectCustomer(ActionEvent event) {
        String cusName = customerCbox.getSelectionModel().getSelectedItem();
        customerNameField.setText(cusName);
        selectCustomerLable.setText("");
        cusId = customerModel.searchId(cusName);

    }

    public void searchRental(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                if (invoiceIDField.getText().isEmpty()) {
                    handleSearchRentByCustomerName();
                } else {
                    handleSerchRentByInvoiceId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchRentByCustomerName() {
        try {
            DriverDTO driverDTO = null;
            customeNICField.setText(customerModel.search(cusId).getNic_or_passport_number());
            RentalDTO rentalDTO = rentalModel.searchRent(cusId);

            rentalId = rentalDTO.getRental_id();

            VehicleDTO vehicleDTO = vehicleModel.search(String.valueOf(rentalDTO.getVehicle_id()));
            RentalDiscountDTO rentalDiscountDTO = rentalDiscountModel.searchRentalById(String.valueOf(rentalDTO.getRental_id()));

            modelLable.setText(vehicleDTO.getModel());
            vehicleRateLable.setText(String.valueOf(vehicleDTO.getRate_per_day()));

            String driverId;
            if (rentalDTO.getDriver_id() == 0) {
                driverId = null;
            } else {
                driverId = String.valueOf(rentalDTO.getDriver_id());
            }

            if (rentalDiscountDTO != null) {
                discount = (rentalDiscountModel.searchRentalById(String.valueOf(rentalDTO.getRental_id())).getDiscount_amount_applied()) + 0.00;
            } else {
                discount = 0.00;
            }

            if (driverId != null) {
                driverDTO = driverModel.search(driverId);
                driverNameField.setText(driverDTO.getName());
                driverRateField.setText(String.valueOf(driverDTO.getDriver_rate_per_day()));
            }

            vehicleTotal = calculateVehicleTotal(vehicleDTO, rentalDTO);
            driverTotal = calculateDriverTotal(driverDTO, rentalDTO);
            lateFee = calculateLateFee(rentalDTO, driverId);

            //total of vehicle, Driver and late fee
            total = vehicleTotal + driverTotal + lateFee;

            //first payment
            basePay = (firstPaymentModel.getFirstPayment(rentalDTO.getRental_id()).getBase_payment());

            firstPayId = firstPaymentModel.getFirstPayment(rentalDTO.getRental_id()).getFirst_payment_id();


            lateDatesLable.setText(String.valueOf(ChronoUnit.DAYS.between(rentalDTO.getReturn_date(), LocalDate.now())));
            daysLable.setText(String.valueOf(rentalDTO.getDates_of_rent()));
            sDateLable.setText(String.valueOf(rentalDTO.getStart_date()));
            eDateLable.setText(String.valueOf(rentalDTO.getReturn_date()));
            returnedDateLable.setText(String.valueOf(LocalDate.now()));
            basePayField.setText(String.valueOf(basePay)+0);
            discountLable.setText(String.valueOf(discount));
            vehicleTotalLable.setText(String.valueOf(vehicleTotal)+0);
            driverTotalLable.setText(String.valueOf(driverTotal)+0);
            lateFeeLable.setText(String.valueOf(lateFee)+0);

            lateDays = Integer.parseInt(lateDatesLable.getText());


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Or Rent!").show();
        }
    }
    @FXML
    private void handleSerchRentByInvoiceId() {

    }
    private void lordCustomerNames(){
        try {
            List<String> customerList = customerModel.getAllOCustomerNames();
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(customerList);
            customerCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private void cleanFields(){
        invoiceIDField.setText("");
        customerNameField.setText("");
        customeNICField.setText("");
        driverNameField.setText("-");
        driverRateField.setText("-");
        modelLable.setText("-");
        vehicleRateLable.setText("-");
        customerCbox.getSelectionModel().clearSelection();
        selectCustomerLable.setText("Select Customer");
        daysLable.setText("-");
        sDateLable.setText("-");
        eDateLable.setText("-");
        returnedDateLable.setText("-");
        lateDatesLable.setText("-");
        vehicleTotalLable.setText("-");
        driverTotalLable.setText("-");
        lateFeeLable.setText("-");
        discountLable.setText("-");
        basePayField.setText("-");
        remainAmountLable.setText("-");
        balanceLable.setText("-");
        totalLable.setText("-");
        vehicleDFField.setText("");
        customerPaidAmountField.setText("");
    }

    //payment for vehicle
    private double calculateVehicleTotal(VehicleDTO vehicleDTO,RentalDTO rentalDTO){
        double vehicleTotal = vehicleDTO.getRate_per_day()*rentalDTO.getDates_of_rent();
        return vehicleTotal;
    }

    //payment for Driver
    private double calculateDriverTotal(DriverDTO driverDTO,RentalDTO rentalDTO){
        double driverTotal = driverDTO.getDriver_rate_per_day()*rentalDTO.getDates_of_rent();
        return driverTotal;
    }

    //late payment fine
    private double calculateLateFee(RentalDTO rentalDTO,String driverId) {
        double lateFee = 0;
        int lateDates = Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(rentalDTO.getReturn_date(), LocalDate.now())));
        if (lateDates > 0 && lateDates != 1) {
            if (driverId != null) {
                lateFee =  (500.00 * lateDates) + (250 * lateDates);
            } else {
                lateFee =  (500.00 * lateDates);
            }
        }
        return lateFee;
    }

    //customer s' Balance
    @FXML
    private void calculateBalance(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            try {
                double balance = Math.round((Double.parseDouble(customerPaidAmountField.getText()) - remainPay) * 100.0) / 100.0;
                if (balance < 0 ){
                    new Alert(Alert.AlertType.ERROR, "Please input Valid Customer Paid Amount!").show();
                }else {
                    balanceLable.setText(String.valueOf(balance));
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Please input Customer Paid Amount!").show();
            }

        }   }

    //calculate total of vehicle ,driver and late fine
    @FXML
    private void calculateTotal(KeyEvent event) {
        String vehicleDFF = "";
        if (event.getCode() == KeyCode.ENTER) {
            String vehicleDFD= vehicleDFField.getText();
            if (vehicleDFD == PAYMENT_REGEX || vehicleDFField.getText() == "" || vehicleDFField.getText() == null ){

                if (vehicleDFField.getText() == "" || vehicleDFField.getText() == null) vehicleDFF = null;

                if (vehicleDFF != null) {
                    totalPay = Math.round(Double.parseDouble(vehicleDFField.getText()) + total * 100.0) / 100.0;
                    totalLable.setText(String.valueOf(totalPay)+0);
                } else {
                    totalPay = Math.round(total * 100.0) / 100.0;
                    vehicleDFField.setText("0.00");
                    totalLable.setText(String.valueOf(totalPay)+0);
                }
                remainPay = totalPay - basePay - discount;
                remainPay = Math.round(remainPay * 100.0) / 100.0;
                remainAmountLable.setText(String.valueOf(remainPay));
            }else {
                new Alert(Alert.AlertType.ERROR, "Please input valid fee Amount!").show();
            }

        }


    }

}


