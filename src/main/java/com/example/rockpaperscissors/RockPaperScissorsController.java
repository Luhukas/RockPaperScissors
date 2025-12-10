package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class RockPaperScissorsController implements Initializable {
    public Button rock;
    public Button paper;
    public Button scissor;
    public Button mediaplayer;
    public Label scoreLabel;
    public Label highScoreLabel;
    public ListView<String> historyListView;
    public ProgressBar progressBar;

    private int score = 0;
    private int highScore = 0;
    private MediaPlayer player;
    private boolean isSoundPlaying = false;
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
                options[userChoice] +
                "\t| " + status +
                " |\t" + options[botChoice]
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
                (userChoice == 2 && botChoice == 1)) {
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
            }
            default -> curScore;
        };
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

    public void onClickMediaPlayer(ActionEvent actionEvent) {
        if (isSoundPlaying) {
            player.pause();
            isSoundPlaying = false;
        } else {
            player.play();
            isSoundPlaying = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtonImage(rock, "rock.jpeg");
        setButtonImage(paper, "paper.jpeg");
        setButtonImage(scissor, "scissors.jpeg");
        setButtonImage(mediaplayer, "vinyl.gif");

        Media media = new Media(
                Objects.requireNonNull(
                        getClass().getResource("/assets/sound/sound.mp3")
                ).toString()
        );

        player = new MediaPlayer(media);
    }
}