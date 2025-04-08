
import java.util.Scanner;

public class LoginMenu {

    Scanner input  = new Scanner(System.in);

    public LoginMenu() {

        System.out.println("* =====  Wellcom to Electric Shop  ===== *");
        System.out.println(" 1) Sing up");
        System.out.println(" 2) Login");
        System.out.println(" 3) Forget password");

        System.out.println("choic an optin(1-3");
        int choic = input.nextInt();

        switch (choic) {
            case 1:
                Sing_up();
                break;
            case 2:
                Login();
                break;
            case 3:
                Forget_password();
                break;

        }
    }


    public void Sing_up() {

        System.out.println("user name : ");
        String username = input.next();

        System.out.println("password : ");
        String password = input.next();

        if (AuthService.register(username,password)){
            System.out.println("Registered successfully.");
//            Shop_menu
        }else {
            System.out.println("Registration failed.");
        }
    }

    public void Login() {

        System.out.println("user name : ");
        String username = input.next();

        System.out.println("password : ");
        String password = input.next();

        if (AuthService.login(username,password)){
            System.out.println("Login successful!");
//            Shop_menu
        }else {
            System.out.println("Invalid credentials.");
        }
    }

    public void Forget_password() {

        System.out.println("user name : ");
        String username = input.next();

        System.out.println("password : ");
        String password = input.next();

        if (AuthService.resetPassword(username,password)){
            System.out.println("Password reset successful.");
//            new LoginMenu();
        }else {
            System.out.println("User not found. Try again.");
        }
    }
}
