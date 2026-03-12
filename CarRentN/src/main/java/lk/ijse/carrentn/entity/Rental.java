package lk.ijse.carrentn.entity;

import java.time.LocalDate;

public class Rental {
    int rental_id;
    int customer_id;
    Integer vehicle_id;
    int driver_id;
    LocalDate start_DATE;
    int dates_of_rent;
    LocalDate return_date;

    public Rental() {
    }

    public Rental(int customer_id, int vehicle_id, Integer driver_id, LocalDate start_DATE, int dates_of_rent, LocalDate return_date) {
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.driver_id = driver_id;
        this.start_DATE = start_DATE;
        this.dates_of_rent = dates_of_rent;
        this.return_date = return_date;
    }

    public Rental(int rental_id, int customer_id, Integer vehicle_id, int driver_id, LocalDate start_DATE, int dates_of_rent, LocalDate return_date) {
        this.rental_id = rental_id;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.driver_id = driver_id;
        this.start_DATE = start_DATE;
        this.dates_of_rent = dates_of_rent;
        this.return_date = return_date;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
    }

    public LocalDate getStart_DATE() {
        return start_DATE;
    }

    public void setStart_DATE(LocalDate start_DATE) {
        this.start_DATE = start_DATE;
    }

    public int getDates_of_rent() {
        return dates_of_rent;
    }

    public void setDates_of_rent(int dates_of_rent) {
        this.dates_of_rent = dates_of_rent;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rental_id=" + rental_id +
                ", customer_id=" + customer_id +
                ", vehicle_id=" + vehicle_id +
                ", driver_id=" + driver_id +
                ", start_DATE=" + start_DATE +
                ", dates_of_rent=" + dates_of_rent +
                ", return_date=" + return_date +
                '}';
    }
}
