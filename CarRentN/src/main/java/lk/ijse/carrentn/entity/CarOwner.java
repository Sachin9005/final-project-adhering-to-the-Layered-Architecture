package lk.ijse.carrentn.entity;

public class CarOwner {
    int owner_id;
    String name ;
    String phone;
    String bank_account;

    public CarOwner() {
    }

    public CarOwner(String name, String phone, String bank_account) {
        this.name = name;
        this.phone = phone;
        this.bank_account = bank_account;
    }

    public CarOwner(int owner_id, String name, String phone, String bank_account) {
        this.owner_id = owner_id;
        this.name = name;
        this.phone = phone;
        this.bank_account = bank_account;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    @Override
    public String toString() {
        return "CarOwner{" +
                "owner_id=" + owner_id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", bank_account='" + bank_account + '\'' +
                '}';
    }
}
