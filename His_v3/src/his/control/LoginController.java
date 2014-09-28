package his.control;

import exceptions.CouldNotLoginException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick
 */
public class LoginController {

    public static void executeLogin(String username, String password, Runnable success, Stage primaryStage) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws InterruptedException, CouldNotLoginException {
                        updateMessage("Logging in . . .");
                        Thread.sleep(500);
                        if(username.equals("pkkann")) {
                            Thread.sleep(500);
                            if(password.equals("rollercoaster")) {
                                Thread.sleep(500);
                            } else {
                                throw new CouldNotLoginException();
                            }
                        } else {
                            throw new CouldNotLoginException();
                        }
                        return null;
                    }
                };
            }
        };

        Dialogs.create()
                .owner(primaryStage)
                .title("Login")
                .showWorkerProgress(service);
        
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                Dialogs.create()
                        .owner(primaryStage)
                        .title("Failed")
                        .masthead("Failed to login")
                        .message("Username or password is wrong...\n\nIf you have forgotten, then it is your problem...")
                        .showError();
            }
        });
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                if(success != null) {
                    success.run();
                }
            }
        });

        service.start();
        
    }
}
