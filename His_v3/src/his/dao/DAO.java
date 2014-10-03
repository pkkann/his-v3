
package his.dao;

import java.util.ArrayList;

/**
 *
 * @author Patrick
 * @param <DTYPE>
 */
public abstract class DAO<DTYPE> {
    
    public abstract void insert(DTYPE source);
    
    public abstract void update(DTYPE source);
    
    public abstract ArrayList<DTYPE> selectAll();
    
    public abstract void delete(DTYPE target);

}
