package pong.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pong.models.PongLeaderboardEntry;
import snake.app.Program;
import snake.models.LeaderboardEntry;
import snake.models.ObservableLeaderboardEntry;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private static String path = "pongLeaderboard.xml";
    private static boolean isFirstLaunch = true;
    private static ObservableList<PongLeaderboardEntry> scores = FXCollections.observableArrayList();
    
    public static ObservableList<PongLeaderboardEntry> getScores() {
        return scores;
    }
    
    public static void setScores(ObservableList<PongLeaderboardEntry> scores) {
        Leaderboard.scores = scores;
    }
    
    public static void onClose() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            XMLEncoder writer = new XMLEncoder(out);
        
            writer.writeObject(scores);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ObservableList<PongLeaderboardEntry> readLeaderboard() {
        
        FileInputStream in = null;
        ObservableList<PongLeaderboardEntry> retVal = FXCollections.observableArrayList();
        if (isFirstLaunch) {
            try {
                in = new FileInputStream(path);
                XMLDecoder reader = new XMLDecoder(in);
                retVal = (ObservableList<PongLeaderboardEntry>) reader.readObject();
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
            }
        }
        
        return retVal;
    }
}
