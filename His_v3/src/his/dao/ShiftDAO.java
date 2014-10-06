package his.dao;

import his.model.Shift;
import his.model.User;
import his.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class ShiftDAO extends DAO<Shift> {

    @Override
    public void insert(Shift source) {
        try {
            Statement st = DBUtil.getStatement();

            int id = source.getId();
            int leader_userId = source.getLeader().getId();
            int closed = 0;
            if (source.isClosed()) {
                closed = 1;
            }

            st.addBatch("INSERT INTO shift (id, leader_user_id, closed) VALUES(" + id + ", " + leader_userId + ", " + closed + ");");

            for (User u : source.getUsers()) {
                st.addBatch("INSERT INTO shift_user (user_id, shift_id) VALUES(" + u.getId() + ", " + id + ");");
            }
            st.executeBatch();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void update(Shift source) {
        
    }

    @Override
    public ArrayList<Shift> selectAll() {
        ArrayList<Shift> shifts = new ArrayList<>();

        return shifts;
    }

    @Override
    public void delete(Shift target) {
    }

}
