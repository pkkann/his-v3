
package his.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author Patrick
 */
public abstract class ModelClass {
    
    private final IntegerProperty id;
    private final LongProperty createDate;
    
    public ModelClass(int id, long createDate) {
        this.id = new SimpleIntegerProperty(id);
        this.createDate = new SimpleLongProperty(createDate);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
    
    public long getCreateDate() {
        return createDate.get();
    }
    
    public void setCreateDate(long createDate) {
        this.createDate.set(createDate);
    }

    public IntegerProperty getIdProperty() {
        return id;
    }
    
    public LongProperty getCreateDateProperty() {
        return createDate;
    }

}
