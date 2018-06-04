package snake.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ObservableLeaderboardEntry implements Comparable<ObservableLeaderboardEntry> {
    private StringProperty entryName = new SimpleStringProperty();
    private IntegerProperty entryScore = new SimpleIntegerProperty();
    
    public ObservableLeaderboardEntry(String entryName, Integer entryScore) {
        setEntryName(entryName);
        setEntryScore(entryScore);
    }
    
    public String getEntryName() {
        return entryName.get();
    }
    
    public StringProperty entryNameProperty() {
        return entryName;
    }
    
    public void setEntryName(String entryName) {
        this.entryName.set(entryName);
    }
    
    public int getEntryScore() {
        return entryScore.get();
    }
    
    public IntegerProperty entryScoreProperty() {
        return entryScore;
    }
    
    public void setEntryScore(int entryScore) {
        this.entryScore.set(entryScore);
    }
    
    @Override
    public int compareTo(ObservableLeaderboardEntry o) {
        return o.getEntryScore() - this.getEntryScore();
    }
}
