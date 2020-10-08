package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoversViewModel extends ViewModel {
    @FXML
    Button buttonSleep;

    @Override
    public void getStart() {

    }

    public void sleep() {
        boardLayoutViewModel.sleepAll();
        next();
    }

    public void next() {
        scene = GameScene.SEER;
        nextScene();
    }
}
