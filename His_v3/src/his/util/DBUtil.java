package his.util;

import his.control.ConfigHandler;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author Patrick Kann
 */
public class DBUtil {

    private static Connection instance = null;
    private static Statement statement = null;

    /**
     * private constructor (this class is singleton)
     */
    private DBUtil() {
    }

    /**
     * Checks if a connection instance already exists. If it does, it will be returned. If not, it will be created and returned. This makes it impossible to have more than one instance of a connection (singleton)
     *
     * @return connection
     */
    public static Connection getInstance() {
        try {
            if (instance == null || instance.isClosed()) {
                makeInstance();
                return instance;
            } else {
                return instance;
            }
        } catch (SQLException ex) {
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
            }
        }
        return null;
    }

    /**
     * Checks if a statement already exists. If it does, it will be returned, If not, it will be created and returned. This makes it impossible to have more than one statement (singleton)
     *
     * @return statement
     */
    public static Statement getStatement() {
        try {
            if (statement == null || statement.isClosed()) {
                makeStatement();
                return statement;
            } else {
                return statement;
            }
        } catch (SQLException ex) {
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
            }
        }
        return null;
    }

    /**
     * Creates a statement
     */
    private static void makeStatement() {
        try {
            if (instance == null || instance.isClosed()) {
                makeInstance();
            }
            statement = instance.createStatement();
        } catch (SQLException ex) {
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
            }
        }
    }

    /**
     * Creates a connection instance
     */
    private static void makeInstance() {
        String FILENAME = "dbconfig.properties"; // Indikates where config file is
        Properties dbProp = new Properties(); // Creates a properties type object
        FileInputStream in = null;

        try {
            in = new FileInputStream(FILENAME); // Creates a fileinputstream and loads the config file
            dbProp.load(in); // Loads the config file into our properties object (to hold the config in memory to use)
            in.close(); // closes the fileinputstream (we dont need anymore)
        } catch (IOException ex) {
            Dialogs.create().title("File not found").message("The database configuration file was not found\nContact an administrator").showError();
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("IOException").message("An IOException occurred...").showException(ex);
            }
            System.exit(0);
        }

        String dbUser = dbProp.getProperty("dbUser"); // Gets the dbUser from the properties object
        String dbPass = dbProp.getProperty("dbPass"); // Gets the dbPass from the properties object
        String dbURL = dbProp.getProperty("dbURL"); // Gets the dbURL from the properies object

        try {
            instance = DriverManager.getConnection(dbURL, dbUser, dbPass); // Creates and gets the MySQL connection and saves it to the instance variable
        } catch (SQLException ex) {
            Dialogs.create().title("Connection error").message("Could not connect to database\nContact an administrator if the problem persists...").showError();
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
            }
            System.exit(0);
        }
    }

    /**
     * Closes the statement and the connection
     */
    public static void close() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (instance != null && !instance.isClosed()) {
                instance.close();
            }
        } catch (SQLException ex) {
            if (ConfigHandler.getInstance().getDebug()) {
                Dialogs.create().title("SQLException").message("An SQLException occurred...").showException(ex);
            }
        }
    }
}
