package models;

import javafx.scene.canvas.GraphicsContext;

public interface Paddle {
	public void draw(GraphicsContext g);
	public void move();
	public int getY();
}
