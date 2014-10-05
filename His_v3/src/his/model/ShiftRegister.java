
package his.model;

import his.control.IDHandler;
import his.dao.DAO;
import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class ShiftRegister extends Register<Shift> {

    public ShiftRegister(DAO<Shift> dao, IDHandler idHandler) {
        super(dao, idHandler);
    }
    
    public void create(ArrayList<User> users, User leader) {
        int id = idHandler.getNextShiftId();
        Shift shift = new Shift(id, users, leader);
        insert(shift);
    }

}
