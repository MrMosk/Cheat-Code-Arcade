package snake.models;

import snake.app.Program;

import java.util.TimerTask;

public class SnakeTimerTask extends TimerTask {
    
    @Override   //override from timer task
    public void run() {
        Program.getSnake().move();  //calls the snake instances  move() method
        //Program.getSnake().grow();
    }
}
