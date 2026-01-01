package lk.ijse.carrentn.dto;

import java.time.LocalDate;


public class RentalDTO {
    private int rental_id;
    private int customer_id;
    private int vehicle_id;
    private Integer driver_id;
    private LocalDate start_date;
    private int dates_of_rent;
    private LocalDate return_date;
    private double total;


    public RentalDTO() {
    }

    public RentalDTO(int customer_id, int vehicle_id, Integer driver_id, LocalDate start_date, int dates_of_rent, LocalDate return_date) {
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.driver_id = driver_id;
        this.start_date = start_date;
        this.dates_of_rent = dates_of_rent;
        this.return_date = return_date;
    }

    public RentalDTO(int rental_id, int customer_id, int vehicle_id, Integer driver_id, LocalDate start_date, int dates_of_rent, LocalDate return_date) {
        this.rental_id = rental_id;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.driver_id = driver_id;
        this.start_date = start_date;
        this.dates_of_rent = dates_of_rent;
        this.return_date = return_date;
    }

    public RentalDTO(int rental_id, int customer_id, int vehicle_id, Integer driver_id, LocalDate start_date, int dates_of_rent, LocalDate return_date, double total) {
        this.rental_id = rental_id;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.driver_id = driver_id;
        this.start_date = start_date;
        this.dates_of_rent = dates_of_rent;
        this.return_date = return_date;
        this.total = total;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
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
        return "RentalDTO{" +
                "rental_id=" + rental_id +
                ", customer_id=" + customer_id +
                ", vehicle_id=" + vehicle_id +
                ", driver_id=" + driver_id +
                ", start_date=" + start_date +
                ", dates_of_rent=" + dates_of_rent +
                ", return_date=" + return_date +
                '}';
    }
}
