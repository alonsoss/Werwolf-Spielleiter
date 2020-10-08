package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.viewModel.PlayerVictimViewModel;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;
import java.util.List;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class HunterViewModel extends ViewModel {
    // Victim controller for displaying all victims
    private PlayerVictimViewModel playerVictimViewModel;

    // Pane to show hunter and his victim
    private GridPane gridHunterVictim;

    // Label to show amount of chosen players
    private Label lblDisplayChosenAmount;

    // Button to confirm hunter victim
    private Button buttonConfirmVictim;

    /* Variables */
    // If hunter is allowed to kill
    private boolean isHunterPhase = false;
    // List of player layout controllers which are chosen
    List<PlayerLayoutViewModel> chosenPlayerLayoutList = new LinkedList<>();

    /**
     * A chosen player will be added to the chosenPlayerLayoutList
     * or removed if chosen again
     * @author Eric De Ron
     * @param playerLayoutController Player layout controller of a chosen
     *                               player
     */
    public void choosing(PlayerLayoutViewModel playerLayoutController) {
        if (chosenPlayerLayoutList.contains(playerLayoutController)) {
            chosenPlayerLayoutList.remove(playerLayoutController);
            playerLayoutController.unhighlightPlayer();
        } else {
            chosenPlayerLayoutList.add(playerLayoutController);
            playerLayoutController.highlightPlayer();
        }
        updateDisplay();
    }

    /**
     * Update lblDisplayChosenAmount accordingly
     * the amount of chosen player(s)
     * @author Eric De Ron
     */
    public void updateDisplay() {
        if (chosenPlayerLayoutList.size() == 1) {
            buttonConfirmVictim.setDisable(false);
            lblDisplayChosenAmount.setText("Ausgewählt:\n" + chosenPlayerLayoutList.get(0).getPlayer());
        } else if (chosenPlayerLayoutList.size() == 0) {
            buttonConfirmVictim.setDisable(true);
            lblDisplayChosenAmount.setText(HUNTER_SELECT_1_PLAYER);
        } else {
            buttonConfirmVictim.setDisable(true);
            lblDisplayChosenAmount.setText(
                    String.format("Noch %d Spieler\nabwählen.", chosenPlayerLayoutList.size() - 1));
        }
    }

    /**
     * Setup styles for hunter controls
     * @author Eric De Ron
     */
    @Override
    public void getStart() {
        setupAmountLabel();
        setupConfirmButton();
    }

    /**
     * Method to add controls for the hunter
     * @author Eric De Ron
     * @param gridColumn Column where controls should be added
     * @param gridRow Row where to start to add controls
     */
    public void addHunterControls(int gridColumn, int gridRow) {
        // Adds selected player amount label
        // and confirm button to the grid
        gridHunterVictim.add(lblDisplayChosenAmount, gridColumn, gridRow++);
        gridHunterVictim.add(buttonConfirmVictim, gridColumn, gridRow);
    }

    /**
     * Method to style the selected player amount
     * @author Eric De Ron
     */
    public void setupAmountLabel() {
        this.lblDisplayChosenAmount = new Label(HUNTER_SELECT_1_PLAYER);
        lblDisplayChosenAmount.getStyleClass().add(HUNTER_STYLE_LBL);
    }

    /**
     * Method to style next button
     * @author Eric De Ron
     */
    public void setupConfirmButton() {
        this.buttonConfirmVictim = new Button(HUNTER_CONFIRM_BUTTON);
        buttonConfirmVictim.setDisable(true);
        buttonConfirmVictim.setOnAction(this::confirmVictim);
    }

    /**
     * @author Eric De Ron
     * @param actionEvent Event of the called action listener
     */
    private void confirmVictim(ActionEvent actionEvent) {
        // Reset clicked player layout
        chosenPlayerLayoutList.get(0).unhighlightPlayer();
        chosenPlayerLayoutList.get(0).resetClick();

        // Set by hunter chosen player
        model.getVictims().setVictimHunter(chosenPlayerLayoutList.get(0).getPlayer());

        // Refresh victim grid
        playerVictimViewModel.setupVictimGrid();
        playerVictimViewModel.getStart();
        setHunterPhase(false);
        model.setHunterPhase(false);
        chosenPlayerLayoutList.clear();
    }

    /**
     * @author Eric De Ron
     * @return True if hunter is allowed to choose
     * someone to kill
     */
    public boolean isHunterPhase() {
        return isHunterPhase;
    }

    /**
     * @author Eric De Ron
     * @param isHunterPhase True if hunter phase should be
     *                      enabled set
     * @return True if not set false/null
     */
    public boolean setHunterPhase(boolean isHunterPhase) {
        this.isHunterPhase = isHunterPhase;
        return isHunterPhase;
    }

    /**
     * @author Eric De Ron
     * @param gridHunterVictim Grid where hunter controls
     *                         and victim should be added
     * @return True if not null
     */
    public boolean setGridHunterVictim(GridPane gridHunterVictim) {
        if (gridHunterVictim == null) return false;
        this.gridHunterVictim = gridHunterVictim;
        return true;
    }

    /**
     * @author Eric De Ron
     * @param playerVictimController Victim controller
     *                               to show all victims
     * @return True if not null
     */
    public boolean setPlayerVictimViewModel(PlayerVictimViewModel playerVictimController) {
        if (playerVictimController == null) return false;
        this.playerVictimViewModel = playerVictimController;
        return true;
    }

    /**
     * For testing get chosenPlayerLayoutList
     * @return All chosen player layouts
     */
    public List<PlayerLayoutViewModel> getChosenPlayerLayoutList() {
        return chosenPlayerLayoutList;
    }

    /**
     * For testing get getLblDisplayChosenAmount
     * @return Label to display chosen player amount
     */
    public Label getLblDisplayChosenAmount() {
        return lblDisplayChosenAmount;
    }
}
