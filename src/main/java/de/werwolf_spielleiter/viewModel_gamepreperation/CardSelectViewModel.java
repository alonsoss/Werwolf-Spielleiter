package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.NewRoundViewModell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.HashMap;
import java.util.Map;

import static de.werwolf_spielleiter.config.ConfigLoader.configGetOrDefault;
import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

/**
 * This class handles the objects within the card selection scene.
 *
 * @author Henrik Möhlmann
 */
public class CardSelectViewModel extends ViewModel {

    @FXML
    private Button buttonSelectCardsNext;
    @FXML
    private Button buttonSelectCardsLast;
    @FXML
    private ComboBox<Integer> comboCountWerewolf;
    @FXML
    private ComboBox<Integer> comboCountGreatWolf;
    @FXML
    private ComboBox<Integer> comboCountBigBadWolf;
    @FXML
    private ComboBox<Integer> comboCountWhiteWerewolf;
    @FXML
    private ComboBox<Integer> comboCountVillager;
    @FXML
    private ComboBox<Integer> comboCountWitch;
    @FXML
    private ComboBox<Integer> comboCountHunter;
    @FXML
    private ComboBox<Integer> comboCountSeer;
    @FXML
    private ComboBox<Integer> comboCountCupid;
    @FXML
    private ComboBox<Integer> comboCountLittleGirl;
    @FXML
    private ComboBox<Integer> comboCountThief;
    @FXML
    private ComboBox<Integer> comboCountWildChild;
    @FXML
    private ComboBox<Integer> comboCountDogWolf;
    @FXML
    private ComboBox<Integer> comboCountFox;
    @FXML
    private Label lblDisplaySelectCards;
    @FXML
    private RadioButton radioSheriff;
    @FXML
    private RadioButton radioTrade;


    private int extraCards = 0;

    private boolean sceneStarted = false;

    @FXML
    public void initialize() {

    }

    /**
     * for testing
     *
     * @author Henrik Möhlmann
     */
    public void testInit() {
        buttonSelectCardsNext = new Button();
        comboCountWerewolf = new ComboBox<>();
        comboCountGreatWolf = new ComboBox<>();
        comboCountBigBadWolf = new ComboBox<>();
        comboCountWhiteWerewolf = new ComboBox<>();
        comboCountVillager = new ComboBox<>();
        comboCountWitch = new ComboBox<>();
        comboCountHunter = new ComboBox<>();
        comboCountSeer = new ComboBox<>();
        comboCountCupid = new ComboBox<>();
        comboCountLittleGirl = new ComboBox<>();
        comboCountThief = new ComboBox<>();
        comboCountWildChild = new ComboBox<>();
        comboCountDogWolf = new ComboBox<>();
        comboCountWerewolf = new ComboBox<>();
        comboCountFox = new ComboBox<>();

        lblDisplaySelectCards = new Label();
        radioSheriff = new RadioButton();
        radioTrade = new RadioButton();
    }

    /**
     * for testing
     *
     * @param bool
     * @author Henrik Möhlmann
     */
    public void testSelectRadioTrade(boolean bool) {
        radioTrade.setSelected(bool);
    }

    /**
     * Initialize the combo boxes with selectable numbers.
     * Initialize the display label with the amount of cards to select
     *
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    public void getStart() {
        if (sceneStarted) resetScene();
        this.sceneStarted = true;
        //fill the combo boxes with selectable numbers
        for (int i = 0; i <= model.getCountPlayer(); i++) {
            comboCountWerewolf.getItems().add(i);
            comboCountVillager.getItems().add(i);
        }
        //fill the combo boxes for the characters that exists only once in the game
        for (int i = 0; i <= 1; i++) {
            comboCountGreatWolf.getItems().add(i);
            comboCountBigBadWolf.getItems().add(i);
            comboCountWhiteWerewolf.getItems().add(i);
            comboCountWitch.getItems().add(i);
            comboCountHunter.getItems().add(i);
            comboCountSeer.getItems().add(i);
            comboCountCupid.getItems().add(i);
            comboCountLittleGirl.getItems().add(i);
            comboCountThief.getItems().add(i);
            comboCountWildChild.getItems().add(i);
            comboCountFox.getItems().add(i);
            comboCountDogWolf.getItems().add(i);
        }
        int werewolfCount = 0;
        int greatWolfCount = 0;
        int bigBadWolfCount = 0;
        int whiteWerewolfCount = 0;
        int villagerCount = 0;
        int witchCount = 0;
        int hunterCount = 0;
        int cupidCount = 0;
        int seerCount = 0;
        int littleGirlCount = 0;
        int thiefCount = 0;
        int wildChildCount = 0;
        int foxCount = 0;
        int dogWolfCount = 0;
        if (!NewRoundViewModell.getInstance().isRoundLoop()) {
            // Default values for better testing
            // No need to check for sanity of values here
            // getSelectionModel().select doesn't crash
            double percentageWerewolves = ((Double) configGetOrDefault("DEFAULT_PERCENTAGE_WEREWOLVES"));
            double percentageVillagers = ((Double) configGetOrDefault("DEFAULT_PERCENTAGE_VILLAGERS"));
            int amountExtraCards = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_EXTRA_CARDS"));
            int amountWerewolves = (int) Math.round((model.getCountPlayer() + amountExtraCards) * percentageWerewolves);
            int amountVillagers = (int) Math.round((model.getCountPlayer() + amountExtraCards) * percentageVillagers);


            //prepare the selection of werewolf cards
            if (amountWerewolves > 0) {
                whiteWerewolfCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_WHITE_WEREWOLF"));
                amountWerewolves--;
            }
            if (amountWerewolves > 0) {
                greatWolfCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_GREAT_WOLF"));
                amountWerewolves--;
            }
            if (amountWerewolves > 0) {
                bigBadWolfCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_BIG_BAD_WOLF"));
                amountWerewolves--;
            }
            if (amountWerewolves > 0) {
                werewolfCount = amountWerewolves;
            }

            //prepare the selection of villager cards
            if (amountVillagers > 0) {
                witchCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_WITCH"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                hunterCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_HUNTER"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                cupidCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_CUPID"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                seerCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_SEER"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                littleGirlCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_LITTLE_GIRL"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                thiefCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_THIEF"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                wildChildCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_WILD_CHILD"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                foxCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_FOX"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                dogWolfCount = ((Integer) configGetOrDefault("DEFAULT_AMOUNT_DOG_WOLF"));
                amountVillagers--;
            }
            if (amountVillagers > 0) {
                villagerCount = amountVillagers;
            }
        } else {
            werewolfCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_WEREWOLF_NAME);
            greatWolfCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_GREAT_WOLF_NAME);
            bigBadWolfCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_BIG_BAD_WOLF_NAME);
            whiteWerewolfCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_WHITE_WEREWOLF_NAME);
            villagerCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_VILLAGER_NAME);
            witchCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_WITCH_NAME);
            hunterCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_HUNTER_NAME);
            cupidCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_CUPID_NAME);
            seerCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_SEER_NAME);
            littleGirlCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_LITTLE_GIRL_NAME);
            thiefCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_THIEF_NAME);
            wildChildCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_WILD_CHILD_NAME);
            foxCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_FOX_NAME);
            dogWolfCount = NewRoundViewModell.getInstance().getCharAmount().get(CHAR_DOG_WOLF_NAME);
        }

        comboCountWerewolf.getSelectionModel().select(werewolfCount);
        comboCountGreatWolf.getSelectionModel().select(greatWolfCount);
        comboCountBigBadWolf.getSelectionModel().select(bigBadWolfCount);
        comboCountWhiteWerewolf.getSelectionModel().select(whiteWerewolfCount);
        comboCountVillager.getSelectionModel().select(villagerCount);
        comboCountWitch.getSelectionModel().select(witchCount);
        comboCountHunter.getSelectionModel().select(hunterCount);
        comboCountCupid.getSelectionModel().select(cupidCount);
        comboCountSeer.getSelectionModel().select(seerCount);
        comboCountLittleGirl.getSelectionModel().select(littleGirlCount);
        comboCountThief.getSelectionModel().select(thiefCount);
        comboCountWildChild.getSelectionModel().select(wildChildCount);
        comboCountFox.getSelectionModel().select(foxCount);
        comboCountDogWolf.getSelectionModel().select(dogWolfCount);
        if (!NewRoundViewModell.getInstance().isRoundLoop()) {
            radioSheriff.setSelected(true);
            radioTrade.setSelected(true);
        } else {
            radioSheriff.setSelected(NewRoundViewModell.getInstance().getRadioSelect().get(SHERIFF_RADIO));
            radioTrade.setSelected(NewRoundViewModell.getInstance().getRadioSelect().get(TRADE_RADIO));
        }
        /////// Default values for better testing


        //display the number of cards to select
        String displayString = String.format("Noch %d Karten auswählen. ", model.getCountPlayer());
        lblDisplaySelectCards.setText(displayString);
        updateDisplay();
        //add change listener to the combo boxes
        comboCountWerewolf.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountGreatWolf.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountBigBadWolf.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountWhiteWerewolf.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });

        comboCountVillager.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountWitch.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountHunter.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountCupid.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountSeer.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountLittleGirl.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountThief.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountWildChild.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountFox.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
        comboCountDogWolf.valueProperty().addListener((observableValue, integer, t1) -> {
            updateDisplay();
        });
    }


    /**
     * Method to update the display label with the actual amount of cards to select before the next scene is available.
     * Enable the button to next scene if the right amount is selected.
     *
     * @author Henrik Möhlmann
     */
    private void updateDisplay() {
        int sumCards = getSumCards();
        String displayString;
        int difference = model.getCountPlayer() + extraCards - sumCards;
        if (difference > 0) {
            displayString = String.format("Noch %d Karte(n) auswählen", difference);
            buttonSelectCardsNext.setDisable(true);
        } else if (difference == 0) {
            displayString = CARD_SELECT_CORRECT_AMOUNT;
            buttonSelectCardsNext.setDisable(false);
        } else {
            displayString = String.format("Noch %d Karte(n) abwählen. ", difference * (-1));
            buttonSelectCardsNext.setDisable(true);
        }
        lblDisplaySelectCards.setText(displayString);
    }

    /**
     * Get the amount of selected cards.
     *
     * @return the amount of selected cards
     * @author Henrik Möhlmann
     */
    private int getSumCards() {
        extraCards = 0;
        int sumCards = 0;
        if (comboCountWerewolf.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountWerewolf.getSelectionModel().getSelectedItem();
        }
        if (comboCountGreatWolf.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountGreatWolf.getSelectionModel().getSelectedItem();
        }
        if (comboCountBigBadWolf.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountBigBadWolf.getSelectionModel().getSelectedItem();
        }
        if (comboCountWhiteWerewolf.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountWhiteWerewolf.getSelectionModel().getSelectedItem();
        }

        if (comboCountVillager.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountVillager.getSelectionModel().getSelectedItem();
        }
        if (comboCountWitch.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountWitch.getSelectionModel().getSelectedItem();
        }
        if (comboCountHunter.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountHunter.getSelectionModel().getSelectedItem();
        }
        if (comboCountCupid.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountCupid.getSelectionModel().getSelectedItem();
        }
        if (comboCountSeer.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountSeer.getSelectionModel().getSelectedItem();
        }
        if (comboCountLittleGirl.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountLittleGirl.getSelectionModel().getSelectedItem();
        }
        if (comboCountThief.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountThief.getSelectionModel().getSelectedItem();
            if (comboCountThief.getSelectionModel().getSelectedItem() == 1) {
                extraCards += 2;
            }
        }
        if (comboCountWildChild.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountWildChild.getSelectionModel().getSelectedItem();
        }
        if (comboCountFox.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountFox.getSelectionModel().getSelectedIndex();
        }
        if (comboCountDogWolf.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountDogWolf.getSelectionModel().getSelectedItem();
        }
        return sumCards;
    }

    /**
     * for testing
     *
     * @author Henrik Möhlmann
     */
    public void saveTest() {
        saveCardCount();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void save() {
        saveCardCount();
        next();
    }

    /**
     * Method is used by a button to save the selected amount of cards, start the game and go on to the next scene.
     *
     * @author Henrik Möhlmann
     */
    public void saveCardCount() {
        model.setCountWerewolf(comboCountWerewolf != null
                ? comboCountWerewolf.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountGreatWolf(comboCountGreatWolf != null
                ? comboCountGreatWolf.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountBigBadWolf(comboCountBigBadWolf != null
                ? comboCountBigBadWolf.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountWhiteWerewolf(comboCountWhiteWerewolf != null
                ? comboCountWhiteWerewolf.getSelectionModel().getSelectedItem()
                : 0);

        model.setCountVillager(comboCountVillager != null
                ? comboCountVillager.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountWitch(comboCountWitch != null
                ? comboCountWitch.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountHunter(comboCountHunter != null
                ? comboCountHunter.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountCupid(comboCountCupid != null
                ? comboCountCupid.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountSeer(comboCountSeer != null
                ? comboCountSeer.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountLittleGirl(comboCountLittleGirl != null
                ? comboCountLittleGirl.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountThief(comboCountThief != null
                ? comboCountThief.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountWildChild(comboCountWildChild != null
                ? comboCountWildChild.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountFox(comboCountFox != null
                ? comboCountFox.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountDogWolf(comboCountDogWolf != null
                ? comboCountDogWolf.getSelectionModel().getSelectedItem()
                : 0);
        model.setSheriff(radioSheriff.isSelected());
        model.setPlayWithTrade(radioTrade.isSelected());

        if (!radioTrade.isSelected()) {
            model.startGame();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void next() {
        if (radioTrade.isSelected()) {
            scene = GameScene.TRADE_AMOUNT_CHOOSING;
            boardLayoutViewModel.enableTradeMenu();
            if (model.getCountConfessor() > 0) {
                boardLayoutViewModel.enableConfessorMenu();
            }
        } else {
            //model.startGame();
            scene = GameScene.FIRST_NIGHT;
            boardLayoutViewModel.disableTradeMenu();
            boardLayoutViewModel.disableConfessorMenu();
        }
        nextScene();
    }

    /**
     * Moves view to the previous scene
     *
     * @author Eric De Ron
     */
    @FXML
    public void lastSceneButton() {
        scene = GameScene.PLAYER_NAME_SELECTING;
        nextScene();
    }

    /**
     * Resets all changeable variables of the scene
     *
     * @author Eric De Ron
     */
    public void resetScene() {
        comboCountWerewolf.getItems().clear();
        comboCountVillager.getItems().clear();
        comboCountGreatWolf.getItems().clear();
        comboCountBigBadWolf.getItems().clear();
        comboCountWhiteWerewolf.getItems().clear();
        comboCountWitch.getItems().clear();
        comboCountHunter.getItems().clear();
        comboCountSeer.getItems().clear();
        comboCountCupid.getItems().clear();
        comboCountLittleGirl.getItems().clear();
        comboCountThief.getItems().clear();
        comboCountWildChild.getItems().clear();
        comboCountFox.getItems().clear();
        comboCountDogWolf.getItems().clear();
    }

    /**
     * @author Florian Müller
     * @return the selected character for a new round
     */
    public Map<String, Integer> getCharacterCountMap() {
        Map<String, Integer> characterCountMap = new HashMap<>();
        characterCountMap.put(CHAR_WEREWOLF_NAME, model.getCountWerewolf());
        characterCountMap.put(CHAR_VILLAGER_NAME, model.getCountVillager());
        characterCountMap.put(CHAR_GREAT_WOLF_NAME, model.getCountGreatWolf());
        characterCountMap.put(CHAR_BIG_BAD_WOLF_NAME, model.getCountBigBadWolf());
        characterCountMap.put(CHAR_WHITE_WEREWOLF_NAME, model.getCountWhiteWerewolf());
        characterCountMap.put(CHAR_WITCH_NAME, model.getCountWitch());
        characterCountMap.put(CHAR_HUNTER_NAME, model.getCountHunter());
        characterCountMap.put(CHAR_SEER_NAME, model.getCountSeer());
        characterCountMap.put(CHAR_CUPID_NAME, model.getCountCupid());
        characterCountMap.put(CHAR_LITTLE_GIRL_NAME, model.getCountLittleGirl());
        characterCountMap.put(CHAR_THIEF_NAME, model.getCountThief());
        characterCountMap.put(CHAR_WILD_CHILD_NAME, model.getCountWildChild());
        characterCountMap.put(CHAR_FOX_NAME, model.getCountFox());
        characterCountMap.put(CHAR_DOG_WOLF_NAME, model.getCountDogWolf());
        return characterCountMap;
    }

    /**
     * @author Florian Müller
     * @return the selection of the radio buttons for a new round
     */
    public Map<String, Boolean> getRadioButtonStatusMap() {
        Map<String, Boolean> radioButtonStatusMap = new HashMap<>();
        radioButtonStatusMap.put(SHERIFF_RADIO, radioSheriff.isSelected());
        radioButtonStatusMap.put(TRADE_RADIO, radioTrade.isSelected());
        return radioButtonStatusMap;
    }
}
