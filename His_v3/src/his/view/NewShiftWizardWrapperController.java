package his.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Patrick
 */
public class NewShiftWizardWrapperController extends FadeAble implements Initializable {

    @FXML
    private BorderPane pane;
    @FXML
    private Label subtitleL;
    @FXML
    private Button nextBTN;
    @FXML
    private Button backBTN;
    @FXML
    private Label currentPageL;
    @FXML
    private Label allPagesL;

    private FadeAble currentController;

    private ViewController viewController;

    private int page = 0;
    private int totalPages = 3;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPane(pane);
    }

    private void showChooseUsersView() {
        if (this.viewController.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("ChooseUsersView.fxml"));
                StackPane pane = (StackPane) loader.load();

                ChooseUsersViewController controller = loader.getController();
                controller.setViewController(viewController);
                setCenterView(pane, controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showChooseEventsView() {
        if (this.viewController.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("ChooseEventsView.fxml"));
                StackPane pane = (StackPane) loader.load();

                ChooseEventsViewController controller = loader.getController();
                controller.setViewController(viewController);
                setCenterView(pane, controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showShiftSetupView() {
        if (this.viewController.primaryStage.isShowing()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ViewController.class.getResource("ShiftSetupView.fxml"));
                StackPane pane = (StackPane) loader.load();

                ShiftSetupViewController controller = loader.getController();
                controller.setViewController(viewController);
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
                    NewShiftWizardWrapperController.this.pane.setCenter(pane);
                    NewShiftWizardWrapperController.this.currentController = controller;
                    NewShiftWizardWrapperController.this.currentController.fadeIn(duration, null);
                }
            });
        } else {
            this.pane.setCenter(pane);
            this.currentController = controller;
            this.currentController.fadeIn(duration, null);
        }
    }

    @FXML
    private void handleNext() {
        if (page != 2) {
            page++;
            setPage();
        } else {
            viewController.showMainView();
        }
    }

    public void setPage() {
        switch (page) {
            case 0:
                currentPageL.setText(String.valueOf(page + 1));
                allPagesL.setText(String.valueOf(totalPages));
                backBTN.setDisable(true);
                nextBTN.setDisable(false);
                nextBTN.setText("Next");
                backBTN.setText("Back");
                subtitleL.setText("Choose users for this shift, at least 2");
                showChooseUsersView();
                break;
            case 1:
                currentPageL.setText(String.valueOf(page + 1));
                allPagesL.setText(String.valueOf(totalPages));
                backBTN.setDisable(false);
                nextBTN.setDisable(false);
                nextBTN.setText("Next");
                backBTN.setText("Back");
                subtitleL.setText("Choose events, if any");
                showChooseEventsView();
                break;
            case 2:
                currentPageL.setText(String.valueOf(page + 1));
                allPagesL.setText(String.valueOf(totalPages));
                backBTN.setDisable(false);
                nextBTN.setDisable(false);
                nextBTN.setText("Finish");
                backBTN.setText("Back");
                subtitleL.setText("General finishing shift stuff");
                showShiftSetupView();
                break;
        }
    }

    @FXML
    private void handleBack() {
        page--;
        setPage();
    }

}
