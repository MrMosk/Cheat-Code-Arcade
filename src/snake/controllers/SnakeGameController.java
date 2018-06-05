package snake.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import snake.app.Program;
import snake.enums.SnakeDirection;
import snake.enums.SnakePart;
import snake.interfaces.Observable;
import snake.interfaces.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import snake.models.FoodPellet;
import snake.models.Snake;
import snake.models.SnakeTimerTask;
import snake.models.SubSnake;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SnakeGameController implements Initializable, Observer {
    private Rectangle[][] rectangles;
    private FoodPellet foodPellet;
    private boolean snakeIsGrowing;
    private TimerTask timerTask = new SnakeTimerTask();     //creates a timer task based of custom definition
    private Timer timer = new Timer(true);          //creates a new Timer Daemon thread, allowing it to exist along side the existing user thread
//    private int period = 500;
    private int score = 0;
    
    @FXML
    private Label scoreSnake;
    
    @FXML
    private GridPane gameBoard;
    
    @FXML
    void keyPressedSnake(KeyEvent event) {  //key pressed event in the pong.game grid
        switch (Program.getSnake().getDirection()) {    //switch based on the direction of the snake, intended to prevent doubling back on oneself
            case UP:    //if snake direction is "up"
                switch (event.getCode()) {
                    case UP:    //extraneous code to prevent IDE from noticing similar code
                        break;
    
                    case LEFT:  //if the key pressed is "left"
                        
                        Program.getSnake().setDirection(SnakeDirection.LEFT);   //sets snake direction to "left"
                        break;
    
                    case RIGHT:  //if the key pressed is "right"
                        
                        Program.getSnake().setDirection(SnakeDirection.RIGHT);   //sets snake direction to "right"
                        break;
                }
                break;
            case DOWN:    //if snake direction is "down"
                switch (event.getCode()) {
                    
                    case DOWN:    //extraneous code to prevent IDE from noticing similar code
                        break;
    
                    case LEFT:  //if the key pressed is "left"
                        
                        Program.getSnake().setDirection(SnakeDirection.LEFT);   //sets snake direction to "left"
                        break;
    
                    case RIGHT:  //if the key pressed is "right"
                        
                        Program.getSnake().setDirection(SnakeDirection.RIGHT);   //sets snake direction to "right"
                        break;
                }
                break;
    
            case LEFT:    //if snake direction is "left"
                switch (event.getCode()) {
                    
                    case UP:  //if the key pressed is "up"
                        
                        Program.getSnake().setDirection(SnakeDirection.UP);   //sets snake direction to "up"
                        break;
    
                    case DOWN:  //if the key pressed is "down"
                        
                        Program.getSnake().setDirection(SnakeDirection.DOWN);   //sets snake direction to "down"
                        break;
    
                    case LEFT:    //extraneous code to prevent IDE from noticing similar code
                        break;
                }
                break;
    
            case RIGHT:    //if snake direction is "right"
                
                switch (event.getCode()) {
                    
                    case UP:  //if the key pressed is "up"
                        
                        Program.getSnake().setDirection(SnakeDirection.UP);   //sets snake direction to "up"
                        break;
    
                    case DOWN:  //if the key pressed is "down"
                        
                        Program.getSnake().setDirection(SnakeDirection.DOWN);   //sets snake direction to "down"
                        
                        break;
    
                    case RIGHT:    //extraneous code to prevent IDE from noticing similar code
                        break;
                }
                break;
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {    //override from Initializable
    
        rectangles = new Rectangle[20][20];     //creates the squares representing the board space, in a 20x20 2D array
        for (int x = 0; x < rectangles.length; x++) {   //loops through the first dimension
            for (int y = 0; y < rectangles[x].length; y++) {    //loops through the second dimension
    
                rectangles[x][y] = new Rectangle(29, 29);   //sets the size of the squares to 29x29
                rectangles[x][y].setFill(Color.WHITE);      //sets the square color to white
                rectangles[x][y].setStroke(Color.WHITE);    //sets the square outline to black
                gameBoard.add(rectangles[x][y], x, y);      //assigns the squares to their respective locations within the gridpane
    
            }
        }
    
        gameBoard.setFocusTraversable(true);      //sets the pong.game space to be in focus, allowing for key capture
    
        Program.createSnake(20, 20);    //creates the snake instance within the Program class
        Program.addSnakeObserver(this);     //adds this controller to the observers of the snake instance
    
        foodPellet = new FoodPellet(20,20);
        rectangles[foodPellet.getXPosition()][foodPellet.getYPosition()].setFill(Color.RED);
        timer.scheduleAtFixedRate(timerTask, 0, 150);   //sets the timer to tick the timer task's method every 1 second with 0 delay
                //Note, the timer task calls the Snake's move() method
        
        snakeIsGrowing = false;
        
    }
    
    @Override
    public void update(Observable subject) {    //override from Observer
        
        try {   //Try catch for ArrayIndexOutOfBound for when the snake exits the play area, and IllegalStateException for when the snake overlaps itself
            
            for (SubSnake subSnake : ((Snake) subject).getSubSnakes()) {    //iterates through every snake piece
    
                if (subSnake.getSnakePart().equals(SnakePart.HEAD)) {
                    if (rectangles[subSnake.getXPosition()][subSnake.getYPosition()].getFill().equals(Color.BLACK)) {
                            //checks if piece is head and inside other piece's square, throws exception if it is
        
                        throw new IllegalStateException();
        
                    } else {    //else sets the pieces square to black, representing a snake piece
    
                        if (rectangles[subSnake.getXPosition()][subSnake.getYPosition()].getFill().equals(Color.RED)) {
                            snakeIsGrowing = true;
                            rectangles[subSnake.getXPosition()][subSnake.getYPosition()].setFill(Color.BLACK);
                            
                            score++;
                            
                            Platform.runLater(() -> scoreSnake.setText(Integer.toString(score)));
                            
                            do {
                                foodPellet = new FoodPellet(20, 20);
                                
                                
                                boolean checkForTail = false;
                                for (SubSnake subSnake1 : ((Snake) subject).getSubSnakes()) {
                                    if (subSnake1.getXPosition() == foodPellet.getXPosition()
                                            && subSnake1.getYPosition() == foodPellet.getYPosition()) {
                                        checkForTail = true;
                                        
                                    }
                                }
                                if (!checkForTail) {
                                    rectangles[foodPellet.getXPosition()][foodPellet.getYPosition()].setFill(Color.RED);
                                    break;
                                }
                            } while  (true);
                        }
                        
                        rectangles[subSnake.getXPosition()][subSnake.getYPosition()].setFill(Color.BLACK);  //sets square at piece location to black
                        
                    }
                }
                
                if (subSnake.getSnakePart().equals(SnakePart.TAIL)) {
                    rectangles[((Snake) subject).getSubSnakes().get(((Snake) subject).getSubSnakes().size() - 1).getXPreviousPosition()]
                            [((Snake) subject).getSubSnakes().get(((Snake) subject).getSubSnakes().size() - 1).getYPreviousPosition()].setFill(Color.WHITE);
                }
            }
            if (snakeIsGrowing) {
                Program.getSnake().grow();
                snakeIsGrowing = false;
            }
            
        
    
        } catch (ArrayIndexOutOfBoundsException | IllegalStateException e) {    //catchs thrown exceptions, representing pong.game over
            timer.cancel();
            
            int score  = Integer.parseInt(scoreSnake.getText());
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/snakeEndGameUI.fxml"));
                    
                    SnakeEndGameController controller;
    
                    try {
    
                        GridPane grid = loader.load();
    
                        controller = loader.getController();
                        
                        Stage stage = (Stage) scoreSnake.getScene().getWindow();
                        
                        controller.initData(score);
                        
                        stage.setScene(new Scene(grid));
                        
                        
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }
}
