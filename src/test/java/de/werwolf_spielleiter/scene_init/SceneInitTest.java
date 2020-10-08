package de.werwolf_spielleiter.scene_init;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.viewModel_trade.ConfessorViewModel;
import javafx.scene.Parent;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;

import java.io.IOException;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.SCENE_MANAGER_FXML_CONFESSOR;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class SceneInitTest {

    private Model model;
    private SceneManager view;
    private BoardLayoutViewModel boardLayoutViewModel;
    SceneInit sceneInit;

    @BeforeEach
    void setUp() {
        model = new Model();
        view = new SceneManager();
        boardLayoutViewModel = new BoardLayoutViewModel();
        sceneInit = new SceneInit(model, view, boardLayoutViewModel);
    }

    @Test
    void basicInit() {
        Pair<ViewModel, Parent> pair = sceneInit.basicInit(SCENE_MANAGER_FXML_CONFESSOR);
        assertNotNull(pair);
        ViewModel confessorViewModel = (ConfessorViewModel) pair.getKey();
        assertNotNull(confessorViewModel);
        Parent confessorScene = pair.getValue();
        assertNotNull(confessorScene);
        assertThrows(NullPointerException.class, (Executable) sceneInit.basicInit(""));
    }
}