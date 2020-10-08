package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.character.CharVillager;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.role.Role;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.victim.VictimLynch;
import de.werwolf_spielleiter.victim.VictimWerewolf;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class PlayerVictimViewModelTest {
    public final PlayerVictimViewModel testController = new PlayerVictimViewModel();
    public final Model testModel = new Model();
    public final SceneManager testView = new SceneManager();
    private final BoardLayoutViewModel boardLayoutController = new BoardLayoutViewModel();
    private final PlayerLayoutViewModel playerLayoutController = new PlayerLayoutViewModel();

    /**
     * @author Eric De Ron
     * Test the victims grid if a test players
     * info is placed right and has the right
     * context
     */
    @ParameterizedTest
    @CsvSource({"Test, NIGHT", "'', NIGHT", "null, NIGHT",
            "Test, DAY", "'', DAY", "null, DAY"})
    void getStart(String testName, PlayerVictimViewModel.Phase testPhase) {
        Player testPlayer = new Player(testName);
        testPlayer.setCharacter(new CharVillager());
        playerLayoutController.setTestGui();
        List<Role> testRoleList = new ArrayList<>();
        Role testRole = new RoleWerewolf();
        Role test2Roles = new RoleWerewolf();
        testRoleList.add(testRole);
        testRoleList.add(test2Roles);
        testPlayer.setRolesList(testRoleList);

        boardLayoutController.setModel(testModel);
        boardLayoutController.createPlayerPanes();

        testView.testSceneManager();

        testController.setView(testView);
        testController.setModel(testModel);
        testController.setPhase(testPhase);
        testController.setTestPlayer(testPlayer);
        testController.setBoardLayoutViewModel(boardLayoutController);
        testController.getStart();

        if(testPhase.equals(PlayerVictimViewModel.Phase.NIGHT)) {
            assertEquals(testController.getLblVarSceneTitle().getText(), "Opfer der Nacht:");
        } else {
            assertEquals(testController.getLblVarSceneTitle().getText(), "Opfer des Lynches:");
        }

        // Track if every element on the grid was tested
        int testedElements = 0;

        // Test if all elements are placed in the grid and if they
        // contain the right text
        GridPane testPane = testController.getGridPlayerVictim();
        for (Node n : testPane.getChildren()) {
            if (GridPane.getRowIndex(n) == 0 && GridPane.getColumnIndex(n) == 0) {
                assertEquals(((ImageView)n).getImage(), testPlayer.getCharacterCardFront());
                testedElements++;
            } else if (GridPane.getRowIndex(n) == 1 && GridPane.getColumnIndex(n) == 0) {
                assertEquals(((Label)n).getText(), "Spielername:\n" + testPlayer.getName());
                testedElements++;
            } else if (GridPane.getRowIndex(n) == 2 && GridPane.getColumnIndex(n) == 0) {
                // Victim class is to be set in the tested class
                String testDeathReason = testPhase.equals(PlayerVictimViewModel.Phase.NIGHT) ?
                        "Todesursache:\n" + new VictimWerewolf(testPlayer).getDeathReason() :
                        "Todesursache:\n" + new VictimLynch(testPlayer).getDeathReason();
                assertEquals(((Label)n).getText(), testDeathReason);
                testedElements++;
            } else if (GridPane.getRowIndex(n) == 3 && GridPane.getColumnIndex(n) == 0) {
                StringBuilder testRolesStringBuilder = new StringBuilder();
                for (Role playerRole : testPlayer.getRolesList()) {
                    testRolesStringBuilder.append(playerRole).append(", ");
                }
                if (!testRolesStringBuilder.toString().equals("")) {
                    testRolesStringBuilder.replace(testRolesStringBuilder.length() - 2, testRolesStringBuilder.length(), "");
                }
                assertEquals(((Label)n).getText(), testPlayer.getRolesList().size() == 0 ?
                        "Rollen: Keine" : "Rollen: " + testRolesStringBuilder.toString());
                testedElements++;
            }
        }
        // Currently 4 elements should be placed on the grid
        // and tested in the loop
        assertEquals(testedElements, 4);
    }

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

    @ParameterizedTest
    @EnumSource(
            value = PlayerVictimViewModel.Phase.class,
            names = {"NIGHT", "DAY"})
    void setPhase(PlayerVictimViewModel.Phase testPhase) {
        assertTrue(testController.setPhase(testPhase));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setViewNullTest() {
        assertFalse(testController.setView(null));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setModelNullTest() {
        assertFalse(testController.setModel(null));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setPhaseNullTest() {
        assertFalse(testController.setPhase(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setBoardController() {
        assertFalse(testController.setBoardLayoutViewModel(null));
        assertTrue(testController.setBoardLayoutViewModel(boardLayoutController));
    }
}