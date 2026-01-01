package lk.ijse.booklib.view;

import lk.ijse.booklib.Launcher;
import lk.ijse.booklib.controller.StudentController;
import lk.ijse.booklib.model.StudentModel;

import java.util.Scanner;

public class StudentView {
    private StudentController studentController =  new StudentController();

    public void addView() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Add Student");
        System.out.println("-----------------------------");
        System.out.println("Enter Student Name");
        String name = sc.nextLine();
        studentController.saveStudent(name);

        System.out.println("1.add another Student");
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
