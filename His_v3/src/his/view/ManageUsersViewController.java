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
import org.controlsfx.control.action.Action;
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
    private Button createBTN;
    @FXML
    private Button editBTN;
    @FXML
    private Button deleteBTN;
    @FXML
    private TextField searchTF;

    @FXML
    private TableView<User> usersTV;
    @FXML
    private TableColumn<User, String> nameTC;
    @FXML
    private TableColumn<User, String> usernameTC;
    @FXML
    private TableColumn<User, String> emailTC;
    @FXML
    private TableColumn<User, String> phoneTC;
    @FXML
    private TableColumn<User, Boolean> administratorTC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setPane(pane);

        createBTN.setTooltip(new Tooltip("Create"));
        editBTN.setTooltip(new Tooltip("Edit"));
        deleteBTN.setTooltip(new Tooltip("Delete"));

        Glyph gCreate = ResourceManager.getGlyph("default", 'h', Color.WHITE, 16);
        Glyph gEdit = ResourceManager.getGlyph("default", 'g', Color.WHITE, 16);
        Glyph gDelete = ResourceManager.getGlyph("default", 'i', Color.WHITE, 16);

        createBTN.setGraphic(gCreate);
        editBTN.setGraphic(gEdit);
        deleteBTN.setGraphic(gDelete);

        nameTC.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        usernameTC.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
        emailTC.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        phoneTC.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
        administratorTC.setCellValueFactory(cellData -> cellData.getValue().isAdministratorProperty());

        usersTV.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> userSelected(newValue));
    }

    private void userSelected(User u) {
        if (u != null && u != viewController.userRegister.getLoggedInUser()) {
            editBTN.setDisable(false);
            deleteBTN.setDisable(false);
        } else {
            editBTN.setDisable(true);
            deleteBTN.setDisable(true);
        }
    }

    public void loadTable() {
        usersTV.setItems(viewController.userRegister.getObservableObjects());

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(viewController.userRegister.getObservableObjects(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchTF.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(user -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare info
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    userSelected(null);
                    return true; // Filter matches first name.
                } else if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    userSelected(null);
                    return true; // Filter mateches username.
                } else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    userSelected(null);
                    return true; // Filter mateches email.
                } else if (user.getPhone().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    userSelected(null);
                    return true; // Filter mateches phone.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(usersTV.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        usersTV.setItems(sortedData);
    }

    @FXML
    private void handleCreate() {
        viewController.showCreateUserView();
    }

    @FXML
    private void handleEdit() {
        User u = usersTV.getSelectionModel().getSelectedItem();
        viewController.showEditUserView(u);
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
