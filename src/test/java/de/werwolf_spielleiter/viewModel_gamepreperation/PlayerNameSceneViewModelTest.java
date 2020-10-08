package de.werwolf_spielleiter.viewModel_gamepreperation;

import de.werwolf_spielleiter.SceneManager;
import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.scene.GameScene;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class PlayerNameSceneViewModelTest {
    public final PlayerNameSceneViewModel testController = new PlayerNameSceneViewModel();
    public final Model testModel = new Model();
    public final SceneManager testView = new SceneManager();

    /**
     * Init controllers for testing
     * @author Eric De Ron
     */
    @BeforeEach
    void initTest() {
        testController.setModel(testModel);
        testController.setView(testView);
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setView() {
        assertTrue(testController.setView(testView));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setModel() {
        assertTrue(testController.setModel(testModel));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setViewNullTest() {
        assertFalse(testController.setView(null));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setModelNullTest() {
        assertFalse(testController.setModel(null));
    }

    /**
     * Test getStart by checking if the controller has the
     * same amount of text field as players given by the model
     * and if the text fields have the right prompt text
     * @author Eric De Ron
     * @param testNumber A test number loaded from resources
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ControllerTestSource.csv")
    void getStart(int testNumber) {
        testModel.initPlayerList(testNumber);
        testController.getStart();

        // For the amount of given players there should be the same amount of text fields
        // Test resources can also contain negative numbers but a list can't be smaller then 0
        assertEquals(testController.getPlayerNameTextFieldList().size(), Math.max(testModel.getCountPlayer(), 0));

        // Every text field should contain "Spieler " + some index
        for (int i = 0; i < testController.getPlayerNameTextFieldList().size(); i++) {
            assertEquals(testController.getPlayerNameTextFieldList().get(i).getPromptText(),
                    "Spieler " + (i + 1));
        }
    }

    /**
     * Test if refillTextFields keeps the same amount
     * of text fields if run again, if more players are added
     * the text fields list grows to the amount and if players
     * are removed if the fields list shrinks to the amount
     * @author Eric De Ron
     * @param testNumber A test number loaded from resources
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ControllerTestSource.csv")
    void refillTextFields(int testNumber) {
        testModel.initPlayerList(testNumber);

        testController.getStart();
        testController.refillTextFields();
        assertEquals(Math.max(testNumber, 0), testController.getPlayerNameTextFieldList().size());

        testModel.initPlayerList(testNumber + testNumber);
        testController.getStart();
        testController.refillTextFields();
        assertEquals(Math.max(testNumber + testNumber, 0), testController.getPlayerNameTextFieldList().size());

        testModel.initPlayerList(testNumber - 1);
        testController.getStart();
        testController.refillTextFields();
        assertEquals(Math.max(testNumber - 1, 0), testController.getPlayerNameTextFieldList().size());
    }

    /**
     * Text if resetTextBoxesButton resets all
     * text fields after they where filled
     * @author Eric De Ron
     * @param testNumber A test number loaded from resources
     *                   used as text for text fields
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ControllerTestSource.csv")
    void resetTextBoxesButton(int testNumber) {
        testModel.initPlayerList(testNumber);
        for (TextField textField : testController.getPlayerNameTextFieldList()) {
            textField.setText(testNumber + "");
        }
        testController.resetTextBoxesButton(null);
        for (TextField textField : testController.getPlayerNameTextFieldList()) {
            assertEquals("", textField.getText());
        }
    }

    /**
     * Test nextSceneButton by checking if players are created in
     * the given model, if they have the right name and if
     * the method switches to the next scene
     * @author Eric De Ron
     * @param testNumber A test number loaded from resources
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ControllerTestSource.csv")
    void nextSceneButton(int testNumber) {
        testModel.initPlayerList(testNumber);
        // Use unused index to prevent fxml load errors
        testController.getStart();
        // Fxml isn't loaded
        assertThrows(NullPointerException.class, () -> testController.nextSceneButton(null));
        // Every text field should be empty hence every name should be "Spieler " + some index
        for (int i = 0; i < testModel.getPlayerList().size(); i++) {
            assertEquals(testModel.getPlayerList().get(i).toString(), new Player("Spieler " + (i + 1)).toString());
        }
        // Test resources can also contain negative numbers but a list can't be smaller then 0
        assertEquals(testModel.getPlayerList().size(), Math.max(testNumber, 0));
        // Next scene should be character amount choosing
        assertEquals(testView.getCurrentScene(), GameScene.CHARACTER_AMOUNT_CHOOSING);
    }

    /**
     * Test if one player is created
     * @author Janik Dohrmann
     * @author Eric De Ron
     */
    @Test
    void nextSceneButtonTest() {
        testModel.initPlayerList(1);
        // Use unused index to prevent fxml load errors
        testController.getStart();

        List<TextField> testFieldList = testController.getPlayerNameTextFieldList();
        testFieldList.get(0).setText("Max");

        // Fxml isn't loaded
        assertThrows(NullPointerException.class, () -> testController.nextSceneButton(null));

        assertEquals("Max", testModel.getPlayerList().get(0).toString());
    }

    /**
     * Test if all list of the controller
     * are reset after running resetScene
     * @param testNumber A test number loaded from resources
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ControllerTestSource.csv")
    void resetScene(int testNumber) {
        testModel.initPlayerList(testNumber);
        testController.getStart();
        // This will fill the player list
        assertThrows(NullPointerException.class, () -> testController.nextSceneButton(null));
        testController.resetScene();
        assertTrue(testController.getPlayerNameTextFieldList().isEmpty());
        assertTrue(testModel.getPlayerList().isEmpty());
    }
}
