package lk.ijse.booklib.view;

import lk.ijse.booklib.DTO.BooksDTO;
import lk.ijse.booklib.Launcher;
import lk.ijse.booklib.controller.BookController;

import java.util.Scanner;

public class BookViwe {
    public void addView() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Add Book");
        System.out.println("-----------------------------");
        System.out.println("Enter Book title");
        String name = sc.nextLine();
        System.out.println("Enter Book available quantity");
        int quantity = sc.nextInt();
        BookController.saveBook(new BooksDTO(name,quantity));

        System.out.println("1.add another Book");
        System.out.println("2.back");
        System.out.println("3.exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> addView();
            case 2 -> Launcher.MainView();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }
}
