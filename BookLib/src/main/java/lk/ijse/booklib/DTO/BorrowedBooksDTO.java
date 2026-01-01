package lk.ijse.booklib.DTO;

import java.time.LocalDate;

public class BorrowedBooksDTO {
    private int id;
    private int studentId;
    private int bookId;
    private LocalDate borrowedDate;

    public BorrowedBooksDTO(int studentId, int bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public BorrowedBooksDTO(int studentId, int bookId, LocalDate borrowedDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    @Override
    public String toString() {
        return "BorrowedBooksDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", bookId=" + bookId +
                ", borrowedDate=" + borrowedDate +
                '}';
    }
}
