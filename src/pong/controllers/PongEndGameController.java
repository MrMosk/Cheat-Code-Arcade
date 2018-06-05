package pong.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pong.game.Leaderboard;
import pong.models.Player;
import pong.models.PongOLB;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PongEndGameController implements Initializable {
    private Player player;
    
    @FXML
    private Button confirmButton;
    
    @FXML
    private TextField textField;
    
    @FXML
    void confimButtonClicked(MouseEvent event) {
        Leaderboard.getOlbScores().add(new PongOLB(textField.getText(),
                ((player.getP1Score()>player.getP2Score()) ? player.getP1Score() : player.getP2Score()) +
                        "-" + ((player.getP1Score()>player.getP2Score()) ? player.getP2Score() : player.getP1Score())));
    
        Leaderboard.onClose();
    
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/pongMenu.fxml"));
            stage.setScene(new Scene(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    public void initData(Player player) {
        this.player = player;
    }
}
