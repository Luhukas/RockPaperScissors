package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class RockPaperScissorsController {
    public Label scoreLabel;
    public Label resultLabel;
    public ProgressBar progressBar;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onClick(ActionEvent actionEvent) {
    }
}
