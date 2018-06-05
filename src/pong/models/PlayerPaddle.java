package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerPaddle implements Paddle {
	
	double y, yVol;
	boolean goingUp, goingDown;
	int player, x;
	final double STOP = 0.90;
	int paddleWidth = 20;
	int paddleHeight = 80;
	int paddleMinSpeed = 2;
	int paddleMaxSpeed = 5;
	int p1Pos = 20;
	int p2Pos = 660;
	int p1TopScreen = 0;
	int p2TopScreen = 420;

	public PlayerPaddle(int player) {
		goingUp = false;
		goingDown = false;
		y = 210;
		yVol = 0;

		if (player == 1) {
			x = p1Pos;
		} else {
			x = p2Pos;
		}
	}
	// test

	public void draw(GraphicsContext g) {
		g.setFill(Color.WHITE);
		g.fillRect(x, (int) y, paddleWidth, paddleHeight);

	}

	public void setGoingUp(boolean input) {
		goingUp = input;
	}

	public void setGoingDown(boolean input) {
		goingDown = input;
	}

	public void move() {
		if (goingUp) {
			yVol -= paddleMinSpeed;
		} else if (goingDown) {
			yVol += paddleMinSpeed;
		// Slows down paddle
		} else if (!goingUp && !goingDown) {
			yVol *= STOP;
		}
		
		// Sets varying speeds consistently to max speed
		if (yVol >= paddleMaxSpeed) {
			yVol = paddleMaxSpeed;
		} else if (yVol <= - paddleMaxSpeed) {
			yVol = - paddleMaxSpeed;
		}

		y += yVol;

		if (y < p1TopScreen) {
			y = p1TopScreen;
		}
		if (y > p2TopScreen) {
			y = p2TopScreen;
		}
	}

	public int getY() {
		return (int) y;
	}

	public int getPaddleWidth() {
		return paddleWidth;
	}

	public int getP1Pos() {
		return p1Pos;
	}

	public int getP2Pos() {
		return p2Pos;
	}

	public int getPaddleHeight() {
		return paddleHeight;
	}

}
