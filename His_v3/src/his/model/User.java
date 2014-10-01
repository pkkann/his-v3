
package his.model;

/**
 *
 * @author Patrick
 */
public class User extends ModelClass {
    
    private String name;
    private String username;
    private String password;

    public User(int id, String name, String username, String password) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
