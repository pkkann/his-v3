package his.view;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Patrick
 */
public abstract class FadeAble {

    private Pane pane;
    private FadeTransition ft;
    private TranslateTransition tt;
    private ParallelTransition pt;

    protected void setPane(Pane pane) {
        this.pane = pane;
        ft = new FadeTransition(Duration.millis(300), pane);
        tt = new TranslateTransition(Duration.millis(300), pane);
        pt = new ParallelTransition(ft, tt);
    }

    protected FadeTransition getFadeTransition() {
        if (ft != null) {
            return ft;
        }
        return null;
    }

    protected TranslateTransition getTranslateTransition() {
        return tt;
    }

    protected ParallelTransition getParallelTransition() {
        return pt;
    }
    
    public void fadeIn(double milis, Runnable run) {
        if (ft != null && tt != null && pt != null) {
            ft.setDuration(Duration.millis(milis));
            ft.setFromValue(0.0);
            ft.setToValue(1.0);

            tt.setDuration(Duration.millis(milis));
            pane.setLayoutX(-100);
            tt.setFromX(pane.getLayoutX());
            tt.setToX(0);
            if (run != null) {
                pt.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        run.run();
                    }
                });
            }
            pt.play();
        } else {
            throw new NullPointerException("Transition is null!");
        }
    }
    
    public void fadeOut(double milis, Runnable run) {
        if (ft != null && tt != null && pt != null) {
            ft.setDuration(Duration.millis(milis));
            ft.setFromValue(1.0);
            ft.setToValue(0.0);

            tt.setDuration(Duration.millis(milis));
            pane.setLayoutX(0);
            tt.setFromX(pane.getLayoutX());
            tt.setToX(100);
            if (run != null) {
                pt.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        run.run();
                    }
                });
            }
            pt.play();
        } else {
            throw new NullPointerException("Transition is null!");
        }
    }

}
