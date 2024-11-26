import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.03; // 3% Interest Rate
    private List<String> transactionHistory = new ArrayList<>(); // Transaction history log

    // Constructor
    public SavingsAccount(String accountHolder, double initialDeposit) {
        setAccountHolder(accountHolder);
        setAccountNumber(generateAccountNumber());
        setBalance(initialDeposit);
        transactionHistory.add("Account created with initial deposit: " + initialDeposit);
        System.out.println("Savings Account created successfully!");
        System.out.println("Account Holder: " + getAccountHolder());
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Initial Balance: " + getBalance());
    }

    // Generate a unique account number for Savings Account
    private String generateAccountNumber() {
        Random random = new Random();
        return "SAV" + (100000 + random.nextInt(900000));
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

    // Method to print the transaction history
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
