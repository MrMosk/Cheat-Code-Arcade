package snake.app;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import snake.models.LeaderboardEntry;
import snake.models.ObservableLeaderboardEntry;

import java.io.*;
import java.util.ArrayList;

public class Driver extends Application {

	public static void main(String[] args) {
        launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        GridPane grid = FXMLLoader.load(getClass().getResource("../ui/snakeMenu.fxml"));
  
		Scene scene = new Scene(grid, 600, 650);
        primaryStage.setScene(scene);
        
//        primaryStage.setOnCloseRequest(Program::onClose);
        
        primaryStage.setResizable(false);
        
        primaryStage.show();
        
	}
	
}
