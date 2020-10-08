package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleSeer;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.stream.Collectors;


public class SeerViewModel extends ViewModel {
    @FXML
    Button buttonSleep;

    private boolean noTarget = true;
    private Player target = null;

    @Override
    public void getStart() {
        buttonSleep.setDisable(true);
    }

    /**
     * @author Janik Dohrmann
     * @param playerLayoutViewModel the ViewModel of the Player that was clicked
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        if (noTarget) {
            target = playerLayoutViewModel.getPlayer();
            Player seer = model.getPlayerAliveList().stream().filter(x -> x.hasRole(new RoleSeer())).collect(Collectors.toList()).get(0);
            if (!target.equals(seer)) {
                target.revealCharacterCard(true);
                noTarget = false;
                buttonSleep.setDisable(false);
            }
        }
        playerLayoutViewModel.resetClick();
    }

    /**
     * This method sleeps the seer.
     * @author Janik Dohrmann
     */
    public void seerSleep() {
        boardLayoutViewModel.sleepAll();
        if (!boardLayoutViewModel.isCardsRevealed() && target != null) {
            target.revealCharacterCard(false);
        }
        if (boardLayoutViewModel.isJobsRevealed()) {
            target.showHideJobs(true, boardLayoutViewModel.isCardsRevealed());
        }
        scene = GameScene.WILD_CHILD;
        noTarget = true;
        nextScene();
    }
}
