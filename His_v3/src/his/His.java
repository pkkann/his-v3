package his;

import his.control.IDHandler;
import his.control.ResourceManager;
import his.dao.*;
import his.model.*;
import his.util.DBUtil;
import his.util.Loader;
import his.view.ViewController;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.glyphfont.GlyphFont;

/**
 *
 * @author Patrick
 */
public class His extends Application {

    public static final String name = "Den våde hønes indskrivnings system";
    public static final String version = "3.0";
    public static final String title = name + " " + version;

    private IDHandler idHandler;

    private UserDAO userDAO;
    private UserRegister userRegister;

    private ViewController viewController;
    private ResourceManager resourceManager;

    @Override
    public void start(Stage primaryStage) {
        Loader.showSimpleLoader(primaryStage, "Loading...", "Just a moment, loading everything...", new Runnable() {

            @Override
            public void run() {
                loadRegisters();
            }
        }, new Runnable() {

            @Override
            public void run() {
                registerGlyphs();
                viewController = new ViewController(primaryStage, userRegister);
                viewController.showLoginView();
            }
        }, new Runnable() {

            @Override
            public void run() {
                try {
                    Dialogs.create().title("ERROR").message("We could not load registers.. this is pretty bad..\nContact administrator").showError();
                    stop();
                } catch (Exception ex) {
                    Dialogs.create().title("ERROR").message("LOL, we could not stop the program either.. your screwed xD\nContact administrator").showError();
                }
            }
        });

    }

    @Override
    public void stop() throws Exception {
        DBUtil.close();
        System.exit(0);
    }

    private void loadRegisters() {
        idHandler = new IDHandler();

        userDAO = new UserDAO();
        userRegister = new UserRegister(userDAO, idHandler);

        userRegister.loadRegister();
        DBUtil.close();
        idHandler.init(userRegister);
    }

    private void registerGlyphs() {
        resourceManager = new ResourceManager();
        GlyphFont gf = new GlyphFont("icomoon", 16, this.getClass().getResourceAsStream("res/icomoon.ttf")) {

            @Override
            public Map<String, Character> getGlyphs() {
                HashMap<String, Character> map = new HashMap<>();
                map.put("expand", 'a');
                map.put("contract", 'b');
                map.put("close", 'c');
                map.put("minimize", 'd');
                map.put("logout", 'e');
                map.put("help", 'f');
                map.put("edit", 'g');
                map.put("new", 'h');
                map.put("delete", 'i');
                map.put("back", 'j');
                return map;
            }
        };
        ResourceManager.registerGlyphFont("default", gf);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
