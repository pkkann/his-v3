
package his.control;

import his.model.User;
import his.model.UserRegister;

/**
 *
 * @author Patrick
 */
public class IDHandler {
    
    private int currentUserId;
    
    private UserRegister userRegister;
    
    /**
     * This method should be called after all registers have loaded
     * @param userRegister 
     */
    public void init(UserRegister userRegister) {
        this.userRegister = userRegister;
        refresh();
    }
    
    /**
     * Refreshes all the current IDs.
     * This method is automatically called in the init method
     */
    public void refresh() {
        for(User u : userRegister.getObjects()) {
            if(u.getId() > currentUserId) {
                currentUserId = u.getId();
            }
        }
    }
    
    public int getNextUserId() {
        this.currentUserId++;
        return currentUserId;
    }
    
    public int getCurrentUserId() {
        return currentUserId;
    }

}
