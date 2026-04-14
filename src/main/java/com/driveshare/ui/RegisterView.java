package com.driveshare.ui;

import com.driveshare.model.SecurityQuestion;
import com.driveshare.model.User;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Builds the register screen UI using pure JavaFX code.
 */
public class RegisterView {

    private final Stage stage;
    private final VBox root;

    private TextField fullNameField;
    private TextField emailField;
    private PasswordField passwordField;

    private TextField question1Field;
    private TextField answer1Field;

    private TextField question2Field;
    private TextField answer2Field;

    private TextField question3Field;
    private TextField answer3Field;

    private Label messageLabel;

    private final AuthService authService;

    /**
     * Creates the register screen.
     *
     * @param stage the main application stage
     */
    public RegisterView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(12);
        this.authService = new AuthService();

        initializeComponents();
        buildLayout();
    }

    /**
     * Initializes the UI components for the register screen.
     */
    private void initializeComponents() {
        Label titleLabel = new Label("DriveShare Register");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        fullNameField = new TextField();
        fullNameField.setPromptText("Full Name");

        emailField = new TextField();
        emailField.setPromptText("Email");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        question1Field = new TextField();
        question1Field.setPromptText("Security Question 1");
        answer1Field = new TextField();
        answer1Field.setPromptText("Answer 1");

        question2Field = new TextField();
        question2Field.setPromptText("Security Question 2");
        answer2Field = new TextField();
        answer2Field.setPromptText("Answer 2");

        question3Field = new TextField();
        question3Field.setPromptText("Security Question 3");
        answer3Field = new TextField();
        answer3Field.setPromptText("Answer 3");

        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #8b0000; -fx-font-size: 13px;");

        Button registerButton = new Button("Register");
        Button backButton = new Button("Back to Login");

        registerButton.setOnAction(e -> handleRegister());

        backButton.setOnAction(e -> {
            LoginView loginView = new LoginView(stage);
            stage.getScene().setRoot(loginView.getView());
        });

        root.getChildren().addAll(
                titleLabel,
                fullNameField,
                emailField,
                passwordField,
                question1Field,
                answer1Field,
                question2Field,
                answer2Field,
                question3Field,
                answer3Field,
                registerButton,
                backButton,
                messageLabel
        );
    }

    /**
     * Handles user registration using AuthService.
     */
    private void handleRegister() {
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = new User(fullName, email, password, 0.0);

        List<SecurityQuestion> questions = new ArrayList<>();
        questions.add(new SecurityQuestion(0, question1Field.getText(), answer1Field.getText(), 1));
        questions.add(new SecurityQuestion(0, question2Field.getText(), answer2Field.getText(), 2));
        questions.add(new SecurityQuestion(0, question3Field.getText(), answer3Field.getText(), 3));

        boolean registered = authService.registerUser(user, questions);

        if (registered) {
            messageLabel.setText("Registration successful. Go back and log in.");
        } else {
            messageLabel.setText("Registration failed. Check your fields or email.");
        }
    }

    /**
     * Applies layout styling to the root container.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f4f4f4;");

        fullNameField.setMaxWidth(300);
        emailField.setMaxWidth(300);
        passwordField.setMaxWidth(300);

        question1Field.setMaxWidth(300);
        answer1Field.setMaxWidth(300);

        question2Field.setMaxWidth(300);
        answer2Field.setMaxWidth(300);

        question3Field.setMaxWidth(300);
        answer3Field.setMaxWidth(300);

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(160);
                button.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
            }
        }
    }

    /**
     * Returns the root node for this screen.
     *
     * @return the register screen root node
     */
    public Parent getView() {
        return root;
    }
}