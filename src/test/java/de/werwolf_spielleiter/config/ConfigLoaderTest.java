package de.werwolf_spielleiter.config;

import de.werwolf_spielleiter.constants.PublicGameConstants;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static de.werwolf_spielleiter.config.ConfigLoader.configGetOrDefault;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConfigLoaderTest {
    private final PublicGameConstants testPublicGameConstants = new PublicGameConstants();

    /**
     * Test singleton
     * @author Eric De Ron
     */
    @Test
    void getInstance() {
        assertNotNull(ConfigLoader.getInstance());
    }

    /**
     * Test that every field is getable
     * with this method
     * @author Eric De Ron
     */
    @Test
    void testConfigGetOrDefault() {
        Field[] testList = PublicGameConstants.class.getDeclaredFields();
        for (Field testField : testList) {
            try {
                assertEquals(testField.get(testPublicGameConstants).getClass(), configGetOrDefault(testField.getName()).getClass());
            } catch (IllegalAccessException ignored) {}
        }
    }
}