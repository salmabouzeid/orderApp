
import java.util.ArrayList;

public class order {
    private ArrayList<observer> observers = new ArrayList<>();

    public void addObserver(observer o) {
        observers.add(o);
    }

    public void notifyObservers(String msg) {
        for (observer o : observers) {
            o.update(msg);
        }
    }
}