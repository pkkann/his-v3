/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view.login;

import his.model.user.User;
import his.view.View;
import java.net.URL;
import java.util.ResourceBundle;
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
public class LoginViewController extends View implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPane(pane);
        pane.setOpacity(0.0);
    }

    private boolean validate() {
        usernameTF.setStyle("");
        passwordTF.setStyle("");

        if (!usernameTF.getText().isEmpty()) {
            if (!passwordTF.getText().isEmpty()) {
                return true;
            } else {
                Dialogs.create().owner(viewController.primaryStage).title("Login failed").message("Password can not be empty...").showWarning();
                passwordTF.setStyle("-fx-background: red");
            }
        } else {
            Dialogs.create().owner(viewController.primaryStage).title("Login failed").message("Username can not be empty...").showWarning();
            usernameTF.setStyle("-fx-background: red");
        }
        return false;
    }

    @FXML
    private void handleGo() {
        if (validate()) {
            String username = usernameTF.getText();
            String password = passwordTF.getText();
            User user = viewController.userRegister.get(username, password);

            if (user == null) {
                Dialogs.create().owner(viewController.primaryStage).title("Login failed").message("Username or password is wrong...").showWarning();
                usernameTF.setStyle("-fx-background: orange");
                passwordTF.setStyle("-fx-background: orange");
            } else {
                viewController.setLoggedInUser(user);
                if (user.isAdministrator()) {
                    viewController.showAdminMenuView(0);
                } else {
                    viewController.showNewShiftWizard();
                }
            }
        }
    }

}
