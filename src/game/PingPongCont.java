package game;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PingPongCont implements Initializable{

    @FXML
    private Button playPong;

    @FXML
    private Button leaderboard;

    @FXML
    private Button exitbutton;

    @FXML
    void PlayPongClicked(MouseEvent event) {
    	System.out.println("I Live");

    }

    @FXML
    void exitButtonClicked(MouseEvent event) {

    }

    @FXML
    void leaderboardClicked(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
