package lk.ijse.carrentn.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carrentn.App;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileManageController implements Initializable {
    @FXML
    private AnchorPane mainContent;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        try {
            handleProfileShow();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCredentialShow()throws IOException {
        Parent customerFXML = App.loadFXML("CredentialManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void handleProfileShow() throws IOException{
        Parent customerFXML = App.loadFXML("ProfileShow");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void changePasswordNav() throws IOException{
        Parent customerFXML = App.loadFXML("PasswordManage");
        mainContent.getChildren().setAll(customerFXML);
    }

    @FXML
    private void back()throws IOException {
        App.setRoot("MainDashboard");
    }

}
