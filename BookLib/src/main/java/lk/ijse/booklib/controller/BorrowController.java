package lk.ijse.booklib.controller;

import lk.ijse.booklib.DTO.BooksDTO;
import lk.ijse.booklib.DTO.BorrowedBooksDTO;
import lk.ijse.booklib.DTO.StudentsDTO;
import lk.ijse.booklib.model.BookModel;
import lk.ijse.booklib.model.BorrowedModel;
import lk.ijse.booklib.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class BorrowController {
    private final BorrowedModel borrowedModel = new BorrowedModel();
    private final StudentModel studentModel = new StudentModel();
    private final BookModel bookModel = new BookModel();

    public void saveBorrow(BorrowedBooksDTO borrowedBooksDTO){
        try {
            boolean isSaved = borrowedModel.save(borrowedBooksDTO);
            if(isSaved){
                System.out.println("Book Borrow Successfully");
            }else {
                System.out.println("Book Borrow Failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void returnBorrowedBooks(BorrowedBooksDTO borrowedBooksDTO){
        try {
            boolean idReturned = borrowedModel.delete(borrowedBooksDTO);
            if(idReturned){
                System.out.println("Book Returned Successfully");
            }else  {
                System.out.println("Book Returned Failed");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getBorrowedBooks() {
        try {
            List<BorrowedBooksDTO> borrowedBooks = borrowedModel.getAllBorrowedBooks();
            for (BorrowedBooksDTO dto : borrowedBooks) {

                StudentsDTO student = studentModel.search(dto.getStudentId());
                BooksDTO book = bookModel.findById(dto.getBookId());
                String studentName = (student != null) ? student.getName() : "Unknown Student";
                String bookTitle = (book != null) ? book.getTitle() : "Unknown Book";

                System.out.printf("%-20s %-20s%n", studentName, bookTitle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
