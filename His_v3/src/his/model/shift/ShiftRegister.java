
package his.model.shift;

import his.model.user.User;
import his.control.IDHandler;
import his.dao.DAO;
import his.model.Register;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class ShiftRegister extends Register<Shift> {

    public ShiftRegister(DAO<Shift> dao, IDHandler idHandler) {
        super(dao, idHandler);
    }
    
    public Shift create(ArrayList<User> users, User leader) {
        int id = idHandler.getNextShiftId();
        long createDate = new Date().getTime();
        Shift shift = new Shift(id, createDate, users, leader);
        insert(shift);
        return shift;
    }

}
