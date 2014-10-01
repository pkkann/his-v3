
package his.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Patrick
 */
public class MainViewController extends View implements Initializable {
    
    //Buttons
    @FXML
    private Button newResidentBTN;
    @FXML
    private Button editResidentBTN;
    @FXML
    private Button tagResidentForDeletionBTN;
    @FXML
    private MenuButton saveReportMBTN;
    @FXML
    private Button endShiftBTN;
    @FXML
    private Button enrollBTN;
    @FXML
    private Button kickBTN;
    @FXML
    private Button requestQuarantineBTN;
    @FXML
    private RadioButton allRBTN;
    @FXML
    private RadioButton enrolledRBTN;
    @FXML
    private RadioButton hoeneRBTN;
    @FXML
    private RadioButton reserveRBTN;
    @FXML
    private RadioButton oneOneRBTN;
    
    //Fields
    @FXML
    private TextField searchTF;
    
    //Labels
    @FXML
    private Label residentStatusL;
    
    //Table
    @FXML
    private TableView residentTV;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn birthdayColumn;
    @FXML
    private TableColumn hoeneColumn;
    @FXML
    private TableColumn reserveColumn;
    @FXML
    private TableColumn oneOneColumn;
    
    //Pane
    @FXML
    private StackPane pane;
    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPane(pane);
        pane.setOpacity(0.0);
    }
    
    @FXML
    private void handleNewResident() {
        
    }
    
    @FXML
    private void handleEditResident() {
        
    }
    
    @FXML
    private void handleTagResidentForDeletion() {
        
    }
    
    @FXML
    private void handleEndShift() {
        
    }
    
    @FXML
    private void handleEnroll() {
        
    }
    
    @FXML
    private void handleKick() {
        
    }
    
    @FXML
    private void handleRequestQuarantine() {
        
    }
}
