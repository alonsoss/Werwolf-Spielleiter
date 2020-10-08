package de.werwolf_spielleiter;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.scene.GameScene;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class SceneManagerTest {
    public final SceneManager testView = new SceneManager();

    /**
     * Switching to any viable scene in the scene manager
     * should cause a NullPointer because the fxml isn't
     * loaded, also the current scene should change
     * @author Eric De Ron
     * @param testScene A test scene of the GameScene class
     *                  to test functionality of the method
     */
    @ParameterizedTest
    @EnumSource(GameScene.class)
    void switchToScene(GameScene testScene) {
        // Default: first scene
        testView.setCurrentScene(GameScene.PLAYER_AMOUNT_CHOOSING);
        assertThrows(NullPointerException.class, () -> testView.switchToScene(testScene));
        assertEquals(testView.getCurrentScene(), testScene);
    }

    /**
     * A null pointer must not change the scene
     * the scene must stay at the given test scene
     * @author Eric De Ron
     * @param testScene A test scene of the GameScene class
     *                  to use as default scene
     */
    @ParameterizedTest
    @EnumSource(GameScene.class)
    void switchToSceneNullTest(GameScene testScene) {
        // Default: first scene
        testView.setCurrentScene(testScene);
        // Shouldn't switch from the current scene on null pointer
        testView.switchToScene(null);

        assertEquals(testView.getCurrentScene(), testScene);
    }

    /**
     * After setting the current scene it should
     * return the set scene
     * @author Eric De Ron
     * @param testScene A test scene of the GameScene class
     *                  to test functionality of the method
     */
    @ParameterizedTest
    @EnumSource(GameScene.class)
    void getCurrentScene(GameScene testScene) {
        testView.setCurrentScene(testScene);
        assertEquals(testView.getCurrentScene(), testScene);
    }

    /**
     * A null pointer must not change the scene
     * the scene must stay at the given test scene
     * @author Eric De Ron
     * @param testScene A test scene of the GameScene class
     *                  to use as default scene
     */
    @ParameterizedTest
    @EnumSource(GameScene.class)
    void getCurrentSceneNullTest(GameScene testScene) {
        // Shouldn't change from the current scene on null pointer
        testView.setCurrentScene(testScene);
        testView.setCurrentScene(null);
        assertEquals(testView.getCurrentScene(), testScene);
    }
}