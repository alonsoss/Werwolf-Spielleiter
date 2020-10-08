package de.werwolf_spielleiter.viewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static de.werwolf_spielleiter.scene.GameScene.LYNCH_VICTIM_CHOOSING;
import static de.werwolf_spielleiter.scene.GameScene.SHERIFF;

public class NoNightVictimsViewModel extends ViewModel{

    @FXML
    public Button buttonContinue;

    @Override
    public void getStart() {

    }

    /**
     * Jumps to next scene. If Sheriff is enable jump to Sheriff scene, else jump to Lynch_Victim_Choosing
     * @author Janik Dohrmann
     * @param actionEvent
     */
    public void forward(ActionEvent actionEvent) {
        boardLayoutViewModel.disableConfessorMenu();
        if (!model.isSheriff()) {
            scene = LYNCH_VICTIM_CHOOSING;
        } else {
            scene = SHERIFF;
        }
        nextScene();
    }
}
