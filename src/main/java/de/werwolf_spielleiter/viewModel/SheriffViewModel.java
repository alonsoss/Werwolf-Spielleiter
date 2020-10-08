package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleSheriff;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.trade.TradeFarmer;
import de.werwolf_spielleiter.trade.Trades;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class SheriffViewModel extends ViewModel {
    private Player player;
    private boolean wasVote = false;
    private boolean nextSheriffControlInit = false;
    private int alive = 0;
    private int difference = 0;
    private ArrayList<Player> votingSheriff;
    private PlayerVictimViewModel playerVictimViewModel;
    private List<PlayerLayoutViewModel> chosenPlayerLayoutList = new LinkedList<>();

    @FXML
    private Button buttonSelectConfirm;
    @FXML
    private Label lblChoosing;
    @FXML
    private Label lblVoting;
    @FXML
    private ComboBox<Integer> comboVotingPlayerOne;
    @FXML
    private ComboBox<Integer> comboVotingPlayerTwo;
    @FXML
    private ComboBox<Integer> comboVotingPlayerThree;
    @FXML
    private ComboBox<Integer> comboVotingPlayerFour;
    @FXML
    private Label lblPlayerOne;
    @FXML
    private Label lblPlayerTwo;
    @FXML
    private Label lblPlayerThree;
    @FXML
    private Label lblPlayerFour;
    @FXML
    private Label lblHinweis;
    @FXML
    private Button buttonConfirm;

    private Label lblDisplayChosenAmount;
    private Button buttonConfirmNextSheriff;
    private Label lblNextSheriff;

    private GridPane gridNextSheriff;

    /**
     * Voting the first time for the sheriff
     *
     * @author Florian Müller
     */
    @Override
    public void getStart() {

        alive = model.getPlayerAliveList().size();
        wasVote = false;
        int comboSize = comboVotingPlayerOne.getItems().size();
        for (int i = 0; i < comboSize; i++) {
            comboVotingPlayerOne.getItems().remove(0);
            comboVotingPlayerTwo.getItems().remove(0);
            comboVotingPlayerThree.getItems().remove(0);
            comboVotingPlayerFour.getItems().remove(0);
        }
        int size = votingSheriff.size();
        if (!model.isPlayWithTrade()) {
            // combobox fill without trades
            for (int i = 0; i <= alive; i++) {
                comboVotingPlayerOne.getItems().add(i);
                comboVotingPlayerTwo.getItems().add(i);
                comboVotingPlayerThree.getItems().add(i);
                comboVotingPlayerFour.getItems().add(i);
            }
        } else {
            // combobox fill with trades
            for (int i = 0; i <= Trades.getInstance().getFarmerList().size(); i++) {
                comboVotingPlayerOne.getItems().add(i);
                comboVotingPlayerTwo.getItems().add(i);
                comboVotingPlayerThree.getItems().add(i);
                comboVotingPlayerFour.getItems().add(i);
            }
        }

        resetVotingItems();
        switch (size) {
            case 4:
                comboVotingPlayerFour.setVisible(true);
                lblPlayerFour.setText(votingSheriff.get(3).toString());
                comboVotingPlayerFour.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            case 3:
                comboVotingPlayerThree.setVisible(true);
                lblPlayerThree.setText(votingSheriff.get(2).toString());
                comboVotingPlayerThree.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            case 2:
                comboVotingPlayerTwo.setVisible(true);
                lblPlayerTwo.setText(votingSheriff.get(1).toString());
                comboVotingPlayerTwo.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            case 1:
                comboVotingPlayerOne.setVisible(true);
                lblPlayerOne.setText(votingSheriff.get(0).toString());
                comboVotingPlayerOne.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

        }
        updateDisplay();
    }

    /**
     * Choosing the persons they want to be the Sheriff
     *
     * @author Florian Müller
     */

    public void choosing() {
        String display = SHERIFF_SELECT_X_PLAYERS;
        int clicked = 0;
        votingSheriff = new ArrayList<>();
        boardLayoutViewModel = view.getBoardLayoutViewModel();
        // choosing for the sheriff if its not played with trades
        if (!model.isPlayWithTrade()) {
            for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
                if (c.getClick() && c.getPlayer().getStatus().equals(PLAYER_ALIVE)) {
                    c.highlightPlayer();
                    clicked++;
                    player = c.getPlayer();
                } else {
                    c.unhighlightPlayer();
                    c.resetClick();
                }
                if (clicked == 0) {
                    display = String.format("Es dürfen noch %d Spieler ausgewählt werden.", SHERIFF_SHERIFF_VOTES);
                    lblChoosing.setText(display);
                    buttonSelectConfirm.setDisable(true);
                } else if (clicked < 4) {
                    int t = SHERIFF_SHERIFF_VOTES - clicked;
                    display = String.format("Es dürfen noch %d Spieler ausgewählt werden.", t);
                    lblChoosing.setText(display);
                    buttonSelectConfirm.setDisable(false);
                } else if (clicked == 4) {
                    display = SHERIFF_MAX_SELECTED;
                    lblChoosing.setText(display);
                    buttonSelectConfirm.setDisable(false);
                } else {
                    int t = clicked - SHERIFF_SHERIFF_VOTES;
                    display = String.format("Es müssen noch %d Spieler abgewählt werden.", t);
                    buttonSelectConfirm.setDisable(true);
                }
            }
        } else { // the sheriff choosing with trades
            for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
                if (c.getClick() && c.getPlayer().getStatus().equals(PLAYER_ALIVE) && c.getPlayer().getTrade().isSameClass(new TradeFarmer())) {
                    c.highlightPlayer();
                    clicked++;
                    player = c.getPlayer();
                } else {
                    c.unhighlightPlayer();
                    c.resetClick();
                }
                if (clicked == 0) {
                    display = String.format("Es dürfen noch %d Spieler ausgewählt werden.", SHERIFF_SHERIFF_VOTES);
                    lblChoosing.setText(display);
                    buttonSelectConfirm.setDisable(true);
                } else if (clicked < 4) {
                    int t = SHERIFF_SHERIFF_VOTES - clicked;
                    display = String.format("Es dürfen noch %d Spieler ausgewählt werden.", t);
                    lblChoosing.setText(display);
                    buttonSelectConfirm.setDisable(false);
                } else if (clicked == 4) {
                    display = SHERIFF_MAX_SELECTED;
                    lblChoosing.setText(display);
                    buttonSelectConfirm.setDisable(false);
                } else {
                    int t = clicked - SHERIFF_SHERIFF_VOTES;
                    display = String.format("Es müssen noch %d Spieler abgewählt werden.", t);
                    buttonSelectConfirm.setDisable(true);
                }

            }
        }
        lblChoosing.setText(display);


    }

    /**
     * update the Display
     *
     * @author Florian Müller
     */
    public void updateDisplay() {
        String display = SHERIFF_X_VOTES;
        int sumVote = getSumVotes();
        if (!model.isPlayWithTrade()) {
            difference = alive - sumVote;
        } else {
            difference = Trades.getInstance().getFarmerList().size() - sumVote;
        }
        if (difference > 0) {
            display = String.format("Noch %d Stimmen sind abzugeben.", difference);
            buttonConfirm.setDisable(true);
        } else if (difference == 0) {
            display = SHERIFF_MAX_VOTED;
            buttonConfirm.setDisable(false);
        } else {
            display = String.format("Es sind %d Stimmen zuviel", difference * (-1));
            buttonConfirm.setDisable(true);
        }
        lblVoting.setText(display);
        if (wasVote) {
            lblHinweis.setVisible(true);
        }
    }

    /**
     * get the number of votes set
     *
     * @author Florian Müller
     */
    private int getSumVotes() {
        int sumVote = 0;
        if (comboVotingPlayerOne.getSelectionModel().getSelectedItem() != null & comboVotingPlayerOne.isVisible()) {
            sumVote += comboVotingPlayerOne.getSelectionModel().getSelectedItem();
        }
        if (comboVotingPlayerTwo.getSelectionModel().getSelectedItem() != null & comboVotingPlayerTwo.isVisible()) {
            sumVote += comboVotingPlayerTwo.getSelectionModel().getSelectedIndex();
        }
        if (comboVotingPlayerThree.getSelectionModel().getSelectedItem() != null & comboVotingPlayerThree.isVisible()) {
            sumVote += comboVotingPlayerThree.getSelectionModel().getSelectedIndex();
        }
        if (comboVotingPlayerFour.getSelectionModel().getSelectedItem() != null & comboVotingPlayerFour.isVisible()) {
            sumVote += comboVotingPlayerFour.getSelectionModel().getSelectedIndex();
        }
        return sumVote;
    }

    /**
     * Voting for the Sheriff the second round if there is a pat between two or more players
     *
     * @author Florian Müller
     */
    public void voting(ArrayList<Player> list) {
        votingSheriff = list;
        wasVote = true;
        int size = votingSheriff.size();
        resetVotingItems();
        switch (size) {
            case 4:
                comboVotingPlayerFour.setVisible(true);
                lblPlayerFour.setText(votingSheriff.get(3).toString());
                comboVotingPlayerFour.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            case 3:
                comboVotingPlayerThree.setVisible(true);
                lblPlayerThree.setText(votingSheriff.get(2).toString());
                comboVotingPlayerThree.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            case 2:
                comboVotingPlayerTwo.setVisible(true);
                lblPlayerTwo.setText(votingSheriff.get(1).toString());
                comboVotingPlayerTwo.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            case 1:
                comboVotingPlayerOne.setVisible(true);
                lblPlayerOne.setText(votingSheriff.get(0).toString());
                comboVotingPlayerOne.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

        }
        updateDisplay();
    }

    /**
     * to get in the next scene after the players where chosen
     *
     * @author Florian Müller
     */
    @FXML
    public void nextSceneChoosing() {
        for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
            if (c.getClick()) {
                votingSheriff.add(c.getPlayer());
                c.resetClick();
            }
        }
        scene = GameScene.SHERIFF_VOTE;
        nextScene();

    }

    /**
     * to get after the voting in the next scene.
     * See if players have the same number of votes and if they do, a runoff vote is taken.
     *
     * @author Florian Müller
     */

    @FXML
    public void nextSceneVoting() {
        int max = 0;
        ArrayList<Player> originalSheriff = votingSheriff;
        votingSheriff = new ArrayList<>();
        if (comboVotingPlayerOne.getSelectionModel().getSelectedItem() != null & comboVotingPlayerOne.isVisible()) {
            max = comboVotingPlayerOne.getSelectionModel().getSelectedItem();
            votingSheriff.add(originalSheriff.get(0));
        }
        if (comboVotingPlayerTwo.getSelectionModel().getSelectedItem() != null & comboVotingPlayerTwo.isVisible()) {
            int select = comboVotingPlayerTwo.getSelectionModel().getSelectedItem();

            if (select == max && !wasVote) {
                votingSheriff.add(originalSheriff.get(1));
            } else if (select == max && wasVote) {
                votingSheriff = new ArrayList<>();
            } else if (select > max) {
                max = select;
                votingSheriff = new ArrayList<>();
                votingSheriff.add(originalSheriff.get(1));
            }
        }
        if (comboVotingPlayerThree.getSelectionModel().getSelectedItem() != null & comboVotingPlayerThree.isVisible()) {
            int select = comboVotingPlayerThree.getSelectionModel().getSelectedItem();

            if (select == max && !wasVote) {
                votingSheriff.add(originalSheriff.get(2));
            } else if (select == max && wasVote) {
                votingSheriff = new ArrayList<>();
            } else if (select > max) {
                max = select;
                votingSheriff = new ArrayList<>();
                votingSheriff.add(originalSheriff.get(2));
            }
        }
        if (comboVotingPlayerFour.getSelectionModel().getSelectedItem() != null & comboVotingPlayerFour.isVisible()) {
            int select = comboVotingPlayerFour.getSelectionModel().getSelectedItem();

            if (select == max && !wasVote) {
                votingSheriff.add(originalSheriff.get(3));
            } else if (select == max && wasVote) {
                votingSheriff = new ArrayList<>();
            } else if (select > max) {
                max = select;
                votingSheriff = new ArrayList<>();
                votingSheriff.add(originalSheriff.get(3));
            }
        }
        if (votingSheriff.size() == 1 || wasVote) {
            if (votingSheriff.size() == 1) {
                player = votingSheriff.get(0);
                player.addRole(new RoleSheriff());
                model.setSheriffWasVote(true);
                for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
                    if (c.getPlayer().hasRole(new RoleSheriff()))
                        c.markSheriff();
                    c.resetClick();
                }
            }
            boardLayoutViewModel.unhiglightAllPlayers();
            // Switch to lynch choosing scene
            scene = GameScene.LYNCH_VICTIM_CHOOSING;
            nextScene();

        } else {
            voting(votingSheriff);
        }

    }

    /**
     * add the player the role of teh player how was chosen next
     *
     * @param p is the player how was chosen
     * @author Florian Müller
     */
    public void setNewSheriff(Player p) {
        p.addRole(new RoleSheriff());
    }

    /**
     * setup the choosing for the next sheriff when the old on died
     *
     * @author Florian Müller
     */
    public void setupNextSheriff() {
        nextSheriffControlInit = true;
        chosenPlayerLayoutList.clear();
        setupConfirmButton();
        setupAmountLabel();
        setupNextSheriffLabel();

    }

    /**
     * to get the labe showen in the grid when the old sheriff died
     *
     * @param gridColumn
     * @param gridRow
     * @author Florian Müller
     */
    public void addSheriffControls(int gridColumn, int gridRow) {
        gridNextSheriff.add(lblNextSheriff, gridColumn, gridRow++);
        gridNextSheriff.add(lblDisplayChosenAmount, gridColumn, gridRow++);
        gridNextSheriff.add(buttonConfirmNextSheriff, gridColumn, gridRow);
    }

    /**
     * set the next sheriff choose items not visible
     *
     * @author Florian Müller
     */
    public void removeNextSheriffControl() {
        nextSheriffControlInit = false;
        lblNextSheriff.setVisible(false);
        lblDisplayChosenAmount.setVisible(false);
        buttonConfirmNextSheriff.setVisible(false);
    }

    /**
     * setup the Label Amount to show the player how many he can chose
     *
     * @author Florian Müller
     */
    public void setupAmountLabel() {
        this.lblDisplayChosenAmount = new Label(SHERIFF_SELECT_1_PLAYER);
        lblDisplayChosenAmount.getStyleClass().add(SHERIFF_STYLE_LBL);
        lblDisplayChosenAmount.setVisible(true);
    }

    /**
     * setup the label for the next sheriff that the player know what he should do
     *
     * @author Florian Müller
     */
    public void setupNextSheriffLabel() {
        this.lblNextSheriff = new Label(SHERIFF_SELECT_NEW_SHERIFF);
        lblNextSheriff.getStyleClass().add(SHERIFF_STYLE_LBL);
        lblNextSheriff.setVisible(true);
    }

    /**
     * setup the button to confirm the new chosen sheriff
     *
     * @author Florian Müller
     */
    public void setupConfirmButton() {
        this.buttonConfirmNextSheriff = new Button("Wahl bestätigen");
        buttonConfirmNextSheriff.setDisable(true);
        buttonConfirmNextSheriff.setOnAction(this::confirmNextSheriff);
        buttonConfirmNextSheriff.setVisible(true);
    }

    /**
     * activated the choosing for the next sheriff
     *
     * @param playerLayoutController to highlight and unhighlight the player how was chosen
     * @author Florian Müller
     */
    public void choosing(PlayerLayoutViewModel playerLayoutController) {
        if (chosenPlayerLayoutList.contains(playerLayoutController)) {
            chosenPlayerLayoutList.remove(playerLayoutController);
            playerLayoutController.unhighlightPlayer();
        } else {
            chosenPlayerLayoutList.add(playerLayoutController);
            playerLayoutController.highlightPlayer();
        }
        updateDisplayNextSheriff();
    }

    /**
     * update the display for the choosing the next sheriff
     *
     * @author Florian Müller
     */
    public void updateDisplayNextSheriff() {

        if (chosenPlayerLayoutList.size() == 1) {
            buttonConfirmNextSheriff.setDisable(false);
            lblDisplayChosenAmount.setText("Ausgewählt:\n" + chosenPlayerLayoutList.get(0).getPlayer());
        } else if (chosenPlayerLayoutList.size() == 0) {
            buttonConfirmNextSheriff.setDisable(true);
            lblDisplayChosenAmount.setText(SHERIFF_SELECT_1_PLAYER);
        } else {
            buttonConfirmNextSheriff.setDisable(true);
            lblDisplayChosenAmount.setText(
                    String.format("Noch %d Spieler\nabwählen.", chosenPlayerLayoutList.size() - 1));
        }
    }

    /**
     * confirm the next sheriff and switch to the next right scene
     *
     * @param actionEvent
     * @author Florian Müller
     */
    public void confirmNextSheriff(ActionEvent actionEvent) {
        if (boardLayoutViewModel.showWinner() == null) {
            // Reset clicked player layout
            model.getVictims().resetLovers();
            chosenPlayerLayoutList.get(0).unhighlightPlayer();
            chosenPlayerLayoutList.get(0).resetClick();
            for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
                c.unhighlightPlayer();
                c.resetClick();
            }
            chosenPlayerLayoutList.get(0).markSheriff();
            setNewSheriff(chosenPlayerLayoutList.get(0).getPlayer());
            model.setNextSheriffVote(false);
            if (playerVictimViewModel.getPhase() == PlayerVictimViewModel.Phase.NIGHT) {
                scene = GameScene.LYNCH_VICTIM_CHOOSING;
                nextScene();
            } else if (playerVictimViewModel.getPhase() == PlayerVictimViewModel.Phase.DAY) {
                scene = GameScene.NEW_NIGHT;
                nextScene();
            }
        }
    }

    /**
     * to rest the voting items for the case that the sheriff was not vote on the first day
     * and the default value is set for the voting
     *
     * @author Florian Müller
     */
    public void resetVotingItems() {
        comboVotingPlayerOne.getSelectionModel().selectFirst();
        comboVotingPlayerTwo.getSelectionModel().selectFirst();
        comboVotingPlayerThree.getSelectionModel().selectFirst();
        comboVotingPlayerFour.getSelectionModel().selectFirst();
        comboVotingPlayerOne.setVisible(false);
        comboVotingPlayerTwo.setVisible(false);
        comboVotingPlayerThree.setVisible(false);
        comboVotingPlayerFour.setVisible(false);
        lblHinweis.setVisible(false);
        lblPlayerOne.setText("");
        lblPlayerTwo.setText("");
        lblPlayerThree.setText("");
        lblPlayerFour.setText("");
    }

    /**
     * to set the PlayerVictimViewModel for the choosing the next sheriff
     *
     * @param playerVictimController
     * @return
     * @author Florian Müller
     */
    public boolean setPlayerVictimViewModel(PlayerVictimViewModel playerVictimController) {
        if (playerVictimController == null) return false;
        this.playerVictimViewModel = playerVictimController;
        return true;
    }

    /**
     * set the grid for the next sheriff to show it on the scene
     *
     * @param grid
     * @return
     * @author Florian Müller
     */
    public boolean setGridNextSheriff(GridPane grid) {
        if (grid == null) return false;
        this.gridNextSheriff = grid;
        return true;
    }

    /**
     * for testing the PlayerVictimViewModel
     *
     * @author Florian Müller
     */
    public void testingSheriffViewModel() {
        setupNextSheriff();
        gridNextSheriff = new GridPane();
    }

    /**
     *
     * @return true if the next sheriff controls are initialized
     * @author Florian Müller
     */
    public boolean isNextSheriffControlInit(){
        return nextSheriffControlInit;
    }
}
