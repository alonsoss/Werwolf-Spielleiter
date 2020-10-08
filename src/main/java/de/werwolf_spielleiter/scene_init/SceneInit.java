package de.werwolf_spielleiter.scene_init;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Pair;

import java.io.IOException;

public class SceneInit {

    private final Model model;
    private final SceneManager view;
    private final BoardLayoutViewModel boardLayoutViewModel;

    /**
     * The constructor
     * @author Janik Dohrmann
     * @param model the main model
     * @param view the Scene Manager
     * @param boardLayoutViewModel the BoardLayoutViewModel
     */
    public SceneInit(Model model, SceneManager view, BoardLayoutViewModel boardLayoutViewModel) {
        this.model = model;
        this.view = view;
        this.boardLayoutViewModel = boardLayoutViewModel;
    }

    /**
     * @author Janik Dohrmann
     * @param path the path of the FXML File
     * @return A Tuple of ViewModel and Parent
     */
    public Pair<ViewModel, Parent> basicInit(String path){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
        try {
            ViewModel viewModel;
            Parent scene;
            scene = fxmlLoader.load();
            viewModel = fxmlLoader.getController();
            viewModel.setView(view);
            viewModel.setModel(model);
            viewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            return new Pair<>(viewModel, scene);
        } catch (IOException e) {
            System.err.println("Unable to load scene");
            return null;
        }
    }
}
