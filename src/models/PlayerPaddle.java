package models;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerPaddle implements Paddle{
	double y, yVol;
	boolean goingUp, goingDown;
	int player , x;
	final double STOP = 0.90;
	int paddleWidth = 20;
	int paddleHeight = 80;
	int p1Pos = 20;
	int p2Pos = 660;
	int p1TopScreen = 0;
	int p2TopScreen = 420;

	public PlayerPaddle(int player) {
		goingUp = false;
		goingDown = false;
		y = 210;
		yVol =0;
		
		
		if(player == 1) {
			x = p1Pos;
		}
		else {
			x = p2Pos;
		}
	}
	//test
	
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, paddleWidth, paddleHeight);
		
	}
	public void setGoingUp(boolean input) {
		goingUp = input;
	}
	
	public void setGoingDown(boolean input) {
		goingDown = input;
	}
	
	public void move() {
		if(goingUp) {
			yVol -= 2;
		}
		else if (goingDown) {
			yVol += 2;
		}
		else if(!goingUp && !goingDown) {
			yVol *= STOP;
		}
		if(yVol >= 5) {
			yVol = 5;
		}
		else if(yVol <= -5) {
			yVol = -5;
		}
		
		y += yVol;
		
		
		if(y<p1TopScreen) {
			y=p1TopScreen;
		}
		if(y> p2TopScreen) {
			y = p2TopScreen;
		}
	}


	public int getY() {
	
		return (int)y;
	}

}
