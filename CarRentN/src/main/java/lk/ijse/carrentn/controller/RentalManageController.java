package lk.ijse.carrentn.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.dao.custom.*;
import lk.ijse.carrentn.dao.custom.impl.*;
import lk.ijse.carrentn.db.DBConnection;
import lk.ijse.carrentn.dto.*;
import lk.ijse.carrentn.dto.TM.DriverTM;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final String BASE_PAYMENT_REGEX = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";

    DiscountDAO discountDAO = new DiscountDAOImpl();
    RentalDAO rentalDAO = new RentalDAOImpl();
    CustomerDAO customerDAO = new CustomerDAOImpl();
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    DriverDAO driverDAO = new DriverDAOImpl();
    FirstPaymentDAO firstPaymentDAO = new FirstPaymentDAOImpl();
    RentalDiscountDAO rentalDiscountDAO = new RentalDiscountDAOImpl();
    LastPaymentDAO lastPaymentDAO = new LastPaymentDAOImpl();

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
        sDateField.setText(String.valueOf(LocalDate.now()));
        driverIdField.setText("");

        lordCustomerNames();
        lordDriverNames();
        lordVehicleNames();
        lordDiscountDes();
        lordRentalTable();

    }

    @FXML
    private void handleSaveRental() {

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
        }else if(!basePay.matches(BASE_PAYMENT_REGEX) || Integer.parseInt(basePay) < 2000) {
            new Alert(Alert.AlertType.ERROR, "Invalid Base Payment").show();
        }else{
            String total = totalPriceLable.getText();
            Integer driverIdValue = null;
            if (!driverId.isEmpty()) {
                driverIdValue = Integer.parseInt(driverId);
            }
            Integer discountId = null;

            if (discountDesc != null) {
                DiscountDTO discountDTO = discountDAO.searchId(discountDesc);
                if (discountDTO != null) {
                    discountId = discountDTO.getDiscount_id();
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

                boolean result = save(rentalDTO,Double.parseDouble(basePay),Double.parseDouble(total),discountId);
                sDateField.setText(String.valueOf(LocalDate.now()));

                if(result) {
                    System.out.println("Rental saved successfully!");
                    cleanFileds();
                    lordRentalTable();
                    lordVehicleNames();
                    lordDriverNames();
                    lordCustomerNames();
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
                    RentalDTO rentalDTO = rentalDAO.search(id);
                    //firstPaymentDAO.search(id).getFinal_payment()
                    if (rentalDTO != null) {
                        customerIdField.setText(String.valueOf(rentalDTO.getCustomer_id()));
                        vehicleIDField.setText(String.valueOf(rentalDTO.getVehicle_id()));

                        if (rentalDTO.getDriver_id() != null) {
                            driverIdField.setText(String.valueOf(rentalDTO.getDriver_id()));
                        } else {
                            driverIdField.setText("");
                        }
                        sDateField.setText(String.valueOf(rentalDTO.getStart_date()));
                        daysField.setText(String.valueOf(rentalDTO.getDates_of_rent()));
                        eDateField.setText(String.valueOf(rentalDTO.getReturn_date()));
                        basePayField.setText(String.valueOf(firstPaymentDAO.search(id).getBase_payment()));
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
    private void handlePrint() {
        try {
            rentalDAO.printBasePayInvoice(firstPaymentDAO.search(rentalDAO.getSaveLastRentalId()).getFirst_payment_id());
            //firstPaymentDAO.getFirstPayment(Integer.parseInt(rentalDAO.getSaveLastRentalId())).getFirst_payment_id()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteRental() {
        try {
            String id = retalIDField.getText();
            if (id.matches(RENTAL_ID_REGEX)) {
                boolean result2 = rentalDiscountDAO.deleteRentalDiscount(Integer.parseInt(id));
                boolean result3 = firstPaymentDAO.deleteFirstPayment(Integer.parseInt(id));
                boolean result4 = lastPaymentDAO.deleteLastPayment(Integer.parseInt(id));
                boolean result1 = rentalDAO.delete(id);
                lordRentalTable();
                if(result2 && result3 && result4 &&result1) {
                    System.out.println("Rental Delete successfully!");
                    cleanFileds();
                    lordRentalTable();
                    lordDriverNames();
                    lordVehicleNames();
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
    private void handleSelectCustomer() {
        customerIdField.clear();
        String cusName = customeCbox.getSelectionModel().getSelectedItem();

        List<String> customerHaveUnpaid = customerDAO.getCustomersNotPaidLast();
        if (customerHaveUnpaid.contains(cusName)) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Has Unpaid Rent!").show();
            customerLable.setText("");
            return;
        }
        customerIdField.setText(cusName);
        customerLable.setText("");

    }

    @FXML
    private void handleSelectDriver() {
        String driverName = driveerCbox.getSelectionModel().getSelectedItem();
        driverIdField.setText(driverName);
        driverLable.setText("");
    }

    @FXML
    private void handleSelectVehicle() {
        String vehicleModel = vehicleCbox.getSelectionModel().getSelectedItem();
        vehicleIDField.setText(vehicleModel);
        vehicleLable.setText("");
    }

    @FXML
    private void handleSelectDiscountId() {
        String discountDesc = comboDiscountId.getSelectionModel().getSelectedItem();
        DiscountDTO discount = discountDAO.searchId(discountDesc);
        discountLable.setText("");

        if (discount != null){
            double discountPrec = discount.getPercentage();
            double discountedTotal = Double.parseDouble(totalPrice) - ((Double.parseDouble(totalPrice)*discountPrec)/100);
            totalPriceLable.setText(String.valueOf(discountedTotal));
        }

    }

    @FXML
    private void calculateTotal(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            String vehicleId = vehicleDAO.searchId(vehicleIDField.getText());
            String driverId = driverDAO.searchId(driverIdField.getText());
            String days = daysField.getText().trim();

            if (vehicleId == null || vehicleId.isEmpty()) {
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

    private void lordCustomerNames(){
        try {
            List<CustomerDTO> customerList = customerDAO.getAllCustomer();
            List<String> customerNames = new ArrayList<>();
            for (CustomerDTO cust : customerList) {
                customerNames.add(cust.getName());
            }
            ObservableList<String> obList = FXCollections.observableArrayList();
            obList.addAll(customerNames);
            customeCbox.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
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
            List<DriverTM> driverList = driverDAO.getAvailableDrivers(LocalDate.now());
            List<String> driverNames = new ArrayList<>();
            for (DriverTM driver : driverList) {
                driverNames.add(driver.getName());
            }
            driveerCbox.getItems().addAll(driverNames);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private void lordDiscountDes(){
        try {
            List<DiscountDTO> discountList = discountDAO.getAll();
            List<String> discountDes = new ArrayList<>();
            for (DiscountDTO discount : discountList) {
                discountDes.add(discount.getDescription());
            }
            comboDiscountId.getItems().addAll(discountDes);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    private void lordRentalTable(){
        try {
            List<RentalDTO> rentalDTOS = rentalDAO.getAllRentals();
            ObservableList<RentalDTO> obList = FXCollections.observableArrayList();
            obList.addAll(rentalDTOS);
            tblRent.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculatetotal(String driverId, String vehicleId, int days){
        //total pay calcuulation
        double total = 0.0;
        String strdate = sDateField.getText();

        try {
            if (driverId.isEmpty()){
                total = vehicleDAO.searchPrioce(vehicleId) * days;
            }else{
                //with vehicle pay,discount,driver payment
                total = (vehicleDAO.searchPrioce(driverId) * days)+(driverDAO.search(driverId).getDriver_rate_per_day()*days);

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
        sDateField.setText(String.valueOf(LocalDate.now()));
    }

    public boolean save(RentalDTO rentalDTO,double basPay,double totalPay,Integer discountId) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);
            int rentalId = rentalDAO.getSaveId(rentalDTO);

            if (rentalId == 0) {
                throw new Exception("Failed to generate rental id");
            }
            boolean isBasePaySaved = firstPaymentDAO.saveBasePayment(rentalId, basPay, totalPay);

            boolean isDiscountSaved = true;
            if (discountId != null) {
                double disAmount = (totalPay * discountDAO.search(String.valueOf(discountId)).getPercentage())/100;
                isDiscountSaved = rentalDiscountDAO.saveRentalDiscount(new RentalDiscountDTO(rentalId, discountId, disAmount));
            }

            if (!isBasePaySaved && !isDiscountSaved) {
                throw new Exception("Something went Wrong");
            }
            conn.commit();
            return true;

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

    }

}