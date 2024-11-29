import java.util.Random;

public abstract class Account implements AccountInterface {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor to initialize balance
    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    // Default constructor (if needed)
    public Account() {
        this.balance = 0.0; // Set balance to 0 if no initial balance is provided
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Abstract method from AccountInterface
    @Override
    public abstract void deposit(double amount);

    @Override
    public abstract void withdraw(double amount) throws Exception;

    // Method to generate a random account number
    public String accountGenerator() {
        Random rand = new Random();
        int accountNumber = rand.nextInt(1000000000);  // Random account number of 10 digits
        return String.format("%010d", accountNumber);  // Return the account number as a 10-digit string
    }

    // Method to apply interest, to be implemented in specific account types (e.g., SavingsAccount)
    public void applyInterest() {
        // Default implementation (if needed, otherwise can be overridden)
        System.out.println("Interest applied to account.");
    }

    // Transfer method for transferring between accounts
    @Override
    public void transfer(Account accType, double amount) throws Exception {
        if (this.getBalance() < amount) {
            throw new Exception("Insufficient funds for transfer.");
        }
        this.setBalance(this.getBalance() - amount);  // Deduct from current account
        accType.setBalance(accType.getBalance() + amount);  // Add to the target account
    }
}
