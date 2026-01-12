package lk.ijse.viwe;

import lk.ijse.DTO.MemberDTO;
import lk.ijse.controller.MemberController;

import java.util.Scanner;

public class MemberView {
    private final MemberController memberController = new MemberController();

    public void oparesionsView(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Member Manage ===========");
        System.out.println("1. Add Member");
        System.out.println("2. Update Member");
        System.out.println("3. Delete Member");
        System.out.println("4. back");
        System.out.println("5.Exit");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 ->AddMember();
            case 2 ->UpdateMember();
            case 3 ->DeleteMember();
            case 4 ->MainView.showMenu();
            case 5 ->System.exit(0);
            default -> System.out.println("Wrong choice");
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }

    public void AddMember(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Add Member ===========");
        System.out.print("Enter Member Name :");
        String memberName = sc.nextLine();
        System.out.print("Enter Membership Type :");
        String memberMembershipType = sc.nextLine();
        memberController.saveMember(new MemberDTO(memberName,memberMembershipType));
        System.out.println("\n");
        System.out.println("1.Add Another Member");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> AddMember();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }
    public void UpdateMember(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Update Member ===========");
        System.out.print("Enter Member Id :");
        int memberId = sc.nextInt();
        System.out.print("Enter Member Name :");
        String memberName = sc.nextLine();
        System.out.print("Enter Membership Type :");
        String memberMembershipType = sc.nextLine();
        memberController.updateMember(new MemberDTO(memberId,memberName,memberMembershipType));
        System.out.println("\n");
        System.out.println("1.Update Another Member");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> UpdateMember();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }
    public void DeleteMember(){
        Scanner sc = new Scanner(System.in);
        System.out.println("=========== Delete Member ===========");
        System.out.print("Enter Member Id :");
        int memberId = sc.nextInt();
        memberController.deleteMember(memberId);
        System.out.println("\n");
        System.out.println("1.Delete Another Member");
        System.out.println("2.Back");
        System.out.print("Enter your choice :");
        int choice = sc.nextInt();
        switch (choice){
            case 1 -> DeleteMember();
            case 2 -> MainView.showMenu();
        }
        System.out.println("\n\n----------------------------------------------------------------");
    }
}
