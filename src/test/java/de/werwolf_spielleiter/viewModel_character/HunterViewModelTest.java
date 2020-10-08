package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.viewModel_character.HunterViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class HunterViewModelTest {
    private final HunterViewModel testHunterController = new HunterViewModel();
    private final Model testModel = new Model();
    private final SceneManager testView = new SceneManager();
    private final Player testPlayer = new Player("Test");
    private final PlayerLayoutViewModel testPlayerLayoutController = new PlayerLayoutViewModel();

    /**
     * Init controllers for testing
     * @author Eric De Ron
     */
    @BeforeEach
    void initTest() {
        // Init a test player layout
        testPlayerLayoutController.setModel(testModel);
        testPlayerLayoutController.setTestGui();
        testPlayerLayoutController.showPlayerDetails(null);

        testHunterController.getStart();
        testPlayer.setCharacter(new CharHunter());
    }

    /**
     * Test if a chosen player layout is added and
     * removed and re-adding
     * @author Eric De Ron
     */
    @Test
    void choosing() {
        // Should be same object if get and chosen list should be 1
        testHunterController.choosing(testPlayerLayoutController);
        assertEquals(testPlayerLayoutController, testHunterController.getChosenPlayerLayoutList().get(0));
        assertEquals(1, testHunterController.getChosenPlayerLayoutList().size());

        // Should be empty on adding the same chosen object
        testHunterController.choosing(testPlayerLayoutController);
        assertEquals(0, testHunterController.getChosenPlayerLayoutList().size());
    }

    /**
     * Test if correct amount of selected player
     * layouts is displayed
     * updateDisplay is called in choosing
     * @author Eric De Ron
     */
    @Test
    void updateDisplay() {
        // If 1 player layout selected
        testHunterController.choosing(testPlayerLayoutController);
        assertEquals("Ausgewählt:\n" + "test", testHunterController.getLblDisplayChosenAmount().getText());

        // If no player layout selected
        testHunterController.choosing(testPlayerLayoutController);
        assertEquals("Noch 1 Spieler\nauswählen", testHunterController.getLblDisplayChosenAmount().getText());

        // If to much player layouts selected
        testHunterController.choosing(testPlayerLayoutController);
        PlayerLayoutViewModel test2PlayerLayoutController = new PlayerLayoutViewModel();
        test2PlayerLayoutController.setTestGui();
        test2PlayerLayoutController.showPlayerDetails(null);
        testHunterController.choosing(test2PlayerLayoutController);
        assertEquals(String.format("Noch %d Spieler\nabwählen.", 1),
                testHunterController.getLblDisplayChosenAmount().getText());
    }

    /**
     * Test if default is false and
     * if it changes after setting it
     * @author Eric De Ron
     */
    @Test
    void isHunterPhase() {
        assertFalse(testHunterController.isHunterPhase());
        testHunterController.setHunterPhase(true);
        assertTrue(testHunterController.isHunterPhase());
    }

    /**
     * Test if setting a phase
     * is applied correctly
     * @author Eric De Ron
     */
    @Test
    void setHunterPhase() {
        testHunterController.setHunterPhase(false);
        assertFalse(testHunterController.isHunterPhase());

        testHunterController.setHunterPhase(true);
        assertTrue(testHunterController.isHunterPhase());
    }
}