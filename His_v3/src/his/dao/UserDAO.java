package his.dao;

import his.model.User;
import his.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick
 */
public class UserDAO extends DAO<User> {

    @Override
    public void insert(User source) {
        try {
            Statement st = DBUtil.getStatement();

            int id = source.getId();
            String name = source.getName();
            String username = source.getUsername();
            String password = source.getPassword();
            int administrator = 0;
            if(source.isAdministrator()) {
                administrator = 1;
            }

            String sql = "INSERT INTO user (id, name, username, password, administrator) VALUES(" + id + ", '" + name + "', '" + username + "', '" + password + "', "+administrator+");";
            st.execute(sql);
        } catch (SQLException ex) {
            Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
        }
    }

    @Override
    public void update(User target, User source) {
        try {
            Statement st = DBUtil.getStatement();

            int id = target.getId();
            String name = source.getName();
            String username = source.getUsername();
            String password = source.getPassword();
            int administrator = 0;
            if(source.isAdministrator()) {
                administrator = 1;
            }

            String sql = "UPDATE user SET name='" + name + "', username='" + username + "', password='" + password + "', administrator="+administrator+" WHERE id=" + id + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
        }
    }

    @Override
    public ArrayList<User> selectAll() {
        ArrayList<User> objects = null;
        try {
            objects = new ArrayList<>();
            Statement st = DBUtil.getStatement();

            String sql = "SELECT * FROM user;";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                boolean administrator = false;
                if(rs.getInt("administrator") == 1) {
                    administrator = true;
                }
                User u = new User(id, name, username, password, administrator);
                objects.add(u);
            }
        } catch (SQLException ex) {
            Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
        }
        return objects;
    }

    @Override
    public void delete(User target) {
        try {
            Statement st = DBUtil.getStatement();

            int id = target.getId();

            String sql = "DELETE FROM user WHERE id="+id+";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
        }
    }

}