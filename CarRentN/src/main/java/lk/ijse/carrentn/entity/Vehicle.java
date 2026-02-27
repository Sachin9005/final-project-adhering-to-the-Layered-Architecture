package lk.ijse.carrentn.entity;

import java.math.BigDecimal;

public class Vehicle {
    int vehicle_id;
    int owner_id;
    String model;
    String manufacturer;
    String type;
    BigDecimal rate_per_day;
    BigDecimal ownership_percentage;
    String vehicle_No;

    public Vehicle() {
    }

    public Vehicle(int owner_id, String model, String manufacturer, String type, BigDecimal rate_per_day, BigDecimal ownership_percentage, String vehicle_No) {
        this.owner_id = owner_id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.rate_per_day = rate_per_day;
        this.ownership_percentage = ownership_percentage;
        this.vehicle_No = vehicle_No;
    }

    public Vehicle(int vehicle_id, int owner_id, String model, String manufacturer, String type, BigDecimal rate_per_day, BigDecimal ownership_percentage, String vehicle_No) {
        this.vehicle_id = vehicle_id;
        this.owner_id = owner_id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.type = type;
        this.rate_per_day = rate_per_day;
        this.ownership_percentage = ownership_percentage;
        this.vehicle_No = vehicle_No;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getRate_per_day() {
        return rate_per_day;
    }

    public void setRate_per_day(BigDecimal rate_per_day) {
        this.rate_per_day = rate_per_day;
    }

    public BigDecimal getOwnership_percentage() {
        return ownership_percentage;
    }

    public void setOwnership_percentage(BigDecimal ownership_percentage) {
        this.ownership_percentage = ownership_percentage;
    }

    public String getVehicle_No() {
        return vehicle_No;
    }

    public void setVehicle_No(String vehicle_No) {
        this.vehicle_No = vehicle_No;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + vehicle_id +
                ", owner_id=" + owner_id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", rate_per_day=" + rate_per_day +
                ", ownership_percentage=" + ownership_percentage +
                ", vehicle_No='" + vehicle_No + '\'' +
                '}';
    }
}
