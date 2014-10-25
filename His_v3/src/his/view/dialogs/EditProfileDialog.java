package his.view.dialogs;

import his.model.user.User;
import his.model.user.UserRegister;
import his.util.Loader;
import his.view.ViewController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.action.AbstractAction;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick
 */
public class EditProfileDialog {

    private final ViewController viewController;

    private Dialog dlg;

    private final TextField nameTF;
    private final TextField usernameTF;
    private final PasswordField passwordPF;
    private final PasswordField reenterPasswordPF;
    private final TextField emailTF;
    private final TextField phoneTF;
    private final CheckBox administratorCB;
    private final User u;

    private Action actionSave;

    public EditProfileDialog(ViewController viewController, User u) {
        this.viewController = viewController;
        this.u = u;
        this.administratorCB = new CheckBox();
        this.phoneTF = new TextField();
        this.emailTF = new TextField();
        this.reenterPasswordPF = new PasswordField();
        this.passwordPF = new PasswordField();
        this.usernameTF = new TextField();
        this.nameTF = new TextField();
        if (u != null) {
            init();
            dlg.show();
        } else {
            throw new NullPointerException("User is null");
        }
    }

    private void init() {
        actionSave = new AbstractAction("Save") {

            @Override
            public void handle(ActionEvent event) {
                boolean validated = validate(u);

                if (validated) {
                    u.setUsername(usernameTF.getText());
                    u.setName(nameTF.getText());
                    u.setPassword(passwordPF.getText());
                    u.setEmail(emailTF.getText());
                    u.setPhone(phoneTF.getText());
                    u.setAdministrator(administratorCB.isSelected());
                    viewController.userRegister.update(u);
                    dlg.hide();
                }

            }
        };

        dlg = new Dialog(viewController.primaryStage, "Edit profile");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        nameTF.setPromptText("Required");
        usernameTF.setPromptText("Required");
        passwordPF.setPromptText("Required");
        reenterPasswordPF.setPromptText("Required");
        administratorCB.setDisable(true);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameTF, 1, 0);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(usernameTF, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(passwordPF, 1, 2);
        grid.add(new Label("Reenter password:"), 0, 3);
        grid.add(reenterPasswordPF, 1, 3);
        grid.add(new Label("E-mail:"), 0, 4);
        grid.add(emailTF, 1, 4);
        grid.add(new Label("Phone:"), 0, 5);
        grid.add(phoneTF, 1, 5);
        grid.add(new Label("Administrator:"), 0, 6);
        grid.add(administratorCB, 1, 6);

        nameTF.setText(u.getName());
        usernameTF.setText(u.getUsername());
        passwordPF.setText(u.getPassword());
        reenterPasswordPF.setText(u.getPassword());
        emailTF.setText(u.getEmail());
        phoneTF.setText(u.getPhone());
        administratorCB.setSelected(u.isAdministrator());

        ButtonBar.setType(actionSave, ButtonBar.ButtonType.OK_DONE);

        dlg.setContent(grid);
        dlg.getActions().addAll(actionSave, Dialog.Actions.CANCEL);
    }

    private boolean validate(User u) {
        boolean edit = true;
        nameTF.setStyle("");
        usernameTF.setStyle("");
        passwordPF.setStyle("");
        reenterPasswordPF.setStyle("");
        emailTF.setStyle("");
        phoneTF.setStyle("");

        String name = nameTF.getText();
        String username = usernameTF.getText();
        String password = passwordPF.getText();
        String reenterPassword = reenterPasswordPF.getText();
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
                                passwordPF.setStyle("-fx-background: orange");
                                reenterPasswordPF.setStyle("-fx-background: orange");
                            }
                        } else {
                            Dialogs.create().title("Password").message("The two password are not identical...").showWarning();
                            passwordPF.setStyle("-fx-background: red");
                            reenterPasswordPF.setStyle("-fx-background: red");
                        }
                    } else {
                        Dialogs.create().title("Fields not filled").message("The two password field can not be empty").showWarning();
                        passwordPF.setStyle("-fx-background: red");
                        reenterPasswordPF.setStyle("-fx-background: red");
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

}
