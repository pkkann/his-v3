/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view.createuser;

import his.model.user.User;
import his.util.Loader;
import his.view.View;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.dialog.Dialogs;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class CreateUserViewController extends View implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button createBTN;
    @FXML
    private Button cancelBTN;

    @FXML
    private TextField nameTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;
    @FXML
    private TextField reenterPasswordTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField phoneTF;
    @FXML
    private CheckBox administratorCB;

    private boolean edit = false;

    private User editUser;

    public void edit(User u) {
        if (u != null) {
            editUser = u;
            edit = true;
            nameTF.setText(u.getName());
            usernameTF.setText(u.getUsername());
            passwordTF.setText(u.getPassword());
            reenterPasswordTF.setText(u.getPassword());
            emailTF.setText(u.getEmail());
            phoneTF.setText(u.getPhone());
            administratorCB.setSelected(u.isAdministrator());

            createBTN.setText("Save");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setPane(pane);
    }

    @FXML
    private void handleCreate() {
        if (!edit) {
            create();
        } else {
            save(editUser);
        }

    }

    private boolean validate(User u) {
        nameTF.setStyle("");
        usernameTF.setStyle("");
        passwordTF.setStyle("");
        reenterPasswordTF.setStyle("");
        emailTF.setStyle("");
        phoneTF.setStyle("");

        String name = nameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String reenterPassword = reenterPasswordTF.getText();
        String email = emailTF.getText();
        String phone = phoneTF.getText();
        boolean administrator = administratorCB.isSelected();

        if (!name.isEmpty()) {
            if (!username.isEmpty()) {
                boolean free;
                if (!edit) {
                    free = viewController.userRegister.isUsernameFree(username);
                } else {
                    if (!username.equals(u.getUsername())) {
                        free = viewController.userRegister.isUsernameFree(username);
                    } else {
                        free = true;
                    }
                }

                if (free) {
                    if (!password.isEmpty() && !reenterPassword.isEmpty()) {
                        if (password.equals(reenterPassword)) {
                            if (password.length() >= 7) {
                                return true;
                            } else {
                                Dialogs.create().title("Password").message("Password should at least be 7 characters long...").showWarning();
                                passwordTF.setStyle("-fx-background: orange");
                                reenterPasswordTF.setStyle("-fx-background: orange");
                            }
                        } else {
                            Dialogs.create().title("Password").message("The two password are not identical...").showWarning();
                            passwordTF.setStyle("-fx-background: red");
                            reenterPasswordTF.setStyle("-fx-background: red");
                        }
                    } else {
                        Dialogs.create().title("Fields not filled").message("The two password field can not be empty").showWarning();
                        passwordTF.setStyle("-fx-background: red");
                        reenterPasswordTF.setStyle("-fx-background: red");
                    }
                } else {
                    Dialogs.create().title("In use").message("Username is already in use...").showWarning();
                    usernameTF.setStyle("-fx-background: orange");
                }
            } else {
                Dialogs.create().title("Fields not filled").message("Username is empty...").showWarning();
                usernameTF.setStyle("-fx-background: red");
            }
        } else {
            Dialogs.create().title("Fields not filled").message("Name is empty...").showWarning();
            nameTF.setStyle("-fx-background: red");
        }
        return false;
    }

    private void save(User u) {
        String name = nameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String reenterPassword = reenterPasswordTF.getText();
        String email = emailTF.getText();
        String phone = phoneTF.getText();
        boolean administrator = administratorCB.isSelected();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                editUser.setName(name);
                editUser.setUsername(username);
                editUser.setPassword(password);
                editUser.setEmail(email);
                editUser.setPhone(phone);
                editUser.setAdministrator(administrator);
                viewController.userRegister.update(u);
            }
        };
        Runnable fail = new Runnable() {
            @Override
            public void run() {
                Dialogs.create().title("Error").message("Could not save user...\nContact administrator").showError();
            }
        };
        Runnable success = new Runnable() {

            @Override
            public void run() {
                viewController.showAdminMenuView(1);
            }
        };

        boolean validated = validate(u);
        if (validated) {
            Loader.showSimpleLoader(viewController.primaryStage, "Saving", "Saving user...", task, success, fail);
        }
    }

    private void create() {
        String name = nameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();
        String reenterPassword = reenterPasswordTF.getText();
        String email = emailTF.getText();
        String phone = phoneTF.getText();
        boolean administrator = administratorCB.isSelected();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                viewController.userRegister.create(name, username, password, email, phone, administrator);
            }
        };
        Runnable fail = new Runnable() {
            @Override
            public void run() {
                Dialogs.create().title("Error").message("Could not create user...\nContact administrator").showError();
            }
        };
        Runnable success = new Runnable() {

            @Override
            public void run() {
                viewController.showAdminMenuView(1);
            }
        };

        boolean validated = validate(null);
        if (validated) {
            Loader.showSimpleLoader(viewController.primaryStage, "Creating", "Creating user...", task, success, fail);
        }

    }

    @FXML
    private void handleCancel() {
        viewController.showAdminMenuView(1);
    }

}
