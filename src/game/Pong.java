package game;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends Applet implements Runnable, KeyListener {
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	PlayerPaddle p1;
	PlayerPaddle p2;
	public void init() {
		this.resize(WIDTH, HEIGHT);
		
		this.addKeyListener(this);
		p1 = new PlayerPaddle(1);
		p2 = new PlayerPaddle(2);
		thread = new Thread(this);
		thread.start();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
		p2.draw(g);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void run() {
		for (;;) {
			
			
			p1.move();
			p2.move();
			
			
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
		}
	}

	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_W) {
			p1.setGoingUp(false);
		} else if (ke.getKeyCode() == KeyEvent.VK_S) {
			p1.setGoingDown(true);
		}
		if (ke.getKeyCode() == KeyEvent.VK_I) {
			p2.setGoingUp(false);
		} else if (ke.getKeyCode() == KeyEvent.VK_K) {
			p2.setGoingDown(true);
		}

	}

	public void keyTyped(KeyEvent ke) {

	}

}
