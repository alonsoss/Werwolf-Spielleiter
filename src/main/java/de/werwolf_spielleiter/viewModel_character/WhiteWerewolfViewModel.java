package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.role.RoleWhiteWerewolf;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;
import java.util.stream.Collectors;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.PLAYER_ALIVE;

/**
 * This class handles the choosing of the white werewolf victim.
 * @author Henrik Möhlmann
 */
public class WhiteWerewolfViewModel extends ViewModel {
    private PlayerLayoutViewModel chosenVictim;
    @FXML
    Button buttonVictim;
    @FXML
    Button buttonNoVictim;

    @FXML
    public void initialize() {

    }

    /**
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        Player whiteWerewolf = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWhiteWerewolf()))
                .collect(Collectors.toList()).get(0);
        boolean whiteWerewolfInLove = whiteWerewolf.hasRole(new RoleLovers());

        if (whiteWerewolfInLove) {
            List<PlayerLayoutViewModel> lovers =
                    view.getBoardLayoutViewModel().getViewModelList().stream()
                            .filter(x -> x.getPlayer().getStatus().equals(PLAYER_ALIVE) && x.getPlayer().hasRole(new RoleLovers()))
                            .collect(Collectors.toList());
            lovers.forEach(x -> x.markLovers());
        }
        updateDisplay();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        Player whiteWerewolf = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWhiteWerewolf()))
                .collect(Collectors.toList()).get(0);
        boolean whiteWerewolfInLove = whiteWerewolf.hasRole(new RoleLovers());

        //prevent clicking white werewolf
        if (playerLayoutViewModel.getPlayer().hasRole(new RoleWhiteWerewolf())) {
            playerLayoutViewModel.resetClick();
            return;
        }
        //prevent clicking villagers
        if (!playerLayoutViewModel.getPlayer().hasRole(new RoleWerewolf())) {
            playerLayoutViewModel.resetClick();
            return;
        }
        //prevent clicking on loved one if white werewolf is lover
        if (whiteWerewolfInLove && playerLayoutViewModel.getPlayer().hasRole(new RoleLovers())) {
            playerLayoutViewModel.resetClick();
            return;
        }
        //handle click on werewolf
        if (playerLayoutViewModel.getClick()) {
            //reset the old chosen one
            if (chosenVictim != null) {
                chosenVictim.unhighlightPlayer();
                chosenVictim.resetClick();
            }
            //get the new chosen one
            playerLayoutViewModel.highlightPlayer();
            chosenVictim = playerLayoutViewModel;
        } else {
            playerLayoutViewModel.unhighlightPlayer();
            chosenVictim = null;
        }
        updateDisplay();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void updateDisplay() {
        if (chosenVictim == null) {
            buttonVictim.setDisable(true);
        } else {
            buttonVictim.setDisable(false);
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void saveVictim() {
        model.getVictims().setVictimWhiteWerewolf(chosenVictim.getPlayer());
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void saveNoVictim() {
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void next() {
        if (chosenVictim != null) {
            chosenVictim.unhighlightPlayer();
            chosenVictim.resetClick();
        }
        boardLayoutViewModel.sleepAll();
        boardLayoutViewModel.getViewModelList().forEach(x -> x.unmarkLovers());

        scene = GameScene.BIG_BAD_WOLF;
        nextScene();
    }
}
