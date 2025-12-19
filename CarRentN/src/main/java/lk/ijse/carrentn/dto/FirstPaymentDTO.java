package lk.ijse.carrentn.dto;

import java.util.Date;

public class FirstPaymentDTO {
    private int first_payment_id;
    private int rental_id;
    private double base_payment;
    private double final_payment;
    private Date base_payment_date;

    public FirstPaymentDTO() {
    }

    public FirstPaymentDTO(int rental_id, double base_payment, double final_payment, Date base_payment_date) {
        this.rental_id = rental_id;
        this.base_payment = base_payment;
        this.final_payment = final_payment;
        this.base_payment_date = base_payment_date;
    }

    public FirstPaymentDTO(int first_payment_id, int rental_id, double base_payment, double final_payment, Date base_payment_date) {
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

    public double getBase_payment() {
        return base_payment;
    }

    public void setBase_payment(double base_payment) {
        this.base_payment = base_payment;
    }

    public double getFinal_payment() {
        return final_payment;
    }

    public void setFinal_payment(double final_payment) {
        this.final_payment = final_payment;
    }

    public Date getBase_payment_date() {
        return base_payment_date;
    }

    public void setBase_payment_date(Date base_payment_date) {
        this.base_payment_date = base_payment_date;
    }

    @Override
    public String toString() {
        return "FirstPaymentDTO{" +
                "first_payment_id=" + first_payment_id +
                ", rental_id=" + rental_id +
                ", base_payment=" + base_payment +
                ", final_payment=" + final_payment +
                ", base_payment_date=" + base_payment_date +
                '}';
    }
}
