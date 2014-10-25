
package his.model.shift;

import his.model.ModelClass;
import his.model.user.User;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Patrick
 */
public class Shift extends ModelClass {
    
    private final ObservableList<User> users;
    private final ObjectProperty<User> leader;
    private final BooleanProperty closed;

    public Shift(int id, long createDate, ArrayList<User> users, User leader) {
        super(id, createDate);
        this.users = FXCollections.observableArrayList(users);
        this.leader = new SimpleObjectProperty<>(leader);
        this.closed = new SimpleBooleanProperty(false);
    }
    
    public ArrayList<User> getUsers() {
        ArrayList<User> array = new ArrayList<>();
        array.addAll(this.users);
        return array;
    }
    
    public void setUsers(ArrayList<User> users) {
        this.users.clear();
        this.users.addAll(users);
    }
    
    public ObservableList<User> getObservableUsers() {
        return this.users;
    }
    
    public User getLeader() {
        return this.leader.get();
    }
    
    public void setLeader(User u) {
        this.leader.set(u);
    }
    
    public ObjectProperty<User> getLeaderProperty() {
        return this.leader;
    }
    
    public boolean isClosed() {
        return this.closed.get();
    }
    
    public void setClosed(boolean closed) {
        this.closed.set(closed);
    }
    
    public BooleanProperty isClosedProperty() {
        return this.closed;
    }

}
