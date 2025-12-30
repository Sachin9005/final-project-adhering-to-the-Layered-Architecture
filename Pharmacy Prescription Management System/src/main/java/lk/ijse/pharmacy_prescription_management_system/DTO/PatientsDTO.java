package lk.ijse.pharmacy_prescription_management_system.DTO;

public class PatientsDTO {
    private int  id;
    private String name;
    private String contact;

    public PatientsDTO() {
    }

    public PatientsDTO(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public PatientsDTO(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "PatientsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
