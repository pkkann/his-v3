/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view;

import his.control.ConfigHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class SettingsViewController extends View implements Initializable {

    @FXML
    private StackPane pane;
    @FXML
    private CheckBox fullscreenCB;
    @FXML
    private CheckBox debugCB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPane(pane);
        
        fullscreenCB.setSelected(ConfigHandler.getInstance().getFullscreen());
        debugCB.setSelected(ConfigHandler.getInstance().getDebug());
    }
    
    @FXML
    private void handleFullscreen() {
        ConfigHandler.getInstance().setFullscreen(fullscreenCB.isSelected());
    }
    
    @FXML
    private void handleDebug() {
        ConfigHandler.getInstance().setDebug(debugCB.isSelected());
    }
    
}
