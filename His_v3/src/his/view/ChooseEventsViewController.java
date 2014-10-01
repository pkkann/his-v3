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
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class ChooseEventsViewController extends View implements Initializable {

    @FXML
    private StackPane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPane(pane);
    }    
    
}
