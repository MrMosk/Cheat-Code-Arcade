package pong.models;

import java.io.Serializable;

public class PongLB implements Serializable {
    private String entryName;
    private String entryScore;
    
    public PongLB(String entryName, String entryScore) {
        this.entryName = entryName;
        this.entryScore = entryScore;
    }
    
    public String getEntryName() {
        return entryName;
    }
    
    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }
    
    public String getEntryScore() {
        return entryScore;
    }
    
    public void setEntryScore(String entryScore) {
        this.entryScore = entryScore;
    }
}
