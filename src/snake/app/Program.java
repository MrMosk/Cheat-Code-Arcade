package snake.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.WindowEvent;
import snake.interfaces.Observer;
import snake.models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Program {
    private static Snake snake;
    private static ArrayList<LeaderboardEntry> leaderboard = new ArrayList<>();
    private static ObservableList<ObservableLeaderboardEntry> observableLeaderboard = FXCollections.observableArrayList();
    private static String path = "snakeLeaderboard.ccalb";
    private static boolean isFirstLaunch = true;
    
    public static Snake getSnake() {
        return snake;
    }
    
    public static void setSnake(Snake snake) {
        Program.snake = snake;
    }
    
    public static void createSnake(int boardSizeX, int boardSizeY) {
        Random rand = new Random();
    
        int posX = rand.nextInt(boardSizeX - 3) + 1;
        int posY = rand.nextInt(boardSizeY - 3) + 1;
        int[] position = {posX, posY};
        
        snake = new Snake(position);
    }
    
    public static void addSnakeObserver(Observer obs) {
        getSnake().attach(obs);
    }
    
    public static String getPath() {
        return path;
    }
    
    public static void setPath(String path) {
        Program.path = path;
    }
    
    public static ArrayList<LeaderboardEntry> observableLeaderboardToLeaderboard(ObservableList<ObservableLeaderboardEntry> observableLeaderboard) {
        for (int i = 0; i < observableLeaderboard.size(); i++) {
            int entryScore = observableLeaderboard.get(i).getEntryScore();
            String entryName = observableLeaderboard.get(i).getEntryName();
            leaderboard.add(new LeaderboardEntry(entryName,entryScore));
        }
        return leaderboard;
    }
    
    public static ObservableList<ObservableLeaderboardEntry> leaderboardToObservbleLeaderboard(ArrayList<LeaderboardEntry> leaderboard) {
        for (LeaderboardEntry aLeaderboard : leaderboard) {
            int entryScore = aLeaderboard.getEntryScore();
            String entryName = aLeaderboard.getEntryName();
            observableLeaderboard.add(new ObservableLeaderboardEntry(entryName, entryScore));
        }
        return observableLeaderboard;
    }
    
    public static ArrayList<LeaderboardEntry> getLeaderboard() {
        return leaderboard;
    }
    
    public static void setLeaderboard(ArrayList<LeaderboardEntry> leaderboard) {
        Program.leaderboard = leaderboard;
    }
    
    public static ObservableList<ObservableLeaderboardEntry> getObservableLeaderboard() {
        return observableLeaderboard;
    }
    
    public static void setObservableLeaderboard(ObservableList<ObservableLeaderboardEntry> observableLeaderboard) {
        Program.observableLeaderboard = observableLeaderboard;
    }
    
    public static void onClose(WindowEvent event) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            ObjectOutputStream writer = new ObjectOutputStream(out);
            
            Program.setLeaderboard(Program.observableLeaderboardToLeaderboard(Program.getObservableLeaderboard()));
            
            writer.writeObject(Program.getLeaderboard());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void onClose() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            ObjectOutputStream writer = new ObjectOutputStream(out);
            
            Program.setLeaderboard(Program.observableLeaderboardToLeaderboard(Program.getObservableLeaderboard()));
            
            writer.writeObject(Program.getLeaderboard());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ObservableList<ObservableLeaderboardEntry> readLeaderboard() {
        
        FileInputStream in = null;
        ArrayList<LeaderboardEntry> retVal = new ArrayList<>();
        if (isFirstLaunch) {
            try {
                in = new FileInputStream(path);
                ObjectInputStream reader = new ObjectInputStream(in);
                retVal = (ArrayList<LeaderboardEntry>) reader.readObject();
                reader.close();
                isFirstLaunch = false;
            } catch (IOException e) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        
        return Program.leaderboardToObservbleLeaderboard(retVal);
    }
}
