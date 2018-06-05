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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PongLeaderboardController implements Initializable {
    
    @FXML
    private TableView<?> leaderboardTable;
    
    @FXML
    private TableColumn<?, ?> entryNameColumn;
    
    @FXML
    private TableColumn<?, ?> entryScoreColumn;
    
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
    
    }
}
