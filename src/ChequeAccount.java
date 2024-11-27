import java.util.Random;
import java.util.Scanner;

public class ChequeAccount extends Account {

    private static final double INTEREST_RATE = 0.03;  // Interest rate on withdrawals after the free limit
    private static final double MONTHLY_FEE = 50.0;  // Example monthly account fee
    private long balance;
    private double overdraftLimit;
    private int freeWithdrawals = 4;  // Free withdrawals per month

    // Constructor
    public ChequeAccount(double initialBalance, double overdraftLimit) {
        this.balance = (long) initialBalance;
        this.setAccountHolder(accountHolder);  // Assuming accountHolder is set somewhere in Account class
        this.setBalance(initialBalance);
        this.setAccountNumber(accountGenerator());
        this.overdraftLimit = overdraftLimit;
    }

    // Getters and Setters
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("\nAccount Details:");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Balance: " + balance);
        System.out.println("Overdraft Limit: " + overdraftLimit);
    }

    // Deposit method
    public void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the amount you want to deposit:");
        long dpAmount = sc.nextLong();
        balance += dpAmount;
        System.out.println("Deposit successful. Your new balance is: " + balance);
    }

    // Withdraw method with overdraft check
    public void withdraw(double amount) throws Exception {
        double availableBalance = balance + overdraftLimit;
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds, overdraft limit exceeded.");
        } else if (amount > 0) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }

    // Withdrawal with transaction limits and interest if exceeded
    public void withdrawal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the amount you want to withdraw:");
        long WAmount = sc.nextLong();

        if (freeWithdrawals > 0) {
            // Check if enough balance is available
            if (balance >= WAmount) {
                balance -= WAmount;
                freeWithdrawals--;  // Decrease the free withdrawals
                System.out.println("Withdrawal successful. Your new balance is: " + balance);
                System.out.println("Free withdrawals left: " + freeWithdrawals);
            } else {
                System.out.println("Insufficient funds for withdrawal. Transaction failed.");
            }
        } else {
            // If free withdrawals are over, apply interest
            if (balance >= WAmount) {
                double interest = WAmount * INTEREST_RATE;  // Apply interest on the withdrawal
                balance -= (WAmount + interest);  // Deduct amount + interest
                System.out.println("Withdrawal successful with interest. Your new balance is: " + balance);
                System.out.println("Free withdrawals exceeded, interest fee applied.");
            } else {
                System.out.println("Insufficient funds for withdrawal. Transaction failed.");
            }
        }
    }

    // Issue cheque method with overdraft check
    public void issueCheque(double amount) throws Exception {
        double availableBalance = balance + overdraftLimit;
        if (amount > availableBalance) {
            throw new Exception("Insufficient funds for cheque, overdraft limit exceeded.");
        } else {
            balance -= amount;
            System.out.println("Cheque issued for: " + amount);
            System.out.println("New balance: " + balance);
        }
    }


    // Account number generator
    @Override
    public String accountGenerator() {
        Random rand = new Random();
        long accountNumber = 1000000l + rand.nextLong(9000000);
        return String.valueOf(accountNumber);
    }
}