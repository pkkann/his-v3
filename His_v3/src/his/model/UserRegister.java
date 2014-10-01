
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
    
    public void create(String name, String username, String password) {
        int id = idHandler.getNextUserId();
        User u = new User(id, name, username, password);
        insert(u);
    }

}
