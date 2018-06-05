package pong.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pong.models.PongLB;
import pong.models.PongOLB;


import java.io.*;
import java.util.ArrayList;

public class Leaderboard {
    private static String path = "pongLeaderboard.ccalb";
    private static boolean isFirstLaunch = true;
    private static ObservableList<PongOLB> olbScores = FXCollections.observableArrayList();
    private static ArrayList<PongLB> lbScores = new ArrayList<>();
    
    public static void onClose() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            ObjectOutputStream writer = new ObjectOutputStream(out);
            
            Leaderboard.setLbScores(Leaderboard.observableLeaderboardToLeaderboard(Leaderboard.getOlbScores()));
            
            writer.writeObject(Leaderboard.getLbScores());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ObservableList<PongOLB> readLeaderboard() {
        
        FileInputStream in = null;
        ArrayList<PongLB> retVal = new ArrayList<>();
        if (isFirstLaunch) {
            try {
                in = new FileInputStream(path);
                ObjectInputStream reader = new ObjectInputStream(in);
                retVal = (ArrayList<PongLB>) reader.readObject();
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
        
        return Leaderboard.leaderboardToObservbleLeaderboard(retVal);
    }
    
    public static ArrayList<PongLB> observableLeaderboardToLeaderboard(ObservableList<PongOLB> olbScores) {
        for (int i = 0; i < olbScores.size(); i++) {
            String entryScore = olbScores.get(i).getEntryScore();
            String entryName = olbScores.get(i).getEntryName();
            lbScores.add(new PongLB(entryName, entryScore));
        }
        return lbScores;
    }
    
    public static ObservableList<PongOLB> leaderboardToObservbleLeaderboard(ArrayList<PongLB> leaderboard) {
        for (PongLB aLeaderboard : leaderboard) {
            String entryScore = aLeaderboard.getEntryScore();
            String entryName = aLeaderboard.getEntryName();
            olbScores.add(new PongOLB(entryName, entryScore));
        }
        return olbScores;
    }
    
    
    public static ObservableList<PongOLB> getOlbScores() {
        return olbScores;
    }
    
    public static void setOlbScores(ObservableList<PongOLB> olbScores) {
        Leaderboard.olbScores = olbScores;
    }
    
    public static ArrayList<PongLB> getLbScores() {
        return lbScores;
    }
    
    public static void setLbScores(ArrayList<PongLB> lbScores) {
        Leaderboard.lbScores = lbScores;
    }
}
