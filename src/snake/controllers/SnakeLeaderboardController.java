package snake.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import snake.app.Program;
import snake.models.ObservableLeaderboardEntry;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SnakeLeaderboardController implements Initializable {
    
    @FXML
    private TableView<ObservableLeaderboardEntry> leaderboardTable;
    
    @FXML
    private Button exitButton;
    
    @FXML
    private TableColumn<ObservableLeaderboardEntry, String> entryNameColumn;
    
    @FXML
    private TableColumn<ObservableLeaderboardEntry, Integer> entryScoreColumn;
    
    @FXML
    void exitButtonClicked(MouseEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../ui/snakeMenu.fxml"));
            stage.setScene(new Scene(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        leaderboardTable.setItems(Program.getObservableLeaderboard());
        entryNameColumn.setCellValueFactory(rowData -> rowData.getValue().entryNameProperty());
        entryScoreColumn.setCellValueFactory(new PropertyValueFactory<ObservableLeaderboardEntry, Integer>("entryScore"));
    }
}
