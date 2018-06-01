package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import models.*;

public class Pong extends Applet implements Runnable, KeyListener {

	public final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	PlayerPaddle p1;
	PlayerPaddle p2;
	Ball ball;
	boolean startGame;
	Graphics special;
	Image img;
	Player player;
	PlayerPaddle playerPaddle;
	int p1Score;
	int p2Score;

	public void init() {
		this.resize(WIDTH, HEIGHT);
		startGame = false;
		this.addKeyListener(this);
		p1 = new PlayerPaddle(1);
		p2 = new PlayerPaddle(2);
		ball = new Ball();
		img = createImage(WIDTH, HEIGHT);
		special = img.getGraphics();
		thread = new Thread(this);
		thread.start();
	}

	public void paint(Graphics g) {
		// Background
		special.setColor(Color.black);
		special.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Centers & redraws pall past p1 paddle
		if (ball.getX() < -10) {
			ball.setX(350);
			ball.setY(250);
			ball.draw(special);
		// Centers & redraws ball past p2 paddle
		} else if (ball.getX() > 710) {
			ball.setX(350);
			ball.setY(250);
			ball.draw(special);
		// Redraws paddles & ball
		} else {
			p1.draw(special);
			p2.draw(special);
			ball.draw(special);
		}
		
		// At start, displays message
		if (!startGame) {
			special.drawString("Press enter to begin", 310, 130);
			special.drawString("Press P to pause", 290, 110);
		}
		
		special.setColor(Color.WHITE);
		special.drawString("" + p1Score, WIDTH / 4, HEIGHT / 16);
		special.drawString("" + p2Score, WIDTH / 4 * 3, HEIGHT / 16);
		
		g.drawImage(img, 0, 0, this);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for (;;) {

			if (startGame) {
				p1.move();
				p2.move();
				ball.move();
				ball.checkCollision(p1, p2);
			}

			repaint();
			
			//score();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_W) {
			p1.setGoingUp(true);
		} else if (ke.getKeyCode() == KeyEvent.VK_S) {
			p1.setGoingDown(true);
		}
		if (ke.getKeyCode() == KeyEvent.VK_I) {
			p2.setGoingUp(true);
		} else if (ke.getKeyCode() == KeyEvent.VK_K) {
			p2.setGoingDown(true);
		} else if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			startGame = true;
		} else if (ke.getKeyCode() == KeyEvent.VK_P) {
			startGame = false;
		}
	}

	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_W) {
			p1.setGoingUp(false);
		} else if (ke.getKeyCode() == KeyEvent.VK_S) {
			p1.setGoingDown(false);
		}
		if (ke.getKeyCode() == KeyEvent.VK_I) {
			p2.setGoingUp(false);
		} else if (ke.getKeyCode() == KeyEvent.VK_K) {
			p2.setGoingDown(false);
		}
	}

	public void keyTyped(KeyEvent ke) {
		// REQUIRED
	}
	
	public void score() {
		// P1 Scores
		if (ball.getX() > WIDTH - 10) {
			player.setP1Score(player.getP1Score() + 1);
		//P2 Scores
		} else if (ball.getX() < 10) {
			player.setP2Score(player.getP2Score() + 1);
		}
	}

}
