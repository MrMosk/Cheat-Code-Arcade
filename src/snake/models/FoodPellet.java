package snake.models;

import java.util.Random;

public class FoodPellet {
	private int[] postion;
	
	public FoodPellet(int[] position) {
	    setPostion(position);
    }
    
    public FoodPellet(int boardSizeX, int boardSizeY) {
        Random rand = new Random();
    
        int posX = rand.nextInt(boardSizeX);
        int posY = rand.nextInt(boardSizeY);
        int[] position = {posX, posY};
    
        setPostion(position);
    }
    
    public int[] getPostion() {
        return postion;
    }
    
    public void setPostion(int[] postion) {
        this.postion = postion;
    }
    
    public int getXPosition() {
	    return getPostion()[0];
    }
    
    public int getYPosition() {
	    return getPostion()[1];
    }
    
}
