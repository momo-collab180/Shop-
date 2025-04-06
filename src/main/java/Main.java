
public class Main {
    public static void main(String[] args) {

        if (AnthServise.register("mohamad", "1234")) {
            System.out.println("Registered successfully.");
        } else {
            System.out.println("Registration failed.");
        }
    }
}