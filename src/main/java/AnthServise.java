import java.sql.*;

public class AnthServise {

    public static boolean register(String username , String password) {
        Connection connection = null;
        String hashedpassword = HashUtil.hashpassword(password);
        String Query = "INSERT INTO aunthnticat (username,password)"
                +"VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Query);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hashedpassword);
            preparedStatement.executeUpdate();
            return true
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value")) {
                System.out.println("Username already exists.");
            } else {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }

}
