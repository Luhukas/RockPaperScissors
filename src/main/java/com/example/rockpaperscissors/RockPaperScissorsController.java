package com.example.rockpaperscissors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RockPaperScissorsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
