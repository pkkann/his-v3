/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class AdminMenuViewController extends View implements Initializable {

    @FXML
    private StackPane pane;
    @FXML
    private TabPane adminTP;
    @FXML
    private Tab usersT;
    @FXML
    private Tab shiftsT;
    @FXML
    private Tab quarantinesT;
    @FXML
    private Tab settingsT;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPane(pane);
    }

    public void loadTabs(int selection) {
        usersT.setContent(viewController.constructManageUsersView());
        settingsT.setContent(viewController.constructSettingsView());
        
        if(selection != 0) {
            switch(selection) {
                case 1:
                    adminTP.getSelectionModel().select(usersT);
                    break;
                case 2:
                    adminTP.getSelectionModel().select(shiftsT);
                    break;
                case 3:
                    adminTP.getSelectionModel().select(quarantinesT);
                    break;
                case 4:
                    adminTP.getSelectionModel().select(settingsT);
                    break;
            }
        }
    }

}
