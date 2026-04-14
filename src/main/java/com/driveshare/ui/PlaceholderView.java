package com.driveshare.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Simple placeholder screen for sections not yet fully built.
 */
public class PlaceholderView {

    private final Stage stage;
    private final VBox root;

    /**
     * Creates a placeholder screen.
     *
     * @param stage the main application stage
     * @param title the title of the placeholder section
     */
    public PlaceholderView(Stage stage, String title) {
        this.stage = stage;
        this.root = new VBox(20);

        initializeComponents(title);
        buildLayout();
    }

    /**
     * Initializes the UI components.
     *
     * @param title the section title
     */
    private void initializeComponents(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label infoLabel = new Label("This screen will be connected next.");

        Button backButton = new Button("Back to Dashboard");

        backButton.setOnAction(e -> {
            DashboardView dashboardView = new DashboardView(stage);
            stage.getScene().setRoot(dashboardView.getView());
        });

        root.getChildren().addAll(titleLabel, infoLabel, backButton);
    }

    /**
     * Applies layout styling to the root container.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f4f4f4;");

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(180);
            }
        }
    }

    /**
     * Returns the root node for this screen.
     *
     * @return placeholder root node
     */
    public Parent getView() {
        return root;
    }
}