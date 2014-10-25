
package his.model.resident;

import his.control.IDHandler;
import his.dao.DAO;
import his.model.Register;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class ResidentRegister extends Register<Resident> {

    public ResidentRegister(DAO<Resident> dao, IDHandler idHandler) {
        super(dao, idHandler);
    }
    
    public Resident create(String name, String address, long birthday, String phone, String email, boolean hoene, boolean reserve, boolean oneOne, String barcode, long expirationDate) {
        int id = idHandler.getNextResidentId();
        long createDate = new Date().getTime();
        Resident r = new Resident(id, createDate, name, address, birthday, phone, email, hoene, reserve, oneOne, barcode, expirationDate);
        insert(r);
        return r;
    }

}
