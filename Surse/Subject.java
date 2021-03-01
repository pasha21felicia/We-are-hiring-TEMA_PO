package temaPOO;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;

public interface Subject {
    public void addObserver(User user);
    public void removeObserver(User c);
    public void notifyAllObservers();


}

