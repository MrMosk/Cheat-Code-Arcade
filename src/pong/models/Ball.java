package pong.models;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	
	PlayerPaddle playerPaddle;
	Player player;
	double xVol, yVol, x, y;

	public Ball() {
		x = 350;
		y = 250;
		xVol = -2;
		yVol = 1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int) x - 10, (int) y - 10, 20, 20);
	}

	public void checkCollision(Paddle p1, Paddle p2) {
		if (x <= 50) {
			if (y >= p1.getY() && y <= p1.getY() + 80) {
				xVol = -xVol;
			}
		} else if (x >= 650) {
			if (y >= p2.getY() && y <= p2.getY() + 80) {
				xVol = -xVol;
			}
		}
	}

	public void move() {
		x += xVol;
		y += yVol;
		if (y < 10) {
			yVol = -yVol;
		}
		if (y > 490) {
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
