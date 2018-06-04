package snake.models;

import snake.enums.SnakeDirection;
import snake.enums.SnakePart;
import snake.interfaces.Observable;
import snake.interfaces.Observer;

import java.util.ArrayList;

public class SubSnake implements Observable, Observer {
    private SnakePart snakePart;
    private int[] position;
    private int[] previousPostion;
    private ArrayList<Observer> observers;
    
    SubSnake(SnakePart snakePart, int[] position) {     //SubSnakes constructor, takes in the SnakePart enum, ie head, tail, body, and the piece's starting position
        
        if (position.length == 2) {  //ensures that position is a propper position in terms of array size
            
            setSnakePart(snakePart);    //sets SnakePart
            setPosition(position);      //sets position
            setPreviousPostion(position);
            
        } else {    //throws exeception if position is impropperly sized
            throw new IllegalArgumentException();
        }
        
        observers = new ArrayList<>();  //initializes observers arraylist
    }
    
    public SnakePart getSnakePart() {   //returns SnakePart
        return snakePart;
    }
    
    public void setSnakePart(SnakePart snakePart) {     //changes current SnakePArt
        this.snakePart = snakePart;
    }
    
    public int[] getPosition() {    //returns the pieces postion
        return position;
    }
    
    public void setPosition(int[] position) {   //changes the pieces current position
        this.position = position;
    }
    
    public int[] getPreviousPostion() {
        return previousPostion;
    }
    
    public void setPreviousPostion(int[] previousPostion) {
        this.previousPostion = previousPostion;
    }
    
    public int getXPosition() {     //grabs the X value from position, used for array index within the gameBoards squares
        return getPosition()[0];
    }
    
    public int getYPosition() {      //grabs the Y value from position, used for array index within the gameBoards squares
        return getPosition()[1];
    }
    
    public void setXPosition(int x) {     //sets the X value for position, used for moving the piece horizontally
        getPosition()[0] = x;
    }
    
    public void setYPosition(int y) {     //sets the X value for position, used for moving the piece vertically
        getPosition()[1] = y;
    }
    
    public int getXPreviousPosition() {     //grabs the X value from previous position, used for array index within the gameBoards squares
        return getPreviousPostion()[0];
    }
    
    public int getYPreviousPosition() {      //grabs the Y value from previous position, used for array index within the gameBoards squares
        return getPreviousPostion()[1];
    }
    
    public ArrayList<Observer> getObservers() {     //returns the list of observers, intended to only contain 1 instance of SubSnake per SubSnake
        return observers;
    }
    
    public void setObservers(ArrayList<Observer> observers) {   //overwrites the observer list, should not be used
        this.observers = observers;
    }
    
    @Override
    public void attach(Observer obs) {      //override from Observable
        observers.add(obs);     //adds the Observer, intended to be a subsequent SubSnake, to the list of observers, should only ever contain 1 subsnake
    }
    
    @Override
    public void dettach(Observer obs) {     //override from Observable
        observers.remove(obs);     //removes the Observer, intended to be a subsequent SubSnake, from the list of observers
    }
    
    @Override
    public void notifyObservers() {         //override from Observable
        for (Observer observer : observers) {       //iterates through obervers (inteded to only contain 1)
            observer.update(this);      //updates each oberver via instance of current SubSnake
        }
    }
    
    @Override
    public void update(Observable subSnake) {       //override from Observer
        int[] newCurrentPosition = new int[2];
        int[] newPreviousPosition = new int[2];
        
        newPreviousPosition[0] = getPosition()[0];
        newPreviousPosition[1] = getPosition()[1];
        
        newCurrentPosition[0] = ((SubSnake) subSnake).getPreviousPostion()[0];
        newCurrentPosition[1] = ((SubSnake) subSnake).getPreviousPostion()[1];
        
        setPreviousPostion(newPreviousPosition);
        setPosition(newCurrentPosition);
        
        notifyObservers();  //notfies this SubSnakes oberveres, if any, after this SubSnake finishes updating
    }
}
