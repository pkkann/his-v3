package his.view;

import his.view.dialogs.EditProfileDialog;
import his.view.createuser.CreateUserViewController;
import his.view.login.LoginViewController;
import his.view.main.MainViewController;
import his.view.manageshifts.ManageShiftsViewController;
import his.view.manageusers.ManageUsersViewController;
import his.view.newshiftwizardwrapper.NewShiftWizardWrapperController;
import his.view.settings.SettingsViewController;
import his.view.viewwrapper.ViewWrapperController;
import his.view.adminmenu.AdminMenuViewController;
import his.control.ConfigHandler;
import his.model.shift.ShiftRegister;
import his.model.user.User;
import his.model.user.UserRegister;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick
 */
public class ViewController {

    public UserRegister userRegister;
    public ShiftRegister shiftRegister;

    public Stage primaryStage;

    private BorderPane wrapperView;
    private ViewWrapperController wrapperController;
    private View currentController;

    public ViewController(Stage primaryStage, UserRegister userRegister, ShiftRegister shiftRegister) {
        this.primaryStage = primaryStage;
        this.userRegister = userRegister;
        this.shiftRegister = shiftRegister;
        init();
    }

    private void init() {
        this.primaryStage.setFullScreenExitHint("");
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.setMinHeight(600);
        this.primaryStage.setTitle(his.His.title);
        if (ConfigHandler.getInstance().getFullscreen()) {
            this.primaryStage.setFullScreen(true);
        }
        initViewWrapper();

        this.primaryStage.show();
    }

    public void setLoggedInUser(User u) {
        if (wrapperController != null) {
            if (u != null) {
                userRegister.setLoggedInUser(u);
                wrapperController.bindLoggedInL(true);
                wrapperController.setEditProfileBTNDisabled(false);
                wrapperController.setLogoutBTNDisabled(false);
            } else {
                wrapperController.bindLoggedInL(false);
                wrapperController.setEditProfileBTNDisabled(true);
                wrapperController.setLogoutBTNDisabled(true);
            }
        }
    }

    public void setViewTitle(String text) {
        if (wrapperController != null) {
            this.wrapperController.setTitleLText(text);
        }
    }

    public void setFullscreen(boolean fullscreen) {
        this.primaryStage.setFullScreen(fullscreen);
    }

    public boolean isFullscreen() {
        return this.primaryStage.isFullScreen();
    }

    public void setIconified(boolean iconified) {
        this.primaryStage.setIconified(iconified);
    }

    public boolean isIconified() {
        return this.primaryStage.isIconified();
    }

    public void setMaximized(boolean maximized) {
        this.primaryStage.setMaximized(maximized);
    }

    public boolean isMaximized() {
        return this.primaryStage.isMaximized();
    }

    public void close() {
        this.primaryStage.close();
    }

    private void initViewWrapper() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ViewController.class.getResource("viewwrapper/ViewWrapper.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            Scene scene = new Scene(pane);
            ViewWrapperController controller = loader.getController();
            controller.setViewController(this);
            controller.initButtons();

            this.wrapperController = controller;

            this.primaryStage.setScene(scene);
            this.wrapperView = pane;
        } catch (IOException e) {
            Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
            }
        }
    }

    public void showMainView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("main/MainView.fxml"));
                StackPane pane = (StackPane) loader.load();

                MainViewController controller = loader.getController();
                controller.setViewController(this);
                setCenterView(pane, controller);
                setViewTitle("Residents");
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }

    }

    public void showLoginView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("login/LoginView.fxml"));
                StackPane pane = (StackPane) loader.load();

                LoginViewController controller = loader.getController();
                controller.setViewController(this);
                setCenterView(pane, controller);
                setLoggedInUser(null);
                setViewTitle("Login");
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
    }

    public void showNewShiftWizard() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("newshiftwizardwrapper/NewShiftWizardWrapper.fxml"));
                BorderPane pane = (BorderPane) loader.load();

                NewShiftWizardWrapperController controller = loader.getController();
                controller.setViewController(this);
                setCenterView(pane, controller);
                controller.setPage();
                setViewTitle("New shift wizard");
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
    }

    public void showAdminMenuView(int selection) {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("adminmenu/AdminMenuView.fxml"));
                StackPane pane = (StackPane) loader.load();

                AdminMenuViewController controller = loader.getController();
                controller.setViewController(this);
                controller.loadTabs(selection);
                setCenterView(pane, controller);

                setViewTitle("Administrators menu");

            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
    }

    public StackPane constructManageUsersView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("manageusers/ManageUsersView.fxml"));
                StackPane pane = (StackPane) loader.load();

                ManageUsersViewController controller = loader.getController();
                controller.setViewController(this);
                //setCenterView(pane, controller);
                //setViewTitle("Manage users");
                controller.loadTable();
                return pane;
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
        return null;
    }
    
    public StackPane constructManageShiftsView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("manageshifts/ManageShiftsView.fxml"));
                StackPane pane = (StackPane) loader.load();

                ManageShiftsViewController controller = loader.getController();
                controller.setViewController(this);
                //setCenterView(pane, controller);
                //setViewTitle("Manage users");
                controller.loadTable();
                return pane;
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
        return null;
    }

    public StackPane constructSettingsView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("settings/SettingsView.fxml"));
                StackPane pane = (StackPane) loader.load();

                SettingsViewController controller = loader.getController();
                controller.setViewController(this);
                //setCenterView(pane, controller);
                //setViewTitle("Manage users");
                return pane;
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
        return null;
    }

    public void showCreateUserView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("createuser/CreateUserView.fxml"));
                AnchorPane pane = (AnchorPane) loader.load();

                CreateUserViewController controller = loader.getController();
                controller.setViewController(this);
                setCenterView(pane, controller);
                setViewTitle("Create user");
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
    }

    public void showEditUserView(User u) {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("createuser/CreateUserView.fxml"));
                AnchorPane pane = (AnchorPane) loader.load();

                CreateUserViewController controller = loader.getController();
                controller.setViewController(this);
                controller.edit(u);
                setCenterView(pane, controller);
                setViewTitle("Edit user");
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
    }

    public void showEditProfileDialog(User u) {
        EditProfileDialog epd = new EditProfileDialog(this, u);
    }

    private void setCenterView(Pane pane, View controller) {
        int duration = 300;
        if (this.currentController != null) {
            this.currentController.fadeOut(duration, new Runnable() {
                @Override
                public void run() {
                    ViewController.this.wrapperView.setCenter(pane);
                    ViewController.this.currentController = controller;
                    ViewController.this.currentController.fadeIn(duration, null);
                }
            });
        } else {
            this.wrapperView.setCenter(pane);
            this.currentController = controller;
            this.currentController.fadeIn(duration, null);
        }
    }

}
