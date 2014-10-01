
package his.util;

import his.exceptions.CouldNotLoginException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick
 */
public class Loader {
    
    private Loader() {}
    
    public static void showSimpleLoader(Object owner, String title, String message, Runnable task, Runnable success, Runnable fail) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws InterruptedException, CouldNotLoginException {
                        updateMessage(message);
                        if(task != null) {
                            task.run();
                        }
                        return null;
                    }
                };
            }
        };

        Dialogs.create()
                .owner(owner)
                .title(title)
                .showWorkerProgress(service);
        
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent event) {
                if(fail != null) {
                    fail.run();
                }
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
