package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    
    @FXML
    private Button snakeButton;
    
    @FXML
    private Button pongButton;
    
    @FXML
    void pongButtonClicked(MouseEvent event) {
    
    }
    
    @FXML
    void snakeButtonClicked(MouseEvent event) {
        Stage stage = (Stage) snakeButton.getScene().getWindow();
    
        try {
            GridPane grid = FXMLLoader.load(getClass().getResource("../snake/ui/snakeMenu.fxml"));
            stage.setScene(new Scene(grid));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
    }
}
