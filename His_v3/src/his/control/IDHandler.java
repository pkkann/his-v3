
package his.control;

import his.model.resident.Resident;
import his.model.resident.ResidentRegister;
import his.model.shift.Shift;
import his.model.shift.ShiftRegister;
import his.model.user.User;
import his.model.user.UserRegister;

/**
 *
 * @author Patrick
 */
public class IDHandler {
    
    private int currentUserId;
    private int currentShiftId;
    private int currentResidentId;
    
    private UserRegister userRegister;
    private ShiftRegister shiftRegister;
    private ResidentRegister residentRegister;
    
    /**
     * This method should be called after all registers have loaded
     * @param userRegister 
     * @param shiftRegister 
     * @param residentRegister 
     */
    public void init(UserRegister userRegister, ShiftRegister shiftRegister, ResidentRegister residentRegister) {
        this.userRegister = userRegister;
        this.shiftRegister = shiftRegister;
        this.residentRegister = residentRegister;
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
        
        for(Resident r : residentRegister.getObjects()) {
            if(r.getId() > currentShiftId) {
                currentShiftId = r.getId();
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
    
    public int getNextResidentId() {
        this.currentResidentId++;
        return currentResidentId;
    }
    
    public int getCurrentResidentId() {
        return currentResidentId;
    }

}
