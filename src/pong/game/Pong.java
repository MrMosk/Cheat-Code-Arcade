package pong.game;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import pong.controllers.PongEndGameController;
import pong.models.*;

public class Pong extends Application {
	
	Ball ball;

	Player player;
	Paddle paddle;
	PlayerPaddle playerPaddle1;
	PlayerPaddle playerPaddle2;
	
	Stage newStage;
	Timeline timeLine;
	
	public final int GAME_WIDTH = 700;
	public final int GAME_HEIGHT = 500;
	public final int POINTS_TO_WIN = 5;
	
	public boolean gameWin = false;
	public boolean startGame = false;
	
	public double screenX = 0;
	public double screenY = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			/**
			 * PING MENU
			 */
			newStage = primaryStage;
			
			// MAX PANE
			Pane root = new Pane();
			
			// Declare Canvas
			Canvas pingCanvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
			GraphicsContext g = pingCanvas.getGraphicsContext2D();
			
			// Declare Timeline
			timeLine = new Timeline(new KeyFrame(Duration.millis(10), e -> run(g)));
			timeLine.setCycleCount(Timeline.INDEFINITE);
			
			startGame = false;
			playerPaddle1 = new PlayerPaddle(1);
			playerPaddle2 = new PlayerPaddle(2);
			ball = new Ball();
			player = new Player();
			
			pingCanvas.setFocusTraversable(true); // NECESSARY FOR MOVEMENT
			
			// Start Game or Pause
			pingCanvas.setOnMouseClicked(keyEvent -> {
				if (startGame && !gameWin) {
					startGame = false;
				} else if (!startGame) {
					startGame = true;
				}
			});
			
			// Move paddles on key press
			pingCanvas.setOnKeyPressed(keyEvent -> {
				switch (keyEvent.getCode()) {
					case W: playerPaddle1.setGoingUp(true); break;
					case S: playerPaddle1.setGoingDown(true); break;
					case UP: playerPaddle2.setGoingUp(true); break;
					case DOWN: playerPaddle2.setGoingDown(true); break;
				}
		    });
			
			// Stop moving paddles on key release
			pingCanvas.setOnKeyReleased(keyEvent -> {
				switch (keyEvent.getCode()) {
					case W: playerPaddle1.setGoingUp(false); break;
					case S: playerPaddle1.setGoingDown(false); break;
					case UP: playerPaddle2.setGoingUp(false); break;
					case DOWN: playerPaddle2.setGoingDown(false); break;
				}
		    });
			
			root.getChildren().add(pingCanvas);
			
			Scene pingScene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
			
			primaryStage.setScene(pingScene);
			
			primaryStage.show();
			timeLine.play();
			
			if (gameWin) {
				
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run(GraphicsContext g) {
		if (startGame) {
			playerPaddle1.move();
			playerPaddle2.move();
			ball.move();
			ball.checkCollision(playerPaddle1);
			ball.checkCollision(playerPaddle2);
		}

		paint(g);

		endGameCondition(g);
	}
	
	public void paint(GraphicsContext g) {
		// Background
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		// Centers & redraws pall past p1 paddle
		if (ball.getX() < -10) {
			player.setP2Score(player.getP2Score() + 1); // Increments p2 score
			ball.setX(350);
			ball.setY(250);
			ball.draw(g);
		// Centers & redraws ball past p2 paddle
		} else if (ball.getX() > 710) {
			player.setP1Score(player.getP1Score() + 1); // Increments p2 score
			ball.setX(350);
			ball.setY(250);
			ball.draw(g);
		// Redraws paddles & ball
		} else {
			playerPaddle1.draw(g);
			playerPaddle2.draw(g);
			ball.draw(g);
		}
		
		// At start, displays message
		if (!startGame) {
			g.fillText("Click on screen to begin!", 100, 100);
		}
		
		g.fillText("X: " + screenX + " Y: " + screenY, 600, 100);
		
		g.setFill(Color.WHITE);
		g.fillText("" + player.getP1Score(), GAME_WIDTH / 4, GAME_HEIGHT / 16);
		g.fillText("" + player.getP2Score(), GAME_WIDTH / 4 * 3, GAME_HEIGHT / 16);
	}
	
	public void endGameCondition(GraphicsContext g) {
		if (player.getP1Score() == POINTS_TO_WIN || player.getP2Score() == POINTS_TO_WIN) {
			gameWin = true;
			g.setFill(Color.WHITE);
			g.fillText("GAME OVER", GAME_WIDTH / 2 - 37, GAME_HEIGHT / 3);
			startGame = false;
			
			
			
			
			
			timeLine.stop();
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/pongEndGame.fxml"));
	            GridPane grid = loader.load();
				
				PongEndGameController controller = loader.getController();
	   
				controller.initData(player);
				
	            newStage.setScene(new Scene(grid));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
	}

}
