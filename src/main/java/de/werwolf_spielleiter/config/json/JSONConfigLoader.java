package de.werwolf_spielleiter.config.json;

import de.werwolf_spielleiter.constants.PublicGameConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.CONFIG_JSON_OBJECT;

public class JSONConfigLoader {
    /* Variables */
    private boolean isJSONIntact = false;

    /**
     * @author Eric De Ron
     * @param constantMap Map of constants which should be written
     *                    to JSON
     * @param path Path of JSON config file
     * @param name Name of JSON config file
     */
    public void writeConfigToJSON(Map<String, Object> constantMap, String path, String name) {
        JSONArray constantsArray = new JSONArray();
        JSONObject constantsJson;

        // Add all constants to the JSONObject
        // Sorted as per PublicGameConstants order
        for (Field field : PublicGameConstants.class.getDeclaredFields()) {
            constantsJson = new JSONObject();
            constantsJson.put(field.getName(), constantMap.get(field.getName()));
            constantsArray.put(constantsJson);
        }

        // Add constantsArray to root JSONObject
        JSONObject settingsName = new JSONObject();
        settingsName.put(CONFIG_JSON_OBJECT, constantsArray);

        // Write config to JSON file
        try (FileWriter file = new FileWriter(path + "\\" + name)) {
            // Number of spaces for every indent
            file.write(settingsName.toString(4));
            file.flush();
        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * @author Eric De Ron
     * @param file JSON config file which should be read
     * @return Map of keys gotten from JSON config
     */
    public Map<String, Object> readConfigFromJSON(File file) {
        Map<String, Object> retConstantMap = new HashMap<>();

        try {
            // Read JSON config file
            JSONTokener jsonTokener = new JSONTokener(file.toURI().toURL().openStream());
            // Get JSONObject config root
            JSONObject root = new JSONObject(jsonTokener);

            // Get array of constant JSONObejcts
            JSONArray constantsArray = root.getJSONArray(CONFIG_JSON_OBJECT);

            // Add all JSONObject constants
            // to the return map
            JSONObject jsonConstant;
            String constantKey;
            for (Object jsonObject : constantsArray) {
                jsonConstant = (JSONObject) jsonObject;
                // Ignore empty JSONObjects, they will
                // be replaced with default values
                if (jsonConstant.keys().hasNext()) {
                    constantKey = jsonConstant.keys().next();
                    retConstantMap.put(constantKey, jsonConstant.get(constantKey));
                }
            }
            setJSONIntact(true);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return retConstantMap;
    }

    /**
     * @author Eric De Ron
     * @return True if read JSON config is not malformed
     */
    public boolean isJSONIntact() {
        return isJSONIntact;
    }

    /**
     * @author Eric De Ron
     * @param JSONIntact True if JSON config is intact
     *                   else false
     */
    public void setJSONIntact(boolean JSONIntact) {
        isJSONIntact = JSONIntact;
    }
}
