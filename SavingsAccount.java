import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.03; // 3% Interest Rate
    private List<String> transactionHistory = new ArrayList<>(); // Transaction history log


    public SavingsAccount() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Account Holder's Name: ");
        String accountHolder = sc.nextLine();

        setAccountHolder(accountHolder);
        setAccountNumber(accountGenerator());
        setBalance(0.0);
        transactionHistory.add("Account created with initial balance: 0.0");

        System.out.println("Savings Account created successfully!");
        System.out.println("Account Holder: " + getAccountHolder());
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Initial Balance: " + getBalance());
    }

    // Override deposit method to log transactions
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        transactionHistory.add("Deposited: " + amount + ", New Balance: " + getBalance());
    }

    // Override withdraw method to log transactions
    @Override
    public void withdraw(double amount) throws Exception {
        super.withdraw(amount);
        transactionHistory.add("Withdrew: " + amount + ", New Balance: " + getBalance());
    }

    // Method to apply interest and log the transaction
    public void applyInterest() {
        if (getBalance() > 0) {
            double interest = getBalance() * INTEREST_RATE;
            setBalance(getBalance() + interest);
            transactionHistory.add("Applied interest: " + interest + ", New Balance: " + getBalance());
            System.out.println("Monthly interest of " + interest + " applied to account: " + getAccountNumber());
            System.out.println("New Balance: " + getBalance());
        } else {
            System.out.println("No interest applied. Balance is zero or negative.");
        }
    }


    public void transferTo(Account targetAccount, double amount) {
        try {
            super.transfer(targetAccount, amount); // Use the superclass transfer method
            transactionHistory.add("Transferred: " + amount + " to " + targetAccount.getAccountHolder());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void printTransactionHistory() {
        System.out.println("Transaction History for Account: " + getAccountNumber());
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
