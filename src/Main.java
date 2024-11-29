import javax.swing.*;
import java.awt.*;

public class Main {
    private static SavingsAccount savingsAccount;
    private static ChequeAccount chequeAccount;
    private static AccountDAO accountDAO = new AccountDAO();  // Create an AccountDAO instance

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Bank Account Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // Create the main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Define layout constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title label
        JLabel titleLabel = new JLabel("Bank Account Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Account Holder Name Input
        JLabel nameLabel = new JLabel("Enter your name & surname: ");
        JTextField nameField = new JTextField(20);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Account creation buttons
        JButton createSavingsButton = new JButton("Create Savings Account");
        JButton createChequeButton = new JButton("Create Cheque Account");
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(createSavingsButton, gbc);
        gbc.gridx = 1;
        panel.add(createChequeButton, gbc);

        // Operation buttons
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton viewBalanceButton = new JButton("View Balance");
        JButton viewDetailsButton = new JButton("View Details");
        JButton applyInterestButton = new JButton("Apply Interest");
        JButton exitButton = new JButton("Exit");

        // Initially disable operation buttons
        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);
        transferButton.setEnabled(false);
        viewBalanceButton.setEnabled(false);
        viewDetailsButton.setEnabled(false);
        applyInterestButton.setEnabled(false);

        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(depositButton, gbc);
        gbc.gridx = 1;
        panel.add(withdrawButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(transferButton, gbc);
        gbc.gridx = 1;
        panel.add(viewBalanceButton, gbc);

        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(viewDetailsButton, gbc);
        gbc.gridx = 1;
        panel.add(applyInterestButton, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(exitButton, gbc);

        // Add panel to frame
        frame.add(panel);

        // Define actions for Savings Account creation
        createSavingsButton.addActionListener(e -> {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
                return;
            }

            // Create the savings account
            savingsAccount = new SavingsAccount();
            savingsAccount.setAccountHolder(name);
            savingsAccount.setAccountNumber(savingsAccount.accountGenerator());
            savingsAccount.setBalance(0.0);

            // Insert account into the database
            accountDAO.insertAccount(savingsAccount.getAccountNumber(), savingsAccount.getAccountHolder(), savingsAccount.getBalance(), "Savings", 0.0, 3.5);

            JOptionPane.showMessageDialog(frame, "Savings Account created successfully!\n" +
                    "Account Holder: " + savingsAccount.getAccountHolder() + "\n" +
                    "Account Number: " + savingsAccount.getAccountNumber());

            // Enable operation buttons for savings account
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            transferButton.setEnabled(true);
            viewBalanceButton.setEnabled(true);
            viewDetailsButton.setEnabled(true);
            applyInterestButton.setEnabled(true);  // Enable interest application for savings
        });

        // Define actions for Cheque Account creation
        createChequeButton.addActionListener(e -> {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
                return;
            }

            // Set overdraft limit automatically to 5000
            double overdraftLimit = 5000.0;

            chequeAccount = new ChequeAccount(0.0, overdraftLimit);
            chequeAccount.setAccountHolder(name);
            chequeAccount.setAccountNumber(chequeAccount.accountGenerator());

            // Insert account into the database
            accountDAO.insertAccount(chequeAccount.getAccountNumber(), chequeAccount.getAccountHolder(), chequeAccount.getBalance(), "Cheque", overdraftLimit, 0.0);

            JOptionPane.showMessageDialog(frame, "Cheque Account created successfully!\n" +
                    "Account Holder: " + chequeAccount.getAccountHolder() + "\n" +
                    "Account Number: " + chequeAccount.getAccountNumber());

            // Enable operation buttons for cheque account
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            transferButton.setEnabled(true);
            viewBalanceButton.setEnabled(true);
            viewDetailsButton.setEnabled(true);
            applyInterestButton.setEnabled(false);  // Cheque accounts don't get interest
        });

        // Define action for deposit button
        depositButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(input);
                if (savingsAccount != null) {
                    savingsAccount.deposit(amount);
                } else if (chequeAccount != null) {
                    chequeAccount.deposit(amount);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount.");
            }
        });

        // Define action for withdraw button
        withdrawButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(input);
                if (savingsAccount != null) {
                    savingsAccount.withdraw(amount);
                } else if (chequeAccount != null) {
                    chequeAccount.withdraw(amount);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        // Define action for transfer button
        transferButton.addActionListener(e -> {
            if (savingsAccount == null || chequeAccount == null) {
                JOptionPane.showMessageDialog(frame, "Both accounts need to be created before transferring.");
                return;
            }

            String input = JOptionPane.showInputDialog("Enter amount to transfer from Savings to Cheque:");
            try {
                double amount = Double.parseDouble(input);
                savingsAccount.transfer(chequeAccount, amount);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        // Define action for view balance button
        viewBalanceButton.addActionListener(e -> {
            if (savingsAccount != null) {
                JOptionPane.showMessageDialog(frame, "Savings Account Balance: " + savingsAccount.getBalance());
            } else if (chequeAccount != null) {
                JOptionPane.showMessageDialog(frame, "Cheque Account Balance: " + chequeAccount.getBalance());
            }
        });

        // Define action for view details button
        viewDetailsButton.addActionListener(e -> {
            if (savingsAccount != null) {
                JOptionPane.showMessageDialog(frame, "Account Holder: " + savingsAccount.getAccountHolder() +
                        "\nAccount Number: " + savingsAccount.getAccountNumber());
            } else if (chequeAccount != null) {
                JOptionPane.showMessageDialog(frame, "Account Holder: " + chequeAccount.getAccountHolder() +
                        "\nAccount Number: " + chequeAccount.getAccountNumber());
            }
        });

        // Define action for apply interest button
        applyInterestButton.addActionListener(e -> {
            if (savingsAccount != null) {
                savingsAccount.applyInterest();
                JOptionPane.showMessageDialog(frame, "Interest applied! New balance: " + savingsAccount.getBalance());
            }
        });

        // Define action for exit button
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        // Show the frame
        frame.setVisible(true);
    }
}
