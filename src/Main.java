import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        // Ask for initial balance and overdraft limit
        System.out.println("Please enter the initial amount for your account:");
        long initialBalance = sc.nextLong();

        System.out.println("Please enter the overdraft limit for your account:");
        double overdraftLimit = sc.nextDouble();

        // Create a ChequeAccount object
        ChequeAccount chqAcc = new ChequeAccount(initialBalance, overdraftLimit);

        do {
            System.out.println("\n------- Cheque Account Menu ------");
            System.out.println("1. Deposit Funds");
            System.out.println("2. Withdraw Funds");
            System.out.println("3. Issue a Cheque");
            System.out.println("4. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Please enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    chqAcc.deposit();  // Deposit funds into account
                    break;

                case 2:
                    chqAcc.withdrawal();  // Make a withdrawal with limits
                    break;

                case 3:
                    // Issue a cheque
                    System.out.print("Please enter the cheque amount: ");
                    double chequeAmount = sc.nextDouble();
                    try {
                        chqAcc.issueCheque(chequeAmount);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    chqAcc.displayAccountDetails();  // Show account details
                    break;



                case 6:
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();  // Close the scanner to prevent memory leaks
    }
}
