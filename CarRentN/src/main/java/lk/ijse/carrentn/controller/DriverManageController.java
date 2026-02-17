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
import lk.ijse.carrentn.dao.custom.DriverDAO;
import lk.ijse.carrentn.dao.impl.DriverDAOImpl;
import lk.ijse.carrentn.dto.CarOwnerDTO;
import lk.ijse.carrentn.dto.DriverDTO;


public class DriverManageController implements Initializable {
    @FXML
    private TextField driverIdField;
    @FXML
    private TextField nameFiled;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField licenseField;
    @FXML
    private TextField rateFiled;

    @FXML
    private TableView<DriverDTO> tblDrivers;
    @FXML
    private TableColumn<CarOwnerDTO, Integer> colDriverId;
    @FXML
    private TableColumn<CarOwnerDTO , String> colName;
    @FXML
    private TableColumn<CarOwnerDTO , String> colPhone;
    @FXML
    private TableColumn<CarOwnerDTO , String> colLicense;
    @FXML
    private TableColumn<CarOwnerDTO , Double> colRate;

    private final String DRIVER_ID_REGEX = "^[0-9]+$";
    private final String DRIVER_NAME_REGEX = "^[A-Za-z ]{2,50}$";
    private final String DRIVER_PHONE_NUMBER_REGEX = "^(?:\\+94|0)?7[0-9]{8}$";
    private final String DRIVER_LICENSE_NUMBER_REGEX = "^[A-Z]{1,4}[0-9]{6,10}$";
    private final String DRIVER_RATE_REGEX = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";

    DriverDAO driverDAO = new DriverDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Driver table loaded");
        colDriverId.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colLicense.setCellValueFactory(new PropertyValueFactory<>("license_number"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("driver_rate_per_day"));
        lordDriverTable();
    }

    @FXML
    private void handleSaveDriver(){
        String name = nameFiled.getText().trim();
        String pNO = phoneField.getText().trim();
        String licenNo = licenseField.getText().trim();
        String rate = rateFiled.getText().trim();

        if (!name.matches(DRIVER_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
        }else if(!pNO.matches(DRIVER_PHONE_NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Phone Number").show();
        }else if(!licenNo.matches(DRIVER_LICENSE_NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid License Number").show();
        }else if(!rate.matches(DRIVER_RATE_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Driver Rate Per Day").show();
        }else{
            try {
                DriverDTO driverDTO = new DriverDTO(name,pNO,licenNo,Double.parseDouble(rate));
                boolean result = driverDAO.save(driverDTO);
                cleanFileds();
                lordDriverTable();

                if(result) {
                    System.out.println("Driver saved successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Driver saved successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdateDriver() {
        try {
            String id = driverIdField.getText().trim();
            String name = nameFiled.getText().trim();
            String pNO = phoneField.getText().trim();
            String liceNo = licenseField.getText().trim();
            String rate = rateFiled.getText().trim();

            if (!id.matches(DRIVER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            }else if(!name.matches(DRIVER_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            }else if(!pNO.matches(DRIVER_PHONE_NUMBER_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Phone Number").show();
            }else if(!liceNo.matches(DRIVER_LICENSE_NUMBER_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid License Number").show();
            }else if (!rate.matches(DRIVER_RATE_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Driver Rate Per Day ").show();
            }else{
                DriverDTO carOwnerDTO = new DriverDTO(Integer.parseInt(id),name,pNO,liceNo,Double.parseDouble(rate));
                boolean result = driverDAO.update(carOwnerDTO);
                cleanFileds();
                lordDriverTable();

                if(result) {
                    System.out.println("Driver Update successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Driver update successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void handleDeleteDriver() {
        try {
            String id = driverIdField.getText();
            if (id.matches(DRIVER_ID_REGEX)) {

                boolean result = driverDAO.delete(id);
                lordDriverTable();

                if(result) {
                    System.out.println("Driver Delete successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Driver Delete successfully!").show();
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

    public void handleSerchDriverFields(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(event.getCode());
                String id = driverIdField.getText();
                if (id.matches(DRIVER_ID_REGEX)) {
                    DriverDTO carOwnerDTO = driverDAO.search(id);
                    if (carOwnerDTO != null) {
                        nameFiled.setText(carOwnerDTO.getName());
                        phoneField.setText(carOwnerDTO.getPhone_number());
                        licenseField.setText(carOwnerDTO.getLicense_number());
                        rateFiled.setText(String.valueOf(carOwnerDTO.getDriver_rate_per_day()));
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Driver not found").show();
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleResetFields() {
        cleanFileds();
    }

    private void cleanFileds () {
        driverIdField.setText("");
        nameFiled.setText("");
        phoneField.setText("");
        licenseField.setText("");
        rateFiled.setText("");

    }
    private void lordDriverTable(){
        try {
            List<DriverDTO> driverDTOS = driverDAO.getAllDrivers();
            ObservableList<DriverDTO> obList = FXCollections.observableArrayList();
            obList.addAll(driverDTOS);
            tblDrivers.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
