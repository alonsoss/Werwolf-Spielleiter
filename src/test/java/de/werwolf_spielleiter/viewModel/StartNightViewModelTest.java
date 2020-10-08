package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StartNightViewModelTest {
    private Model model;
    private SceneManager sceneManager;
    private StartNightViewModel controller;

    @BeforeEach
    void init() {
        model = new Model();
        sceneManager = new SceneManager();
        controller = new StartNightViewModel();
    }

    @Test
    void setView() {
        assertTrue(controller.setView(sceneManager));
    }

    @Test
    void setModel() {
        assertTrue(controller.setModel(model));
    }

}