package pong.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

public class PongOLB {
    private StringProperty entryName = new SimpleStringProperty();
    private StringProperty entryScore = new SimpleStringProperty();
    
    public PongOLB(String entryName, String entryScore) {
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
    
    public String getEntryScore() {
        return entryScore.get();
    }
    
    public StringProperty entryScoreProperty() {
        return entryScore;
    }
    
    public void setEntryScore(String entryScore) {
        this.entryScore.set(entryScore);
    }
}
