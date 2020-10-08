package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VotingViewModelTest {
    private final VotingViewModel votingController = new VotingViewModel();
    private final Model testModel = new Model();
    private final SceneManager testView = new SceneManager();

    /**
     * @author Florian Müller
     */
    @Test
    void setModel() {
        assertTrue(votingController.setModel(testModel));
        assertFalse(votingController.setModel(null));
    }
    /**
     * @author Florian Müller
     */
    @Test
    void setView() {
        assertTrue(votingController.setView(testView));
        assertFalse(votingController.setView(null));
    }

    @Test
    void getStart() {
    }

    @Test
    void updateDisplay() {
    }

    @Test
    void getStarted() {
    }
}