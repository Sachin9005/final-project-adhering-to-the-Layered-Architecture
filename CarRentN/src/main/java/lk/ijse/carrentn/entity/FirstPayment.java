package lk.ijse.carrentn.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FirstPayment {
    int first_payment_id;
    int rental_id;
    BigDecimal base_payment;
    BigDecimal final_payment;
    LocalDate base_payment_date;

    public FirstPayment() {
    }

    public FirstPayment(int rental_id, BigDecimal base_payment, BigDecimal final_payment, LocalDate base_payment_date) {
        this.rental_id = rental_id;
        this.base_payment = base_payment;
        this.final_payment = final_payment;
        this.base_payment_date = base_payment_date;
    }

    public FirstPayment(int first_payment_id, int rental_id, BigDecimal base_payment, BigDecimal final_payment, LocalDate base_payment_date) {
        this.first_payment_id = first_payment_id;
        this.rental_id = rental_id;
        this.base_payment = base_payment;
        this.final_payment = final_payment;
        this.base_payment_date = base_payment_date;
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

    public BigDecimal getBase_payment() {
        return base_payment;
    }

    public void setBase_payment(BigDecimal base_payment) {
        this.base_payment = base_payment;
    }

    public BigDecimal getFinal_payment() {
        return final_payment;
    }

    public void setFinal_payment(BigDecimal final_payment) {
        this.final_payment = final_payment;
    }

    public LocalDate getBase_payment_date() {
        return base_payment_date;
    }

    public void setBase_payment_date(LocalDate base_payment_date) {
        this.base_payment_date = base_payment_date;
    }

    @Override
    public String toString() {
        return "FirstPayment{" +
                "first_payment_id=" + first_payment_id +
                ", rental_id=" + rental_id +
                ", base_payment=" + base_payment +
                ", final_payment=" + final_payment +
                ", base_payment_date=" + base_payment_date +
                '}';
    }
}
