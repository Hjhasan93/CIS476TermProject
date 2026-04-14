package com.driveshare;

import com.driveshare.ui.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX launcher for the DriveShare application.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application and loads the first screen.
     *
     * @param primaryStage the main application window
     */
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView(primaryStage);

        Scene scene = new Scene(loginView.getView(), 700, 500);

        primaryStage.setTitle("DriveShare");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}