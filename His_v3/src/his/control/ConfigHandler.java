package his.control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick
 */
public class ConfigHandler {

    private static Handler instance;

    private ConfigHandler() {
    }

    public static Handler getInstance() {
        if (instance == null) {
            makeInstance();
        }
        return instance;
    }

    private static void makeInstance() {
        instance = new Handler();
    }

    public static class Handler {

        private String configURL = "config.properties";
        private Properties prop;

        public Handler() {
            try {
                prop = new Properties();
                FileInputStream input = new FileInputStream(configURL);
                prop.load(input);
            } catch (IOException ex) {
                Dialogs.create().title("File not found").message("The configuration file was not found\nContact an administrator").showError();
                System.exit(0);
            }
        }
        
        private void save() {
            try {
                FileOutputStream output = new FileOutputStream(configURL);
                prop.store(output, "");
            } catch (IOException ex) {
                Dialogs.create().title("Error").message("An error happened, trying to save config...\nContact an administrator").showError();
                
            }
        }
        
        public void setDebug(boolean value) {
            prop.put("debug", String.valueOf(value));
            save();
        }
        
        public boolean getDebug() {
            String p = prop.getProperty("debug");
            if(p.equals("true")) {
                return true;
            } else {
                return false;
            }
        }
        
        public void setFullscreen(boolean value) {
            prop.put("fullscreen", String.valueOf(value));
            save();
        }
        
        public boolean getFullscreen() {
            String p = prop.getProperty("fullscreen");
            if(p.equals("true")) {
                return true;
            } else {
                return false;
            }
        }
        
    }
}
