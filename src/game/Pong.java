package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.*;

public class Pong extends Application {
	
	Ball ball;
	Player player;
	Paddle paddle;
	PlayerPaddle playerPaddle1;
	PlayerPaddle playerPaddle2;
	
	public final int GAME_WIDTH = 700;
	public final int GAME_HEIGHT = 500;
	public boolean startGame = false;
	
	double screenX = 0;
	double screenY = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
			GraphicsContext g = canvas.getGraphicsContext2D();
			Timeline timeLine = new Timeline(new KeyFrame(Duration.millis(10), e -> run(g)));
			timeLine.setCycleCount(Timeline.INDEFINITE);
			
			startGame = false;
			playerPaddle1 = new PlayerPaddle(1);
			playerPaddle2 = new PlayerPaddle(2);
			ball = new Ball();
			player = new Player();
			
			canvas.setFocusTraversable(true); // Necessary for movement
			
			// Start Game
			canvas.setOnMouseClicked(keyEvent -> {
				if (startGame) {
					startGame = false;
				} else if (!startGame) {
					startGame = true;
				}
			});
			
			// Move paddles on key press
			canvas.setOnKeyPressed(keyEvent -> {
				switch (keyEvent.getCode()) {
					case W: playerPaddle1.setGoingUp(true); break;
					case S: playerPaddle1.setGoingDown(true); break;
					case UP: playerPaddle2.setGoingUp(true); break;
					case DOWN: playerPaddle2.setGoingDown(true); break;
				}
		    });
			
			// Stop moving paddles on key release
			canvas.setOnKeyReleased(keyEvent -> {
				switch (keyEvent.getCode()) {
					case W: playerPaddle1.setGoingUp(false); break;
					case S: playerPaddle1.setGoingDown(false); break;
					case UP: playerPaddle2.setGoingUp(false); break;
					case DOWN: playerPaddle2.setGoingDown(false); break;
				}
		    });
			
			primaryStage.setScene(new Scene(new StackPane(canvas)));
			
			primaryStage.show();
			timeLine.play();
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
		
		score();
	}
	
	public void paint(GraphicsContext g) {
		// Background
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		
		// Centers & redraws pall past p1 paddle
		if (ball.getX() < -10) {
			ball.setX(350);
			ball.setY(250);
			ball.draw(g);
		// Centers & redraws ball past p2 paddle
		} else if (ball.getX() > 710) {
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
	
	public void score() {
		// P1 Scores
		if (ball.getX() > GAME_WIDTH) {
			player.setP1Score(player.getP1Score() + 1);
		//P2 Scores
		} else if (ball.getX() < 0) {
			player.setP2Score(player.getP2Score() + 1);
		}
	}

}
