package lk.ijse.viwe;

import java.util.Scanner;

public class MainView {

    public static void showMenu(){
        Scanner sc = new Scanner(System.in);
        final MemberView memberView = new MemberView();
        final TrainerView trainerView = new TrainerView();
        final TrainingSessionView trainingSessionView = new TrainingSessionView();

        System.out.println("===============================");
        System.out.println("Gym");
        System.out.println("==============================");
        System.out.println("1.Member Manage");
        System.out.println("2.Trainers Manage");
        System.out.println("3.Assignments Manage");
        System.out.println("4. Trainer Load");
        System.out.println("5.Exit");
        System.out.print("Choose your choice :");
        int choice = sc.nextInt();
        System.out.println("\n\n----------------------------------------------------------------");
        switch (choice){
            case 1 -> memberView.oparesionsView();
            case 2 -> trainerView.OparationsView();
            case 3 -> trainingSessionView.operationsView();
            case  4 -> trainingSessionView.showAllTrainingSessions();
            case 5 -> System.exit(0);
        }
    }

}
