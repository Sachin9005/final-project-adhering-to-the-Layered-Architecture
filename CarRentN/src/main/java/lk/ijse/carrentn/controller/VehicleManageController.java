package lk.ijse.carrentn.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import lk.ijse.carrentn.dto.CarOwnerDTO;
import lk.ijse.carrentn.dto.VehicleDTO;
import lk.ijse.carrentn.model.CarOwnerModel;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.model.VehicleModel;

public class VehicleManageController implements Initializable {
    @FXML
    private TextField vehicleNoField;
    @FXML
    private TextField ownerIdField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField manufacField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField dayRateFied;
    @FXML
    private TextField ownerRateField;
    @FXML
    private ChoiceBox<String> carOwners;
    @FXML
    private ChoiceBox<String> typeBox;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label typeLable;

    @FXML
    private TableView<VehicleDTO> tblVehicle;
    @FXML
    private TableColumn<CarOwnerDTO, Integer> colVID;
    @FXML
    private TableColumn<CarOwnerDTO , Integer> colOID;
    @FXML
    private TableColumn<CarOwnerDTO , String> colModel;
    @FXML
    private TableColumn<CarOwnerDTO , String> colManufac;
    @FXML
    private TableColumn<CarOwnerDTO , String> colType;
    @FXML
    private TableColumn<CarOwnerDTO , Double> colRate;
    @FXML
    private TableColumn<CarOwnerDTO , Double> colOPrec;
    @FXML
    private TableColumn<CarOwnerDTO , String> colVehicleNo;

    private final String VEHICLE_NO_REGEX = "^(?:[A-Z]{2,3}-\\d{3,4}|[A-Z]{2}-[A-Z]{2}-\\d{4})$";
    private final String VEHICLE_OWNE_ID_REGEX = "^[0-9]+$";
    private final String VEHICLE_MODEL_REGEX = "^[A-Za-z ]{2,50}$";
    private final String VEHICLE_MANUFACTURE_REGEX = "^[A-Za-z ]{2,50}$";
    private final String VEHICLE_TYPE_REGEX = "^[A-Za-z]{2,50}$";
    private final String VEHICLE_DAY_RATE_REGEX = "^[1-9][0-9]*(\\.[0-9]{1,2})?$";
    private final String VEHICLE_OWNER_PERCENTAGE_REGEX = "^(100(\\.0{1,2})?|[0-9]{1,2}(\\.[0-9]{1,2})?)$";

    CarOwnerModel carOwnerModel =  new CarOwnerModel();
    VehicleModel vehicleModel = new VehicleModel();
    String[] typeList = {"Car","Van","SUV"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Vehicle table loaded");
        colVID.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colOID.setCellValueFactory(new PropertyValueFactory<>("owner_id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colManufac.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("rate_per_day"));
        colOPrec.setCellValueFactory(new PropertyValueFactory<>("ownership_percentage"));

        setCarOwnersCheckBox();
        typeBox.getItems().addAll(typeList);

        carOwners.setOnAction(this::handleButtonAction1);
        typeBox.setOnAction(this::handleButtonAction2);

        lordVehicleTable();
    }

// to get choiceBox select item
    public void handleButtonAction1(ActionEvent event) {
        String carOwnerid = carOwners.getValue();
        ownerLabel.setText("");
        try {
            ownerIdField.setText(carOwnerModel.searchId(carOwnerid));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleButtonAction2(ActionEvent event) {
        String vehicleType = typeBox.getValue();
        typeLable.setText("");
        typeField.setText(vehicleType);
    }
// lord Owner choiceBox
    public void setCarOwnersCheckBox() {
        try {
            List<String> carOwnerNames = carOwnerModel.getAllOwnersIds();
            carOwners.getItems().setAll(carOwnerNames);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleSaveVehicle(){

        String vehicleNO = vehicleNoField.getText().trim();
        String ownerId = ownerIdField.getText().trim();
        String model = modelField.getText().trim();
        String manufac = manufacField.getText().trim();
        String type = typeField.getText().trim();
        String dayRate = dayRateFied.getText().trim();
        String ownerPrec = ownerRateField.getText().trim();


        if (!vehicleNO.matches(VEHICLE_NO_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Number").show();
        }else if (!ownerId.matches(VEHICLE_OWNE_ID_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Owner Id").show();
        }else if(!model.matches(VEHICLE_MODEL_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Model").show();
        }else if(!manufac.matches(VEHICLE_MANUFACTURE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Manufacture Name").show();
        }else if(!type.matches(VEHICLE_TYPE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Type").show();
        }else if(!dayRate.matches(VEHICLE_DAY_RATE_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Day Rate").show();
        }else if(!ownerPrec.matches(VEHICLE_OWNER_PERCENTAGE_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Ownership Percentage ").show();
        }else{
            try {
                VehicleDTO vehicleDTO = new VehicleDTO(vehicleNO,Integer.parseInt(ownerId),model,manufac,type,Double.parseDouble(dayRate),Double.parseDouble(ownerPrec));
                boolean result = vehicleModel.save(vehicleDTO);
                cleanFileds();
                lordVehicleTable();

                if(result) {
                    System.out.println("Vehicle saved successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Vehicle saved successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdateVehicle() {
        try {
            String vehicleNo = vehicleNoField.getText().trim();
            String ownerId = ownerIdField.getText().trim();
            String model = modelField.getText().trim();
            String manufacture = manufacField.getText().trim();
            String type = typeField.getText().trim();
            String dayRate = dayRateFied.getText().trim();
            String ownerPrec = ownerRateField.getText().trim();

            if (!vehicleNo.matches(VEHICLE_NO_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid vehicleNo").show();
            }else if (!ownerId.matches(VEHICLE_OWNE_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Owner Id").show();
            }else if(!model.matches(VEHICLE_MODEL_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Model").show();
            }else if(!manufacture.matches(VEHICLE_MANUFACTURE_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Manufacture Name").show();
            }else if(!type.matches(VEHICLE_TYPE_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Type").show();
            }else if(!dayRate.matches(VEHICLE_DAY_RATE_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid Vehicle Day Rate").show();
            }else if(!ownerPrec.matches(VEHICLE_OWNER_PERCENTAGE_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Ownership Percentage ").show();
            }else{
                VehicleDTO vehicleDTO = new VehicleDTO(vehicleNo,Integer.parseInt(ownerId),model, manufacture,type,Double.parseDouble(dayRate),Double.parseDouble(ownerPrec));
                boolean result = vehicleModel.update(vehicleDTO);
                cleanFileds();
                lordVehicleTable();

                if(result) {
                    System.out.println("Vehicle Update successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Vehicle update successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void handleDeleteVehicle() {
        try {
            String vehicleNo = vehicleNoField.getText();
            if (vehicleNo.matches(VEHICLE_NO_REGEX)) {
                boolean result = vehicleModel.delete(vehicleNo);
                lordVehicleTable();

                if(result) {
                    System.out.println("Vehicle Delete successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Vehicle Delete successfully!").show();
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

    public void handleSerchVehicle(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(event.getCode());
                String vehicleNo = vehicleNoField.getText();
                if (vehicleNo.matches(VEHICLE_NO_REGEX)) {
                    VehicleDTO vehicleDTO = vehicleModel.search(vehicleNo);

                    if (vehicleDTO != null) {
                        ownerIdField.setText(String.valueOf(vehicleDTO.getOwner_id()));
                        modelField.setText(vehicleDTO.getModel());
                        manufacField.setText(vehicleDTO.getManufacturer());
                        typeField.setText(vehicleDTO.getType());
                        dayRateFied.setText(String.valueOf(vehicleDTO.getRate_per_day()));
                        ownerRateField.setText(String.valueOf(vehicleDTO.getOwnership_percentage()));

                    } else {
                        new Alert(Alert.AlertType.ERROR, "Vehicle not found").show();
                    }
                }
                else{
                    new Alert(Alert.AlertType.ERROR, "Invalid Id").show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleResetlFields() {
        cleanFileds();
    }

    private void cleanFileds () {
        vehicleNoField.setText("");
        ownerIdField.setText("");
        modelField.setText("");
        manufacField.setText("");
        typeField.setText("");
        dayRateFied.setText("");
        ownerRateField.setText("");
        typeBox.setValue(null);
        carOwners.setValue(null);
        ownerLabel.setText("Select Owner");
        typeLable.setText("Select Type");

    }
    private void lordVehicleTable(){
        try {
            List<VehicleDTO> vehicleDTOS = vehicleModel.getAllVehicles();
            ObservableList<VehicleDTO> obList = FXCollections.observableArrayList();
            obList.addAll(vehicleDTOS);
            tblVehicle.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
