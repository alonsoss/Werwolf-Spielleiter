package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.NewRoundViewModell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class PlayerNameSceneViewModel extends ViewModel {
    /* Variables */
    private int amountOfPlayers;
    // Index of the fillTextBoxes method
    private int textFieldIndex = 0;
    // ++ in loop when loop textFieldIndex % maxTextFieldRow = 0
    private int columnIndex = 0;
    // Amount of max textfield per row
    private int maxTextFieldRow = 16;

    @FXML
    // GridPane bound from fxml
    private GridPane gridTextBox;

    // Reset all text fields button
    private Button buttonResetTextBoxes;

    // Next scene button
    private Button buttonNextScene;

    // Last scene button
    private Button buttonLastScene;

    // List of all text fields in the grid pane
    private final List<TextField> playerNameTextFieldList = new ArrayList<>();
    // Player list from the model
    private List<Player> playerList;

    /**
     * for testing
     *
     * @author Henrik Möhlmann
     */
    public void testInit() {
        this.playerList = model.getPlayerList();
        this.amountOfPlayers = model.getCountPlayer();
        setupButton();
    }

    /**
     * Method to initialize used list
     * and place all text fields in the
     * grid pane of the scene, also add the
     * next button
     *
     * @author Eric De Ron
     */
    public void getStart() {
        // For testing purposes bc. fxml element isn't bound
        if (gridTextBox == null) gridTextBox = new GridPane();


        // If the screen has a low resolution put a smaller
        // amount of text fields in a column
        if (Screen.getPrimary().getBounds().getHeight() < 1080) this.maxTextFieldRow = 13;

        this.playerList = model.getPlayerList();
        this.amountOfPlayers = model.getCountPlayer();
        setupButton();

        // Check if the scene was previously used
        if (playerNameTextFieldList.isEmpty()) {
            fillTextFields();
        } else {
            // Use old already filled text fields
            refillTextFields();
        }

        // Add all buttons to the last row of the grid
        // in this order (Reset, Zurück, Weiter)
        GridPane.setColumnIndex(buttonResetTextBoxes, columnIndex);
        GridPane.setRowIndex(buttonResetTextBoxes, maxTextFieldRow - 1);
        GridPane.setColumnIndex(buttonLastScene, columnIndex + 1);
        GridPane.setRowIndex(buttonLastScene, maxTextFieldRow - 1);
        GridPane.setColumnIndex(buttonNextScene, columnIndex + 2);
        GridPane.setRowIndex(buttonNextScene, maxTextFieldRow - 1);
        gridTextBox.getChildren().add(buttonResetTextBoxes);
        gridTextBox.getChildren().add(buttonLastScene);
        gridTextBox.getChildren().add(buttonNextScene);
    }

    /**
     * Fills gridTextBox with empty
     * generic text fields
     *
     * @author Eric De Ron
     */
    public void fillTextFields() {
        TextField textField;
        while (textFieldIndex < amountOfPlayers) {
            textField = new TextField();
            // Index starts at 0 hence textFieldIndex + 1
            textField.setPromptText(PLAYER_NAME_DEFAULT_NAME + (textFieldIndex + 1));
            addTextFieldToGrid(textField);
        }
    }

    /**
     * Method used for testing to set up the text fields
     *
     * @author Henrik Möhlmann
     */
    public void fillTextFieldsTestMethod() {
        TextField textField;
        while (textFieldIndex < amountOfPlayers) {
            textField = new TextField();
            // Index starts at 0 hence textFieldIndex + 1
            textField.setPromptText(PLAYER_NAME_DEFAULT_NAME + (textFieldIndex + 1));
            playerNameTextFieldList.add(textField);
            textFieldIndex++;
        }
    }

    /**
     * Refills gridTextBox with previous
     * used text fields (e.g. after using last button)
     *
     * @author Eric De Ron
     */
    public void refillTextFields() {
        //
        List<TextField> tmpTextFieldList;
        if (NewRoundViewModell.getInstance().isRoundLoop()) {
            tmpTextFieldList = NewRoundViewModell.getInstance().getTextFields();

        } else {

            tmpTextFieldList = new ArrayList<>(this.playerNameTextFieldList);
        }
        resetScene();
        while (textFieldIndex < amountOfPlayers) {
            if (textFieldIndex < tmpTextFieldList.size()) {
                // Modulo every maxTextFieldRow to stay in grid bounds
                TextField textField = tmpTextFieldList.get(textFieldIndex);
                addTextFieldToGrid(textField);
            } else {
                break;
            }
        }
        fillTextFields();
    }

    /**
     * @param textField Text field which should be added to gridTextBox
     * @author Eric De Ron
     */
    public void addTextFieldToGrid(TextField textField) {
        // Modulo every maxTextFieldRow to stay in grid bounds
        GridPane.setRowIndex(textField, textFieldIndex % maxTextFieldRow);
        GridPane.setColumnIndex(textField, columnIndex);
        gridTextBox.getChildren().add(textField);
        playerNameTextFieldList.add(textField);
        textFieldIndex++;
        if (textFieldIndex % maxTextFieldRow == 0) columnIndex++;
    }

    /**
     * Method to style next button
     *
     * @author Eric De Ron
     * @author Matthias Hinrichs
     */
    public void setupButton() {
        this.buttonResetTextBoxes = new Button(PLAYER_NAME_RESET_BUTTON);
        this.buttonNextScene = new Button(PLAYER_NAME_NEXT_BUTTON);
        this.buttonLastScene = new Button(PLAYER_NAME_LAST_BUTTON);
        buttonResetTextBoxes.setOnAction(this::resetTextBoxesButton);
        buttonNextScene.setOnAction(this::nextSceneButton);
        buttonLastScene.setOnAction(this::lastSceneButton);
        buttonNextScene.setDefaultButton(true);
    }

    /**
     * Method to save the names of the players.
     *
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    public void saveNames() {
        for (int i = 0; i < playerNameTextFieldList.size(); i++) {
            if (playerNameTextFieldList.get(i).getText().trim().equals("")) {
                this.playerList.add(new Player(PLAYER_NAME_DEFAULT_NAME + (i + 1)));
            } else {
                this.playerList.add(new Player(playerNameTextFieldList.get(i).getText()));
            }
        }
    }

    /**
     * Get the input texts of all text fields
     * in the playerNameTextFieldList and use them
     * to create all named players in the playerList
     * of the model, after that switch to the next scene
     *
     * @author Eric De Ron
     */
    public void nextSceneButton(ActionEvent actionEvent) {
        saveNames();
        // Go to next scene
        scene = GameScene.CHARACTER_AMOUNT_CHOOSING;
        nextScene();
    }

    /**
     * Moves view to the previous scene
     *
     * @author Eric De Ron
     */
    public void lastSceneButton(ActionEvent actionEvent) {
        scene = GameScene.PLAYER_AMOUNT_CHOOSING;
        nextScene();
    }

    /**
     * Resets all text fields in this scene
     *
     * @param actionEvent Event of the called action listener
     * @author Eric De Ron
     */
    public void resetTextBoxesButton(ActionEvent actionEvent) {
        for (TextField textField : playerNameTextFieldList) {
            textField.setText("");
        }
    }

    /**
     * @return List of text fields used in the controller
     * @author Eric De Ron
     */
    public List<TextField> getPlayerNameTextFieldList() {
        return playerNameTextFieldList;
    }

    /**
     * Resets all changeable variables of the scene
     *
     * @author Eric De Ron
     */
    public void resetScene() {
        this.playerList.clear();
        gridTextBox.getChildren().clear();
        this.playerNameTextFieldList.clear();
        textFieldIndex = 0;
        columnIndex = 0;
    }
}
