package his.view;

import his.control.ConfigHandler;
import his.model.User;
import his.model.UserRegister;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
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

    public Stage primaryStage;

    private BorderPane wrapperView;
    private ViewWrapperController wrapperController;
    private View currentController;

    private User loggedInUser;

    public ViewController(Stage primaryStage, UserRegister userRegister) {
        this.primaryStage = primaryStage;
        this.userRegister = userRegister;
        init();
    }

    private void init() {
        initViewWrapper();
        this.primaryStage.setFullScreenExitHint("");
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.setMinWidth(1044);
        this.primaryStage.setMinHeight(808);
        this.primaryStage.setTitle(his.His.title);
        this.primaryStage.setMaximized(true);
        this.primaryStage.show();
    }

    public void setLoggedInUser(User u) {
        if (wrapperController != null) {
            this.loggedInUser = u;
            if (this.loggedInUser != null) {
                wrapperController.setLoggedInLText(this.loggedInUser.getName());
                wrapperController.setEditProfileBTNDisabled(false);
                wrapperController.setLogoutBTNDisabled(false);
            } else {
                wrapperController.setLoggedInLText("None");
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
            loader.setLocation(ViewController.class.getResource("ViewWrapper.fxml"));
            BorderPane pane = (BorderPane) loader.load();

            Scene scene = new Scene(pane);
            ViewWrapperController controller = loader.getController();
            controller.setViewController(this);

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
                loader.setLocation(ViewController.class.getResource("MainView.fxml"));
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
                loader.setLocation(ViewController.class.getResource("LoginView.fxml"));
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
                loader.setLocation(ViewController.class.getResource("NewShiftWizardWrapper.fxml"));
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

    public void showAdminMenuView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("AdminMenuView.fxml"));
                StackPane pane = (StackPane) loader.load();

                AdminMenuViewController controller = loader.getController();
                controller.setViewController(this);
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

    public void showManageUsersView() {
        if (this.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("ManageUsersView.fxml"));
                StackPane pane = (StackPane) loader.load();

                ManageUsersViewController controller = loader.getController();
                controller.setViewController(this);
                setCenterView(pane, controller);
                setViewTitle("Manage users");
                controller.loadTable();
            } catch (IOException e) {
                Dialogs.create().title("Failed to load").message("Failed to load view...\nContact administrator").showError();
                if (ConfigHandler.getInstance().getDebug()) {
                    Dialogs.create().title("IOException").message("An IOException occurred...").showException(e);
                }
            }
        }
    }

    private void setCenterView(Pane pane, View controller) {
        setLoggedInUser(loggedInUser);
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
