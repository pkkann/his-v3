package his.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Patrick
 */
public class ViewController {

    public Stage primaryStage;

    private BorderPane wrapperView;
    private FadeAble currentController;

    public ViewController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        init();
    }

    private void init() {
        initViewWrapper();
        this.primaryStage.setFullScreenExitHint("");
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.setMinWidth(1360);
        this.primaryStage.setMinHeight(870);
        this.primaryStage.setTitle(his.His.title);
        this.primaryStage.setMaximized(true);
        this.primaryStage.show();
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

            this.primaryStage.setScene(scene);
            this.wrapperView = pane;
        } catch (IOException e) {
            e.printStackTrace();
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
            } catch (IOException e) {
                e.printStackTrace();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void setCenterView(Pane pane, FadeAble controller) {
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
