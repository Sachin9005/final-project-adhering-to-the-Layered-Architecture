package lk.ijse.booklib.DTO;

public class BooksDTO {
    private int id;
    private String title;
    private int availableQuantity;

    public BooksDTO() {
    }

    public BooksDTO(int id, String title, int availableQuantity) {
        this.id = id;
        this.title = title;
        this.availableQuantity = availableQuantity;
    }

    public BooksDTO(String title, int availableQuantity) {
        this.title = title;
        this.availableQuantity = availableQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    @Override
    public String toString() {
        return "BooksDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
