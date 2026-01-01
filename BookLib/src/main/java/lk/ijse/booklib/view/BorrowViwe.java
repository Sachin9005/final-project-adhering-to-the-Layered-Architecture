package lk.ijse.booklib.view;

import lk.ijse.booklib.DTO.BorrowedBooksDTO;
import lk.ijse.booklib.Launcher;
import lk.ijse.booklib.controller.BorrowController;

import java.time.LocalDate;
import java.util.Scanner;

public class BorrowViwe {
    private final BorrowController borrowController =  new BorrowController();

    public void borrow(){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter Student ID");
        int studentID = sc.nextInt();
        System.out.println("Enter Book ID");
        int bookID = sc.nextInt();
        borrowController.saveBorrow(new BorrowedBooksDTO(studentID,bookID, LocalDate.now()));

        System.out.println("----------------------------------------");
        System.out.println("1.add another borrowed Book");
        System.out.println("2.back");
        System.out.println("3.exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> borrow();
            case 2 -> Launcher.MainView();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }

    public void returnBorrow(){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("Enter Student ID");
        int studentID = sc.nextInt();
        System.out.println("Enter Book ID");
        int bookID = sc.nextInt();
        borrowController.returnBorrowedBooks(new BorrowedBooksDTO(studentID,bookID)
        );

        System.out.println("1.Another Return borrowed Book");
        System.out.println("2.back");
        System.out.println("3.exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> returnBorrow();
            case 2 -> Launcher.MainView();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid choice");
        }
    }
    public void borrowListView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n===== Borrowings =====");
        System.out.printf("%-20s %-20s \n",
                "Student Name", "Book title");
        System.out.println("----------------------------------------");
        borrowController.getBorrowedBooks();
        System.out.println("----------------------------------------");
        System.out.println("1.back");
        System.out.println("2.exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> Launcher.MainView();
            case 2 -> System.exit(0);
            default -> Launcher.MainView();
        }
    }
}
