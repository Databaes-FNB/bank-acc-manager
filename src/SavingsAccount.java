import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.05; // Example interest rate for savings account
    private AccountDAO accountDAO; // Instance of AccountDAO to interact with the database

    // Constructor with initial balance and account number
    public SavingsAccount(double initialBalance) {
        super(initialBalance);  // Call the parent constructor to set the balance
        accountDAO = new AccountDAO();  // Initialize the AccountDAO to interact with the database
    }

    // Constructor with default balance of 0
    public SavingsAccount() {
        super(0.0);  // Default balance is 0
        accountDAO = new AccountDAO();  // Initialize the AccountDAO to interact with the database
    }

    // Implement deposit method
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        double newBalance = getBalance() + amount;
        setBalance(newBalance);  // Update the balance after deposit

        // Update the balance in the database
        accountDAO.updateBalance(getAccountNumber(), getBalance());
    }

    // Implement withdraw method
    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be greater than zero.");
        }
        if (getBalance() < amount) {
            throw new Exception("Insufficient balance.");
        }
        setBalance(getBalance() - amount);  // Update balance after withdrawal

        // Update the balance in the database
        accountDAO.updateBalance(getAccountNumber(), getBalance());
    }

    // Override applyInterest method
    @Override
    public void applyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        setBalance(getBalance() + interest);  // Apply interest to the balance

        // Update the balance in the database after applying interest
        accountDAO.updateBalance(getAccountNumber(), getBalance());

        System.out.println("Interest applied. New balance: " + getBalance());
    }
}
