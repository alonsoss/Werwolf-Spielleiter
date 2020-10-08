package de.werwolf_spielleiter.viewModel_board;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
public class BoardLayoutViewModelTest {
    private final BoardLayoutViewModel testViewModel = new BoardLayoutViewModel();
    private final Model testModel = new Model();
    private final SceneManager testView = new SceneManager();

    //private int playerCount = 3;

    /**
     * @author Matthias Hinrichs
     */
    @BeforeEach
    void init() {
        testViewModel.setView(testView);
        testViewModel.setModel(testModel);
        //testModel.initPlayerList(playerCount);
        /*for (int i = 0; i < playerCount; i++) {
            Player player = new Player("Spieler " + String.valueOf(i));
            player.setCharacter(new CharWerewolf());
            testModel.getPlayerList().add(player);
        }*/
    }

    void setUpModel(int playerCount) {
        testModel.initPlayerList(playerCount);
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player("Spieler " + java.lang.String.valueOf(i));
            player.setCharacter(new CharWerewolf());
            testModel.getPlayerList().add(player);
        }
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testSetMainView() {
        assertTrue(testViewModel.setView(testView));
        assertFalse(testViewModel.setView(null));
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testSetMainModel() {
        assertTrue(testViewModel.setModel(testModel));
        assertFalse(testViewModel.setModel(null));
    }

    /**
     * @author Matthias Hinrichs
     */
    @ParameterizedTest
    @ValueSource(ints = {0, 3, 14, 25, 47})
    void testCreatePlayerPanes(int playerCount) {
        setUpModel(playerCount);
        testViewModel.createPlayerPanes();
        assertEquals(playerCount, testViewModel.getPanesSize());
    }

    @Test
    void CardRevealTest() {
        assertFalse(testViewModel.isCardsRevealed());
    }

}
