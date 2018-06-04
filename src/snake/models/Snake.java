package snake.models;

import snake.enums.SnakeDirection;
import snake.enums.SnakePart;
import snake.interfaces.Observable;
import snake.interfaces.Observer;

import java.util.ArrayList;
import java.util.Random;

public class Snake implements Observable {
    private ArrayList<SubSnake> subSnakes;
    private SnakeDirection direction;
    private ArrayList<Observer> observers;
    
    
    public Snake(int[] position) {
        Random rand = new Random();
    
        subSnakes = new ArrayList<>();  //List of snake pieces
        observers = new ArrayList<>();  //List of observers, should only ever be 1
    
//        setDirection(
//                SnakeDirection.values()[rand.nextInt(
//                        SnakeDirection.values().length)]); //Sets random starting direction
    
    
        SnakeDirection predefinedDirection = SnakeDirection.UP;
        setDirection(predefinedDirection);
        
    
        int[] predefinedPosition = {10,10};
//        subSnakes.add(new SubSnake(SnakePart.HEAD, position, getDirection())); //Creates the head snake peice, and adds it the the list of pieces
        SubSnake headSubSnake = new SubSnake(SnakePart.HEAD, predefinedPosition);   //creates the snakes head piece with the determined position
        //headSubSnake.setDirection(predefinedDirection);     //assigns the head piece the determined direction
        
        subSnakes.add(headSubSnake); //adds the head snake peice to the list of pieces
    
        //subSnakes.get(0).setPreviousDirection(subSnakes.get(0).getDirection());  //Sets the "previous" direction to be the the starting direction
    
//        int[] tailPosition = new int[2];  //int[] for the position of the tail piece
        int[] predefinedBodyPosition = {10,11};
        int[] predefinedBody2Position = {10,12};
        int[] predefinedTailPosition = {10,13};
    

//        SubSnake subSnake = new SubSnake(SnakePart.TAIL, tailPosition);   //creates the tail piece with the specified position
        SubSnake bodySubSnake = new SubSnake(SnakePart.BODY, predefinedBodyPosition);
        SubSnake bodySubSnake2 = new SubSnake(SnakePart.BODY, predefinedBody2Position);
        SubSnake tailSubSnake = new SubSnake(SnakePart.TAIL, predefinedTailPosition);
        
        subSnakes.get(0).attach(bodySubSnake);  //attaches the tail piece to the head piece
        
        subSnakes.add(bodySubSnake);    //adds the tail piece to the list of pieces
        
        subSnakes.get(1).attach(bodySubSnake2);
        subSnakes.add(bodySubSnake2);
        
        subSnakes.get(2).attach(tailSubSnake);
        subSnakes.add(tailSubSnake);
        
    }
    
    public ArrayList<SubSnake> getSubSnakes() { //returns the list of pieces
        return subSnakes;
    }
    
    public void setSubSnakes(ArrayList<SubSnake> subSnakes) {   //overwrites the list of pieces shouldn't be used
        this.subSnakes = subSnakes;
    }
    
    public SnakeDirection getDirection() {  //returns the snake's direction
        return direction;
    }
    
    public void setDirection(SnakeDirection direction) {    //changes the snake's direction
        this.direction = direction;
    }
    
    public void move() {    //moves the snake in its specified direction
        //subSnakes.get(0).setPreviousDirection(getDirection());  //sets the previous direction for use in subsequent piece movement
        subSnakes.get(0).setPreviousPostion(subSnakes.get(0).getPosition());
        switch (direction) {
            case UP:    //if the snakes direction is UP
                
                subSnakes.get(0).setYPosition(subSnakes.get(0).getYPosition() - 1); //moves the head piece "up" 1 space
                break;
                
            case DOWN:    //if the snakes direction is DOWN
                
                subSnakes.get(0).setYPosition(subSnakes.get(0).getYPosition() + 1); //moves the head piece "down" 1 space
                break;
                
            case LEFT:    //if the snakes direction is LEFT
                
                subSnakes.get(0).setXPosition(subSnakes.get(0).getXPosition() - 1); //moves the head piece "left" 1 space
                break;
                
            case RIGHT:    //if the snakes direction is RIGHT
                
                subSnakes.get(0).setXPosition(subSnakes.get(0).getXPosition() + 1); //moves the head piece "right" 1 space
                break;
        }
        subSnakes.get(0).notifyObservers();
        
        
//        for (SubSnake subSnake : subSnakes) {
//            System.out.println(subSnake.getXPosition()+ " " + subSnake.getYPosition());
//        }
        
        notifyObservers();  //notifies the list of observers to the snake instance
    }
    
    public void grow() {
        getSubSnakes().get(getSubSnakes().size()-1).setSnakePart(SnakePart.BODY);
        int[] newSubSnakePosition = {getSubSnakes().get(getSubSnakes().size()-1).getYPosition(),getSubSnakes().get(getSubSnakes().size()-1).getYPreviousPosition()};
        SubSnake newSubSnake = new SubSnake(SnakePart.TAIL,newSubSnakePosition);
        getSubSnakes().get(getSubSnakes().size()-1).attach(newSubSnake);
        getSubSnakes().add(newSubSnake);
    }
    
    @Override
    public void attach(Observer obs) {       //override method from Observable
        observers.add(obs);     //adds the passed in object (inteneded to be Game Controller) to the list of observers
    }
    
    @Override
    public void dettach(Observer obs) {     //override method from Observable
        observers.remove(obs);  //removes the passed in object (inteneded to be Game Controller) from the list of observers
    }
    
    @Override
    public void notifyObservers() {         //override method from Observable
        for (Observer observer : observers) {   //iterates through the observers (inteded to only be a single instance of Game Controller) and updates them
            observer.update(this);  //updates each observer via the instance of Snake
        }
    }
}
