
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        Pane pane = new Pane();
        Label label = new Label("* =====  Wellcom to Electric Shop  ===== *");
        pane.getChildren().addAll(label);

        Button buttonSingUp =new Button("         Sing up         ");
        buttonSingUp.setStyle("-fx-background-color: #004d00;"+"-fx-text-fill: #ffffff;");
        Button buttonLogin =new Button("          Login           ");
        buttonLogin.setStyle("-fx-background-color: #004d00;"+"-fx-text-fill: #ffffff;");
        Button buttonReset =new Button("  Forget password  ");
        buttonReset.setStyle("-fx-background-color: #e6ac00;"+"-fx-text-fill: #ffffff;");

        VBox box = new VBox(10);
        box.getChildren().addAll(label,buttonSingUp,buttonLogin,buttonReset);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        new LoginMenu();
    }
}