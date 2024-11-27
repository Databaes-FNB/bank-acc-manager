import java.util.Scanner;


    public class ChequeAccount {

    Scanner sc = new Scanner(System.in);

    private static final double INTEREST_RATE = 0.03;
    private long balance = 0;
    private int transactionCount;

    long WAmount;
    long DpAmount;


        public ChequeAccount(int initialBalance) {
            this.balance = initialBalance;
            this.transactionCount = 0;
        }

        public void deposit ()
    {


        System.out.println("Please enter the amount you want to deposit");
        DpAmount = sc.nextLong();
        balance = balance + DpAmount;
        System.out.println("Balance after deposit: " + balance);
    }

    public void withdrawal ()
    {

        int limitBalance = 4;

        System.out.println("Please enter the amount you want to withdraw");
        WAmount = sc.nextLong();
        for (int i = 0; i > limitBalance ; i--) {


            if (balance >= WAmount )
            {
                balance = balance - WAmount;
                System.out.println("Balance after withdrawal: " + balance);
                System.out.println("Your free amount of withdrawals left: " + i);
            }else
            {
                System.out.println("Your balance is less than " + WAmount + "\tTransaction failed!!");
            }

        }
        if (balance >= WAmount ) {
            balance = balance - WAmount;
            System.out.println("Balance after withdrawal: " + (balance - (balance * INTEREST_RATE)));
            System.out.println("Your free amount of withdrawals left: " + limitBalance);

        } else {
            System.out.println("Your balance is less than " + WAmount + "\tTransaction failed!!");
        }



    }
}



