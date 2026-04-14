package com.driveshare.ui;

import com.driveshare.model.Booking;
import com.driveshare.model.Car;
import com.driveshare.pattern.singleton.SessionManager;
import com.driveshare.service.BookingService;
import com.driveshare.service.CarService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * UI for searching available cars and booking them.
 */
public class SearchBookingView {

    private final Stage stage;
    private final VBox root;

    private TextField locationField;
    private TextField startDateField;
    private TextField endDateField;

    private ListView<String> resultsList;
    private Label messageLabel;

    private final CarService carService;
    private final BookingService bookingService;

    private List<Car> currentResults;

    /**
     * Creates the search and booking screen.
     *
     * @param stage the main application stage
     */
    public SearchBookingView(Stage stage) {
        this.stage = stage;
        this.root = new VBox(15);

        this.carService = new CarService();
        this.bookingService = new BookingService();

        initializeComponents();
        buildLayout();
    }

    /**
     * Initializes the UI components.
     */
    private void initializeComponents() {
        Label title = new Label("Search / Book Car");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        locationField = new TextField();
        locationField.setPromptText("Location");

        startDateField = new TextField();
        startDateField.setPromptText("Start Date (YYYY-MM-DD)");

        endDateField = new TextField();
        endDateField.setPromptText("End Date (YYYY-MM-DD)");

        Button searchButton = new Button("Search Cars");
        Button bookButton = new Button("Book Selected Car");
        Button backButton = new Button("Back to Dashboard");

        resultsList = new ListView<>();
        resultsList.setStyle("-fx-border-color: #2c3e50;");
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #8b0000; -fx-font-size: 13px;");

        messageLabel.setStyle("-fx-text-fill: #8b0000; -fx-font-size: 13px;");

        searchButton.setOnAction(e -> handleSearch());
        bookButton.setOnAction(e -> handleBooking());

        backButton.setOnAction(e -> {
            DashboardView dashboardView = new DashboardView(stage);
            stage.getScene().setRoot(dashboardView.getView());
        });

        root.getChildren().addAll(
                title,
                locationField,
                startDateField,
                endDateField,
                searchButton,
                resultsList,
                bookButton,
                backButton,
                messageLabel
        );
    }

    /**
     * Handles car search by location.
     */
    private void handleSearch() {
        String location = locationField.getText();

        currentResults = carService.searchCarsByLocation(location);
        resultsList.getItems().clear();

        if (currentResults == null || currentResults.isEmpty()) {
            messageLabel.setText("No cars found.");
            return;
        }

        for (Car car : currentResults) {
            resultsList.getItems().add(
                    "ID: " + car.getCarId()
                            + " | " + car.getModel()
                            + " | $" + car.getRentalPricePerDay()
                            + " | " + car.getPickupLocation()
            );
        }

        messageLabel.setText("Select a car and click Book.");
    }

    /**
     * Handles booking creation for the selected car.
     */
    private void handleBooking() {
        int selectedIndex = resultsList.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) {
            messageLabel.setText("Please select a car first.");
            return;
        }

        if (SessionManager.getInstance().getCurrentUser() == null) {
            messageLabel.setText("User not logged in.");
            return;
        }

        try {
            Car selectedCar = currentResults.get(selectedIndex);

            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            // Calculate number of rental days
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);

            long days = ChronoUnit.DAYS.between(start, end) + 1;

            if (days <= 0) {
                messageLabel.setText("End date must be after start date.");
                return;
            }

            double totalAmount = days * selectedCar.getRentalPricePerDay();

            // Create booking using the correct constructor order
            Booking booking = new Booking(
                    selectedCar.getCarId(),
                    SessionManager.getInstance().getCurrentUser().getUserId(),
                    startDate,
                    endDate,
                    totalAmount,
                    "CONFIRMED"
            );

            boolean success = bookingService.createBooking(booking);

            if (success) {
                messageLabel.setText("Booking successful.");
            } else {
                messageLabel.setText("Booking failed. Possibly overlapping dates.");
            }

        } catch (Exception e) {
            messageLabel.setText("Invalid input. Check dates.");
        }
    }

    /**
     * Applies layout styling.
     */
    private void buildLayout() {
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle("-fx-background-color: #f4f4f4;");

        locationField.setMaxWidth(300);
        startDateField.setMaxWidth(300);
        endDateField.setMaxWidth(300);

        resultsList.setMaxWidth(450);
        resultsList.setMaxHeight(150);

        for (var node : root.getChildren()) {
            if (node instanceof Button button) {
                button.setMinWidth(180);
            }
        }
    }

    /**
     * Returns the root node for this screen.
     *
     * @return the search and booking root node
     */
    public Parent getView() {
        return root;
    }
}