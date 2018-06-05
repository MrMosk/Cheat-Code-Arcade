package pong.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pong.game.*;

public class PongMenuController implements Initializable{
	
	Pong ping;
    
    @FXML
    private Button playButton;
    
    @FXML
    private Button leaderboardButton;
    
    @FXML
    private Button exitButton;
    
    @FXML
    void playButtonClicked(MouseEvent event) {
        System.out.println("I Live");
        
    }
    
    @FXML
    void exitButtonClicked(MouseEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../../application/cheatcodes.fxml"));
            stage.setScene(new Scene(anchorPane));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void leaderboardButtonClicked(MouseEvent event) {
        Stage stage = (Stage) leaderboardButton.getScene().getWindow();
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/pongLeaderboard.fxml"));
            stage.setScene(new Scene(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    }
    
}