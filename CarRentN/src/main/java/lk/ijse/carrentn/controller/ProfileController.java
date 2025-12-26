package lk.ijse.carrentn.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.carrentn.dto.UserDTO;
import lk.ijse.carrentn.model.UserModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable{

    @FXML
    private Label adressLable;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label emailLabe;
    @FXML
    private Label nameLable;
    @FXML
    private PasswordField passwordFeild;
    @FXML
    private TextField passwordShowField;
    @FXML
    private Label phoneNoLable;
    @FXML
    private Label roleLable;
    @FXML
    private Label useNameLable;

    private UserModel userModel = new UserModel();

    public void initialize(URL url, ResourceBundle rb){
        setDetails();
    }

    @FXML
    void changeVisibility() {
        if (checkBox.isSelected()){
            passwordShowField.setText(passwordFeild.getText());
            passwordShowField.setVisible(true);
            passwordFeild.setVisible(false);
            return;
        }
        passwordFeild.setText(passwordShowField.getText());
        passwordFeild.setVisible(true);
        passwordShowField.setVisible(false);
    }

    private void setDetails(){
        try {
            UserDTO userDTO =  userModel.getUserDetails();
            nameLable.setText(userDTO.getName());
            roleLable.setText(userDTO.getRole());
            useNameLable.setText(userDTO.getUsername());
            passwordFeild.setText(userDTO.getPassword());
            emailLabe.setText(userDTO.getEmail());
            phoneNoLable.setText("+94"+(String.valueOf(userDTO.getPhone_number())));
            adressLable.setText(userDTO.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}