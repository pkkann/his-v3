/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view;

import his.control.ResourceManager;
import his.model.User;
import his.util.Loader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialog.Actions;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.glyphfont.Glyph;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class ManageUsersViewController extends View implements Initializable {

    @FXML
    private StackPane pane;
    @FXML
    private Button backBTN;
    @FXML
    private Button createBTN;
    @FXML
    private Button editBTN;
    @FXML
    private Button deleteBTN;

    @FXML
    private TableView<User> usersTV;
    @FXML
    private TableColumn<User, String> nameTC;
    @FXML
    private TableColumn<User, String> usernameTC;
    @FXML
    private TableColumn<User, Boolean> administratorTC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPane(pane);

        backBTN.setTooltip(new Tooltip("Back"));
        createBTN.setTooltip(new Tooltip("Create"));
        editBTN.setTooltip(new Tooltip("Edit"));
        deleteBTN.setTooltip(new Tooltip("Delete"));

        Glyph gBack = ResourceManager.getGlyph("default", 'j', Color.WHITE, 16);
        Glyph gCreate = ResourceManager.getGlyph("default", 'h', Color.WHITE, 16);
        Glyph gEdit = ResourceManager.getGlyph("default", 'g', Color.WHITE, 16);
        Glyph gDelete = ResourceManager.getGlyph("default", 'i', Color.WHITE, 16);

        backBTN.setGraphic(gBack);
        createBTN.setGraphic(gCreate);
        editBTN.setGraphic(gEdit);
        deleteBTN.setGraphic(gDelete);

        nameTC.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        usernameTC.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        administratorTC.setCellValueFactory(cellData -> cellData.getValue().isAdministratorProperty());

        usersTV.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> userSelected(newValue));
    }

    private void userSelected(User u) {
        editBTN.setDisable(false);
        deleteBTN.setDisable(false);
    }

    public void loadTable() {
        usersTV.setItems(viewController.userRegister.getObservableObjects());
    }

    @FXML
    private void handleBack() {
        viewController.showAdminMenuView();
    }

    @FXML
    private void handleCreate() {

    }

    @FXML
    private void handleEdit() {

    }

    @FXML
    private void handleDelete() {
        Action action = Dialogs.create().title("Sure?").message("This action can not be undone!!!").masthead("Sure you want to delete this user?").showConfirm();
        if (action == Actions.YES) {
            Runnable task = new Runnable() {

                @Override
                public void run() {
                    User u = usersTV.getSelectionModel().getSelectedItem();
                    viewController.userRegister.delete(u);
                }
            };
            Runnable fail = new Runnable() {

                @Override
                public void run() {
                    Dialogs.create().title("Error").message("An error ocourred when we tried to delete the user...\nContact administrator if problem persists").showError();
                }
            };

            Loader.showSimpleLoader(viewController.primaryStage, "Deleting", "Deleting user...", task, null, fail);
        }
    }

}
