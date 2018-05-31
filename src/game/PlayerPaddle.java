package game;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerPaddle implements Paddle{
	double y, yVol;
	boolean goingUp, goingDown;
	int player , x;
	final double STOP = 0.90;

	public PlayerPaddle(int player) {
		goingUp = false;
		goingDown = false;
		y = 210;
		yVol =0;
		
		if(player == 1) {
			x = 20;
		}
		else {
			x = 660;
		}
	}
	//test
	
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, 20, 80);
		
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
		
		
		if(y<0) {
			y=0;
		}
		if(y> 420) {
			y = 420;
		}
	}


	public int getY() {
	
		return (int)y;
	}

}
