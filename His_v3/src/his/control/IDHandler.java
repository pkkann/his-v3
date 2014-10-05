
package his.control;

import his.model.Shift;
import his.model.ShiftRegister;
import his.model.User;
import his.model.UserRegister;

/**
 *
 * @author Patrick
 */
public class IDHandler {
    
    private int currentUserId;
    private int currentShiftId;
    
    private UserRegister userRegister;
    private ShiftRegister shiftRegister;
    
    /**
     * This method should be called after all registers have loaded
     * @param userRegister 
     * @param shiftRegister 
     */
    public void init(UserRegister userRegister, ShiftRegister shiftRegister) {
        this.userRegister = userRegister;
        this.shiftRegister = shiftRegister;
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
        
        for(Shift s : shiftRegister.getObjects()) {
            if(s.getId() > currentShiftId) {
                currentShiftId = s.getId();
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
    
    public int getNextShiftId() {
        this.currentShiftId++;
        return currentShiftId;
    }
    
    public int getCurrentShiftId() {
        return currentShiftId;
    }

}
