import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.03; // 3% Interest Rate
    private List<String> transactionHistory = new ArrayList<>(); // Transaction history log

    public SavingsAccount() {
        super();  // Calls the constructor of Account
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;  // Reject negative or zero deposit amounts
        }
        super.deposit(amount);
        transactionHistory.add("Deposited: " + amount + ", New Balance: " + getBalance());
    }


    @Override
    public void withdraw(double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Amount must be greater than zero.");
        }
        super.withdraw(amount);  // Calling the superclass method to handle balance updates
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

    // Transfer method for Savings Account to another Account
    public void transferTo(Account targetAccount, double amount) {
        try {
            super.transfer(targetAccount, amount); // Use the superclass transfer method
            transactionHistory.add("Transferred: " + amount + " to " + targetAccount.getAccountHolder());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Print transaction history with a dialog box in the GUI
    public void printTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History for Account: " + getAccountNumber() + "\n");

        if (transactionHistory.isEmpty()) {
            history.append("No transactions recorded.");
        } else {
            for (String transaction : transactionHistory) {
                history.append(transaction).append("\n");
            }
        }

        // Show the history in a dialog box
        JTextArea textArea = new JTextArea(history.toString());
        textArea.setEditable(false);  // Make it non-editable
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
    }

}


