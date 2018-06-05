package pong.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pong.game.Leaderboard;
import pong.models.PongLeaderboardEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PongLeaderboardController implements Initializable {
    
    @FXML
    private TableView<PongLeaderboardEntry> leaderboardTable;
    
    @FXML
    private TableColumn<PongLeaderboardEntry, String> entryNameColumn;
    
    @FXML
    private TableColumn<PongLeaderboardEntry, String> entryScoreColumn;
    
    @FXML
    private Button exitButton;
    
    @FXML
    void exitButtonClicked(MouseEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/pongMenu.fxml"));
            stage.setScene(new Scene(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leaderboardTable.setItems(Leaderboard.getScores());
        entryNameColumn.setCellValueFactory(rowData -> rowData.getValue().entryNameProperty());
        entryScoreColumn.setCellValueFactory(rowData -> rowData.getValue().entryScoreProperty());
    }
}
