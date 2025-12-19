package lk.ijse.carrentn.dto;

import java.util.Date;

public class LastPaymentDTO {
    private int last_payment_id;
    private int first_payment_id;
    private int rental_id;
    private int late_days;
    private double balance_payment;
    private double fine_payment;
    private double last_payment;
    private Date last_payment_date;

    public LastPaymentDTO() {
    }

    public LastPaymentDTO(int first_payment_id, int rental_id, int late_days, double balance_payment, double fine_payment, double last_payment, Date last_payment_date) {
        this.first_payment_id = first_payment_id;
        this.rental_id = rental_id;
        this.late_days = late_days;
        this.balance_payment = balance_payment;
        this.fine_payment = fine_payment;
        this.last_payment = last_payment;
        this.last_payment_date = last_payment_date;
    }

    public LastPaymentDTO(int last_payment_id, int first_payment_id, int rental_id, int late_days, double balance_payment, double fine_payment, double last_payment, Date last_payment_date) {
        this.last_payment_id = last_payment_id;
        this.first_payment_id = first_payment_id;
        this.rental_id = rental_id;
        this.late_days = late_days;
        this.balance_payment = balance_payment;
        this.fine_payment = fine_payment;
        this.last_payment = last_payment;
        this.last_payment_date = last_payment_date;
    }

    public int getLast_payment_id() {
        return last_payment_id;
    }

    public void setLast_payment_id(int last_payment_id) {
        this.last_payment_id = last_payment_id;
    }

    public int getFirst_payment_id() {
        return first_payment_id;
    }

    public void setFirst_payment_id(int first_payment_id) {
        this.first_payment_id = first_payment_id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public void setRental_id(int rental_id) {
        this.rental_id = rental_id;
    }

    public int getLate_days() {
        return late_days;
    }

    public void setLate_days(int late_days) {
        this.late_days = late_days;
    }

    public double getBalance_payment() {
        return balance_payment;
    }

    public void setBalance_payment(double balance_payment) {
        this.balance_payment = balance_payment;
    }

    public double getFine_payment() {
        return fine_payment;
    }

    public void setFine_payment(double fine_payment) {
        this.fine_payment = fine_payment;
    }

    public double getLast_payment() {
        return last_payment;
    }

    public void setLast_payment(double last_payment) {
        this.last_payment = last_payment;
    }

    public Date getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(Date last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    @Override
    public String toString() {
        return "LastPaymentDTO{" +
                "last_payment_id=" + last_payment_id +
                ", first_payment_id=" + first_payment_id +
                ", rental_id=" + rental_id +
                ", late_days=" + late_days +
                ", balance_payment=" + balance_payment +
                ", fine_payment=" + fine_payment +
                ", last_payment=" + last_payment +
                ", last_payment_date=" + last_payment_date +
                '}';
    }
}
