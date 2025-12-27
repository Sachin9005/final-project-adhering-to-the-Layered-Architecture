package lk.ijse.carrentn.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.carrentn.App;
import lk.ijse.carrentn.dto.UserDTO;
import lk.ijse.carrentn.model.UserModel;

public class LoginController {
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField passwordShowField;
    @FXML
    private CheckBox checkBox;

    UserModel userModel = new UserModel();
    
    
    @FXML
        private void login() throws Exception {

        UserDTO userDTO = userModel.getUserDetails();

        String uName = userNameField.getText().trim();
        String pWord = passwordField.getText().trim();
        String sPWORD = passwordShowField.getText().trim();
        
        if((uName.equals(userDTO.getUsername()) && pWord.equals(userDTO.getPassword()))||(uName.equals(userDTO.getUsername()) && sPWORD.equals(userDTO.getPassword()))){
            System.out.println("Logged-in Successfully!");
            App.setRoot("MainDashboard");
            
        } else {
            System.out.println("Invalid User Name or Password!");
            new Alert(Alert.AlertType.ERROR,"Invalid user name or password").show();

        }
    }

    @FXML
    void changeVisibility() {
        if (checkBox.isSelected()){
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