
package his.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Patrick
 */
public class User extends ModelClass {
    
    private final StringProperty name;
    private final StringProperty username;
    private final StringProperty password;
    private final BooleanProperty administrator;

    public User(int id, String name, String username, String password, boolean administrator) {
        super(id);
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.administrator = new SimpleBooleanProperty(administrator);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getUsername() {
        return this.username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return this.password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public boolean isAdministrator() {
        return this.administrator.get();
    }
    
    public void setAdministrator(boolean administrator) {
        this.administrator.set(administrator);
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    public StringProperty getUsernameProperty() {
        return this.username;
    }

    public StringProperty getPasswordProperty() {
        return this.password;
    }
    
    public BooleanProperty isAdministratorProperty() {
        return this.administrator;
    }
    
}
