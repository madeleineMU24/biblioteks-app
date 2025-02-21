import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static final String url = "jdbc:mysql://localhost:3306/bibliotekapp";
    public static final String user = System.getenv("MYSQL_USER");
    public static final String password = System.getenv("MYSQL_PASSWORD");

    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
