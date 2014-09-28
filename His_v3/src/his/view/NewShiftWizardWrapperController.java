
package his.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Patrick
 */
public class NewShiftWizardWrapperController extends FadeAble implements Initializable {
    
    @FXML
    private BorderPane pane;
    
    private FadeAble currentController;
    
    private ViewController viewController;
    
    private int page = 0;
    
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
                AnchorPane pane = (AnchorPane) loader.load();

                ChooseUsersViewController controller = loader.getController();
                controller.setViewController(viewController);
                setCenterView(pane, controller);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void showChooseEventsView() {
        
    }
    
    private void showShiftSetupView() {
        
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
        page++;
        setPage();
    }
    
    public void setPage() {
        switch(page) {
            case 0:
                showChooseUsersView();
                break;
            case 1:
                showChooseEventsView();
                break;
            case 2:
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
