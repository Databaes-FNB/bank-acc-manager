import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Please enter the initial amount");
        long initialBalance = sc.nextLong();

        ChequeAccount chqAcc = new ChequeAccount((int) initialBalance);

       do {
            System.out.println("\n------- Cheque Account Menu ------");
            System.out.println("1.  Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3  Exit");
            System.out.println("Please enter your choice:  ");
            choice = sc.nextInt();

            switch (choice){

                case 1:
                    chqAcc.deposit();
                    break;

                case 2:
                    chqAcc.withdrawal();
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");

            }

        } while (choice != 3);
       sc.close();



    }
}