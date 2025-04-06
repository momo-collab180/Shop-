import java.sql.*;
import java.sql.Connection;

public class AnthServise {

    public static boolean register(String username , String password) {

        Connection connection = DBconnection.connect();

        String hashedpassword = HashUtil.hashpassword(password);
        String Query = "INSERT INTO aunthnticat (username,password)"
                +"VALUES (?,?)";

        try{

            PreparedStatement preparedStatement = connection.prepareStatement(Query);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hashedpassword);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            if (e.getMessage().contains("duplicate key value")) {
                System.out.println("Username already exists.");
            } else {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return false;
    }


    public static boolean login(String username ,String password) {

        Connection connection = DBconnection.connect();

        String hashedpassword = HashUtil.hashpassword(password);
        String Query = "SELECT FROM aunthnticat WHERE username = ? AND password = ?";

        try {

            PreparedStatement preparedStatement  = connection.prepareStatement(Query);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hashedpassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("Error : " + e.getMessage());
            return false;
        }
    }

    public static boolean resetPassword(String usernam,String password) {
        Connection connection = DBconnection.connect();

        String hashedpassword = HashUtil.hashpassword(password);

        String Query = "UPDATE aunthnticat SET password = ? WHERE username = ?";
         try {

             PreparedStatement preparedStatement = connection.prepareStatement(Query);

             preparedStatement.setString(1,hashedpassword);
             preparedStatement.setString(2,usernam);

             int affectedRows = preparedStatement.executeUpdate();

             return affectedRows>0;

         } catch (SQLException e) {
             System.out.println("Error: " + e.getMessage());
             return false;
         }
    }
}
