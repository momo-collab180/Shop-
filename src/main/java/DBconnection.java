import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private final String url ="jdbc:postgresql://localhost:5432/strore_db";
    private final String user ="postgres";
    private final String password ="postgres";

    private Connection connection;

    public DBconnection(){}

    public Connection connect () {
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("successes");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return connection;
    }

}
