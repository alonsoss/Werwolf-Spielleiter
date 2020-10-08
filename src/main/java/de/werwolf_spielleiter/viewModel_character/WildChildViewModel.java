package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleWildChild;
import de.werwolf_spielleiter.role.RoleWitch;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.PlayerVictimViewModel;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class WildChildViewModel extends ViewModel {
    @FXML
    private Label lblWildChildTitle;
    @FXML
    private Label lblDisplayChosenPlayer;
    @FXML
    private Button buttonConfirmIdol;

    // Player layout controller which is chosen
    private PlayerLayoutViewModel chosenIdol;

    List<PlayerLayoutViewModel> lovers;

    /**
     * @author Eric De Ron
     */
    @FXML
    public void initialize() {
        lblWildChildTitle.getStyleClass().add(WILD_CHILD_STYLE_LBL_TITLE);
        lblDisplayChosenPlayer.getStyleClass().add(WILD_CHILD_STYLE_LBL_TEXT);
    }

    @Override
    public void getStart() {
        Player wildChild = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleWildChild()))
                .collect(Collectors.toList()).get(0);
        if (wildChild.hasRole(new RoleLovers())) {
             lovers = view.getBoardLayoutViewModel().getViewModelList().stream()
                     .filter(x -> x.getPlayer().getStatus().equals(PLAYER_ALIVE) && x.getPlayer().hasRole(new RoleLovers()))
                     .collect(Collectors.toList());
             lovers.forEach(PlayerLayoutViewModel::markLovers);
        }
        updateDisplay();
    }

    /**
     * A chosen player will be added to the chosenPlayerLayoutList
     * or removed if chosen again
     * @author Eric De Ron
     * @param playerLayoutViewModel Player layout controller of a chosen
     *                               player
     */
    public void handleClick(PlayerLayoutViewModel playerLayoutViewModel) {
        // Don't allow wild child to select itself
        if (playerLayoutViewModel.getPlayer().hasRole(new RoleWildChild())) {
            playerLayoutViewModel.resetClick();
            return;
        }
        // Don't allow wild child to choose lover
        if (lovers != null && lovers.contains(playerLayoutViewModel)) {
            playerLayoutViewModel.resetClick();
            return;
        }
        if (playerLayoutViewModel.getClick()) {
            // Reset the old chosen one
            if (chosenIdol != null) {
                chosenIdol.unhighlightPlayer();
                chosenIdol.resetClick();
            }
            // Highlight new chosen player
            playerLayoutViewModel.highlightPlayer();
            chosenIdol = playerLayoutViewModel;
        } else {
            playerLayoutViewModel.unhighlightPlayer();
            chosenIdol = null;
        }
        updateDisplay();
    }

    /**
     * Update lblDisplayChosenAmount accordingly
     * the amount of chosen player(s)
     * @author Eric De Ron
     */
    public void updateDisplay() {
        lblDisplayChosenPlayer.setText("");
        lblDisplayChosenPlayer.getStyleClass().clear();
        if (chosenIdol == null) {
            buttonConfirmIdol.setDisable(true);
            lblDisplayChosenPlayer.setText(WILD_CHILD_SELECT_1_PLAYER);
            lblDisplayChosenPlayer.getStyleClass().add(WILD_CHILD_STYLE_LBL_INFO);
        } else {
            buttonConfirmIdol.setDisable(false);
            lblDisplayChosenPlayer.setText(WILD_CHILD_SELECT + chosenIdol.getPlayer());
            lblDisplayChosenPlayer.getStyleClass().add(WILD_CHILD_STYLE_LBL_TEXT);
        }
    }

    @FXML
    public void confirmIdol() {
        // Reset clicked player layout
        chosenIdol.unhighlightPlayer();
        chosenIdol.resetClick();

        // Let every possible awake player sleep
        boardLayoutViewModel.sleepAll();

        // Unmark if wild child is lover marked lovers
        boardLayoutViewModel.getViewModelList().forEach(PlayerLayoutViewModel::unmarkLovers);

        // Set by wild child chosen player as idol
        model.setIdolWildChild(chosenIdol.getPlayer());
        scene = GameScene.FOX;
        nextScene();
    }

    /**
     * For testing logic of this model view
     * setup all variables
     * @author Eric De Ron
     */
    public void setTestGui() {
        lblDisplayChosenPlayer = new Label();
        buttonConfirmIdol = new Button();
    }

    /**
     * For testing get chosenIdol
     * @author Eric De Ron
     * @return A chosen player layouts
     */
    public PlayerLayoutViewModel getChosenIdol() {
        return chosenIdol;
    }

    /**
     * For testing get lblDisplayChosenPlayer
     * @author Eric De Ron
     * @return Label to display name of chosen player
     */
    public Label getLblDisplayChosenPlayer() {
        return lblDisplayChosenPlayer;
    }
}
