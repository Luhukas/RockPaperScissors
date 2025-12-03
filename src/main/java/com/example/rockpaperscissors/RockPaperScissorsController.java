package com.example.rockpaperscissors;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class RockPaperScissorsController implements Initializable {
    public Label scoreLabel;
    public Label resultLabel;
    public ProgressBar progressBar;
    public TableView historyTableView;
    public TableColumn userColumnTableView;
    public TableColumn statusColumnTableView;
    public TableColumn botColumnTableView;

    private final Map<String, String> imageMap = Map.of(
            "0", "rock.jpeg",
            "1", "paper.jpeg",
            "2", "scissors.jpeg"
    );

    public void onClick(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AnchorPane root = (AnchorPane) scoreLabel.getParent();

        for (Node node : root.getChildren()) {

            if (node instanceof Button btn && btn.getId() != null) {

                String imageName = imageMap.get(btn.getId());
                if (imageName == null) continue;

                Image image = new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream("/assets/images/" + imageName)
                ));

                ImageView iv = new ImageView(image);
                iv.setFitWidth(100);
                iv.setFitHeight(100);
                iv.setPreserveRatio(true);

                btn.setGraphic(iv);
            }
        }
    }
}