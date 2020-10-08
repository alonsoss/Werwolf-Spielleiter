package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.character.CharGreatWolf;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleGreatWolf;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.victim.VictimWerewolf;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the infection through the great wolf.
 * @author Henrik Möhlmann
 */
public class GreatWolfViewModel extends ViewModel {
    @FXML
    Label lblWerewolfVictim;
    @FXML
    Button buttonYes;
    @FXML
    Button buttonNo;

    @FXML
    public void initialize() {

    }

    /**
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        if (model.getVictims().getVictimWerewolf().isSet()) {
            lblWerewolfVictim.setText(String.format("Werwolf Opfer: %s", model.getVictims().getVictimWerewolf().getPlayer().getName()));

            //mark werewolf victim
            VictimWerewolf victimWerewolf = model.getVictims().getVictimWerewolf();
            List<PlayerLayoutViewModel> playerLayoutViewModelList = boardLayoutViewModel.getViewModelList();

            playerLayoutViewModelList.stream()
                    .filter(x -> x.getPlayer().equals(victimWerewolf.getPlayer()))
                    .forEach(x -> x.markWerewolfVictim());
            if (model.hasGreatWolfInfected()) {
                buttonYes.setDisable(true);
                buttonYes.setText(PrivateGameConstants.GREAT_WOLF_HAS_INFECTED);
            } else {
                buttonYes.setDisable(false);
            }
            buttonNo.setDisable(false);
        } else {
            lblWerewolfVictim.setText(PrivateGameConstants.GREAT_WOLF_NO_WEREWOLF_VICTIM);
            buttonYes.setDisable(true);
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void saveYes() {
        //set CharGreatWolf has infected
        Player greatWolf = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleGreatWolf()))
                .collect(Collectors.toList()).get(0);
        ((CharGreatWolf) greatWolf.getCharacter()).infect();
        //set model great wolf has infected
        model.greatWolfInfect();
        //infect the wereweolf victim
        model.getVictims().getVictimWerewolf().infect();
        //Put the werewolf victim in the werewolf fraction and add role werewolf
        model.getFraction().changeToWerewolf(model.getVictims().getVictimWerewolf().getPlayer());
        model.getVictims().getVictimWerewolf().getPlayer().addRole(new RoleWerewolf());
        
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void saveNo() {
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void next() {
        List<PlayerLayoutViewModel> playerLayoutViewModelList = boardLayoutViewModel.getViewModelList();
        Player werewolfVictim = model.getVictims().getVictimWerewolf().getPlayer();
        playerLayoutViewModelList.stream()
                .filter(x -> x.getPlayer().equals(werewolfVictim))
                .forEach(x -> x.unmark());

        boardLayoutViewModel.sleepAll();

        scene = GameScene.WHITE_WEREWOLF;
        nextScene();
    }
}
