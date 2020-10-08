package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.character.CharWitch;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleWitch;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.victim.VictimWerewolf;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.List;
import java.util.stream.Collectors;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

/**
 * This class handles the objects and actions during witch phase.
 * @author Henrik Möhlmann
 */
public class WitchViewModel extends ViewModel {

    @FXML
    Label lblWerewolfVictim;
    @FXML
    RadioButton radioHeal;
    @FXML
    RadioButton radioKill;
    @FXML
    Label lblWitchVictim;
    @FXML
    Label lblStory;
    @FXML
    Button buttonSleep;
    @FXML
    Label lblInfo;

    /**
     * Set the text to the story label.
     * @author Henrik Möhlmann
     */
    @FXML
    public void initialize() {
        Player witch = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWitch()))
                .collect(Collectors.toList()).get(0);
        if (((CharWitch) witch.getCharacter()).canKill()) {
            lblStory.setText("Ich zeige ihr das Opfer dieser Nacht ...\n" +
                    "... möchte sie das Opfer retten? \n" +
                    "... möchte sie noch jemanden umbringen? ");
            lblInfo.setText("Zum Töten: Spieler markieren und \"töten\" auswählen");
            lblInfo.getStyleClass().add(WITCH_INFO_STYLE_LBL);
        } else {
            lblStory.setText("Ich zeige ihr das Opfer dieser Nacht ...\n" +
                    "... möchte sie das Opfer retten? \n" +
                    "... möchte sie noch jemanden umbringen? ");
            lblInfo.disableProperty();
        }
    }

    /**
     * @author Henrik Möhlmann
     * @param playerLayoutController plController which was clicked
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutController) {
        Player witch = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWitch()))
                .collect(Collectors.toList()).get(0);
        boolean witchInLove = witch.hasRole(new RoleLovers());

        //no action if witch can't kill, or the clicked is the loved one of the witch
        if (!((CharWitch) witch.getCharacter()).canKill()
                || (witchInLove && playerLayoutController.getPlayer().hasRole(new RoleLovers()))) {
            playerLayoutController.resetClick();
            return;
        }

        //prevent the witch from killing the werewolf victim
        if (model.getVictims().getVictimWerewolf().isSet()
                && playerLayoutController.getPlayer() == model.getVictims().getVictimWerewolf().getPlayer()) {
            playerLayoutController.resetClick();
            return;
        }

        if (playerLayoutController.getPlayer().hasRole(new RoleWitch())) {
            playerLayoutController.resetClick();
            lblWitchVictim.setText(WITCH_NO_VICTIM_SELECTED);
            radioKill.setSelected(false);
            radioKill.setDisable(true);
        } else if (witchInLove && playerLayoutController.getPlayer().hasRole(new RoleLovers())) {
            //prevent the witch from killing her loved one
            playerLayoutController.resetClick();
            lblWitchVictim.setText(WITCH_NO_VICTIM_SELECTED);
            radioKill.setSelected(false);
            radioKill.setDisable(true);
        } else if (playerLayoutController.getClick()) {
            playerLayoutController.highlightPlayer();
            lblWitchVictim.setText(String.format("%s ist dein Opfer. ", playerLayoutController.getPlayer().getName()));
            radioKill.setDisable(false);
        } else if (!playerLayoutController.getClick()) {
            playerLayoutController.unhighlightPlayer();
            lblWitchVictim.setText(WITCH_NO_VICTIM_SELECTED);
            radioKill.setSelected(false);
            radioKill.setDisable(true);
        }
        for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
            if (!(c == playerLayoutController)) {
                c.unhighlightPlayer();
                c.resetClick();
            }
        }
    }

    /**
     * Save the selection that was made during witch phase.
     * Unmark the werewolf victim and let witch sleep.
     * @author Henrik Möhlmann
     */
    public void save() {
        Player witch = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWitch()))
                .collect(Collectors.toList()).get(0);
        //save heal
        if (radioHeal.isSelected()) {
            model.getVictims().getVictimWerewolf().heal();
            ((CharWitch) witch.getCharacter()).heal();
        }
        //save kill
        if (radioKill.isSelected()) {
            Player witchVictim = boardLayoutViewModel.getViewModelList().stream()
                    .filter(x -> x.getClick())
                    .collect(Collectors.toList())
                    .get(0).getPlayer();
            model.getVictims().setVictimWitch(witchVictim);
            ((CharWitch) witch.getCharacter()).kill();
        }
        boardLayoutViewModel.getViewModelList().forEach(x -> {x.unhighlightPlayer(); x.resetClick();});

        //sleep and unmark werewolf victim
        boardLayoutViewModel.sleepAll();
        boardLayoutViewModel.getViewModelList().forEach(x -> x.unmarkLovers());
        if (model.getVictims().getVictimWerewolf().isSet()) {
            List<PlayerLayoutViewModel> playerLayoutViewModelList = boardLayoutViewModel.getViewModelList();
            Player werewolfVictim = model.getVictims().getVictimWerewolf().getPlayer();
            playerLayoutViewModelList.stream()
                    .filter(x -> x.getPlayer().equals(werewolfVictim))
                    .forEach(x -> x.unmark());
        }
        next();
    }

    /**
     * Before showing, this sets up the scene with the data.
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        lblWitchVictim.setText("");
        radioKill.setDisable(true);
        radioHeal.setDisable(true);
        Player witch = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWitch()))
                .collect(Collectors.toList()).get(0);
        boolean witchInLove = witch.hasRole(new RoleLovers());
        if (witchInLove) {
            List<PlayerLayoutViewModel> lovers =
                    view.getBoardLayoutViewModel().getViewModelList().stream()
                    .filter(x -> x.getPlayer().getStatus().equals(PLAYER_ALIVE) && x.getPlayer().hasRole(new RoleLovers()))
                    .collect(Collectors.toList());
            lovers.forEach(x -> x.markLovers());
        }

        if (model.getVictims().getVictimWerewolf().isSet()) {
            if (((CharWitch) witch.getCharacter()).canHeal()) {
                radioHeal.setDisable(false);
            } else {
                radioHeal.setText(WITCH_CANT_HEAL);
            }
            //mark werewolf victim
            VictimWerewolf victimWerewolf = model.getVictims().getVictimWerewolf();
            List<PlayerLayoutViewModel> playerLayoutViewModelList = boardLayoutViewModel.getViewModelList();

            playerLayoutViewModelList.stream()
                    .filter(x -> x.getPlayer().equals(victimWerewolf.getPlayer()))
                    .forEach(x -> x.markWerewolfVictim());

            if (victimWerewolf.getPlayer().hasRole(new RoleWitch())) {
                lblWerewolfVictim.setText(WITCH_IS_WEREWOLF_VICTIM);
            } else if (witchInLove && model.getVictims().getVictimWerewolf().getPlayer().hasRole(new RoleLovers())) {
                lblWerewolfVictim.setText(WITCH_LOVER_IS_VICTIM);
            } else {
                lblWerewolfVictim.setText(String.format("%s ist Werwolf Opfer", victimWerewolf.getPlayer().getName()));
            }
        } else {
            lblWerewolfVictim.setText(WITCH_NO_WEREWOLF_VICTIM);
            radioHeal.setDisable(true);
        }

        if (!((CharWitch) witch.getCharacter()).canKill()) {
            radioKill.setText(WITCH_CANT_KILL);
        }
    }

    /**
     * Go to next scene.
     * @author Henrik Möhlmann
     */
    public void next() {
        scene = GameScene.VICTIM_NIGHT;
        nextScene();
    }
}
