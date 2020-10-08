package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.character.CharFox;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleFox;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.HashSet;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

/**
 * @author Matthias Hinrichs
 */
public class FoxViewModel extends ViewModel {
    @FXML
    Button btnConfirm;
    @FXML
    Button btnCheck;
    @FXML
    Label lblFoxAbility;
    @FXML
    Label lblCheckResult;
    private PlayerLayoutViewModel chosenPlayer;
    private CharFox charFox;

    /**
     * Method to prepare the Scene
     * @author Matthias Hinrichs
     */
    @Override
    public void getStart() {
        if (charFox == null) {
            charFox = (CharFox) model.getFirstPlayerWithRole(new RoleFox()).getCharacter();
        }
        if (charFox.canCheck()) {
            lblCheckResult.setText(FOX_CHECK_AVAILABLE);
            charFox.setCheckedThisRound(false);
        } else {
            lblCheckResult.setText(FOX_CHECK_NOT_AVAILABLE);
        }
        btnCheck.setDisable(true);
    }

    /**
     * Method to handle clicks on the player and setup for checking
     * @author Matthias Hinrichs
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        Player fox = model.getFirstPlayerWithRole(new RoleFox());
        if (charFox.canCheck() && !charFox.hasCheckedThisRound()) {
            if (playerLayoutViewModel.getClick()) {
                if (chosenPlayer != null) {
                    chosenPlayer.unhighlightPlayer();
                    chosenPlayer.resetClick();
                }
                chosenPlayer = playerLayoutViewModel;
                playerLayoutViewModel.highlightPlayer();
            } else {
                playerLayoutViewModel.unhighlightPlayer();
                playerLayoutViewModel.resetClick();
                chosenPlayer = null;
            }
        } else {
            playerLayoutViewModel.resetClick();
        }
        updateButtons();
    }

    /**
     * Method to enable/disable the Buttons as needed
     * @author Matthias Hinrichs
     */
    private void updateButtons() {
        if (chosenPlayer != null && charFox.canCheck() && !charFox.hasCheckedThisRound()) {
            btnCheck.setDisable(false);
        } else {
            btnCheck.setDisable(true);
        }
    }

    /**
     * Method to check the chosen Player and his two neighbors. It iterates over all living Players to find the player
     * chosen and puts this player and his two neighbors in a HashSet. If at least one of the Players in this HashSet is
     * a Werewolf the boolean werewolf is set to true. Depending on the status of this boolean the Label lblCheckResult
     * is filled with the appropriate text. If no Werewolf was found the fox loses his ability to check players.
     * @author Matthias Hinrichs
     */
    @FXML
    protected void checkChosenPlayer() {
        boolean werewolf = false;
        HashSet<Player> neighbors = new HashSet<>();

        for (int i = 0; i < model.getPlayerAliveList().size(); i++) {
            Player p = model.getPlayerAliveList().get(i);
            if (p == chosenPlayer.getPlayer()) {
                neighbors.add(p);
                if (i > 0) {
                    neighbors.add(model.getPlayerAliveList().get(i - 1));
                } else {
                    neighbors.add(model.getPlayerAliveList().get(model.getPlayerAliveList().size() - 1));
                }
                if (i + 1 < model.getPlayerAliveList().size()) {
                    neighbors.add(model.getPlayerAliveList().get(i + 1));
                } else {
                    neighbors.add(model.getPlayerAliveList().get(0));
                }
            }
        }

        for (Player p : neighbors) {
            if (p.hasRole(new RoleWerewolf())) {
                werewolf = true;
            }
        }

        if (werewolf) {
            lblCheckResult.setText(FOX_CHECK_WEREWOLF_FOUND);
        } else {
            lblCheckResult.setText(FOX_CHECK_NO_WEREWOLF_FOUND);
            ((CharFox) model.getFirstPlayerWithRole(new RoleFox()).getCharacter()).forbidChecks();
        }
        lblCheckResult.setVisible(true);
        btnCheck.setDisable(true);
        charFox.setCheckedThisRound(true);
    }

    /**
     * Unhighlight Player and reset Click, put all players to sleep and switch to next scene
     * @author Matthias Hinrichs
     */
    @FXML
    private void next() {
        if (chosenPlayer != null) {
            chosenPlayer.unhighlightPlayer();
            chosenPlayer.resetClick();
            chosenPlayer = null;
        }
        boardLayoutViewModel.sleepAll();
        scene = GameScene.WEREWOLF_VICTIM_CHOOSING;
        nextScene();
    }

    /**
     * Method to setup the ViewModel for Testing
     * @author Matthias Hinrichs
     */
    public void setTestGui() {
        btnCheck = new Button();
        btnConfirm = new Button();
        lblCheckResult = new Label();
        lblFoxAbility = new Label();
    }

    /**
     * Sets the chosenPlayer to a given PlayerLayoutViewModel for Testing
     * @param chosenPlayer the PlayerLayoutViewModel managing the chosen player
     * @author Matthias Hinrichs
     */
    public void testPlayer(PlayerLayoutViewModel chosenPlayer) {
        this.chosenPlayer = chosenPlayer;
        checkChosenPlayer();
    }
}