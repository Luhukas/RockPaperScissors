package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RockPaperScissorsController implements Initializable {

    public Label scoreLabel;
    public Label resultLabel;
    public ProgressBar progressBar;
    public ListView historyListView;

    public Button rock;
    public Button paper;
    public Button scissor;

    public Button mediaplayer;
    private MediaPlayer player;
    boolean isSoundPlaying = false;

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
        if (isSoundPlaying){
            player.pause();
            isSoundPlaying = false;
        } else{
            player.play();
            isSoundPlaying = true;
        }
    }

    public void onClick(ActionEvent actionEvent) {
    }
}
