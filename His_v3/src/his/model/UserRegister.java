
package his.model;

import his.control.IDHandler;
import his.dao.DAO;

/**
 *
 * @author Patrick
 */
public class UserRegister extends Register<User> {

    public UserRegister(DAO<User> dao, IDHandler idHandler) {
        super(dao, idHandler);
    }
    
    public void create(String name, String username, String password, boolean administrator) {
        int id = idHandler.getNextUserId();
        User u = new User(id, name, username, password, administrator);
        insert(u);
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
