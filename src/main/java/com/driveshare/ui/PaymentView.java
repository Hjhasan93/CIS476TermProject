package com.driveshare.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Payment screen UI.
 * Demonstrates payment processing using Proxy pattern.
 */
public class PaymentView {

    private final Stage stage;
    private final VBox root;

    public PaymentView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(15);

        initializeComponents();
        buildLayout();
    }

    private void initializeComponents() {
        Label titleLabel = new Label("Payment");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label infoLabel = new Label("Click below to process payment.");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #8b0000; -fx-font-size: 13px;");

        Button payButton = new Button("Process Payment");
        Button backButton = new Button("Back to Dashboard");

        // Simulate payment success
        payButton.setOnAction(e -> {
            messageLabel.setText("Payment completed successfully.");
        });

        // Back navigation
        backButton.setOnAction(e -> {
            DashboardView dashboardView = new DashboardView(stage);
            stage.getScene().setRoot(dashboardView.getView());
        });

        root.getChildren().addAll(
                titleLabel,
                infoLabel,
                payButton,
                backButton,
                messageLabel
        );
    }

    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f4f4f4;");

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(200);
                button.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
            }
        }
    }

    public Parent getView() {
        return root;
    }
}