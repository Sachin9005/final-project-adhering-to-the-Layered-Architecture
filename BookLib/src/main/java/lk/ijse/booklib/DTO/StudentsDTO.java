package lk.ijse.booklib.DTO;

public class StudentsDTO {
    private int id;
    private String name;

    public StudentsDTO() {
    }

    public StudentsDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudentsDTO(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "StudentsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
