import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bank_accounts"; // Database URL
    private static final String DB_USERNAME = "root";  // MySQL username
    private static final String DB_PASSWORD = "";      // MySQL password

    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            Properties properties = new Properties();
            properties.put("user", DB_USERNAME);
            properties.put("password", DB_PASSWORD);
            properties.put("useSSL", "false");
            properties.put("serverTimezone", "UTC");

            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, properties);
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw e;  // Rethrow the exception after logging
        }

        return connection;
    }
}
