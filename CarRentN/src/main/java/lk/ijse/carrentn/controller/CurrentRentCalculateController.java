package lk.ijse.carrentn.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CurrentRentCalculateController implements Initializable {

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
    private TextField totalField;

    @FXML
    private Label vehicleRateLable;

    @FXML
    private Label vehicleTotalLable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    @FXML
    void handleComfirmPay(ActionEvent event) {

    }

    @FXML
    void handleReset(ActionEvent event) {

    }
    @FXML
    void handleSelectCustomer(ActionEvent event) {

    }

    @FXML
    void handleSerchRentByCustomerName(KeyEvent event) {

    }

    @FXML
    void handleSerchRentByInvoiceId(KeyEvent event) {

    }


}
