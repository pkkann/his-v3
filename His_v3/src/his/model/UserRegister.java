
package his.model;

import his.dao.DAO;

/**
 *
 * @author Patrick
 */
public class UserRegister extends Register<User> {

    public UserRegister(DAO<User> dao) {
        super(dao);
    }
    
    public void create(String name, String username, String password) {
        
    }

}
