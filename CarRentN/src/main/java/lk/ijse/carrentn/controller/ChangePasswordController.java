package lk.ijse.carrentn.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.carrentn.dao.custom.UserDAO;
import lk.ijse.carrentn.dao.impl.UserDAOImpl;
import lk.ijse.carrentn.dto.UserDTO;

import java.sql.SQLException;

public class ChangePasswordController {
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField cnpField;
    @FXML
    private PasswordField conPassField;
    @FXML
    private TextField cpField;
    @FXML
    private PasswordField newPassField;
    @FXML
    private TextField npfield;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    private final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    UserDAO userDAO = new UserDAOImpl();

    @FXML
    private void changeVisibility() {
        if (checkBox.isSelected()){
            cpField.setText(passwordField.getText());
            cpField.setVisible(true);
            passwordField.setVisible(false);

            npfield.setText(newPassField.getText());
            npfield.setVisible(true);
            newPassField.setVisible(false);

            cnpField.setText(conPassField.getText());
            cnpField.setVisible(true);
            conPassField.setVisible(false);
            return;
        }
        passwordField.setText(cpField.getText());
        passwordField.setVisible(true);
        cpField.setVisible(false);

        newPassField.setText(npfield.getText());
        newPassField.setVisible(true);
        npfield.setVisible(false);

        conPassField.setText(cnpField.getText());
        conPassField.setVisible(true);
        cnpField.setVisible(false);
    }

    @FXML
    private void handleConfirm() {
        try {
            UserDTO userDTO = userDAO.getUserDetails();

            String username = usernameField.getText().trim();
            String password = passwordField.getText();
            String pass = cpField.getText();

            String newPassword = newPassField.getText();
            String np = npfield.getText();

            String conNewPass = conPassField.getText();
            String cnp = cnpField.getText();

            if ((username.equals(userDTO.getUsername()) && password.equals(userDTO.getPassword())) || (username.equals(userDTO.getUsername()) && pass.equals(userDTO.getPassword()))){
                if (newPassword.matches(PASSWORD_REGEX)||np.matches(PASSWORD_REGEX)){
                    if(checkBox.isSelected()){
                        changePassword(cnp,np);
                    }else {
                        changePassword(conNewPass,newPassword);
                    }
                }else{
                    new Alert(Alert.AlertType.ERROR,"Password is not strong enough").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Invalid Username or Password").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReset() {cleanFields();}

    private void cleanFields(){
        usernameField.setText("");
        passwordField.setText("");
        newPassField.setText("");
        conPassField.setText("");
        cpField.setText("");
        npfield.setText("");
        cnpField.setText("");
        checkBox.setSelected(false);
    }

    private void changePassword(String confirmPass , String newPass)throws SQLException {
        if (confirmPass.equals(newPass)){
            boolean isChanged= userDAO.updatePassword(newPass);
            if (isChanged){
                new Alert(Alert.AlertType.INFORMATION, "Password Change successfully!").show();
                cleanFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something Went Wrong!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"New Password Field and Confirm Password Field Doesn't Matches").show();
        }
    }
}
