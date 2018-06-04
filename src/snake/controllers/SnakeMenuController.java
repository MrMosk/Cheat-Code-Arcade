package snake.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import snake.app.Program;
import snake.models.LeaderboardEntry;
import snake.models.ObservableLeaderboardEntry;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SnakeMenuController implements Initializable {
    private ObservableList<ObservableLeaderboardEntry> leaderboard = FXCollections.observableArrayList();
    
    @FXML
    private Button playButtonSnake;
    
    @FXML
    private Button leaderboardButtonSnake;
    
    @FXML
    private Button exitButtonSnake;
    
    @FXML
    void exitButtonClickedSnake(ActionEvent event) {
    
    }
    
    @FXML
    void leaderboardButtonClickedSnake(ActionEvent event) {
        Stage stage = (Stage) leaderboardButtonSnake.getScene().getWindow();
        try {
            Collections.sort(Program.getObservableLeaderboard());
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/snakeLeaderboard.fxml"));
            stage.setScene(new Scene(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void playButtonClickedSnake(ActionEvent event) {
        Stage stage = (Stage) playButtonSnake.getScene().getWindow();
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/snakeGame.fxml"));
            stage.setScene(new Scene(grid, 600, 650));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Program.setObservableLeaderboard(Program.readLeaderboard());

    }
    
    public ObservableList<ObservableLeaderboardEntry> getLeaderboard() {
        return leaderboard;
    }
    
    public void setLeaderboard(ObservableList<ObservableLeaderboardEntry> leaderboard) {
        this.leaderboard = leaderboard;
    }
    
    
}
