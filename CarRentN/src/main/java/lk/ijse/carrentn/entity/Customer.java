package lk.ijse.carrentn.entity;

public class Customer {
    int customer_id;
    String name;
    String email;
    String phone_number;
    String nic_or_passport_number;
    String address;

    public Customer() {
    }

    public Customer(String name, String email, String phone_number, String nic_or_passport_number, String address) {
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.nic_or_passport_number = nic_or_passport_number;
        this.address = address;
    }

    public Customer(int customer_id, String name, String email, String phone_number, String nic_or_passport_number, String address) {
        this.customer_id = customer_id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.nic_or_passport_number = nic_or_passport_number;
        this.address = address;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNic_or_passport_number() {
        return nic_or_passport_number;
    }

    public void setNic_or_passport_number(String nic_or_passport_number) {
        this.nic_or_passport_number = nic_or_passport_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", nic_or_passport_number='" + nic_or_passport_number + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
