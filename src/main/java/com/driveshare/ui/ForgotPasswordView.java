package com.driveshare.ui;

import com.driveshare.service.PasswordRecoveryService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Builds the forgot password screen UI using pure JavaFX code.
 */
public class ForgotPasswordView {

    private final Stage stage;
    private final VBox root;

    private TextField emailField;
    private TextField answer1Field;
    private TextField answer2Field;
    private TextField answer3Field;
    private Label messageLabel;

    private final PasswordRecoveryService recoveryService;

    /**
     * Creates the forgot password screen.
     *
     * @param stage the main application stage
     */
    public ForgotPasswordView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(12);
        this.recoveryService = new PasswordRecoveryService();

        initializeComponents();
        buildLayout();
    }

    /**
     * Initializes the UI components for the forgot password screen.
     */
    private void initializeComponents() {
        Label titleLabel = new Label("DriveShare Forgot Password");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        emailField = new TextField();
        emailField.setPromptText("Enter your email");

        answer1Field = new TextField();
        answer1Field.setPromptText("Answer to Question 1");

        answer2Field = new TextField();
        answer2Field.setPromptText("Answer to Question 2");

        answer3Field = new TextField();
        answer3Field.setPromptText("Answer to Question 3");

        messageLabel = new Label();

        Button recoverButton = new Button("Recover Password");
        Button backButton = new Button("Back to Login");

        recoverButton.setOnAction(e -> handleRecovery());

        backButton.setOnAction(e -> {
            LoginView loginView = new LoginView(stage);
            stage.getScene().setRoot(loginView.getView());
        });

        root.getChildren().addAll(
                titleLabel,
                emailField,
                answer1Field,
                answer2Field,
                answer3Field,
                recoverButton,
                backButton,
                messageLabel
        );
    }

    /**
     * Handles password recovery using PasswordRecoveryService.
     */
    private void handleRecovery() {
        String email = emailField.getText();
        String answer1 = answer1Field.getText();
        String answer2 = answer2Field.getText();
        String answer3 = answer3Field.getText();

        String recoveredPassword = recoveryService.getRecoveredPassword(
                email,
                answer1,
                answer2,
                answer3
        );

        if (recoveredPassword != null) {
            messageLabel.setText("Recovered password: " + recoveredPassword);
        } else {
            messageLabel.setText("Recovery failed. Check your answers.");
        }
    }

    /**
     * Applies layout styling to the root container.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f4f4f4;");

        emailField.setMaxWidth(300);
        answer1Field.setMaxWidth(300);
        answer2Field.setMaxWidth(300);
        answer3Field.setMaxWidth(300);

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(160);
            }
        }
    }

    /**
     * Returns the root node for this screen.
     *
     * @return the forgot password screen root node
     */
    public Parent getView() {
        return root;
    }
}