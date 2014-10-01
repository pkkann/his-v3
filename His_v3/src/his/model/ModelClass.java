
package his.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Patrick
 */
public abstract class ModelClass {
    
    private final IntegerProperty id;
    
    public ModelClass(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

}
