import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * LoginPage class represents the login interface of the application.
 * It provides a user interface for authentication with username and password.
 * Features include:
 * - Modern cyberpunk-style UI with neon effects
 * - Animated astronaut background
 * - Form validation
 * - Navigation to SignUp and ForgotPassword pages
 * - Error handling and user feedback
 */
public class LoginPage {
    // UI Components
    private Stage stage;                    // Main application window
    private TextField usernameField;        // Input field for username
    private PasswordField passwordField;    // Input field for password
    private Button loginButton;             // Button to submit login
    private Button signUpButton;            // Button to navigate to signup
    private Button forgotPasswordButton;    // Button to navigate to password reset
    private Label messageLabel;             // Label to display messages/errors

    /**
     * Constructor initializes the login page
     * @param stage The primary stage/window of the application
     */
    public LoginPage(Stage stage) {
        this.stage = stage;
        stage.setMaximized(true);           // Set window to maximized state
        createUI();                         // Create and display the UI
    }

    /**
     * Creates and configures the main user interface
     * Includes:
     * - Background setup
     * - Astronaut image with animations
     * - Title with glitch effects
     * - Form container with input fields
     * - Buttons and event handlers
     */
    private void createUI() {
        // Create main container with dark space background
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #000000;");

        // Add astronaut image in bottom right
        ImageView astronaut = createAstronautImage("/images/astronaut_sitting.png", 250, 250);
        StackPane.setAlignment(astronaut, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(astronaut, new Insets(0, 60, 30, 0));

        // Create main content container
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(40));

        // Create and style the title with glitch effect
        Text title = new Text("ELECTRICAL");
        Text subtitle = new Text("TECHNOLOGIES");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setFill(Color.WHITE);
        subtitle.setFill(Color.WHITE);
        
        // Add glitch effect to title
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(5);
        title.setEffect(shadow);
        
        DropShadow shadow2 = new DropShadow();
        shadow2.setColor(Color.RED);
        shadow2.setRadius(5);
        subtitle.setEffect(shadow2);

        VBox titleBox = new VBox(5);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().addAll(title, subtitle);

        // Create form container with translucent background
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setMaxWidth(350);
        formBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 255, 255, 0.3), 10, 0, 0, 0);");

        // Style input fields
        usernameField = createStyledTextField("Username");
        passwordField = createStyledPasswordField("Password");

        // Create and style buttons
        loginButton = createStyledButton("Login", "linear-gradient(to right, #00ffff, #0099ff)");
        signUpButton = createStyledButton("Create Account", "linear-gradient(to right, #00ff99, #33cc33)");
        forgotPasswordButton = createStyledButton("Forgot Password?", "transparent");
        
        forgotPasswordButton.setStyle("-fx-text-fill: #00ffff; -fx-background-color: transparent;");
        forgotPasswordButton.setOnMouseEntered(e -> forgotPasswordButton.setStyle("-fx-text-fill: #ffffff; -fx-background-color: transparent;"));
        forgotPasswordButton.setOnMouseExited(e -> forgotPasswordButton.setStyle("-fx-text-fill: #00ffff; -fx-background-color: transparent;"));

        // Create message label
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #ff3366;");
        messageLabel.setMaxWidth(300);
        messageLabel.setWrapText(true);
        messageLabel.setAlignment(Pos.CENTER);

        // Add event handlers
        loginButton.setOnAction(e -> handleLogin());
        signUpButton.setOnAction(e -> goToSignUp());
        forgotPasswordButton.setOnAction(e -> handleForgotPassword());

        // Add components to form container
        formBox.getChildren().addAll(
            usernameField,
            passwordField,
            loginButton,
            signUpButton,
            new Separator(),
            forgotPasswordButton,
            messageLabel
        );

        // Add components to main container
        mainContainer.getChildren().addAll(titleBox, formBox);

        // Add all components to root
        root.getChildren().addAll(mainContainer, astronaut);

        // Create and set the scene
        Scene scene = new Scene(root, 450, 600);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Login - Electrical Technologies");
        
        // Add fade-in animation
        FadeTransition fadeIn = new FadeTransition(Duration.millis(1500), mainContainer);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Add subtle floating animation to astronaut
        animateAstronaut(astronaut);

        stage.show();
    }

    /**
     * Creates a styled text field with hover and focus effects
     * @param prompt The placeholder text for the field
     * @return Configured TextField with custom styling
     */
    private TextField createStyledTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setPrefHeight(40);
        field.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); " +
                      "-fx-background-radius: 5; " +
                      "-fx-border-color: #00ffff; " +
                      "-fx-border-radius: 5; " +
                      "-fx-text-fill: white; " +
                      "-fx-prompt-text-fill: #808080; " +
                      "-fx-font-size: 14px;");
        field.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                field.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); " +
                             "-fx-background-radius: 5; " +
                             "-fx-border-color: #00ffff; " +
                             "-fx-border-radius: 5; " +
                             "-fx-text-fill: white; " +
                             "-fx-prompt-text-fill: #808080; " +
                             "-fx-font-size: 14px;");
            } else {
                field.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); " +
                             "-fx-background-radius: 5; " +
                             "-fx-border-color: #00ffff; " +
                             "-fx-border-radius: 5; " +
                             "-fx-text-fill: white; " +
                             "-fx-prompt-text-fill: #808080; " +
                             "-fx-font-size: 14px;");
            }
        });
        return field;
    }

    /**
     * Creates a styled password field with hover and focus effects
     * @param prompt The placeholder text for the field
     * @return Configured PasswordField with custom styling
     */
    private PasswordField createStyledPasswordField(String prompt) {
        PasswordField field = new PasswordField();
        field.setPromptText(prompt);
        field.setPrefHeight(40);
        field.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); " +
                      "-fx-background-radius: 5; " +
                      "-fx-border-color: #00ffff; " +
                      "-fx-border-radius: 5; " +
                      "-fx-text-fill: white; " +
                      "-fx-prompt-text-fill: #808080; " +
                      "-fx-font-size: 14px;");
        field.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
            if (isFocused) {
                field.setStyle("-fx-background-color: rgba(255, 255, 255, 0.15); " +
                             "-fx-background-radius: 5; " +
                             "-fx-border-color: #00ffff; " +
                             "-fx-border-radius: 5; " +
                             "-fx-text-fill: white; " +
                             "-fx-prompt-text-fill: #808080; " +
                             "-fx-font-size: 14px;");
            } else {
                field.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); " +
                             "-fx-background-radius: 5; " +
                             "-fx-border-color: #00ffff; " +
                             "-fx-border-radius: 5; " +
                             "-fx-text-fill: white; " +
                             "-fx-prompt-text-fill: #808080; " +
                             "-fx-font-size: 14px;");
            }
        });
        return field;
    }

    /**
     * Creates a styled button with hover effects and gradients
     * @param text The button text
     * @param gradient The background gradient color
     * @return Configured Button with custom styling
     */
    private Button createStyledButton(String text, String gradient) {
        Button button = new Button(text);
        button.setPrefWidth(300);
        button.setPrefHeight(40);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        if (!gradient.equals("transparent")) {
            button.setStyle("-fx-background-color: " + gradient + "; " +
                          "-fx-text-fill: white; " +
                          "-fx-background-radius: 5; " +
                          "-fx-cursor: hand; " +
                          "-fx-effect: dropshadow(gaussian, rgba(0, 255, 255, 0.3), 10, 0, 0, 0);");
            
            button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + gradient + "; " +
                                                         "-fx-text-fill: white; " +
                                                         "-fx-background-radius: 5; " +
                                                         "-fx-cursor: hand; " +
                                                         "-fx-effect: dropshadow(gaussian, rgba(0, 255, 255, 0.5), 15, 0, 0, 0);"));
            
            button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + gradient + "; " +
                                                        "-fx-text-fill: white; " +
                                                        "-fx-background-radius: 5; " +
                                                        "-fx-cursor: hand; " +
                                                        "-fx-effect: dropshadow(gaussian, rgba(0, 255, 255, 0.3), 10, 0, 0, 0);"));
        }
        return button;
    }

    /**
     * Handles the login process
     * Validates input and attempts authentication
     * Shows success/error messages accordingly
     */
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please fill in all fields");
            return;
        }

        if (AuthService.login(username, password)) {
            showSuccess("Login successful!");
            // TODO: Navigate to main application
        } else {
            showError("Invalid username or password");
        }
    }

    /**
     * Displays an error message with red text and shake animation
     * @param message The error message to display
     */
    private void showError(String message) {
        messageLabel.setStyle("-fx-text-fill: #ff3366;");
        messageLabel.setText(message);
        shakeMessage();
    }

    /**
     * Displays a success message with green text
     * @param message The success message to display
     */
    private void showSuccess(String message) {
        messageLabel.setStyle("-fx-text-fill: #00ff99;");
        messageLabel.setText(message);
    }

    /**
     * Creates a shake animation for the message label
     * Used to emphasize error messages
     */
    private void shakeMessage() {
        messageLabel.setTranslateX(0);
        javafx.animation.Timeline timeline = new javafx.animation.Timeline(
            new javafx.animation.KeyFrame(Duration.millis(0), new javafx.animation.KeyValue(messageLabel.translateXProperty(), 0)),
            new javafx.animation.KeyFrame(Duration.millis(100), new javafx.animation.KeyValue(messageLabel.translateXProperty(), -10)),
            new javafx.animation.KeyFrame(Duration.millis(200), new javafx.animation.KeyValue(messageLabel.translateXProperty(), 10)),
            new javafx.animation.KeyFrame(Duration.millis(300), new javafx.animation.KeyValue(messageLabel.translateXProperty(), -10)),
            new javafx.animation.KeyFrame(Duration.millis(400), new javafx.animation.KeyValue(messageLabel.translateXProperty(), 0))
        );
        timeline.play();
    }

    /**
     * Navigates to the SignUp page
     * Maintains window maximized state
     */
    private void goToSignUp() {
        stage.setMaximized(true);
        new SignUpPage(stage);
    }

    /**
     * Navigates to the ForgotPassword page
     * Maintains window maximized state
     */
    private void handleForgotPassword() {
        stage.setMaximized(true);
        new ForgotPasswordPage(stage);
    }

    /**
     * Creates and configures the astronaut image with glow effects
     * @param imagePath Path to the image resource
     * @param width Desired width of the image
     * @param height Desired height of the image
     * @return Configured ImageView with custom effects
     */
    private ImageView createAstronautImage(String imagePath, double width, double height) {
        try {
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            
            // Add cyan glow effect
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CYAN);
            glow.setRadius(20);
            glow.setSpread(0.2);
            imageView.setEffect(glow);
            
            // Add opacity for better integration
            imageView.setOpacity(0.9);
            
            return imageView;
        } catch (Exception e) {
            System.out.println("Could not load astronaut image: " + imagePath);
            return new ImageView();
        }
    }

    /**
     * Creates and plays animations for the astronaut image
     * Includes floating and rotation animations
     * Adds hover effects for interactivity
     * @param astronaut The ImageView to animate
     */
    private void animateAstronaut(ImageView astronaut) {
        // Floating animation
        javafx.animation.TranslateTransition floatAnim = new javafx.animation.TranslateTransition(Duration.seconds(3), astronaut);
        floatAnim.setByY(-15);
        floatAnim.setCycleCount(javafx.animation.Animation.INDEFINITE);
        floatAnim.setAutoReverse(true);
        
        // Subtle rotation
        javafx.animation.RotateTransition rotateAnim = new javafx.animation.RotateTransition(Duration.seconds(6), astronaut);
        rotateAnim.setByAngle(5);
        rotateAnim.setCycleCount(javafx.animation.Animation.INDEFINITE);
        rotateAnim.setAutoReverse(true);
        
        // Play both animations
        floatAnim.play();
        rotateAnim.play();
        
        // Add hover effect
        astronaut.setOnMouseEntered(e -> {
            astronaut.setEffect(new DropShadow(30, Color.CYAN));
            astronaut.setOpacity(1.0);
        });
        
        astronaut.setOnMouseExited(e -> {
            astronaut.setEffect(new DropShadow(20, Color.CYAN));
            astronaut.setOpacity(0.9);
        });
    }
} 