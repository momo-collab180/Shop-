import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        // Ensure JavaFX is initialized
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            new LoginPage(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}