import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String url ="jdbc:postgresql://localhost:5432/strore_db";
    private static final String user ="postgres";
    private static final String password ="postgres";

    private static Connection connection;

    public DBconnection(){}

    public static Connection connect () {
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("successes");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

}
