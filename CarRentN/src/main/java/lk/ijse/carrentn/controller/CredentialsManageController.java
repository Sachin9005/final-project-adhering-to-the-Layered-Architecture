package lk.ijse.carrentn.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.carrentn.dao.custom.UserDAO;
import lk.ijse.carrentn.dao.impl.UserDAOImpl;
import lk.ijse.carrentn.dto.UserDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class CredentialsManageController implements Initializable {

    @FXML
    private TextArea addressField;

    @FXML
    private Label addressLabel;

    @FXML
    private CheckBox checkBox;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordShowField;

    @FXML
    private TextField phoneNoField;

    @FXML
    private Label phoneNoLabel;

    private final String NAME_REGEX = "^([A-Z]\\.\\s)?[A-Za-z]+(\\s[A-Za-z]+)+$";
    private final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final String PHONE_NUMBER_REGEX = "^(?:\\+94|0)?7[0-9]{8}$";
    private final String ADDRESS_REGEX = "^[A-Za-z0-9\\s,./\\-#]{5,150}$";

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabels();
    }

    @FXML
    private void handleChanges() {
       try {
           String name = nameField.getText().trim();
           String email = emailField.getText().trim();
           String pNo = phoneNoField.getText().trim();
           String address = addressField.getText().trim();
           String password = userDAO.getUserDetails().getPassword().trim();

           boolean isUpdate;

           if (name.isEmpty()){
               name = null;
           }
           if (email.isEmpty()){
               email = null;
           }
           if (pNo.isEmpty()){
               pNo = null;
           }
           if (address.isEmpty()){
               address = null;
           }

           if ((passwordField.getText().trim()).equals(password) || passwordShowField.getText().trim().equals(password)){
               if (name != null){
                   System.out.println(name);
                   if (name.matches(NAME_REGEX)){
                       isUpdate = userDAO.updateName(name.trim());
                       if (isUpdate){
                           new Alert(Alert.AlertType.INFORMATION, "Update successfully!").show();
                           setLabels();
                           cleanFields();
                       }else{
                           new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                       }
                   }else {
                       new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
                   }
               }
               if (email != null){
                   if (email.matches(EMAIL_REGEX)){
                       isUpdate = userDAO.updateEmail(email.trim());
                       if (isUpdate){
                           new Alert(Alert.AlertType.INFORMATION, "Update successfully!").show();
                           setLabels();
                           cleanFields();
                       }else{
                           new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                       }
                   }else {
                       new Alert(Alert.AlertType.ERROR, "Invalid Email").show();
                   }
               }
               if (pNo != null){
                   if (pNo.matches(PHONE_NUMBER_REGEX)){
                       isUpdate = userDAO.updatePhoneNo(pNo.trim());
                       if (isUpdate){
                           new Alert(Alert.AlertType.INFORMATION, "Update successfully!").show();
                           setLabels();
                           cleanFields();
                       }else{
                           new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                       }
                   }else {
                       new Alert(Alert.AlertType.ERROR, "Invalid Phone Number").show();
                   }
               }
               if (address != null){
                   if (address.matches(ADDRESS_REGEX)){
                       isUpdate = userDAO.updateaddress(address.trim());
                       if (isUpdate){
                           new Alert(Alert.AlertType.INFORMATION, "Update successfully!").show();
                           setLabels();
                           cleanFields();
                       }else{
                           new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
                       }
                   }else {
                       new Alert(Alert.AlertType.ERROR, "Invalid Address").show();
                   }
               }

           }else {
               new Alert(Alert.AlertType.ERROR, "Incorrect Password!").show();
           }


       } catch (Exception e) {
           e.printStackTrace();
       }

    }

    @FXML
    private void handleReset() {
        cleanFields();
    }

    private void setLabels(){
        try {
            UserDTO userDTO = userDAO.getUserDetails();
            nameLabel.setText(userDTO.getName());
            emailLabel.setText(userDTO.getEmail());
            phoneNoLabel.setText("+94"+String.valueOf(userDTO.getPhone_number()));
            addressLabel.setText(userDTO.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cleanFields(){
        nameField.setText("");
        emailField.setText("");
        phoneNoField.setText("");
        addressField.setText("");
        passwordField.setText("");
        passwordShowField.setText("");
        checkBox.setSelected(false);
    }

    @FXML
    private void changeVisibility() {
        if (checkBox.isSelected()) {
            passwordShowField.setText(passwordField.getText());
            passwordShowField.setVisible(true);
            passwordField.setVisible(false);
            return;
        }
        passwordField.setText(passwordShowField.getText());
        passwordField.setVisible(true);
        passwordShowField.setVisible(false);
    }
}
