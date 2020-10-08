package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.scene.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * This class handles the objects of the startNightPane.
 * @author Henrik Möhlmann
 */
public class StartNightViewModel extends ViewModel {


    @FXML
    private Button buttonNightBegin;


    /**
     * Method used by the button to next scene.
     * It starts the night in the model.
     * After that, the next scene is triggered.
     * @author Henrik Möhlmann
     */
    public void beginNight() {
        model.startNight();
        scene = GameScene.THIEF;
        nextScene();
    }

    @Override
    public void getStart() {

    }

}
