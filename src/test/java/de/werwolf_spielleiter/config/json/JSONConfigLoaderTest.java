package de.werwolf_spielleiter.config.json;

import de.werwolf_spielleiter.constants.PublicGameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JSONConfigLoaderTest {
    private final JSONConfigLoader testJsonConfigLoader = new JSONConfigLoader();
    private final PublicGameConstants testPublicGameConstants = new PublicGameConstants();

    private final Map<String, Object> testMap = new HashMap<>();
    private final String testFileName = "test_config.json";
    private final File testPath = new File("src/test/resources");
    private final File testFile  = new File(testPath.getAbsolutePath() +  "\\" +  testFileName);

    /**
     * Init test map from PublicGameConstants
     * declared fields
     * @author Eric De Ron
     */
    @BeforeEach
    void initTest() {
        for (Field constField : PublicGameConstants.class.getDeclaredFields()) {
            try {
                testMap.put(constField.getName(), constField.get(testPublicGameConstants));
            } catch (IllegalAccessException e) { e.printStackTrace(); }
        }
    }

    /**
     * Test that the parsed test map
     * has the same size as the amount
     * of declared fields
     * @author Eric De Ron
     */
    @Test
    void JSONConfigLoader() {
        assertEquals(PublicGameConstants.class.getDeclaredFields().length, testMap.size());
    }

    /**
     * Test that a file is written
     * and test that the written map
     * is the same as the given map
     * @author Eric De Ron
     */
    @Test
    void writeConfigToJSON() {
        testJsonConfigLoader.writeConfigToJSON(testMap, testPath.getAbsolutePath(), testFileName);
        Map<String, Object> testWrittenMap = testJsonConfigLoader.readConfigFromJSON(testFile);

        assertTrue(testFile.exists());
        assertEquals(testMap, testWrittenMap);
    }

    /**
     * Test that the read map
     * is the same the given one
     * @author Eric De Ron
     */
    @Test
    void readConfigFromJSON() {
        testJsonConfigLoader.writeConfigToJSON(testMap, testPath.getAbsolutePath(), testFileName);
        Map<String, Object> testReadMap = testJsonConfigLoader.readConfigFromJSON(testFile);

        assertEquals(testMap, testReadMap);
    }

    /**
     * Test if default is false
     * and if it's changeable
     * @author Eric De Ron
     */
    @Test
    void isJSONIntact() {
        assertFalse(testJsonConfigLoader.isJSONIntact());
        testJsonConfigLoader.setJSONIntact(true);
        assertTrue(testJsonConfigLoader.isJSONIntact());
    }

    /**
     * Test if setting JSONIntact
     * works
     * @author Eric De Ron
     */
    @Test
    void setJSONIntact() {
        testJsonConfigLoader.setJSONIntact(true);
        assertTrue(testJsonConfigLoader.isJSONIntact());
        testJsonConfigLoader.setJSONIntact(false);
        assertFalse(testJsonConfigLoader.isJSONIntact());
    }
}