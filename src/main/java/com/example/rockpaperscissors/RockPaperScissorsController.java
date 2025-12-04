package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.util.Random;

public class RockPaperScissorsController {
    public Label scoreLabel;
    public Label highScoreLabel;
    public ProgressBar progressBar;
    public ListView<String> historyListView;

    private int score = 0;
    private int highScore = 0;
    private final String[] options = {"Rock", "Paper", "Scissors"};
    private final Random rand = new Random();

    public void onClick(ActionEvent actionEvent) {
        Button src = (Button) actionEvent.getSource();
        int userChoice = Integer.parseInt(src.getId());
        int botChoice = rand.nextInt(3);

        // get round results
        String status = calcRoundStatus(userChoice, botChoice);
        score = calcNewScores(status, score);

        // update ui
        scoreLabel.setText(String.valueOf(score));
        highScoreLabel.setText(String.valueOf(highScore));
        historyListView.getItems().addFirst(
                status +
                " | Player: " + options[userChoice] +
                ", Bot: " + options[botChoice]
        );
    }

    private String calcRoundStatus(int userChoice, int botChoice) {
        String status;

        // Rock    : 0
        // Paper   : 1
        // Scissor : 2

        if (
                (userChoice == 0 && botChoice == 2) ||
                (userChoice == 1 && botChoice == 0) ||
                (userChoice == 2 && botChoice == 1))
        {
            status = "Win";
        } else if (userChoice == botChoice) {
            status = "Draw";
        } else {
            status = "Lose";
        }

        return status;
    }

    private int calcNewScores(String roundStatus, int curScore) {
        return switch (roundStatus) {
            case "Win" -> ++curScore;
            case "Lose" -> {
                if (curScore > highScore) {
                    highScore = curScore;
                }

                yield 0;
            } default -> curScore;
        };
    }
}
