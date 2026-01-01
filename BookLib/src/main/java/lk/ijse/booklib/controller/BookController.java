package lk.ijse.booklib.controller;

import lk.ijse.booklib.DTO.BooksDTO;
import lk.ijse.booklib.model.BookModel;

public class BookController {

    public static void saveBook(BooksDTO booksDTO){
        BookModel bookModel = new BookModel();
        try {
            boolean isSaved = bookModel.save(booksDTO);
            if(isSaved){
                System.out.println("Book saved successfully");
            }else {
                System.out.println("Book not saved successfully");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
