package de.werwolf_spielleiter.viewModel_board;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.character.CharVillager;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(JfxTestExtension.class)
public class PlayerLayoutViewModelTest {
    private final PlayerLayoutViewModel testController = new PlayerLayoutViewModel();
    private final Model testModel = new Model();
    private final SceneManager testView = new SceneManager();
    private final String defaulBgColor = "-fx-background-color: rgba(255, 255, 0, 1);";
    private final String awakeningBgColor = "-fx-background-color: rgba(255, 0, 0, 1);";

    private Player testPlayer;
    private java.lang.String testPlayerName = "Max";
    //private String testPlayerStatus = "alive";
    private CharVillager testPlayerCharacter = new CharVillager();


    @BeforeEach
    void createPlayer() {
        testPlayer = new Player(testPlayerName);
        testPlayer.setCharacter(testPlayerCharacter);
        testController.setView(testView);
        testController.setModel(testModel);
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testSetMainView() {
        assertTrue(testController.setView(testView));
        assertFalse(testController.setView(null));
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testSetMainModel() {
        assertTrue(testController.setModel(testModel));
        assertFalse(testController.setModel(null));
    }
}
