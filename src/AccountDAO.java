import java.sql.*;

public class AccountDAO {

    // Insert a new account into the database
    public void insertAccount(String accountNumber, String accountHolder, double balance, String accountType, double overdraftLimit, double interestRate) {
        String query = "INSERT INTO accounts (account_number, account_holder, balance, account_type, overdraft_limit, interest_rate) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            // Log values to be inserted for debugging
            System.out.println("Inserting account into DB: ");
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Holder: " + accountHolder);
            System.out.println("Balance: " + balance);
            System.out.println("Account Type: " + accountType);
            System.out.println("Overdraft Limit: " + overdraftLimit);
            System.out.println("Interest Rate: " + interestRate);

            stmt.setString(1, accountNumber);
            stmt.setString(2, accountHolder);
            stmt.setDouble(3, balance);
            stmt.setString(4, accountType);
            stmt.setDouble(5, overdraftLimit);
            stmt.setDouble(6, interestRate);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);  // Log the result to check

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while inserting account: " + e.getMessage());
        }
    }

    // Update account balance in the database
    public void updateBalance(String accountNumber, double balance) {
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setDouble(1, balance);
            stmt.setString(2, accountNumber);

            int rowsAffected = stmt.executeUpdate();
            System.out.println("Rows affected by balance update: " + rowsAffected);  // Log to confirm update

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while updating balance: " + e.getMessage());
        }
    }

    // Retrieve account details from the database
    public void getAccount(String accountNumber) {
        String query = "SELECT * FROM accounts WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, accountNumber);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Account Holder: " + rs.getString("account_holder"));
                System.out.println("Account Number: " + rs.getString("account_number"));
                System.out.println("Balance: " + rs.getDouble("balance"));
                System.out.println("Account Type: " + rs.getString("account_type"));
            } else {
                System.out.println("Account not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while retrieving account: " + e.getMessage());
        }
    }
}
