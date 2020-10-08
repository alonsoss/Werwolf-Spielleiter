package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.NewRoundViewModell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

import static de.werwolf_spielleiter.config.ConfigLoader.configGetOrDefault;
import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class TradeSelectViewModel extends ViewModel {
    @FXML
    public Button buttonSelectTradesNext;
    @FXML
    public ComboBox<Integer> comboCountVagabond;
    @FXML
    public Label lblDisplaySelectCards;
    @FXML
    public ImageView image_vagabond;
    @FXML
    public ImageView image_farmer;
    @FXML
    public ComboBox<Integer> comboCountFarmer;
    @FXML
    public Button buttonSelectTradesReturn;
    @FXML
    public ImageView image_confessor;
    @FXML
    public ComboBox<Integer> comboCountConfessor;

    private boolean sceneStarted = false;

    /**
     * @author Henrik Möhlmann
     */
    public void testInit() {
        buttonSelectTradesNext = new Button();
        lblDisplaySelectCards = new Label();

        comboCountVagabond = new ComboBox<>();
        comboCountFarmer = new ComboBox<>();
        comboCountConfessor = new ComboBox<>();

        image_vagabond = new ImageView();
        image_farmer = new ImageView();
        image_confessor = new ImageView();
    }

    @Override
    public void getStart() {
        if (sceneStarted) resetScene();
        this.sceneStarted = true;
        //fill the combo boxes with selectable numbers
        for (int i = 0; i <= model.getCountPlayer(); i++) {
            comboCountVagabond.getItems().add(i);
            if (i <= 6) {
                comboCountFarmer.getItems().add(i);
            }
            if (i <= 1) {
                comboCountConfessor.getItems().add(i);
            }

        }
        if (!NewRoundViewModell.getInstance().isRoundLoop()) {
            int amount = 0;
            comboCountConfessor.getSelectionModel().select((Integer) configGetOrDefault("DEFAULT_AMOUNT_CONFESSOR"));
            amount++;
            if (amount + (Integer) configGetOrDefault("DEFAULT_AMOUNT_FARMER") <= model.getCountPlayer()) {
                comboCountFarmer.getSelectionModel().select((Integer) configGetOrDefault("DEFAULT_AMOUNT_FARMER"));
                amount += (Integer) configGetOrDefault("DEFAULT_AMOUNT_FARMER");
            } else {
                comboCountFarmer.getSelectionModel().select(model.getCountPlayer() - amount);
                amount += (Integer) configGetOrDefault("DEFAULT_AMOUNT_FARMER");
            }
            if (amount < model.getCountPlayer()) {
                comboCountVagabond.getSelectionModel().select(model.getCountPlayer() - amount);
            } else {
                comboCountVagabond.getSelectionModel().select(0);
            }

        } else {
            comboCountVagabond.getSelectionModel().select(NewRoundViewModell.getInstance().getTradeSelect().get(TRADE_VAGABOND_NAME));
            comboCountFarmer.getSelectionModel().select(NewRoundViewModell.getInstance().getTradeSelect().get(TRADE_FARMER_NAME));
            comboCountConfessor.getSelectionModel().select(NewRoundViewModell.getInstance().getTradeSelect().get(TRADE_CONFESSOR_NAME));
        }


        updateDisplay();

        image_vagabond.setImage(new Image(TRADE_SIGN_VAGABOND));
        image_farmer.setImage(new Image(TRADE_SIGN_FARMER));
        image_confessor.setImage(new Image(TRADE_SIGN_CONFESSOR));

        //add change listener to the combo boxes
        comboCountVagabond.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
        comboCountFarmer.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
        comboCountConfessor.valueProperty().addListener((observableValue, integer, t1) -> updateDisplay());
    }

    private void updateDisplay() {
        int sumCards = getSumCards();
        String displayString;
        int difference = model.getCountPlayer() + -sumCards;
        if (difference > 0) {
            displayString = String.format("Noch %d Beruf(e) auswählen", difference);
            buttonSelectTradesNext.setDisable(true);
        } else if (difference == 0) {
            displayString = TRADE_SELECT_CORRECT_AMOUNT;
            buttonSelectTradesNext.setDisable(false);
        } else {
            displayString = String.format("Noch %d Beruf(e) abwählen. ", difference * (-1));
            buttonSelectTradesNext.setDisable(true);
        }
        lblDisplaySelectCards.setText(displayString);
    }

    private int getSumCards() {
        int sumCards = 0;
        if (comboCountVagabond.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountVagabond.getSelectionModel().getSelectedItem();
        }
        if (comboCountFarmer.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountFarmer.getSelectionModel().getSelectedItem();
        }
        if (comboCountConfessor.getSelectionModel().getSelectedItem() != null) {
            sumCards += comboCountConfessor.getSelectionModel().getSelectedItem();
        }
        return sumCards;
    }

    /**
     * for testing
     *
     * @author Henrik Möhlmann
     */
    public void saveTest() {
        saveTradeCount();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void save() {
        saveTradeCount();
        next();
    }

    public void saveTradeCount() {
        model.setCountVagabond(comboCountVagabond != null
                ? comboCountVagabond.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountFarmer(comboCountFarmer != null
                ? comboCountFarmer.getSelectionModel().getSelectedItem()
                : 0);
        model.setCountConfessor(comboCountConfessor != null
                ? comboCountConfessor.getSelectionModel().getSelectedItem()
                : 0);
        model.startGame();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void next() {
        scene = GameScene.FIRST_NIGHT;
        nextScene();
    }

    public void lastSceneButton(ActionEvent actionEvent) {
        scene = GameScene.CHARACTER_AMOUNT_CHOOSING;
        nextScene();
    }

    public void resetScene() {
        comboCountVagabond.getItems().clear();
    }

    /**
     * @author Florian Müller
     * @return the selected trades a new round
     */
    public Map<String, Integer> getTradeSelectMap() {
        Map<String, Integer> tradeSelectMap = new HashMap<>();
        tradeSelectMap.put(TRADE_VAGABOND_NAME, model.getCountVagabond());
        tradeSelectMap.put(TRADE_FARMER_NAME, model.getCountFarmer());
        tradeSelectMap.put(TRADE_CONFESSOR_NAME, model.getCountConfessor());
        return tradeSelectMap;
    }

}
