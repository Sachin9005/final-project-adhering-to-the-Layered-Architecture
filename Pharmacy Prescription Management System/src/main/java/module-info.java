module lk.ijse.pharmacy_prescription_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;


    opens lk.ijse.pharmacy_prescription_management_system to javafx.fxml;
    exports lk.ijse.pharmacy_prescription_management_system;
}