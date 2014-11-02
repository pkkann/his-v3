
package his.view.manageshifts;

import his.control.ResourceManager;
import his.model.shift.Shift;
import his.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private Button editBTN;
    @FXML
    private Button deleteBTN;
    @FXML
    private Button closeBTN;
    @FXML
    private Button reopenBTN;
    @FXML
    private TextField searchTF;
    
    @FXML
    private TableView<Shift> shiftsTV;
    @FXML
    private TableColumn<Shift, String> startedTC;
    @FXML
    private TableColumn<Shift, String> leaderTC;
    @FXML
    private TableColumn<Shift, String> usersTC;
    @FXML
    private TableColumn<Shift, String> statusTC;
    @FXML
    private TableColumn<Shift, String> closedTC;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPane(pane);
        
        editBTN.setTooltip(new Tooltip("Edit"));
        deleteBTN.setTooltip(new Tooltip("Delete"));
        closeBTN.setTooltip(new Tooltip("Close"));
        reopenBTN.setTooltip(new Tooltip("Reopen"));

        Glyph gEdit = ResourceManager.getGlyph("default", 'g', Color.WHITE, 16);
        Glyph gDelete = ResourceManager.getGlyph("default", 'i', Color.WHITE, 16);
        Glyph gClose = ResourceManager.getGlyph("default", 'j', Color.WHITE, 16);
        Glyph gReopen = ResourceManager.getGlyph("default", 'k', Color.WHITE, 16);

        editBTN.setGraphic(gEdit);
        deleteBTN.setGraphic(gDelete);
        closeBTN.setGraphic(gClose);
        reopenBTN.setGraphic(gReopen);
        
        leaderTC.setCellValueFactory(cellData -> cellData.getValue().getLeader().getNameProperty());
        
    }
    
    public void loadTable() {
        shiftsTV.setItems(viewController.shiftRegister.getObservableObjects());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Shift> filteredData = new FilteredList<>(viewController.shiftRegister.getObservableObjects(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(shift -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare info
                String lowerCaseFilter = newValue.toLowerCase();

                if (shift.getLeader().getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    shiftSelected(null);
                    return true; // Filter mateches username.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Shift> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(shiftsTV.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        shiftsTV.setItems(sortedData);
    }
    
    public void shiftSelected(Shift s) {
        if(s == null) {
            
        } else {
            
        }
    }

}
