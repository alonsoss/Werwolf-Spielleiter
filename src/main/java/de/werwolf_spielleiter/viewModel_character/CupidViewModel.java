package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

/**
 * @author Henrik Möhlmann
 */
public class CupidViewModel extends ViewModel {
    private List<PlayerLayoutViewModel> playerLayoutList;

    @FXML
    Label lblLovers;
    @FXML
    Label lblDisplay;
    @FXML
    Button buttonSleep;

    /**
     * @author Henrik Möhlmann
     */
    @FXML
    public void initialize() {
        playerLayoutList = new LinkedList<>();
    }

    /**
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        updateDisplay();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        if (playerLayoutViewModel.getClick()) {
            playerLayoutViewModel.highlightPlayer();
            playerLayoutList.add(playerLayoutViewModel);
        } else {
            playerLayoutViewModel.unhighlightPlayer();
            playerLayoutList.remove(playerLayoutViewModel);
        }
        updateDisplay();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void updateDisplay() {
        String player1 = "";
        String player2 = "";
        if (playerLayoutList.size() == 2) {
            player1 = playerLayoutList.get(0).getPlayer().getName();
            player2 = playerLayoutList.get(1).getPlayer().getName();
            lblLovers.setText(String.format("Verliebte: \n1. %s\n2. %s", player1, player2));
            lblDisplay.setText(CUPID_MAX_SELECTED);
            buttonSleep.setDisable(false);
        } else if (playerLayoutList.size() == 1) {
            player1 = playerLayoutList.get(0).getPlayer().getName();
            lblLovers.setText(String.format("Verliebte: \n1. %s\n2. %s", player1, player2));
            lblDisplay.setText(CUPID_SELECT_1_PLAYER);
            buttonSleep.setDisable(true);
        } else if (playerLayoutList.size() == 0) {
            lblLovers.setText(String.format("Verliebte: \n1. %s\n2. %s", player1, player2));
            lblDisplay.setText(CUPID_SELECT_2_PLAYERS);
            buttonSleep.setDisable(true);
        } else {
            player1 = playerLayoutList.get(0).getPlayer().getName();
            player2 = playerLayoutList.get(1).getPlayer().getName();
            lblLovers.setText(String.format("Verliebte: \n1. %s\n2. %s", player1, player2));
            lblDisplay.setText(String.format("Noch %s Spieler abwählen!", playerLayoutList.size() - 2));
            buttonSleep.setDisable(true);
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void save() {
        playerLayoutList.get(0).unhighlightPlayer();
        playerLayoutList.get(1).unhighlightPlayer();
        playerLayoutList.get(0).resetClick();
        playerLayoutList.get(1).resetClick();
        boardLayoutViewModel.sleepAll();

        Player player1 = playerLayoutList.get(0).getPlayer();
        Player player2 = playerLayoutList.get(1).getPlayer();

        player1.addRole(new RoleLovers());
        player2.addRole(new RoleLovers());

        model.getFraction().changeToLover(player1, player2);
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void next() {
        scene = GameScene.LOVERS;
        nextScene();
    }
}
