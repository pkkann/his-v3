/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package his.view;

import his.control.ResourceManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFontRegistry;

/**
 * FXML Controller class
 *
 * @author Patrick
 */
public class ViewWrapperController implements Initializable {

    private static double xOffset = 0;
    private static double yOffset = 0;
    private ViewController viewController;

    @FXML
    private AnchorPane topPane;
    @FXML
    private Label titleL;
    @FXML
    private Button closeBTN;
    @FXML
    private Button minimizeBTN;
    @FXML
    private Button fullscreenToggleBTN;
    @FXML
    private Button logoutBTN;
    @FXML
    private Button helpBTN;

    private FadeTransition minimizeBTNTrans;
    private FadeTransition closeBTNTrans;
    private FadeTransition titleLTrans;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        closeBTN.setTooltip(new Tooltip("Close"));
        minimizeBTN.setTooltip(new Tooltip("Minimize"));
        fullscreenToggleBTN.setTooltip(new Tooltip("Toggle fullscreen"));
        logoutBTN.setTooltip(new Tooltip("Logout"));
        helpBTN.setTooltip(new Tooltip("Help"));
        
        Glyph gExpand = ResourceManager.getGlyph("default", 'a', Color.WHITE, 16);
        Glyph gContract = ResourceManager.getGlyph("default", 'b', Color.WHITE, 16);
        Glyph gClose = ResourceManager.getGlyph("default", 'c', Color.WHITE, 16);
        Glyph gMinimize = ResourceManager.getGlyph("default", 'd', Color.WHITE, 16);
        Glyph gLogout = ResourceManager.getGlyph("default", 'e', Color.WHITE, 16);
        Glyph gHelp = ResourceManager.getGlyph("default", 'f', Color.WHITE, 16);
        
        closeBTN.setGraphic(gClose);
        minimizeBTN.setGraphic(gMinimize);
        fullscreenToggleBTN.setGraphic(gExpand);
        logoutBTN.setGraphic(gLogout);
        helpBTN.setGraphic(gHelp);
        
        closeBTNTrans = new FadeTransition(Duration.millis(300), closeBTN);
        closeBTNTrans.setFromValue(0.0);
        closeBTNTrans.setToValue(0.0);
        closeBTNTrans.play();
        closeBTN.setDisable(true);

        minimizeBTNTrans = new FadeTransition(Duration.millis(300), minimizeBTN);
        minimizeBTNTrans.setFromValue(0.0);
        minimizeBTNTrans.setToValue(0.0);
        minimizeBTNTrans.play();
        minimizeBTN.setDisable(true);
        
        titleLTrans = new FadeTransition(Duration.millis(300), titleL);
        titleLTrans.setFromValue(0.0);
        titleLTrans.setToValue(0.0);
        titleLTrans.play();
        titleL.setText(his.His.title);

//        topPane.setOnMousePressed(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                if (!viewController.isMaximized() && !viewController.isFullscreen()) {
//                    xOffset = viewController.primaryStage.getX() - event.getScreenX();
//                    yOffset = viewController.primaryStage.getY() - event.getScreenY();
//                }
//            }
//        });
//
//        topPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                if (!viewController.isMaximized() && !viewController.isFullscreen()) {
//                    viewController.primaryStage.setX(event.getScreenX() + xOffset);
//                    viewController.primaryStage.setY(event.getScreenY() + yOffset);
//                }
//            }
//        });
    }
    
    private void fadeInTitleL() {
        titleLTrans.setFromValue(0.0);
        titleLTrans.setToValue(1.0);
        titleLTrans.play();
    }
    
    private void fadeOutTitleL() {
        titleLTrans.setFromValue(1.0);
        titleLTrans.setToValue(0.0);
        titleLTrans.play();
    }

    private void fadeInMinimizeBTN() {
        minimizeBTN.setDisable(false);
        minimizeBTNTrans.setFromValue(0.0);
        minimizeBTNTrans.setToValue(1.0);
        minimizeBTNTrans.play();
    }

    private void fadeOutMinimizeBTN() {
        minimizeBTNTrans.setFromValue(1.0);
        minimizeBTNTrans.setToValue(0.0);
        minimizeBTNTrans.play();
        minimizeBTN.setDisable(true);
    }

    private void fadeInCloseBTN() {
        closeBTN.setDisable(false);
        closeBTNTrans.setFromValue(0.0);
        closeBTNTrans.setToValue(1.0);
        closeBTNTrans.play();
    }

    private void fadeOutCloseBTN() {
        closeBTNTrans.setFromValue(1.0);
        closeBTNTrans.setToValue(0.0);
        closeBTNTrans.play();
        closeBTN.setDisable(true);
    }

    @FXML
    private void handleClose() {
        viewController.close();
    }

    @FXML
    private void handleMinimize() {
        viewController.setIconified(true);
    }

    @FXML
    private void handleFullscreenToggle() {
        if (viewController.isFullscreen()) {
            viewController.setFullscreen(false);
            fadeOutCloseBTN();
            fadeOutMinimizeBTN();
            fadeOutTitleL();
        } else {
            viewController.setFullscreen(true);
            fadeInCloseBTN();
            fadeInMinimizeBTN();
            fadeInTitleL();
        }
    }
    
    @FXML
    private void handleLogout() {
        viewController.showLoginView();
    }
    
    @FXML
    private void handleHelp() {
        viewController.showHelpView();
    }

}
