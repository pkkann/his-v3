package his;

import his.control.ResourceManager;
import his.view.ViewController;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.GlyphFont;

/**
 *
 * @author Patrick
 */
public class His extends Application {

    public static final String name = "Den våde hønes indskrivnings system";
    public static final String version = "3.0";
    public static final String title = name + " " + version;

    private ViewController viewController;
    private ResourceManager resourceManager;

    @Override
    public void start(Stage primaryStage) {
        registerGlyphs();
        
        viewController = new ViewController(primaryStage);
        viewController.showLoginView();
        
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
                map.put("close", 'e');
                map.put("help", 'f');
                return map;
            }
        };
        ResourceManager.registerGlyphFont("default", gf);
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
