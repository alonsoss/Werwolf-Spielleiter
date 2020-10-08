package de.werwolf_spielleiter.viewModel_trade;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfessorViewModelTest {
    private Model model;
    private SceneManager sceneManager;
    private ConfessorViewModel viewModel;

    @BeforeEach
    void init() {
        model = new Model();
        sceneManager = new SceneManager();
        viewModel = new ConfessorViewModel();
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