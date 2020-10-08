package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel_board.NewRoundViewModell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import static de.werwolf_spielleiter.config.ConfigLoader.configGetOrDefault;

public class PlayerCountSceneViewModel extends ViewModel {


    /* Variables */
    // Track amount of set players by slider
    private int playerCount = (Integer)configGetOrDefault("SLIDER_INIT_DEFAULT_VALUE");
    // Store last selected player count
    private int lastSelectedPlayerCount;

    /* Sliders */
    // Slider for selecting amount of players
    @FXML
    private Slider sliderPlayerCount;

    /* Labels */
    // Label for showing amount of selected players
    @FXML
    private Label lblPlayerCount;

    /* Buttons */
    // Button "Weiter" to go to next scene
    @FXML
    private Button buttonNextPlayerCountSelect;

    /**
     * for testing
     *
     * @author Henrik Möhlmann
     */
    public void initTest() {
        sliderPlayerCount = new Slider();
        lblPlayerCount = new Label();
        buttonNextPlayerCountSelect = new Button();

        initialize();
    }

    /**
     * @author Eric De Ron
     * Function which gets loaded up on Controller object
     * creation in fxml, used to set up controls of the scene
     */
    @FXML
    public void initialize() {
        // Overwrite placeholder values for slider in fxml
        sliderPlayerCount.setMin((Integer)configGetOrDefault("SLIDER_MIN"));
        sliderPlayerCount.setMax((Integer)configGetOrDefault("SLIDER_MAX"));
        if(!NewRoundViewModell.getInstance().isRoundLoop()) {
            sliderPlayerCount.setValue((Integer) configGetOrDefault("SLIDER_INIT_DEFAULT_VALUE"));
        }else {
            sliderPlayerCount.setValue(NewRoundViewModell.getInstance().getPlayerCount());
        }
        sliderPlayerCount.setBlockIncrement((Integer)configGetOrDefault("SLIDER_BLOCK_INCREMENT"));
        sliderPlayerCount.setShowTickMarks((Boolean)configGetOrDefault("SLIDER_SHOW_TICK_MARKS"));
        sliderPlayerCount.setShowTickLabels((Boolean)configGetOrDefault("SLIDER_SHOW_TICK_LABELS"));
        sliderPlayerCount.setMajorTickUnit((Integer)configGetOrDefault("SLIDER_MAJOR_TICK_UNITS"));
        sliderPlayerCount.setMinorTickCount((Integer)configGetOrDefault("SLIDER_MINOR_TICK_UNITS"));

        // Set lblPlayerCount to default value
        lblPlayerCount.setText(playerCount + "");

        // If previously select the stored
        // value as default
        if (lastSelectedPlayerCount != 0) {
            sliderPlayerCount.setValue(lastSelectedPlayerCount);
            lblPlayerCount.setText(lastSelectedPlayerCount + "");
        }

        // Update lblPlayerCount for every changed slider value
        sliderPlayerCount.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.playerCount = newValue.intValue();
            // Update label value in view
            lblPlayerCount.setText(playerCount + "");
        });
    }

    /**
     * Save the data.
     *
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    public void save() {
        // Save selected amount in case
        // the player uses the back button
        lastSelectedPlayerCount = playerCount;
        // Set amount of players in model and init player list
        model.initPlayerList(playerCount);
    }

    /**
     * @author Eric De Ron
     * Function which is used by a button
     * to load the next scene
     */
    @FXML
    public void nextSceneButton() {
        save();
        // Go to next scene
        scene = GameScene.PLAYER_NAME_SELECTING;
        nextScene();
    }

    @Override
    public void getStart() {

    }
}
