package de.werwolf_spielleiter.config;

import de.werwolf_spielleiter.config.json.JSONConfigLoader;
import de.werwolf_spielleiter.constants.PublicGameConstants;
import de.werwolf_spielleiter.constants.exception.NoSuchConstantException;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.CONFIG_NAME;
import static de.werwolf_spielleiter.constants.PrivateGameConstants.CONFIG_PATH;

public class ConfigLoader {
    private static final String LOG_TAG = "ConfigLoader";
    private static ConfigLoader instanceConfigLoader;

    private final JSONConfigLoader jsonConfigLoader;
    private final PublicGameConstants publicGameConstants;
    private final Map<String, Object> constantsMap;

    /* Variables */
    // Map to put all JSON/default constants in
    private Map<String, Object> constantsConfigMap;

    /**
     * A class to fetch all constants
     * from a JSON loader and deliver
     * them over the configGetOrDefault
     * method
     * @author Eric De Ron
     */
    private ConfigLoader() {
        this.jsonConfigLoader = new JSONConfigLoader();
        this.publicGameConstants = new PublicGameConstants();
        this.constantsMap = new HashMap<>();
        this.constantsConfigMap = new HashMap<>();

        // Fill a map with all default constants
        // for later comparisons
        for (Field constField : PublicGameConstants.class.getDeclaredFields()) {
            try {
                constantsMap.put(constField.getName(), constField.get(publicGameConstants));
            } catch (IllegalAccessException e) { e.printStackTrace(); }
        }
    }

    /**
     * @author Eric De Ron
     * @return Singleton ConfigLoader
     */
    public static ConfigLoader getInstance() {
        if (instanceConfigLoader == null) instanceConfigLoader = new ConfigLoader();
        return instanceConfigLoader;
    }

    /**
     * @author Eric De Ron
     * @param publicGameConstant String representation of a constant
     *                     to get
     * @return Object if the constant is available in PublicGameConstants
     */
    public static Object configGetOrDefault(String publicGameConstant) {
        Object retObject = null;
        try {
            // Try to get constant
            retObject = ConfigLoader.getInstance().getOrDefault(publicGameConstant);
        } catch (NoSuchConstantException e) { e.printStackTrace(); }
        return retObject;
    }

    /**
     * @author Eric De Ron
     * @param gameConstant String representation of a constant
     *                     to get
     * @return Object if the constant is available in PublicGameConstants
     * @throws NoSuchConstantException If given constant doesn't exist
     */
    private Object getOrDefault(String gameConstant) throws NoSuchConstantException {
        // Normalize constant key string
        String configConstant = gameConstant.toUpperCase().trim();

        // If constantsConfigMap is empty
        // reload from JSON config or
        // PublicGameConstants
        if (constantsConfigMap.isEmpty())
            initConfig();

        // Check if constant exits in PublicGameConstants
        if (!constantsConfigMap.containsKey(configConstant))
            throw new NoSuchConstantException(LOG_TAG + ":No constant found in "
                    + publicGameConstants.getClass().getName() + " with the name: " + gameConstant);

        return constantsConfigMap.get(gameConstant);
    }

    /**
     * Reads JSON config if it exits,
     * if not, create default config,
     * if JSON config malformed,
     * restore default config
     * @author Eric De Ron
     */
    public void initConfig() {
        File configJSONFile = new File(CONFIG_PATH + "\\" + CONFIG_NAME);

        if (configJSONFile.exists()) {
            // If JSON config exits read it
            Map<String, Object> constantFromJSON = jsonConfigLoader.readConfigFromJSON(configJSONFile);
            if (jsonConfigLoader.isJSONIntact()) {
                // If JSON config had no syntactic error
                loadConstantsFromJSON(constantFromJSON);
            } else {
                // If JSON config a syntactic error
                // Recreate JSON config
                constantsConfigMap = constantsMap;
                restoreDefaultJSONConfig("malformed JSON");
            }
        } else {
            // If no JSON config exits
            // Create a new JSON config
            constantsConfigMap = constantsMap;
            restoreDefaultJSONConfig("no config found");
        }
    }

    /**
     * Gets all loaded constants from the JSON and
     * checks its consistency
     * @author Eric De Ron
     * @param constantFromJSON Map of constants read from JSON
     *                         config
     */
    public void loadConstantsFromJSON(Map<String, Object> constantFromJSON) {
        // Count amount of same keys in
        // constantFromJSON and constantsMap
        int trackKeyEquality = 0;

        // True if json file needs to be
        // rewritten with missing/wrong constants
        boolean constantOverwritten = false;

        for (Map.Entry<String, Object> entry : constantFromJSON.entrySet()) {
            // Check if constant is an actual
            // constant from PublicGameConstants
            Object defaultConstant = constantsMap.get(entry.getKey());

            if (defaultConstant == null) {
                // If not remove line from JSON
                System.err.println(LOG_TAG + ": Ignore invalid constant " + entry.getKey());
                constantOverwritten = true;
                trackKeyEquality--;
            } else if (entry.getValue().getClass().equals(defaultConstant.getClass())) {
                // If yes put it to the constantsConfigMap
                constantsConfigMap.put(entry.getKey(), entry.getValue());
            } else {
                // If type is wrong replace value
                // with default value
                System.err.println(LOG_TAG + ": Overriding incompatible type for " + entry.getKey());
                constantsConfigMap.put(entry.getKey(), defaultConstant);
                constantOverwritten = true;
            }
            trackKeyEquality++;
        }
        // If constant keys from JSON is not the same amount
        // as constantsMap some constant might be missing
        // restore config with missing constant replaced
        // with default values
        if (trackKeyEquality != constantsMap.size()) {
            System.err.println(LOG_TAG + ": Missing constant(s) found");
            for (Map.Entry<String, Object> entry : constantsMap.entrySet()) {
                if (!constantsConfigMap.containsKey(entry.getKey())) {
                    System.err.println(LOG_TAG + ": Restore constant" + entry.getKey() + " with default value");
                    constantsConfigMap.put(entry.getKey(), entry.getValue());
                }
            }
            constantOverwritten = true;
        }
        // If JSON config needs to be rewritten
        if (constantOverwritten)
            jsonConfigLoader.writeConfigToJSON(constantsConfigMap, CONFIG_PATH, CONFIG_NAME);
    }

    /**
     * Creates a JSON config with all
     * default constants
     * @author Eric De Ron
     * @param reason String printed as reason in the error
     */
    public void restoreDefaultJSONConfig(String reason) {
        System.err.println(LOG_TAG + ": Restoring JSON config with default values (" + reason + ")");
        jsonConfigLoader.writeConfigToJSON(constantsMap, CONFIG_PATH, CONFIG_NAME);
    }
}
