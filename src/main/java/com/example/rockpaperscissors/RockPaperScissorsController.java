package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Random;

public class RockPaperScissorsController implements Initializable {

    public Label scoreLabel;
    public Label highScoreLabel;
    public ProgressBar progressBar;
    public ListView<String> historyListView;

    public Button rock;
    public Button paper;
    public Button scissor;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setButtonImage(rock, "rock.jpeg");
        setButtonImage(paper, "paper.jpeg");
        setButtonImage(scissor, "scissors.jpeg");
    }

    private void setButtonImage(Button btn, String imageName) {
        Image img = new Image(
                Objects.requireNonNull(
                        getClass().getResource("/assets/images/" + imageName)
                ).toString()
        );

        ImageView iv = new ImageView(img);
        iv.setFitWidth(100);
        iv.setFitHeight(100);
        iv.setPreserveRatio(true);

        btn.setGraphic(iv);
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