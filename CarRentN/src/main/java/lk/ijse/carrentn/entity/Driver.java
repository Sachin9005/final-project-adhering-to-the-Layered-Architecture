package lk.ijse.carrentn.entity;

import java.math.BigDecimal;

public class Driver {
    int driver_id;
    String name;
    String phone_number;
    String license_number;
    BigDecimal driver_rate_per_day;

    public Driver() {
    }

    public Driver(String name, String phone_number, String license_number, BigDecimal driver_rate_per_day) {
        this.name = name;
        this.phone_number = phone_number;
        this.license_number = license_number;
        this.driver_rate_per_day = driver_rate_per_day;
    }

    public Driver(int driver_id, String name, String phone_number, String license_number, BigDecimal driver_rate_per_day) {
        this.driver_id = driver_id;
        this.name = name;
        this.phone_number = phone_number;
        this.license_number = license_number;
        this.driver_rate_per_day = driver_rate_per_day;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public BigDecimal getDriver_rate_per_day() {
        return driver_rate_per_day;
    }

    public void setDriver_rate_per_day(BigDecimal driver_rate_per_day) {
        this.driver_rate_per_day = driver_rate_per_day;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driver_id=" + driver_id +
                ", name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", license_number='" + license_number + '\'' +
                ", driver_rate_per_day=" + driver_rate_per_day +
                '}';
    }
}
