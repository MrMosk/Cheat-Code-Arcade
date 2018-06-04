package models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
	
	PlayerPaddle playerPaddle;
	Player player;
	
	double xVol, yVol, x, y;
	double centerBall = 10;
	double ballSize = 20;
	int screenTopBound = 10;
	int screenBotBound = 490;
	int paddleOneBound = 50;
	int paddleTwoBound = 650;

	public Ball() {
		x = 350;
		y = 250;
		xVol = -2;
		yVol = 1;
	}

	public void draw(GraphicsContext g) {
		g.setFill(Color.WHITE);
		g.fillOval((int) x - centerBall, (int) y - centerBall, ballSize, ballSize);
	}

	public void checkCollision(PlayerPaddle p1) {
		if (x <= paddleOneBound) {
			if (y >= p1.getY() && y <= p1.getY() + p1.getPaddleHeight()) {
				xVol = -xVol;
			}
		} else if (x >= paddleTwoBound) {
			if (y >= p1.getY() && y <= p1.getY() + p1.getPaddleHeight()) {
				xVol = -xVol;
			}
		}
	}

	public void move() {
		x += xVol;
		y += yVol;
		if (y < screenTopBound) {
			yVol = -yVol;
		}
		if (y > screenBotBound) {
			yVol = -yVol;
		}
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public void setX(int x) {
		this.x = (int) x;
	}

	public void setY(int y) {
		this.y = (int) y;
	}

}
