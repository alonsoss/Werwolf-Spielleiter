package de.werwolf_spielleiter.viewModel_board;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

import static de.werwolf_spielleiter.scene.GameScene.PLAYER_AMOUNT_CHOOSING;

public class NewRoundViewModell {
    private SceneManager view;
    private Model model;
    private static NewRoundViewModell self = null;
    private int playerCount = 0;
    private boolean roundLoop = false;
    private List<TextField> textFields;
    private Map<String,Integer> tradeSelect;
    private Map<String, Integer> charAmount;
    private Map<String, Boolean> radioSelect;

    /**
     * @author Florian Müller
     * @returns An instance of this class
     */
    public static NewRoundViewModell getInstance() {
        if (self == null) {
            self = new NewRoundViewModell();
        }
        return self;
    }

    /**
     * To start a new game with same information and can by modified in the scenes
     * @param oldView get the view reference
     * @param oldModel get the model reference
     * @author Florian Müller
     */
    public void roundLoop(SceneManager oldView, Model oldModel) {
        view = oldView;
        model = oldModel;
        roundLoop = true;
        playerCount = model.getCountPlayer();
        textFields = view.getPlayerNameSceneViewModel().getPlayerNameTextFieldList();
        tradeSelect = view.getTradeSelectViewModel().getTradeSelectMap();
        charAmount = view.getCardSelectViewModel().getCharacterCountMap();
        radioSelect = view.getCardSelectViewModel().getRadioButtonStatusMap();
        ((HBox) view.getBoardLayoutViewModel().getBoard().getBottom()).getChildren().clear();
        ((VBox) view.getBoardLayoutViewModel().getBoard().getLeft()).getChildren().clear();
        ((VBox) view.getBoardLayoutViewModel().getBoard().getRight()).getChildren().clear();
        ((HBox)((VBox) view.getBoardLayoutViewModel().getBoard().getTop()).getChildren().get(1)).getChildren().clear();
        model.resetModel();

        view.switchToScene(PLAYER_AMOUNT_CHOOSING);
    }

    /**
     * @author Florian Müller
     * @return the preselection
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * @author Florian Müller
     * @return the preselection
     */
    public boolean isRoundLoop() {
        return roundLoop;
    }

    /**
     * @author Florian Müller
     * @return the preselection
     */
    public List<TextField> getTextFields() {
        return textFields;
    }

    /**
     * @author Florian Müller
     * @return the preselection
     */
    public Map<String, Integer> getCharAmount() {
        return charAmount;
    }

    /**
     * @author Florian Müller
     * @return the preselection
     */
    public Map<String, Boolean> getRadioSelect() {
        return radioSelect;
    }

    /**
     * @author Florian Müller
     * @return the preselection
     */
    public Map<String, Integer> getTradeSelect() {
        return tradeSelect;
    }
}
