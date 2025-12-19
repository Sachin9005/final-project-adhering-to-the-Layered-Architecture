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
import lk.ijse.carrentn.dto.DiscountDTO;
import lk.ijse.carrentn.model.DiscountModel;

public class DiscountManageController implements Initializable {
    @FXML
    private TextField discountIdFiled;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField percentageField;

    @FXML
    private TableView tblDiscounts;
    @FXML
    private TableColumn colDiscountId;
    @FXML
    private TableColumn colDescription;
    @FXML
    private TableColumn colPercentage;

    private final String DISCOUNT_ID_REGEX = "^[0-9]+$";
    private final String DISCOUNT_DESCRIPTION_REGEX = "^[A-Za-z0-9 ]{2,50}$";
    private final String DISCOUNT_PERCENTAGE_REGEX = "^(100(\\.0{1,2})?|[0-9]{1,2}(\\.[0-9]{1,2})?)$";

    DiscountModel discountModel =  new DiscountModel();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Discount table loaded");
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discount_id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPercentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        lordDiscountTable();
    }

    @FXML
    private void handleSaveDiscount(){
        String dis = descriptionField.getText().trim();
        String prec = percentageField.getText().trim();


        if (!dis.matches(DISCOUNT_DESCRIPTION_REGEX)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
        }else if(!prec.matches(DISCOUNT_PERCENTAGE_REGEX)){
            new Alert(Alert.AlertType.ERROR, "Invalid Percentage").show();
        }else{
            try {
                DiscountDTO discountDTO = new DiscountDTO(dis, Double.parseDouble(prec));
                boolean result = discountModel.save(discountDTO);
                cleanFileds();
                lordDiscountTable();

                if(result) {
                    System.out.println("Discount saved successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Discount saved successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdateDiscount() {
        try {
            String id = discountIdFiled.getText().trim();
            String name = descriptionField.getText().trim();
            String prec = percentageField.getText().trim();

            if (!id.matches(DISCOUNT_ID_REGEX)) {
                new Alert(Alert.AlertType.ERROR, "Invalid id").show();
            }else if(!name.matches(DISCOUNT_DESCRIPTION_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Description").show();
            }else if(!prec.matches(DISCOUNT_PERCENTAGE_REGEX)){
                new Alert(Alert.AlertType.ERROR, "Invalid Percentage").show();
            }else{
                DiscountDTO discountDTO = new DiscountDTO(Integer.parseInt(id),name, Double.parseDouble(prec));
                boolean result = discountModel.update(discountDTO);
                cleanFileds();
                lordDiscountTable();

                if(result) {
                    System.out.println("Discount Update successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Discount update successfully!").show();
                } else {
                    System.out.println("Sorry! Something went wrong!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();}
            }
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }

    public void handleDeleteDiscount() {
        try {
            String id = discountIdFiled.getText();
            if (id.matches(DISCOUNT_ID_REGEX)) {

                boolean result = discountModel.delete(id);
                lordDiscountTable();

                if(result) {
                    System.out.println("Discount Delete successfully!");
                    new Alert(Alert.AlertType.INFORMATION, "Discount Delete successfully!").show();
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

    public void handleSerchDiscount(KeyEvent event) {
        try {
            if (event.getCode() == KeyCode.ENTER) {
                System.out.println(event.getCode());
                String id = discountIdFiled.getText();
                if (id.matches(DISCOUNT_ID_REGEX)) {
                    DiscountDTO discountDTO = discountModel.search(id);

                    if (discountDTO != null) {
                        descriptionField.setText(discountDTO.getDescription());
                        percentageField.setText(String.valueOf(discountDTO.getPercentage()));
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Discount not found").show();
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
        discountIdFiled.setText("");
        descriptionField.setText("");
        percentageField.setText("");

    }
    private void lordDiscountTable(){
        try {
            List<DiscountDTO> discountDTOS = discountModel.getAllDiscounts();

            ObservableList<DiscountDTO> obList = FXCollections.observableArrayList();

            for (DiscountDTO discountDTO : discountDTOS) {
                obList.add(discountDTO);
            }
            tblDiscounts.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

