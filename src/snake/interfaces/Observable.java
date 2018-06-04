package snake.interfaces;

public interface Observable {
    void attach(Observer obs);
    void dettach(Observer obs);
    void notifyObservers();
}
