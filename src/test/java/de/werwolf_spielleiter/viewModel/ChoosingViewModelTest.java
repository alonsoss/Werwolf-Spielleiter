package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChoosingViewModelTest {
    private final ChoosingViewModel choosingController = new ChoosingViewModel();
    private final Model testModel = new Model();
    private final SceneManager testView = new SceneManager();
    private final BoardLayoutViewModel controller = new BoardLayoutViewModel();

    /**
     * @author Florian Müller
     */
    @Test
    void setView() {
        assertTrue(choosingController.setView(testView));
        assertFalse(choosingController.setView(null));
    }

    /**
     * @author Florian Müller
     */

    @Test
    void setModel() {
        assertTrue(choosingController.setModel(testModel));
        assertFalse(choosingController.setModel(null));
    }

    /**
     * @author Florian Müller
     */
    @Test
    void setBoardController() {
        assertFalse(choosingController.setBoardLayoutViewModel(null));
        assertTrue(choosingController.setBoardLayoutViewModel(controller));
    }

    @Test
    void choosing() {
    }

    @Test
    void getLynchVictims() {
    }
}