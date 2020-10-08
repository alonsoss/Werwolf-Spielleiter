package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.MainWrapper;
import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.character.CharFox;
import de.werwolf_spielleiter.character.CharVillager;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleFox;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Matthias Hinrichs
 */
@ExtendWith(JfxTestExtension.class)
public class FoxViewModelTest {
    private FoxViewModel testFoxViewModel = new FoxViewModel();
    private Model testModel = new Model();
    private List<Player> testPlayerList;
    private List<PlayerLayoutViewModel> testPlayerLayoutViewModelList;
    private String initialLblCheckResult = "Einen Spieler auswählen um ihn und seine Nachbarn zu prüfen.";
    private String lblCheckResultWerewolf = "Einer der Spieler ist ein Werwolf!";
    private String lblCheckResultNoWerewolf = "Keiner der Spieler ist ein Werwolf!";
    private String lblCheckNotAvailable = "Es können keine Spieler mehr überprüft werden!";

    /**
     * @author Matthias Hinrichs
     */
    @BeforeEach
    void init() {
        testFoxViewModel.setModel(testModel);
        testFoxViewModel.setTestGui();
        testPlayerList = new ArrayList<>();
        testPlayerLayoutViewModelList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            Player p = new Player("Spieler " + i);
            if (i == 1) {
                p.setCharacter(new CharFox());
                p.addRole(new RoleFox());
            } else {
                p.setCharacter(new CharVillager());
            }
            testPlayerList.add(p);
        }
        for (Player p : testPlayerList) {
            testModel.getPlayerAliveList().add(p);
            PlayerLayoutViewModel playerLayoutViewModel = new PlayerLayoutViewModel();
            playerLayoutViewModel.setTestGui(p, false);

            testPlayerLayoutViewModelList.add(playerLayoutViewModel);
        }
        testFoxViewModel.getStart();
    }

    /**
     * @author Matthias Hinrichs
     * @author Henrik Möhlmann
     */
    @Test
    void checkPlayerOneNoWerewolfTest() {
        //test the label text
        //test if the fox looses his ability to check, if no werewolf is between the players to check
        //test if fox can check himself
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(0);
        playerLayoutViewModel.testGuiSetClick(true);
        assertEquals(initialLblCheckResult, testFoxViewModel.lblCheckResult.getText());
        assertTrue(((CharFox) testPlayerLayoutViewModelList.get(0).getPlayer().getCharacter()).canCheck());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertFalse(((CharFox) testPlayerLayoutViewModelList.get(0).getPlayer().getCharacter()).canCheck());
        assertEquals(lblCheckResultNoWerewolf, testFoxViewModel.lblCheckResult.getText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void foxCannontCheckAnymore() {
        //check if the right text is displayed if fox cannot check anymore.
        //check if the "check button" is disabled if fox cannot check anymore.
        //check if "next button" is always enabled

        //check a triplet without werewolf
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(0);
        playerLayoutViewModel.testGuiSetClick(true);
        testFoxViewModel.handleClick(playerLayoutViewModel);
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertFalse(((CharFox) testPlayerLayoutViewModelList.get(0).getPlayer().getCharacter()).canCheck());

        //next night
        testFoxViewModel.getStart();
        assertEquals(lblCheckNotAvailable, testFoxViewModel.lblCheckResult.getText());
        assertTrue(testFoxViewModel.btnCheck.isDisabled());
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void checkButtonTest() {
        //check if "check button" is only enabled if one player is chosen
        //check if "next button" is always enabled

        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(0);
        assertTrue(testFoxViewModel.btnCheck.isDisabled());
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());

        //select player
        playerLayoutViewModel.testGuiSetClick(true);
        testFoxViewModel.handleClick(playerLayoutViewModel);
        assertFalse(testFoxViewModel.btnCheck.isDisabled());
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());

        //deselect player
        playerLayoutViewModel.testGuiSetClick(false);
        testFoxViewModel.handleClick(playerLayoutViewModel);
        assertTrue(testFoxViewModel.btnCheck.isDisabled());
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void checkPlayerOneLastWerewolfTest() {
        //check if label displays that one of the three players is a werewolf
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(0);
        playerLayoutViewModel.testGuiSetClick(true);
        assertEquals(initialLblCheckResult, testFoxViewModel.lblCheckResult.getText());
        testPlayerList.get(testPlayerList.size() - 1).addRole(new RoleWerewolf());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertEquals(lblCheckResultWerewolf, testFoxViewModel.lblCheckResult.getText());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void checkPlayerThreePlayerFourWerewolfTest() {
        //check if label displays that one of the three players is a werewolf
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(2);
        testPlayerList.get(3).addRole(new RoleWerewolf());
        playerLayoutViewModel.testGuiSetClick(true);
        assertEquals(initialLblCheckResult, testFoxViewModel.lblCheckResult.getText());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertEquals(lblCheckResultWerewolf, testFoxViewModel.lblCheckResult.getText());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void checkLastPlayerNoWerewolfTest() {
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(testPlayerList.size() - 1);
        playerLayoutViewModel.testGuiSetClick(true);
        assertEquals(initialLblCheckResult, testFoxViewModel.lblCheckResult.getText());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertEquals(lblCheckResultNoWerewolf, testFoxViewModel.lblCheckResult.getText());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void checkLastPlayerPlayerOneWerewolfTest() {
        //check if label displays that one of the three players is a werewolf
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(testPlayerList.size() - 1);
        testPlayerList.get(0).addRole(new RoleWerewolf());
        playerLayoutViewModel.testGuiSetClick(true);
        assertEquals(initialLblCheckResult, testFoxViewModel.lblCheckResult.getText());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertEquals(lblCheckResultWerewolf, testFoxViewModel.lblCheckResult.getText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeSelectedPlayer() {
        //check if changing the chosen player works
        PlayerLayoutViewModel player1 = testPlayerLayoutViewModelList.get(0);
        PlayerLayoutViewModel player2 = testPlayerLayoutViewModelList.get(1);
        assertFalse(player1.getClick());
        assertFalse(player2.getClick());
        player1.testGuiSetClick(true);
        testFoxViewModel.handleClick(player1);
        assertTrue(player1.getClick());
        assertFalse(player2.getClick());
        player2.testGuiSetClick(true);
        testFoxViewModel.handleClick(player2);
        assertFalse(player1.getClick());
        assertTrue(player2.getClick());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void resetClickIfCannotCheckTest() {
        //check if after loosing check ability the clicks are reset
        //check a triplet without werewolf
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(0);
        playerLayoutViewModel.testGuiSetClick(true);
        testFoxViewModel.handleClick(playerLayoutViewModel);
        assertFalse(testFoxViewModel.btnConfirm.isDisabled());
        testFoxViewModel.testPlayer(playerLayoutViewModel);
        assertFalse(((CharFox) testPlayerLayoutViewModelList.get(0).getPlayer().getCharacter()).canCheck());
        //reset cklick on player, because next() can't be tested
        playerLayoutViewModel.resetClick();

        //next night
        testFoxViewModel.getStart();
        assertFalse(playerLayoutViewModel.getClick());
        playerLayoutViewModel.testGuiSetClick(true);
        testFoxViewModel.handleClick(playerLayoutViewModel);
        assertFalse(playerLayoutViewModel.getClick());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void foxIsLover() {
        //fox is lover don't affect his check ability
        testPlayerLayoutViewModelList.get(0).getPlayer().addRole(new RoleLovers());
        testPlayerLayoutViewModelList.get(1).getPlayer().addRole(new RoleLovers());
        PlayerLayoutViewModel playerLayoutViewModel = testPlayerLayoutViewModelList.get(0);
        playerLayoutViewModel.testGuiSetClick(true);
        testFoxViewModel.handleClick(playerLayoutViewModel);
        testFoxViewModel.checkChosenPlayer();
        assertEquals(lblCheckResultNoWerewolf, testFoxViewModel.lblCheckResult.getText());
    }
}
