package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCountSceneViewModelTest {
    public final PlayerCountSceneViewModel testController = new PlayerCountSceneViewModel();
    public final Model testModel = new Model();
    public final SceneManager testView = new SceneManager();

    /**
     * @author Eric De Ron
     */
    @Test
    void setView() {
        assertTrue(testController.setView(testView));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setModel() {
        assertTrue(testController.setModel(testModel));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void setMainViewNullTest() {
        assertFalse(testController.setView(null));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void setMainModelNullTest() {
        assertFalse(testController.setModel(null));
    }
}