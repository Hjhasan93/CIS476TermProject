package com.driveshare.ui;

import com.driveshare.pattern.singleton.SessionManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Builds the main dashboard screen shown after successful login.
 * This screen acts as the navigation hub of the application.
 */
public class DashboardView {

    private final Stage stage;
    private final VBox root;

    /**
     * Creates the dashboard screen.
     *
     * @param stage the main application stage
     */
    public DashboardView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(15);

        initializeComponents();
        buildLayout();
    }

    /**
     * Initializes the UI components for the dashboard.
     */
    private void initializeComponents() {
        String userName = "User";

        if (SessionManager.getInstance().getCurrentUser() != null) {
            userName = SessionManager.getInstance().getCurrentUser().getFullName();
        }

        Label titleLabel = new Label("DriveShare Dashboard");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label welcomeLabel = new Label("Welcome, " + userName);
        welcomeLabel.setStyle("-fx-font-size: 14px;");

        Button createCarButton = new Button("Create Car Listing");
        Button searchBookButton = new Button("Search / Book Car");
        Button watchlistButton = new Button("Watchlist / Notifications");
        Button paymentButton = new Button("Payment");
        Button messagesButton = new Button("Messages");
        Button logoutButton = new Button("Logout");


        // Navigation actions
        createCarButton.setOnAction(e -> {
            CarListingView carListingView = new CarListingView(stage);
            stage.getScene().setRoot(carListingView.getView());
        });

        searchBookButton.setOnAction(e -> {
            SearchBookingView view = new SearchBookingView(stage);
            stage.getScene().setRoot(view.getView());
        });
        watchlistButton.setOnAction(e -> {
            PlaceholderView placeholderView = new PlaceholderView(stage, "Watchlist / Notifications");
            stage.getScene().setRoot(placeholderView.getView());
        });

        paymentButton.setOnAction(e -> {
            PaymentView paymentView = new PaymentView(stage);
            stage.getScene().setRoot(paymentView.getView());
        });

        messagesButton.setOnAction(e -> {
            PlaceholderView placeholderView = new PlaceholderView(stage, "Messages");
            stage.getScene().setRoot(placeholderView.getView());
        });

        logoutButton.setOnAction(e -> {
            SessionManager.getInstance().logout();

            LoginView loginView = new LoginView(stage);
            stage.getScene().setRoot(loginView.getView());
        });

        root.getChildren().addAll(
                titleLabel,
                welcomeLabel,
                createCarButton,
                searchBookButton,
                watchlistButton,
                paymentButton,
                messagesButton,
                logoutButton
        );
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
                button.setMinWidth(220);
                button.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
            }
        }
    }

    /**
     * Returns the root node of this view.
     *
     * @return dashboard root node
     */
    public Parent getView() {
        return root;
    }
}