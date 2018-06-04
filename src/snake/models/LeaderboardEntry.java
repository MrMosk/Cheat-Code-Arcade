package snake.models;

import java.io.Serializable;

public class LeaderboardEntry implements Comparable<LeaderboardEntry>, Serializable {
    private String entryName;
    private int entryScore;
    
    public LeaderboardEntry(String entryName, Integer entryScore) {
        setEntryName(entryName);
        setEntryScore(entryScore);
    
    }
    
    public String getEntryName() {
        return entryName;
    }
    
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }
    
    public int getEntryScore() {
        return entryScore;
    }
    
    public void setEntryScore(int entryScore) {
        this.entryScore = entryScore;
    }
    
    @Override
    public int compareTo(LeaderboardEntry leaderboardEntry) {
        return leaderboardEntry.getEntryScore() - this.getEntryScore();
    }
}
