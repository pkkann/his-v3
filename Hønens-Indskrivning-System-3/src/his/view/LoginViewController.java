/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view;

import his.control.LoginController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class LoginViewController extends FadeAble implements Initializable {

    private ViewController viewController;

    //Fields
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;

    //Buttons
    @FXML
    private Button goBTN;

    //Panes
    @FXML
    private StackPane pane;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPane(pane);
        pane.setOpacity(0.0);
    }

    @FXML
    private void handleGo() {
        LoginController.executeLogin(usernameTF.getText(), passwordTF.getText(), new Runnable() {

            @Override
            public void run() {
                LoginViewController.this.viewController.showMainView();
            }
        }, viewController.primaryStage);
    }

}
