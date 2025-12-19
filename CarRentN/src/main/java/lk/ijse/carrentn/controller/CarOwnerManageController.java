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
import lk.ijse.carrentn.dto.CarOwnerDTO;
import lk.ijse.carrentn.model.CarOwnerModel;

public class CarOwnerManageController implements Initializable {
    @FXML
    private TextField ownerIdFiled;
    @FXML
    private TextField ownerNameFiled;
    @FXML
    private TextField phoneNoField;
    @FXML
    private TextField bankNoFielde;

    @FXML
    private TableView tblOwners;
    @FXML
    private TableColumn colOwnerId;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colPhone;
    @FXML
    private TableColumn colBank;

    private final String CAR_OWNER_ID_REGEX = "^[0-9]+$";
    private final String CAR_OWNER_NAME_REGEX = "^[A-Za-z ]{2,50}$";
    private final String CAR_OWNER_PHONE_NUMBER_REGEX = "^(?:\\+94|0)?7[0-9]{8}$";
    private final String CAR_OWNER_BANK_NAME_AND_NUMBER_REGEX = "^[A-Z]{3,}-[0-9]{4,12}$";

    CarOwnerModel carOwnerModel =  new CarOwnerModel();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("CAR OWNER table loaded");
        colOwnerId.setCellValueFactory(new PropertyValueFactory<>("owner_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colBank.setCellValueFactory(new PropertyValueFactory<>("bank_account"));

        lordCarOwnerTable();
    }

    @FXML
    private void handleSaveOwner(){
        String name = ownerNameFiled.getText().trim();
        String pNO = phoneNoField.getText().trim();
        String bankNO = bankNoFielde.getText().trim();


        if (!name.matches(CAR_OWNER_NAME_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
        }else if(!pNO.matches(CAR_OWNER_PHONE_NUMBER_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Phone Number").show();
        }else if(!bankNO.matches(CAR_OWNER_BANK_NAME_AND_NUMBER_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Bank Details").show();
        }else{
            try {
                CarOwnerDTO carOwnerDTO = new CarOwnerDTO(name,pNO, bankNO);
                boolean result = carOwnerModel.save(carOwnerDTO);
                cleanFileds();
                lordCarOwnerTable();

                if(result) {
                    System.out.println("Car Owner saved successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Car Owner saved successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdateOwner() {
        try {
            String id = ownerIdFiled.getText().trim();
            String name = ownerNameFiled.getText().trim();
            String pNO = phoneNoField.getText().trim();
            String bankAcc = bankNoFielde.getText().trim();

            if (!id.matches(CAR_OWNER_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            }else if(!name.matches(CAR_OWNER_NAME_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            }else if(!pNO.matches(CAR_OWNER_PHONE_NUMBER_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Phone Number").show();
            }else if (!bankAcc.matches(CAR_OWNER_BANK_NAME_AND_NUMBER_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Bank Details").show();
            }else{
                CarOwnerDTO carOwnerDTO = new CarOwnerDTO(Integer.parseInt(id),name,pNO, bankAcc);
                boolean result = carOwnerModel.update(carOwnerDTO);
                cleanFileds();
                lordCarOwnerTable();

                if(result) {
                    System.out.println("Car Owner Update successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Car Owner update successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void handleDeleteOwner() {
        try {
            String id = ownerIdFiled.getText();
            if (id.matches(CAR_OWNER_ID_REGEX)) {

                boolean result = carOwnerModel.delete(id);
                lordCarOwnerTable();

                if(result) {
                    System.out.println("Car Owner Delete successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Car Owner Delete successfully!").show();
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

    public void handleSerchOwnerFields(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(event.getCode());
                String id = ownerIdFiled.getText();
                if (id.matches(CAR_OWNER_ID_REGEX)) {
                    CarOwnerDTO carOwnerDTO = carOwnerModel.search(id);

                    if (carOwnerDTO != null) {
                        ownerNameFiled.setText(carOwnerDTO.getName());
                        phoneNoField.setText(carOwnerDTO.getPhone());
                        bankNoFielde.setText(carOwnerDTO.getBank_account());
                        } else {
                        new Alert(Alert.AlertType.ERROR, "Car Owner not found").show();
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
        ownerIdFiled.setText("");
        ownerNameFiled.setText("");
        phoneNoField.setText("");
        bankNoFielde.setText("");

    }
    private void lordCarOwnerTable(){
        try {
            List<CarOwnerDTO> carOwnerDTOS = carOwnerModel.getAllOwners();

            ObservableList<CarOwnerDTO> obList = FXCollections.observableArrayList();

            for (CarOwnerDTO carOwnerDTO : carOwnerDTOS) {
                obList.add(carOwnerDTO);
            }
            tblOwners.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
