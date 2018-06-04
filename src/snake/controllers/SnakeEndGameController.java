package snake.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import snake.app.Program;
import snake.models.ObservableLeaderboardEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SnakeEndGameController implements Initializable {
    
    @FXML
    private Label scoreLabel;
    
    @FXML
    private Button savePromptButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private Button saveButton;
    
    @FXML
    void exitButtonClicked(MouseEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/snakeMenu.fxml"));
            stage.setScene(new Scene(grid));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void saveButtonClicked(MouseEvent event) {
        Program.getObservableLeaderboard().add(new ObservableLeaderboardEntry(nameField.getText(),Integer.parseInt(scoreLabel.getText())));
        
        exitButtonClicked(event);
    }
    
    @FXML
    void savePromptClicked(MouseEvent event) {
        savePromptButton.setDisable(true);
        saveButton.setDisable(false);
        saveButton.setVisible(true);
        nameField.setDisable(false);
        nameField.setVisible(true);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
    
    public void initData(int score) {
        scoreLabel.setText(Integer.toString(score));
    }
}
