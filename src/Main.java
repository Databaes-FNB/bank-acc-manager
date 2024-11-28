import javax.swing.*;
import java.awt.*;

public class Main {
    private static SavingsAccount savingsAccount;
    private static ChequeAccount chequeAccount;

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
        JButton viewTransactionHistoryButton = new JButton("View Transaction History");
        JButton issueChequeButton = new JButton("Issue Cheque");
        JButton exitButton = new JButton("Exit");

        // Initially disable operation buttons
        depositButton.setEnabled(false);
        withdrawButton.setEnabled(false);
        transferButton.setEnabled(false);
        viewBalanceButton.setEnabled(false);
        viewDetailsButton.setEnabled(false);
        applyInterestButton.setEnabled(false);
        viewTransactionHistoryButton.setEnabled(false);
        issueChequeButton.setEnabled(false);

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
        panel.add(viewTransactionHistoryButton, gbc);
        gbc.gridx = 1;
        panel.add(issueChequeButton, gbc);

        gbc.gridy = 7;
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

            JOptionPane.showMessageDialog(frame, "Savings Account created successfully!\n" +
                    "Account Holder: " + savingsAccount.getAccountHolder() + "\n" +
                    "Account Number: " + savingsAccount.getAccountNumber());

            // Remove input components from the GUI
            panel.remove(nameLabel);
            panel.remove(nameField);

            // Revalidate and repaint the panel
            panel.revalidate();
            panel.repaint();

            // Enable operation buttons for savings account
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            transferButton.setEnabled(true);
            viewBalanceButton.setEnabled(true);
            viewDetailsButton.setEnabled(true);
            applyInterestButton.setEnabled(true);
            viewTransactionHistoryButton.setEnabled(true);
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

            JOptionPane.showMessageDialog(frame, "Cheque Account created successfully!\n" +
                    "Account Holder: " + chequeAccount.getAccountHolder() + "\n" +
                    "Account Number: " + chequeAccount.getAccountNumber() + "\n" +
                    "Overdraft Limit: " + overdraftLimit);

            // Remove input components from the GUI
            panel.remove(nameLabel);
            panel.remove(nameField);

            // Revalidate and repaint the panel
            panel.revalidate();
            panel.repaint();

            // Enable operation buttons for cheque account
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(true);
            transferButton.setEnabled(true);
            viewBalanceButton.setEnabled(true);
            viewDetailsButton.setEnabled(true);
            issueChequeButton.setEnabled(true);
        });

        // Remaining button actions are unchanged...
        // Deposit button action
        depositButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(input);

                // Check if the amount is positive
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Deposit amount must be greater than zero.");
                    return;
                }

                // Ask the user which account to deposit to
                String[] options = {"Savings Account", "Cheque Account"};
                int choice = JOptionPane.showOptionDialog(frame, "Select account to deposit into:",
                        "Deposit Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                // Perform deposit based on user's choice
                if (choice == 0 && savingsAccount != null) {
                    savingsAccount.deposit(amount);
                    JOptionPane.showMessageDialog(frame, "Deposited: " + amount + " to Savings Account.");
                } else if (choice == 1 && chequeAccount != null) {
                    chequeAccount.deposit(amount);
                    JOptionPane.showMessageDialog(frame, "Deposited: " + amount + " to Cheque Account.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please create both accounts first.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
            }
        });

        // Withdraw button action
        withdrawButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(input);

                // Check if the amount is positive
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(frame, "Withdrawal amount must be greater than zero.");
                    return;  // Reject negative or zero withdrawal amounts
                }

                // Ask the user which account to withdraw from
                String[] options = {"Savings Account", "Cheque Account"};
                int choice = JOptionPane.showOptionDialog(frame, "Select account to withdraw from:",
                        "Withdrawal Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                // Perform withdrawal based on user's choice
                if (choice == 0 && savingsAccount != null) {
                    savingsAccount.withdraw(amount);
                    JOptionPane.showMessageDialog(frame, "Withdrew: " + amount + " from Savings Account.");
                } else if (choice == 1 && chequeAccount != null) {
                    chequeAccount.withdraw(amount);
                    JOptionPane.showMessageDialog(frame, "Withdrew: " + amount + " from Cheque Account.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please create both accounts first.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        // Transfer button action
        transferButton.addActionListener(e -> {
            if (savingsAccount == null || chequeAccount == null) {
                JOptionPane.showMessageDialog(frame, "Both accounts must be created to transfer funds.");
                return;
            }

            String input = JOptionPane.showInputDialog(frame, "Enter amount to transfer:");
            try {
                double amount = Double.parseDouble(input);

                String[] options = {"Savings to Cheque", "Cheque to Savings"};
                int choice = JOptionPane.showOptionDialog(frame, "Select transfer direction:",
                        "Transfer Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (choice == 0) {
                    savingsAccount.transfer(chequeAccount, amount);
                } else if (choice == 1) {
                    chequeAccount.transfer(savingsAccount, amount);
                }

                JOptionPane.showMessageDialog(frame, "Transfer successful: " + amount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        // Apply Interest button actio
        applyInterestButton.addActionListener(e -> {
            if (savingsAccount != null) {
                savingsAccount.applyInterest();
            } else {
                JOptionPane.showMessageDialog(frame, "Please create a savings account first.");
            }
        });

        // View Transaction History button action
        viewTransactionHistoryButton.addActionListener(e -> {
            if (savingsAccount != null) {
                savingsAccount.printTransactionHistory();
            } else {
                JOptionPane.showMessageDialog(frame, "Please create a savings account first.");
            }
        });

        // Issue Cheque button action
        issueChequeButton.addActionListener(e -> {
            if (chequeAccount != null) {
                String input = JOptionPane.showInputDialog(frame, "Enter cheque amount:");
                try {
                    double amount = Double.parseDouble(input);
                    chequeAccount.issueCheque(amount);  // Call the issueCheque method
                    JOptionPane.showMessageDialog(frame, "Cheque issued successfully for: " + amount);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please create a cheque account first.");
            }
        });


        // View Balance button action
        viewBalanceButton.addActionListener(e -> {
            if (savingsAccount != null) {
                JOptionPane.showMessageDialog(frame, "Savings Account Balance: " + savingsAccount.getBalance());
            }
            if (chequeAccount != null) {
                JOptionPane.showMessageDialog(frame, "Cheque Account Balance: " + chequeAccount.getBalance());
            }
        });

        // View Details button action
        viewDetailsButton.addActionListener(e -> {
            if (savingsAccount != null) {
                JOptionPane.showMessageDialog(frame, "Savings Account Details:\n" +
                        "Account Holder: " + savingsAccount.getAccountHolder() + "\n" +
                        "Account Number: " + savingsAccount.getAccountNumber() + "\n" +
                        "Balance: " + savingsAccount.getBalance());
            }
            if (chequeAccount != null) {
                JOptionPane.showMessageDialog(frame, "Cheque Account Details:\n" +
                        "Account Holder: " + chequeAccount.getAccountHolder() + "\n" +
                        "Account Number: " + chequeAccount.getAccountNumber() + "\n" +
                        "Balance: " + chequeAccount.getBalance());
            }
        });

        // Exit button action
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using the Bank Account Management System!");
            frame.dispose();
        });

        frame.setVisible(true);

    }
}
