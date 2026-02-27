package lk.ijse.carrentn.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LastPayment {
    int last_payment_id;
    int first_payment_id;
    int rental_id;
    int late_days;
    BigDecimal balance_payment;
    BigDecimal fine_payment;
    BigDecimal last_payment;
    LocalDate last_payment_date;

    public LastPayment() {
    }

    public LastPayment(int first_payment_id, int rental_id, int late_days, BigDecimal balance_payment, BigDecimal fine_payment, BigDecimal last_payment, LocalDate last_payment_date) {
        this.first_payment_id = first_payment_id;
        this.rental_id = rental_id;
        this.late_days = late_days;
        this.balance_payment = balance_payment;
        this.fine_payment = fine_payment;
        this.last_payment = last_payment;
        this.last_payment_date = last_payment_date;
    }

    public LastPayment(int last_payment_id, int first_payment_id, int rental_id, int late_days, BigDecimal balance_payment, BigDecimal fine_payment, BigDecimal last_payment, LocalDate last_payment_date) {
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

    public BigDecimal getBalance_payment() {
        return balance_payment;
    }

    public void setBalance_payment(BigDecimal balance_payment) {
        this.balance_payment = balance_payment;
    }

    public BigDecimal getFine_payment() {
        return fine_payment;
    }

    public void setFine_payment(BigDecimal fine_payment) {
        this.fine_payment = fine_payment;
    }

    public BigDecimal getLast_payment() {
        return last_payment;
    }

    public void setLast_payment(BigDecimal last_payment) {
        this.last_payment = last_payment;
    }

    public LocalDate getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(LocalDate last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    @Override
    public String toString() {
        return "LastPayment{" +
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
