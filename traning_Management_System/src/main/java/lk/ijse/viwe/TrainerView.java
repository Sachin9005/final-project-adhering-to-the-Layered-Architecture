package lk.ijse.viwe;

import lk.ijse.DTO.TrainersDTO;
import lk.ijse.controller.TrainerController;

import java.util.Scanner;

public class TrainerView {
    private final TrainerController trainerController = new TrainerController();

    public void OparationsView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Trainer Manage ===========");
        System.out.println("1. Add Trainer");
        System.out.println("2. Update Trainer");
        System.out.println("3. Delete Trainer");
        System.out.println("4. back");
        System.out.println("5.Exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        System.out.println("\n\n----------------------------------------------------------------");
        switch (choice) {
            case 1 -> addTrainer();
            case 2 -> updateTrainer();
            case 3 -> deleteTrainer();
            case 4 -> MainView.showMenu();
            case 5 -> System.exit(0);
            default -> System.out.println("Wrong choice");
        }
    }

    public void addTrainer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Add Trainer ===========");
        System.out.print("Enter Trainer Name :");
        String trainerName = sc.nextLine();
        System.out.print("Enter Max Trainees");
        int maxTrainees = sc.nextInt();
        trainerController.saveTrainer(new TrainersDTO(trainerName,maxTrainees));
        System.out.println("\n");
        System.out.println("1.Add Another Trainer");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> addTrainer();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }

    public void updateTrainer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Update Trainer ===========");
        System.out.println("Enter Trainer Id :");
        int trainerId = sc.nextInt();
        System.out.print("Enter Trainer Name :");
        String trainerName = sc.nextLine();
        System.out.print("Enter Max Trainees");
        int maxTrainees = sc.nextInt();
        trainerController.updateTrainer(new TrainersDTO(trainerId,trainerName,maxTrainees));
        System.out.println("\n");
        System.out.println("1.Update Another Trainer");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> updateTrainer();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }

    private  void deleteTrainer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Update Trainer ===========");
        System.out.println("Enter Trainer Id :");
        int trainerId = sc.nextInt();
        trainerController.deleteTrainer(trainerId);
        System.out.println("\n");
        System.out.println("1.Delete Another Trainer");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> deleteTrainer();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }

}