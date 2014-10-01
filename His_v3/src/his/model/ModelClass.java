
package his.model;

/**
 *
 * @author Patrick
 */
public abstract class ModelClass {
    
    private int id;
    
    public ModelClass(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
