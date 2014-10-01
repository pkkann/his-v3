
package his.model;

import his.dao.DAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Patrick
 * @param <DTYPE>
 */
public abstract class Register<DTYPE extends ModelClass> {
    
    private final DAO<DTYPE> dao;
    private final ObservableList<DTYPE> objects;
    
    public Register(DAO<DTYPE> dao) {
        this.objects = FXCollections.observableArrayList();
        this.dao = dao;
    }
    
    /**
     * Inserts an object
     * @param source 
     */
    public void insert(DTYPE source) {
        objects.add(source);
        dao.insert(source);
    }
    
    /**
     * Updates an object
     * @param target
     * @param source 
     */
    public void update(DTYPE target, DTYPE source) {
        objects.set(objects.indexOf(target), source);
        dao.update(target, source);
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
    }
    
    /**
     * Deletes an object based on id
     * @param id 
     */
    public void delete(int id) {
        delete(get(id));
    }
    
    /**
     * Loads register with database data
     */
    public void loadRegister() {
        objects.addAll(dao.selectAll());
    }

}
