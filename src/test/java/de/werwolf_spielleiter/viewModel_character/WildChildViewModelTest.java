package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleWildChild;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class WildChildViewModelTest {
    private final WildChildViewModel testWildChildViewModel = new WildChildViewModel();
    private final Model testModel = new Model();
    private static final String TEST_PLAYER_NAME = "test";
    private final Player testPlayer = new Player(TEST_PLAYER_NAME);
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

        testWildChildViewModel.setTestGui();
        Player testWildChild = new Player();
        testWildChild.addRole(new RoleWildChild());
        testModel.getPlayerAliveList().add(testWildChild);
        testWildChildViewModel.setModel(testModel);
        testWildChildViewModel.getStart();
        testPlayer.setCharacter(new CharHunter());
    }

    @Test
    void getStart() {
    }

    /**
     * Test if a chosen player layout is added and
     * removed and re-adding
     * @author Eric De Ron
     */
    @Test
    void handleClickTest() {
        // Should be same object if get and chosen list should be 1
        testWildChildViewModel.handleClick(testPlayerLayoutController);
        assertEquals(testPlayerLayoutController, testWildChildViewModel.getChosenIdol());
        assertNotNull(testWildChildViewModel.getChosenIdol());

        // Should be empty on adding the same chosen object
        testPlayerLayoutController.resetClick();
        testWildChildViewModel.handleClick(testPlayerLayoutController);
        assertNull(testWildChildViewModel.getChosenIdol());
    }

    /**
     * Test if correct amount of selected player
     * layouts is displayed
     * updateDisplay is called in choosing
     * @author Eric De Ron
     */
    @Test
    void updateDisplayTest() {
        // If 1 player layout selected
        testWildChildViewModel.handleClick(testPlayerLayoutController);
        assertEquals("Ausgewählt: " + TEST_PLAYER_NAME, testWildChildViewModel.getLblDisplayChosenPlayer().getText());

        // If no player layout selected
        testPlayerLayoutController.resetClick();
        testWildChildViewModel.handleClick(testPlayerLayoutController);
        assertEquals("Noch 1 Spieler auswählen", testWildChildViewModel.getLblDisplayChosenPlayer().getText());

        // If to much player layouts selected
        testWildChildViewModel.handleClick(testPlayerLayoutController);
        PlayerLayoutViewModel test2PlayerLayoutController = new PlayerLayoutViewModel();
        test2PlayerLayoutController.setTestGui();
        test2PlayerLayoutController.showPlayerDetails(null);
        testWildChildViewModel.handleClick(test2PlayerLayoutController);
        assertEquals("Ausgewählt: " + TEST_PLAYER_NAME,
                testWildChildViewModel.getLblDisplayChosenPlayer().getText());
    }
}