module lk.ijse.carrentn {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;

    opens lk.ijse.carrentn.controller to javafx.fxml;
    opens lk.ijse.carrentn.dto to java.base;
    
    exports lk.ijse.carrentn;
    exports lk.ijse.carrentn.controller;
    exports lk.ijse.carrentn.dto;

}
