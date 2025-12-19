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
import lk.ijse.carrentn.dto.VehicleDTO;
import lk.ijse.carrentn.model.CarOwnerModel;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.ijse.carrentn.model.VehicleModel;

public class VehicleManageController implements Initializable {
    @FXML
    private TextField vehicleIdField;
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
    private TableView tblVehicle;
    @FXML
    private TableColumn colVID;
    @FXML
    private TableColumn colOID;
    @FXML
    private TableColumn colModel;
    @FXML
    private TableColumn colManufac;
    @FXML
    private TableColumn colType;
    @FXML
    private TableColumn colRate;
    @FXML
    private TableColumn colOPrec;

    private final String VEHICLE_ID_REGEX = "^[0-9]+$";
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
        String ownerId = ownerIdField.getText().trim();
        String model = modelField.getText().trim();
        String manufac = manufacField.getText().trim();
        String type = typeField.getText().trim();
        String dayRate = dayRateFied.getText().trim();
        String ownerPrec = ownerRateField.getText().trim();



        if (!ownerId.matches(VEHICLE_OWNE_ID_REGEX)) {
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
                VehicleDTO vehicleDTO = new VehicleDTO(Integer.parseInt(ownerId),model,manufac,type,Double.parseDouble(dayRate),Double.parseDouble(ownerPrec));
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
            String id = vehicleIdField.getText().trim();
            String ownerId = ownerIdField.getText().trim();
            String model = modelField.getText().trim();
            String manufac = manufacField.getText().trim();
            String type = typeField.getText().trim();
            String dayRate = dayRateFied.getText().trim();
            String ownerPrec = ownerRateField.getText().trim();

            if (!id.matches(VEHICLE_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
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
                VehicleDTO vehicleDTO = new VehicleDTO(Integer.parseInt(id),Integer.parseInt(ownerId),model,manufac,type,Double.parseDouble(dayRate),Double.parseDouble(ownerPrec));
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
            String id = vehicleIdField.getText();
            if (id.matches(VEHICLE_ID_REGEX)) {

                boolean result = vehicleModel.delete(id);
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
                String id = vehicleIdField.getText();
                if (id.matches(VEHICLE_ID_REGEX)) {
                    VehicleDTO vehicleDTO = vehicleModel.search(id);

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
        vehicleIdField.setText("");
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

            for (VehicleDTO vehicleDTO : vehicleDTOS) {
                obList.add(vehicleDTO);
            }
            tblVehicle.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
