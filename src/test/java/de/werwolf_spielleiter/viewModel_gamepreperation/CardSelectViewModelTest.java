package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
class CardSelectViewModelTest {
    private final CardSelectViewModel testController = new CardSelectViewModel();
    private final Model testModel = new Model();
    private final SceneManager testView = new SceneManager();

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setModel() {
        assertTrue(testController.setModel(testModel));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setView() {
        assertTrue(testController.setView(testView));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void setViewNullTest() {
        assertFalse(testController.setView(null));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void setModelNullTest() {
        assertFalse(testController.setModel(null));
    }
}