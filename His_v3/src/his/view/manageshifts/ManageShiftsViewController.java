
package his.view.manageshifts;

import his.control.ResourceManager;
import his.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlsfx.glyphfont.Glyph;

/**
 *
 * @author Patrick
 */
public class ManageShiftsViewController extends View implements Initializable {
    
    @FXML
    private StackPane pane;
    @FXML
    private Button createBTN;
    @FXML
    private Button editBTN;
    @FXML
    private Button deleteBTN;
    @FXML
    private TextField searchTF;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPane(pane);
        
        createBTN.setTooltip(new Tooltip("Create"));
        editBTN.setTooltip(new Tooltip("Edit"));
        deleteBTN.setTooltip(new Tooltip("Delete"));

        Glyph gCreate = ResourceManager.getGlyph("default", 'h', Color.WHITE, 16);
        Glyph gEdit = ResourceManager.getGlyph("default", 'g', Color.WHITE, 16);
        Glyph gDelete = ResourceManager.getGlyph("default", 'i', Color.WHITE, 16);

        createBTN.setGraphic(gCreate);
        editBTN.setGraphic(gEdit);
        deleteBTN.setGraphic(gDelete);
    }
    
    public void loadTable() {
        
    }

}
