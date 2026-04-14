package com.driveshare.ui;

import com.driveshare.model.User;
import com.driveshare.pattern.singleton.SessionManager;
import com.driveshare.service.AuthService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Builds the login screen UI using pure JavaFX code.
 */
public class LoginView {

    private final Stage stage;
    private final VBox root;

    private TextField emailField;
    private PasswordField passwordField;
    private Label messageLabel;

    private final AuthService authService;

    /**
     * Creates the login screen.
     *
     * @param stage the main application stage
     */
    public LoginView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(15);
        this.authService = new AuthService();

        initializeComponents();
        buildLayout();
    }

    /**
     * Initializes the UI components for the login screen.
     */
    private void initializeComponents() {
        Label titleLabel = new Label("DriveShare Login");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label emailLabel = new Label("Email");
        emailField = new TextField();
        emailField.setPromptText("Enter your email");

        Label passwordLabel = new Label("Password");
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #8b0000; -fx-font-size: 13px;");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Go to Register");
        Button forgotPasswordButton = new Button("Forgot Password");

        // Real login action
        loginButton.setOnAction(e -> handleLogin());

        // Open register screen
        registerButton.setOnAction(e -> {
            RegisterView registerView = new RegisterView(stage);
            stage.getScene().setRoot(registerView.getView());
        });

        // Open forgot password screen
        forgotPasswordButton.setOnAction(e -> {
            ForgotPasswordView forgotPasswordView = new ForgotPasswordView(stage);
            stage.getScene().setRoot(forgotPasswordView.getView());
        });

        root.getChildren().addAll(
                titleLabel,
                emailLabel,
                emailField,
                passwordLabel,
                passwordField,
                loginButton,
                registerButton,
                forgotPasswordButton,
                messageLabel
        );
    }

    /**
     * Handles login logic using AuthService.
     */
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        User loggedInUser = authService.loginUser(email, password);

        if (loggedInUser != null) {
            // Store logged-in user in the singleton session
            SessionManager.getInstance().loginUser(loggedInUser);

            messageLabel.setText("Login successful.");

            // Navigate to dashboard
            DashboardView dashboardView = new DashboardView(stage);
            stage.getScene().setRoot(dashboardView.getView());
        } else {
            messageLabel.setText("Invalid email or password.");
        }
    }

    /**
     * Applies layout styling to the root container.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f4f4f4;");

        emailField.setMaxWidth(250);
        passwordField.setMaxWidth(250);

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(160);
                button.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
            }
        }
    }

    /**
     * Returns the root view node for this screen.
     *
     * @return the login screen root node
     */
    public Parent getView() {
        return root;
    }
}