package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.viewModel_character.SeerViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeerViewModelTest {
    private Model model;
    private SceneManager sceneManager;
    private SeerViewModel viewModel;

    @BeforeEach
    void init() {
        model = new Model();
        sceneManager = new SceneManager();
        viewModel = new SeerViewModel();
    }

    @Test
    void setView() {
        assertTrue(viewModel.setView(sceneManager));
    }

    @Test
    void setModel() {
        assertTrue(viewModel.setModel(model));
    }

}