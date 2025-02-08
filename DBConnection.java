import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("db_config.properties");
                props.load(fis);

                String url = props.getProperty("db.url");
                String user = props.getProperty("db.username");
                String password = props.getProperty("db.password");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.out.println("MySQL JDBC Driver not found! Check if the JAR is added.");
                    e.printStackTrace();
                }
                connection = DriverManager.getConnection(url, user, password);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                System.out.println("Error: " + e.getMessage());
            }
        }
        return connection;
    }
}
