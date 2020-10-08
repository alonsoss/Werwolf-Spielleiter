package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.viewModel_character.WitchViewModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class WitchViewModelTest {
    private final WitchViewModel witchController = new WitchViewModel();
    private final Model model = new Model();
    private final SceneManager sceneManager = new SceneManager();
    private final BoardLayoutViewModel boardLayoutController = new BoardLayoutViewModel();

    @Test
    void initialize() {
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setView() {
        assertFalse(witchController.setView(null));
        assertTrue(witchController.setView(sceneManager));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setModel() {
        assertFalse(witchController.setModel(null));
        assertTrue(witchController.setModel(model));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setBoardController() {
        assertFalse(witchController.setBoardLayoutViewModel(null));
        assertTrue(witchController.setBoardLayoutViewModel(boardLayoutController));
    }

    @Test
    void save() {
    }

    @Test
    void getStart() {
    }
}