package his.model.user;

import his.control.IDHandler;
import his.dao.DAO;
import his.model.Register;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class UserRegister extends Register<User> {
    
    private User loggedInUser;

    public UserRegister(DAO<User> dao, IDHandler idHandler) {
        super(dao, idHandler);
    }
    
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    
    public User getLoggedInUser() {
        return this.loggedInUser;
    }
    
    public boolean isUsernameFree(String username) {
        for(User u : objects) {
            if(u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    
    public User create(String name, String username, String password, String email, String phone, boolean administrator) {
        int id = idHandler.getNextUserId();
        long createDate = new Date().getTime();
        User u = new User(id, createDate, name, username, password, email, phone, administrator);
        insert(u);
        return u;
    }
    
    /**
     * Returns a user based on a username and a password
     * @param username
     * @param password
     * @return User
     */
    public User get(String username, String password) {
        for(User u : objects) {
            if(username.equalsIgnoreCase(u.getUsername())) {
                if(password.equals(u.getPassword())) {
                    return u;
                }
            }
        }
        return null;
    }

}
