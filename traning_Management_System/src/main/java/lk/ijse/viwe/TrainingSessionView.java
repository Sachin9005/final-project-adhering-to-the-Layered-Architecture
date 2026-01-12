package lk.ijse.viwe;

import lk.ijse.DTO.TrainingSessionsDTO;
import lk.ijse.controller.TrainingSessionController;

import java.util.Scanner;

public class TrainingSessionView {
    private final TrainingSessionController trainingSessionController = new TrainingSessionController();

    public void operationsView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Training Session Manage ===========");
        System.out.println("1. Assign Training Session");
        System.out.println("2. Remove Training Session");
        System.out.println("3. back");
        System.out.println("4.Exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        System.out.println("\n\n----------------------------------------------------------------");
        switch (choice){
            case 1 -> addTrainingSession();
            case 2 -> removeTrainingSession();
            case 3 -> MainView.showMenu();
            case 4 -> System.exit(0);
            default -> System.out.println("Wrong choice");
        }
    }

    public void addTrainingSession(){
        Scanner sc = new Scanner(System.in);
        System.out.println("======= Assign Training Session =============");
        System.out.print("Enter Member Id : ");
        int memberId = sc.nextInt();
        System.out.print("Enter Trainer Id : ");
        int trainerId = sc.nextInt();
        trainingSessionController.assignTrainer(new TrainingSessionsDTO(memberId,trainerId));
        System.out.println("\n");
        System.out.println("1.Assign Another Training Session");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> addTrainingSession();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");

    }

    public void removeTrainingSession(){
        Scanner sc = new Scanner(System.in);
        System.out.println("======= Remove Training Session =============");
        System.out.print("Enter Member Id : ");
        int memberId = sc.nextInt();
        System.out.print("Enter Trainer Id : ");
        int trainerId = sc.nextInt();
        trainingSessionController.removeTrainer(new TrainingSessionsDTO(memberId,trainerId));
        System.out.println("\n");
        System.out.println("1.Remove Another Training Session");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 ->removeTrainingSession();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }

    public void showAllTrainingSessions(){
        System.out.println("============= Training Sessions ==============\n");
        System.out.println("---------------------------------");
        System.out.println("|| Trainer Name || Member Name ||");
        System.out.println("---------------------------------");
        trainingSessionController.lordTrainingSession();
        System.out.println("---------------------------------");


    }
}
