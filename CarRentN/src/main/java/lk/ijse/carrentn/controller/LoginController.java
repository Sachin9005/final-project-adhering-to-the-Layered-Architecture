package lk.ijse.carrentn.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.carrentn.App;

public class LoginController {
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    
    
    @FXML
        private void login() throws Exception {
        
        String realUserName = "1";
        String realPassword = "1";
        
        String uName = userNameField.getText();
        String pWord = passwordField.getText();
        
        if(uName.equals(realUserName) && pWord.equals(realPassword)){
            System.out.println("Logged-in Successfully!");
            App.setRoot("MainDashboard");
            
        } else {
            System.out.println("Invalid User Name or Password!");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Massage");
            alert.setHeaderText("Invalid user name or password");
            alert.show();
        }
    }
}