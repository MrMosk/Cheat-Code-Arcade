package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	PlayerPaddle p1;
	PlayerPaddle p2;
	Ball ball;
	boolean startGame;
	Graphics special;
	Image img;

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
		special.setColor(Color.black);
		special.fillRect(0, 0, WIDTH, HEIGHT);
		if (ball.getX() < -10 || ball.getX() > 710) {
			special.setColor(Color.green);
			special.drawString("Just testing for now", 350, 250);
		} else {
			p1.draw(special);
			p2.draw(special);
			ball.draw(special);
		}
		if(!startGame) {
			special.drawString("Press enter to begin", 310, 130);
		}
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

	}

}
