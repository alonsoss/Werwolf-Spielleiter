package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleBigBadWolf;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.victim.VictimWerewolf;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the choosing of the big bad wolf victim.
 * @author Henrik Möhlmann
 */
public class BigBadWolfViewModel extends ViewModel {
    private PlayerLayoutViewModel chosenVictim;
    @FXML
    Button buttonVictim;
    @FXML
    Button buttonNoVictim;

    /**
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        Player bigBadWolf = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleBigBadWolf()))
                .collect(Collectors.toList()).get(0);
        boolean bigBadWolfInLove = bigBadWolf.hasRole(new RoleLovers());

        if (bigBadWolfInLove) {
            List<PlayerLayoutViewModel> lovers =
                    view.getBoardLayoutViewModel().getViewModelList().stream()
                    .filter(x -> x.getPlayer().getStatus().equals(PrivateGameConstants.PLAYER_ALIVE) && x.getPlayer().hasRole(new RoleLovers()))
                    .collect(Collectors.toList());
            lovers.forEach(x -> x.markLovers());
        }

        //mark werewolf victim
        VictimWerewolf victimWerewolf = model.getVictims().getVictimWerewolf();
        List<PlayerLayoutViewModel> playerLayoutViewModelList = boardLayoutViewModel.getViewModelList();

        playerLayoutViewModelList.stream()
                .filter(x -> x.getPlayer().equals(victimWerewolf.getPlayer()))
                .forEach(x -> x.markWerewolfVictim());

        updateDisplay();
    }

    /**
     * @author Henrik Möhlmann
     * @param playerLayoutViewModel
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        Player bigBadWolf = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleBigBadWolf()))
                .collect(Collectors.toList()).get(0);
        boolean bigBadWolfInLove = bigBadWolf.hasRole(new RoleLovers());

        //prevent clicking on werewolf
        if (playerLayoutViewModel.getPlayer().hasRole(new RoleWerewolf())) {
            playerLayoutViewModel.resetClick();
            return;
        }
        //prevent clicking on werewolf victim
        if (model.getVictims().getVictimWerewolf().isSet()
                && playerLayoutViewModel.getPlayer() == model.getVictims().getVictimWerewolf().getPlayer()) {
            playerLayoutViewModel.resetClick();
            return;
        }
        //prevent clicking on loved one if big bad wolf is lover
        if (bigBadWolfInLove && playerLayoutViewModel.getPlayer().hasRole(new RoleLovers())) {
            playerLayoutViewModel.resetClick();
            return;
        }

        //handle click on villager
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
        model.getVictims().setVictimBigBadWolf(chosenVictim.getPlayer());
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
        //unmark werewolf victim
        if (model.getVictims().getVictimWerewolf().isSet()) {
            List<PlayerLayoutViewModel> playerLayoutViewModelList = boardLayoutViewModel.getViewModelList();
            Player werewolfVictim = model.getVictims().getVictimWerewolf().getPlayer();
            playerLayoutViewModelList.stream()
                    .filter(x -> x.getPlayer().equals(werewolfVictim))
                    .forEach(x -> x.unmark());
        }

        scene = GameScene.WITCH;
        nextScene();
    }
}
