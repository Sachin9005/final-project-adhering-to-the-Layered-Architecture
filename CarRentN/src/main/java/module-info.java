module lk.ijse.carrentn {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;
    requires net.sf.jasperreports.core;

    opens lk.ijse.carrentn.controller to javafx.fxml;
    opens lk.ijse.carrentn.dto to java.base;
    opens lk.ijse.carrentn.dto.TM to java.base;
    
    exports lk.ijse.carrentn;
    exports lk.ijse.carrentn.controller;
    exports lk.ijse.carrentn.dto;
    exports lk.ijse.carrentn.dto.TM;
    exports lk.ijse.carrentn.dao;

}
