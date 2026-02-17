package lk.ijse.carrentn.controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.dao.custom.CustomerDAO;
import lk.ijse.carrentn.dao.impl.CustomerDAOImpl;
import lk.ijse.carrentn.dto.CarOwnerDTO;
import lk.ijse.carrentn.dto.CustomerDTO;


public class CustomerManageController implements Initializable {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phNoField;
    @FXML
    private TextField NicField;
    @FXML
    private TextArea addressField;
    @FXML
    private TableView<CustomerDTO> tblCustomer;
    @FXML
    private TableColumn<CarOwnerDTO, Integer> colId;
    @FXML
    private TableColumn<CarOwnerDTO , String> colName;
    @FXML
    private TableColumn<CarOwnerDTO , String> coleEmail;
    @FXML
    private TableColumn<CarOwnerDTO , String> colPNo;
    @FXML
    private TableColumn<CarOwnerDTO , String> colNOP;
    @FXML
    private TableColumn<CarOwnerDTO , String> colAddess;

    private final String CUSTOMER_ID_REGEX = "^[0-9]+$";
    private final String CUSTOMER_NAME_REGEX = "^[A-Za-z ]{2,50}$";
    private final String CUSTOMER_EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final String CUSTOMER_PHONE_NUMBER_REGEX = "^(?:\\+94|0)?7[0-9]{8}$";
    private final String CUSTOMER_NIC_OR_PASSPORT_NUMBER_REGEX = "^(([0-9]{9}[VvXx]|[0-9]{12})|[A-Za-z0-9]{5,15})$";
    private final String CUSTOMER_ADDRESS_REGEX = "^[A-Za-z0-9\\s,./\\-#]{5,150}$";

    CustomerDAO customerDAO = new CustomerDAOImpl();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("customer manage table loaded");
        colId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        coleEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPNo.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colNOP.setCellValueFactory(new PropertyValueFactory<>("nic_or_passport_number"));
        colAddess.setCellValueFactory(new PropertyValueFactory<>("address"));

        lordCustomerTable();
    }

    @FXML
    private void hanleSaveCustomer(){
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String pNO = phNoField.getText().trim();
        String nicOpass = NicField.getText().trim();
        String address = addressField.getText().trim();


        if (!name.matches(CUSTOMER_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Name").show();
        }else if(!email.matches(CUSTOMER_EMAIL_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Email").show();
        }else if(!pNO.matches(CUSTOMER_PHONE_NUMBER_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Phone Number").show();
        }else if(!nicOpass.matches(CUSTOMER_NIC_OR_PASSPORT_NUMBER_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer NIC or Passport Number").show();
        } else if(!address.matches(CUSTOMER_ADDRESS_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Adress").show();
        }else{
            try {
                CustomerDTO cusDTO = new CustomerDTO(name,email,pNO,nicOpass,address);
                boolean result = customerDAO.save(cusDTO);
                cleanFileds();
                lordCustomerTable();

                if(result) {
                    System.out.println("Customer saved successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleCustomerUpdate() {
        try {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String pNO = phNoField.getText().trim();
            String nicOpass = NicField.getText().trim();
            String address = addressField.getText().trim();


            if (!id.matches(CUSTOMER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Customer id").show();
            }else if(!name.matches(CUSTOMER_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Customer name").show();
            }else if(!email.matches(CUSTOMER_EMAIL_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Email").show();
            }else if(!pNO.matches(CUSTOMER_PHONE_NUMBER_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Phone Number").show();
            }else if (!nicOpass.matches(CUSTOMER_NIC_OR_PASSPORT_NUMBER_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Customer NIC or Passport Number").show();
            }else if(!address.matches(CUSTOMER_ADDRESS_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Customer Adress").show();
            }else{
                CustomerDTO cusDTO = new CustomerDTO(Integer.parseInt(id),name,email,pNO,nicOpass,address);

                boolean result = customerDAO.update(cusDTO);
                cleanFileds();
                lordCustomerTable();

                if(result) {
                    System.out.println("Customer Update successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Customer update successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void handleCustomerDelete() {
        try {
            String id = idField.getText();
            if (id.matches(CUSTOMER_ID_REGEX)) {

                boolean result = customerDAO.delete(id);
                lordCustomerTable();

                if(result) {
                    System.out.println("Customer Delete successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Customer Delete successfully!").show();
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

    public void handleSearchCustomer(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(event.getCode());
                String id = idField.getText();
                if (id.matches(CUSTOMER_ID_REGEX)) {

                    CustomerDTO cusDTO = customerDAO.search(id);

                    if (cusDTO != null) {
                        nameField.setText(cusDTO.getName());
                        emailField.setText(cusDTO.getEmail());
                        phNoField.setText(cusDTO.getPhone_number());
                        NicField.setText(cusDTO.getNic_or_passport_number());
                        addressField.setText(cusDTO.getAddress());
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Customer not found").show();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleReset() {
        cleanFileds();
    }

    private void cleanFileds () {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        phNoField.setText("");
        NicField.setText("");
        addressField.setText("");
    }

    private void lordCustomerTable(){
        try {

            List<CustomerDTO> cusDTO= customerDAO.getAllCustomer();
            ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();
            obList.addAll(cusDTO);
            tblCustomer.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}