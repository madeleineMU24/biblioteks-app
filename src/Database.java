import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static final String url = "jdbc:mysql://localhost:3306/bibliotekapp";
    public static final String user = "root";
    public static final String password = "pass123";



    public static Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
