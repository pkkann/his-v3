
package his.model;

import his.control.IDHandler;
import his.dao.DAO;
import his.util.DBUtil;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Patrick
 * @param <DTYPE>
 */
public abstract class Register<DTYPE extends ModelClass> {
    
    private final DAO<DTYPE> dao;
    protected final ObservableList<DTYPE> objects;
    protected IDHandler idHandler;
    
    public Register(DAO<DTYPE> dao, IDHandler idHandler) {
        this.objects = FXCollections.observableArrayList();
        this.dao = dao;
        this.idHandler = idHandler;
    }
    
    /**
     * Inserts an object
     * @param source 
     */
    public void insert(DTYPE source) {
        objects.add(source);
        dao.insert(source);
        DBUtil.close();
    }
    
    /**
     * Updates an object
     * @param target
     * @param source 
     */
    public void update(DTYPE target, DTYPE source) {
        objects.set(objects.indexOf(target), source);
        dao.update(target, source);
        DBUtil.close();
    }
    
    /**
     * Returns an object based on id
     * @param id
     * @return object
     */
    public DTYPE get(int id) {
        for(DTYPE d : objects) {
            if(d.getId() == id) {
                return d;
            }
        }
        return null;
    }
    
    /**
     * Deletes an object based on the object
     * @param target 
     */
    public void delete(DTYPE target) {
        objects.remove(target);
        dao.delete(target);
        DBUtil.close();
    }
    
    /**
     * Deletes an object based on id
     * @param id 
     */
    public void delete(int id) {
        delete(get(id));
    }
    
    /**
     * Loads register with database data.
     * Remember to run DBUtil.close() after all registers has loaded.
     */
    public void loadRegister() {
        objects.addAll(dao.selectAll());
    }
    
    /**
     * Returns all objects
     * @return objects
     */
    public ArrayList<DTYPE> getObjects() {
        ArrayList<DTYPE> objs = new ArrayList<>(objects);
        return objs;
    }
    
    /**
     * Returns all objects as an observable list
     * @return objects
     */
    public ObservableList<DTYPE> getObservableObjects() {
        return objects;
    }

}
