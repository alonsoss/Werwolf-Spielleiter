package de.werwolf_spielleiter;

import de.werwolf_spielleiter.config.ConfigLoader;

public class MainWrapper {
    /**
     *
     * Used as wrapper to launch the main javafx application
     * Classes which extends javafx .Application will cause:
     * "Error: JavaFX runtime components are missing, and are
     * required to run this application"
     * @author Eric De Ron
     * @param args Runtime parameters
     */
    public static void main(String[] args) {
        // Before starting the game read
        // all PublicGameConstants from
        // config file
        ConfigLoader.getInstance().initConfig();

        // Start the game
        SceneManager.main(args);
    }
}
