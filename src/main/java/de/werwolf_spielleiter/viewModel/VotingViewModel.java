package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.trade.Trades;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.VOTING_ALL_VOTED;
import static de.werwolf_spielleiter.constants.PrivateGameConstants.VOTING_SHERIFF_CHOOSES;

public class VotingViewModel extends ViewModel {

    private ArrayList<Player> victimsList;
    private int alive = 0;
    private boolean wasVote = false;
    private BoardLayoutViewModel boardLayoutController;
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
    private Button buttonConfirm;
    @FXML
    private Button buttonSheriffVotingOne;
    @FXML
    private Button buttonSheriffVotingTwo;
    @FXML
    private Button buttonSheriffVotingThree;
    @FXML
    private Button buttonSheriffVotingFour;
    @FXML
    private Label lblHinweis;


    /**
     * Initialize the combo boxes with selectable numbers.
     * Initialize the display label with the amount of votes to select.
     *
     * @author Florian Müller
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        victimsList = view.getChoosingViewModel().getLynchVictims();
        alive = model.getPlayerAliveList().size();
        wasVote = false;
        if (model.isPlayWithTrade() && Trades.getInstance().getFarmerList().isEmpty()) {
            model.setSheriff(false);
            model.setSheriffWasVote(false);
        }
        boardLayoutController = view.getBoardLayoutViewModel();
        int comboSize = comboVotingPlayerOne.getItems().size();
        for (int i = 0; i < comboSize; i++) {
            comboVotingPlayerOne.getItems().remove(0);
            comboVotingPlayerTwo.getItems().remove(0);
            comboVotingPlayerThree.getItems().remove(0);
            comboVotingPlayerFour.getItems().remove(0);
        }
        int size = victimsList.size();
        if (model.isPlayWithTrade() && !Trades.getInstance().getFarmerList().isEmpty()) {
            if (model.getSheriffWasVote()) {
                alive++;
            }
        } else if (!model.isPlayWithTrade()) {
            if (model.getSheriffWasVote()) {
                alive++;
            }
        }
        for (int i = 0; i <= alive; i++) {
            comboVotingPlayerOne.getItems().add(i);
            comboVotingPlayerTwo.getItems().add(i);
            comboVotingPlayerThree.getItems().add(i);
            comboVotingPlayerFour.getItems().add(i);
        }
        resetVotingItems();
        switch (size) {
            case 4:
                comboVotingPlayerFour.setVisible(true);
                lblPlayerFour.setText(victimsList.get(3).toString());
                comboVotingPlayerFour.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
                buttonSheriffVotingFour.setVisible(false);

            case 3:
                comboVotingPlayerThree.setVisible(true);
                lblPlayerThree.setText(victimsList.get(2).toString());
                comboVotingPlayerThree.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
                buttonSheriffVotingThree.setVisible(false);
            case 2:
                comboVotingPlayerTwo.setVisible(true);
                lblPlayerTwo.setText(victimsList.get(1).toString());
                comboVotingPlayerTwo.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
                buttonSheriffVotingTwo.setVisible(false);

            case 1:
                comboVotingPlayerOne.setVisible(true);
                lblPlayerOne.setText(victimsList.get(0).toString());
                comboVotingPlayerOne.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
                buttonSheriffVotingOne.setVisible(false);
        }
        if (model.isSheriff())
            lblHinweis.setVisible(true);
        updateDisplay();
    }


    /**
     * update the Display
     *
     * @author Florian Müller
     */
    public void updateDisplay() {
        String display = "noch X stimmen abgeben";
        int sumVote = getSumVotes();
        int difference = alive - sumVote;
        if (difference > 0) {
            display = String.format("Noch %d Stimmen sind abzugeben.", difference);
            buttonConfirm.setDisable(true);
        } else if (difference == 0) {
            display = VOTING_ALL_VOTED;
            buttonConfirm.setDisable(false);
        } else {
            display = String.format("Es sind %d Stimmen zuviel", difference * (-1));
            buttonConfirm.setDisable(true);
        }
        lblVoting.setText(display);
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
     * switch scene
     *
     * @author Florian Müller
     */
    @FXML
    private void nextSceneVoting() {
        int max = 0;
        ArrayList<Player> originalVictim = victimsList;
        victimsList = new ArrayList<>();
        if (comboVotingPlayerOne.getSelectionModel().getSelectedItem() != null & comboVotingPlayerOne.isVisible()) {
            max = comboVotingPlayerOne.getSelectionModel().getSelectedItem();
            victimsList.add(originalVictim.get(0));
        }
        if (comboVotingPlayerTwo.getSelectionModel().getSelectedItem() != null & comboVotingPlayerTwo.isVisible()) {
            int select = comboVotingPlayerTwo.getSelectionModel().getSelectedItem();

            if (select == max && !wasVote) {
                victimsList.add(originalVictim.get(1));
            } else if (select == max && wasVote) {
                victimsList = new ArrayList<>();
            } else if (select > max) {
                max = select;
                victimsList = new ArrayList<>();
                victimsList.add(originalVictim.get(1));
            }
        }
        if (comboVotingPlayerThree.getSelectionModel().getSelectedItem() != null & comboVotingPlayerThree.isVisible()) {
            int select = comboVotingPlayerThree.getSelectionModel().getSelectedItem();

            if (select == max && !wasVote) {
                victimsList.add(originalVictim.get(2));
            } else if (select == max && wasVote) {
                victimsList = new ArrayList<>();
            } else if (select > max) {
                max = select;
                victimsList = new ArrayList<>();
                victimsList.add(originalVictim.get(2));
            }
        }
        if (comboVotingPlayerFour.getSelectionModel().getSelectedItem() != null & comboVotingPlayerFour.isVisible()) {
            int select = comboVotingPlayerFour.getSelectionModel().getSelectedItem();

            if (select == max && !wasVote) {
                victimsList.add(originalVictim.get(3));
            } else if (select == max && wasVote) {
                victimsList = new ArrayList<>();
            } else if (select > max) {
                max = select;
                victimsList = new ArrayList<>();
                victimsList.add(originalVictim.get(3));
            }
        }
        if (victimsList.size() == 1 || wasVote) {
            if (victimsList.size() == 1) {
                model.getVictims().setVictimLynch(victimsList.get(0));
            }
            for (PlayerLayoutViewModel c : boardLayoutController.getViewModelList()) {
                c.unhighlightPlayer();
                c.resetClick();
            }
            boardLayoutController.unhiglightAllPlayers();
            boardLayoutController.getViewModelList().forEach(x -> x.unmarkLovers());
            // Switch to show victim dead scene
            scene = GameScene.VICTIM_DAY;
            nextScene();


        } else {
            getStarted(victimsList);
        }
    }

    /**
     * Initialize the combo boxes with selectable numbers.
     * Initialize the display label with the amount of votes to select.
     *
     * @author Florian Müller
     */
    public void getStarted(ArrayList<Player> list) {
        victimsList = list;
        wasVote = true;
        int size = victimsList.size();
        resetVotingItems();
        if (!model.getSheriffWasVote()) {
            // if sheriff was not voted than its the runoff between the selected players
            switch (size) {
                case 4:
                    comboVotingPlayerFour.setVisible(true);
                    lblPlayerFour.setText(victimsList.get(3).toString());
                    comboVotingPlayerFour.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

                case 3:
                    comboVotingPlayerThree.setVisible(true);
                    lblPlayerThree.setText(victimsList.get(2).toString());
                    comboVotingPlayerThree.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

                case 2:
                    comboVotingPlayerTwo.setVisible(true);
                    lblPlayerTwo.setText(victimsList.get(1).toString());
                    comboVotingPlayerTwo.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

                case 1:
                    comboVotingPlayerOne.setVisible(true);
                    lblPlayerOne.setText(victimsList.get(0).toString());
                    comboVotingPlayerOne.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());

            }
            updateDisplay();
        } else {
            // sheriff decided how to kill
            switch (size) {
                case 4:
                    lblPlayerFour.setText(victimsList.get(3).toString());
                    buttonSheriffVotingFour.setVisible(true);
                case 3:
                    lblPlayerThree.setText(victimsList.get(2).toString());
                    buttonSheriffVotingThree.setVisible(true);
                case 2:
                    lblPlayerTwo.setText(victimsList.get(1).toString());
                    buttonSheriffVotingTwo.setVisible(true);
                case 1:
                    lblPlayerOne.setText(victimsList.get(0).toString());
                    buttonSheriffVotingOne.setVisible(true);
            }
            buttonConfirm.setVisible(false);
            lblVoting.setText(VOTING_SHERIFF_CHOOSES);
        }

    }

    /**
     * @author Florian Müller
     * This sets all Labels of the GridBox on an emtpy String.
     * And all the ComboBoxen are not visible.
     * Select the frist item of the Combo boxen.
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
        buttonSheriffVotingOne.setVisible(false);
        buttonSheriffVotingTwo.setVisible(false);
        buttonSheriffVotingThree.setVisible(false);
        buttonSheriffVotingFour.setVisible(false);
        buttonConfirm.setVisible(true);
        lblHinweis.setVisible(false);
        lblPlayerOne.setText("");
        lblPlayerTwo.setText("");
        lblPlayerThree.setText("");
        lblPlayerFour.setText("");
    }

    /**
     * to come to the next scene if the sheriff chose the lynch victim
     *
     * @author Florian Müller
     */
    @FXML
    public void nextSceneSheriffOne() {
        model.getVictims().setVictimLynch(victimsList.get(0));
        for (PlayerLayoutViewModel c : boardLayoutController.getViewModelList()) {
            c.unhighlightPlayer();
            c.resetClick();
        }
        scene = GameScene.VICTIM_DAY;
        nextScene();
    }

    @FXML
    public void nextSceneSheriffTwo() {
        model.getVictims().setVictimLynch(victimsList.get(1));
        for (PlayerLayoutViewModel c : boardLayoutController.getViewModelList()) {
            c.unhighlightPlayer();
            c.resetClick();
        }
        scene = GameScene.VICTIM_DAY;
        nextScene();
    }

    @FXML
    public void nextSceneSheriffThree() {
        model.getVictims().setVictimLynch(victimsList.get(2));
        for (PlayerLayoutViewModel c : boardLayoutController.getViewModelList()) {
            c.unhighlightPlayer();
            c.resetClick();
        }
        scene = GameScene.VICTIM_DAY;
        nextScene();
    }

    @FXML
    public void nextSceneSheriffFour() {
        model.getVictims().setVictimLynch(victimsList.get(3));
        for (PlayerLayoutViewModel c : boardLayoutController.getViewModelList()) {
            c.unhighlightPlayer();
            c.resetClick();
        }
        scene = GameScene.VICTIM_DAY;
        nextScene();
    }

}
