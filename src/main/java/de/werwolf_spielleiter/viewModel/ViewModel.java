package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;

public abstract class ViewModel {
    protected SceneManager view;
    protected Model model;
    protected GameScene scene;
    protected BoardLayoutViewModel boardLayoutViewModel;


    /**
     * Is called by the main application to give reference to itself
     *
     * @param mainView
     * @return
     * @author Florian Müller
     */
    public boolean setView(SceneManager mainView) {
        view = mainView;
        return mainView != null;
    }

    /**
     * Set main model for viewModel
     *
     * @param mainModel
     * @return
     * @author Florian Müller
     */
    public boolean setModel(Model mainModel) {
        model = mainModel;
        return mainModel != null;
    }

    /**
     * Set BoardLayoutViewModel
     * @author Janik Dohrmann
     * @param boardLayoutViewModel a BoardLayoutViewModel
     * @return true if BoardLayoutViewModel is set
     */
    public boolean setBoardLayoutViewModel(BoardLayoutViewModel boardLayoutViewModel) {
        this.boardLayoutViewModel = boardLayoutViewModel;
        return boardLayoutViewModel != null;
    }

    /**
     * Abstract methode for the start of some scenes
     * @author Florian Müller
     */

    public abstract void getStart();

    /**
     * Methode to get to the next scene
     * @author Florian Müller
     */
    public void nextScene() {
        view.switchToScene(scene);
    }
}
