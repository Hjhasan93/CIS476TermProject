package com.driveshare.ui;

import com.driveshare.model.Car;
import com.driveshare.pattern.builder.CarListingBuilder;
import com.driveshare.pattern.singleton.SessionManager;
import com.driveshare.service.CarService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Builds the car listing screen UI using pure JavaFX code.
 */
public class CarListingView {

    private final Stage stage;
    private final VBox root;

    private TextField modelField;
    private TextField yearField;
    private TextField mileageField;
    private TextField pickupLocationField;
    private TextField priceField;
    private TextField availableFromField;
    private TextField availableToField;
    private Label messageLabel;

    private final CarService carService;

    /**
     * Creates the car listing screen.
     *
     * @param stage the main application stage
     */
    public CarListingView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(12);
        this.carService = new CarService();

        initializeComponents();
        buildLayout();
    }

    /**
     * Initializes the UI components for the car listing screen.
     */
    private void initializeComponents() {
        Label titleLabel = new Label("Create Car Listing");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        modelField = new TextField();
        modelField.setPromptText("Car Model");

        yearField = new TextField();
        yearField.setPromptText("Year");

        mileageField = new TextField();
        mileageField.setPromptText("Mileage");

        pickupLocationField = new TextField();
        pickupLocationField.setPromptText("Pickup Location");

        priceField = new TextField();
        priceField.setPromptText("Rental Price Per Day");

        availableFromField = new TextField();
        availableFromField.setPromptText("Available From (YYYY-MM-DD)");

        availableToField = new TextField();
        availableToField.setPromptText("Available To (YYYY-MM-DD)");

        messageLabel = new Label();

        Button createButton = new Button("Create Listing");
        Button backButton = new Button("Back to Dashboard");

        createButton.setOnAction(e -> handleCreateListing());

        backButton.setOnAction(e -> {
            DashboardView dashboardView = new DashboardView(stage);
            stage.getScene().setRoot(dashboardView.getView());
        });

        root.getChildren().addAll(
                titleLabel,
                modelField,
                yearField,
                mileageField,
                pickupLocationField,
                priceField,
                availableFromField,
                availableToField,
                createButton,
                backButton,
                messageLabel
        );
    }

    /**
     * Handles car listing creation using Builder pattern and CarService.
     */
    private void handleCreateListing() {
        try {
            if (SessionManager.getInstance().getCurrentUser() == null) {
                messageLabel.setText("No active logged-in user.");
                return;
            }

            int ownerId = SessionManager.getInstance().getCurrentUser().getUserId();

            Car car = new CarListingBuilder()
                    .setOwnerId(ownerId)
                    .setModel(modelField.getText())
                    .setYear(Integer.parseInt(yearField.getText()))
                    .setMileage(Integer.parseInt(mileageField.getText()))
                    .setPickupLocation(pickupLocationField.getText())
                    .setRentalPricePerDay(Double.parseDouble(priceField.getText()))
                    .setAvailableFrom(availableFromField.getText())
                    .setAvailableTo(availableToField.getText())
                    .build();

            boolean created = carService.createCarListing(car);

            if (created) {
                messageLabel.setText("Car listing created successfully.");
            } else {
                messageLabel.setText("Car listing creation failed.");
            }

        } catch (NumberFormatException e) {
            messageLabel.setText("Year, mileage, and price must be valid numbers.");
        }
    }

    /**
     * Applies layout styling to the root container.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f4f4f4;");

        modelField.setMaxWidth(320);
        yearField.setMaxWidth(320);
        mileageField.setMaxWidth(320);
        pickupLocationField.setMaxWidth(320);
        priceField.setMaxWidth(320);
        availableFromField.setMaxWidth(320);
        availableToField.setMaxWidth(320);

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(180);
            }
        }
    }

    /**
     * Returns the root node for this screen.
     *
     * @return car listing root node
     */
    public Parent getView() {
        return root;
    }
}