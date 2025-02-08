import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDriverTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/chrisis_users"; // Change DB name if needed
        String user = "root";
        String password = "Hello@1234";

        try {
            // Load MySQL JDBC Driver (Optional in modern JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Attempt to connect
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("✅ MySQL JDBC Driver is working! Connected to the database.");
                connection.close(); // Close connection after checking
            } else {
                System.out.println("❌ Failed to connect.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found! Check if the JAR is added.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
    }
}
