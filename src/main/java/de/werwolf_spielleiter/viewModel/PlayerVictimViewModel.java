package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.Role;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleSheriff;
import de.werwolf_spielleiter.role.RoleWildChild;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.trade.TradeConfessor;
import de.werwolf_spielleiter.trade.Trades;
import de.werwolf_spielleiter.victim.Victim;
import de.werwolf_spielleiter.victim.Victims;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import de.werwolf_spielleiter.viewModel_character.HunterViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class PlayerVictimViewModel extends ViewModel {

    /* Variables */
    // Current game phase
    private Phase phase;
    // Current victims object
    private Victims playerVictims;
    // List of current victims
    List<Victim> playerVictimList;

    @FXML
    private GridPane gridPlayerVictim;

    @FXML
    // Title of the scene
    private Label lblVarSceneTitle;

    // Button to move to the next scene
    private Button buttonNextScene;

    // Controller for hunter logic
    private HunterViewModel hunterViewModel;

    /* Test variables */
    private Player testPlayer;

    public PlayerVictimViewModel() {
        this.hunterViewModel = new HunterViewModel();
    }

    /**
     * Enum to define the game phase
     * NIGHT = Show victims of the night
     * DAY = Show victims of the day/lynch
     */
    public enum Phase {
        NIGHT, DAY
    }

    /**
     * Method to initialize all shown victim infos
     * in a grid, can be used to display victims
     * of the night and day
     *
     * @author Eric De Ron
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        this.playerVictims = model.getVictims();
        int countLoversAsVictims = 0;
        Player lover1 = null;

        //<test>
        // If these elements aren't initialized we might run this
        // method in a test so set dummy elements
        if (gridPlayerVictim == null && this.lblVarSceneTitle == null) {
            this.gridPlayerVictim = new GridPane();
            ;
            this.lblVarSceneTitle = new Label();
            // Test player to show another player in view
            // Needs to be set by the tested class
            playerVictims.setVictimWerewolf(testPlayer);
            playerVictims.setVictimLynch(testPlayer);
        }

        //</test>

        // Setup scene
        setupVictimGrid();
        setupNextButton();

        this.playerVictimList = new ArrayList<>();

        if (phase == Phase.NIGHT) {
            // Add victims of the night here
            // Werewolf victim
            if (playerVictims.getVictimWerewolf().isSet()
                    && !playerVictims.getVictimWerewolf().isHealed()
                    && !playerVictims.getVictimWerewolf().isInfected()) {
                playerVictimList.add(playerVictims.getVictimWerewolf());
                if (playerVictims.getVictimWerewolf().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimWerewolf().getPlayer();
                }
            }
            //big bad wolf victim
            if (playerVictims.getVictimBigBadWolf().isSet()) {
                playerVictimList.add(playerVictims.getVictimBigBadWolf());
                if (playerVictims.getVictimBigBadWolf().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimBigBadWolf().getPlayer();
                }
            }
            //white werewolf victim
            if (playerVictims.getVictimWhiteWerewolf().isSet()) {
                playerVictimList.add(playerVictims.getVictimWhiteWerewolf());
                if (playerVictims.getVictimWhiteWerewolf().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimWhiteWerewolf().getPlayer();
                }
            }
            // Witch victim
            if (playerVictims.getVictimWitch().isSet()) {
                playerVictimList.add(playerVictims.getVictimWitch());
                if (playerVictims.getVictimWitch().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimWitch().getPlayer();
                }
            }
            // Hunter victim
            if (playerVictims.getVictimHunter().isSet()) {
                playerVictimList.add(playerVictims.getVictimHunter());
                if (playerVictims.getVictimHunter().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimHunter().getPlayer();
                }
            }
            //eventually set lover victim
            if (countLoversAsVictims == 1 && !playerVictims.getVictimLover().isSet()) {
                Player finalLover = lover1;
                List<Player> secondLover = model.getPlayerAliveList().stream().filter(x -> x.hasRole(new RoleLovers()) && x != finalLover).collect(Collectors.toList());
                if (secondLover.size() > 0) {
                    Player lover2 = secondLover.get(0);
                    playerVictims.setVictimLover(lover2);
                }
            }
            //add lover victim
            if (playerVictims.getVictimLover().isSet()) {
                playerVictimList.add(playerVictims.getVictimLover());
            }

            // Title of the scene
            lblVarSceneTitle.setText(PLAYER_VICTIM_NIGHT);

            // Night is over with this scene
            model.setNight(false);
        } else if (phase == Phase.DAY) {
            // Add lynch victim
            if (playerVictims.getVictimLynch().isSet()) {
                playerVictimList.add(playerVictims.getVictimLynch());
                if (playerVictims.getVictimLynch().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimLynch().getPlayer();
                }
            }
            // Add hunter victim
            if (playerVictims.getVictimHunter().isSet()) {
                playerVictimList.add(playerVictims.getVictimHunter());
                if (playerVictims.getVictimHunter().getPlayer().hasRole(new RoleLovers())) {
                    countLoversAsVictims++;
                    lover1 = playerVictims.getVictimHunter().getPlayer();
                }
            }
            //eventually set lover victim
            if (countLoversAsVictims == 1 && !playerVictims.getVictimLover().isSet()) {
                Player finalLover = lover1;
                List<Player> secondLover = model.getPlayerAliveList().stream().filter(x -> x.hasRole(new RoleLovers()) && x != finalLover).collect(Collectors.toList());
                if (secondLover.size() > 0) {
                    Player lover2 = secondLover.get(0);
                    playerVictims.setVictimLover(lover2);
                }
            }
            //add lover victim
            if (playerVictims.getVictimLover().isSet()) {
                playerVictimList.add(playerVictims.getVictimLover());
            }
            // sheriff is victim
            if (playerVictimList.stream().filter(x -> x.getPlayer().hasRole(new RoleSheriff())).collect(Collectors.toList()).size() > 0) {
                model.setNextSheriffVote(true);
            }
            // Title of the scene
            lblVarSceneTitle.setText(PLAYER_VICTIM_DAY);
        }
        // Fill grid with victims of this phase
        fillVictimGrid();
    }

    public void fillVictimGrid() {
        // Track grid column and row to use at as
        // index to add the "Weiter" button
        int gridColumn;
        int gridRow = 0;
        // If the next button should be displayed
        boolean displayNextButton = true;

        // Player info shown on the grid
        Victim victimPlayer;
        ImageView imv;
        Label lblPlayerName;
        Label lblPlayerDeathReason;
        Label lblPlayerRoles;

        for (gridColumn = 0; gridColumn < playerVictimList.size(); gridColumn++) {
            gridRow = 0;
            // Get the current victim of the night
            victimPlayer = playerVictimList.get(gridColumn);

            // Get the fraction image of the player
            imv = new ImageView();
            imv.setImage(victimPlayer.getPlayer().getCharacterCardFront());
            imv.setPreserveRatio(true);
            imv.setFitWidth(75);
            imv.setFitHeight(75);

            // Label to show the name of the player
            lblPlayerName = new Label("Spielername:\n" + victimPlayer.getPlayer().getName());
            setupLabel(lblPlayerName);

            // Label to show the death reason of the player
            lblPlayerDeathReason = new Label("Todesursache:\n" + victimPlayer.getDeathReason());
            setupLabel(lblPlayerDeathReason);

            // Get all roles of the player
            StringBuilder rolesStringBuilder = new StringBuilder();
            for (Role playerRole : victimPlayer.getPlayer().getRolesList()) {
                rolesStringBuilder.append(playerRole).append(", ");
            }
            if (!rolesStringBuilder.toString().equals("")) {
                rolesStringBuilder.replace(rolesStringBuilder.length() - 2, rolesStringBuilder.length(), "");
            }
            // AK: Show all roles of the player/victim
            // Label to show all roles of the player
            lblPlayerRoles = new Label("Rollen: " +
                    (rolesStringBuilder.toString().equals("") ? PLAYER_VICTIM_NO_ROLE : rolesStringBuilder.toString()));
            lblPlayerRoles.setWrapText(true);
            // To Prevent NullPointerException it PlayerVictimViewModelTest wrap setMaxWidth in try-catch
            try {
                lblPlayerRoles.setMaxWidth(boardLayoutViewModel.getCenterWidth() / playerVictimList.size());
            } catch (NullPointerException e) {
                System.err.println("Could not calculate width!");
                lblPlayerRoles.setMaxWidth(100);
            }
            setupLabel(lblPlayerRoles);

            // Place the fraction image and the player info labels in the grid
            gridPlayerVictim.add(imv, gridColumn, gridRow++);
            gridPlayerVictim.add(lblPlayerName, gridColumn, gridRow++);
            gridPlayerVictim.add(lblPlayerDeathReason, gridColumn, gridRow++);
            gridPlayerVictim.add(lblPlayerRoles, gridColumn, gridRow++);

            if (victimPlayer.getPlayer().getCharacter() instanceof CharHunter &&
                    !playerVictims.getVictimHunter().isSet()) {
                // Disable next button until there is a victim of the hunter
                displayNextButton = false;
                // Let hunter controller add his
                // logic to the grid
                hunterViewModel.setModel(model);
                hunterViewModel.setPlayerVictimViewModel(this);
                hunterViewModel.setGridHunterVictim(gridPlayerVictim);
                hunterViewModel.getStart();
                hunterViewModel.addHunterControls(gridColumn, gridRow);
                hunterViewModel.setHunterPhase(true);
                model.setHunterPhase(true);
            } else if (victimPlayer.getPlayer().hasRole(new RoleSheriff())) {
                if (model.isPlayWithTrade() && Trades.getInstance().getFarmerList().size() <= 1) {
                    // no selecting next Sheriff because sheriff was last farmer
                    displayNextButton = true;
                    if (model.getSheriffWasVote() && view.getSheriffViewModel().isNextSheriffControlInit()) {
                        view.getSheriffViewModel().removeNextSheriffControl();
                    }

                } else if (!model.isPlayWithTrade() && model.getPlayerAliveList().size() == 0) {
                    // no selecting next Sheriff because all players are dead

                    displayNextButton = true;

                } else {
                    // next Sheriff has to be selected
                    displayNextButton = false;
                    for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
                        if (c.getPlayer().hasRole(new RoleSheriff())) {
                            c.unmarkSheriff();
                        }
                    }
                    model.setNextSheriffVote(true);
                    view.getSheriffViewModel().setModel(model);
                    view.getSheriffViewModel().setPlayerVictimViewModel(this);
                    view.getSheriffViewModel().setGridNextSheriff(gridPlayerVictim);
                    view.getSheriffViewModel().setupNextSheriff();
                    view.getSheriffViewModel().addSheriffControls(gridColumn, gridRow);
                }

            } else if (model.getIdolWildChild() != null && model.getIdolWildChild().equals(victimPlayer.getPlayer())) {
                Player playerWildChild = model.getFirstPlayerWithRole(new RoleWildChild());
                // If wild child idol dies and wild child is alive
                if (playerWildChild != null) {
                    model.getFraction().changeWildChildToWerewolf(playerWildChild);
                }
            }

            Player diePlayer = victimPlayer.getPlayer();
            //die player in view
            boardLayoutViewModel.getViewModelList().stream()
                    .filter(x -> x.getPlayer() == diePlayer)
                    .forEach(x -> x.die());
            // AK: Player/victim must die in software
            if (model.isPlayWithTrade() && diePlayer.getTrade().isSameClass(new TradeConfessor())) {
                boardLayoutViewModel.disableConfessorMenu();
            }
            model.die(victimPlayer.getPlayer());
        }

        // Reset hunter victim after it is displayed
        if (playerVictims.getVictimHunter().isSet()) playerVictims.getVictimHunter().reset();

        // if all players are dead switch to the winner scene without selecting new Sheriff
        if (model.getPlayerAliveList().size() == 0) {
            displayNextButton = true;
            if (model.getSheriffWasVote() && view.getSheriffViewModel().isNextSheriffControlInit()) {
                view.getSheriffViewModel().removeNextSheriffControl();
            }
        }
        // i - 1 because the loop does ++ before it stops but we want to
        // add the button to the current last column
        if (displayNextButton) gridPlayerVictim.add(buttonNextScene, gridColumn - 1, gridRow);
    }


    /**
     * @param label Label to apply the style
     * @author Eric De Ron
     */
    public void setupLabel(Label label) {
        label.getStyleClass().add(PLAYER_VICTIM_STYLE_LBL);
    }

    /**
     * Method to style next button
     *
     * @author Eric De Ron
     */
    public void setupNextButton() {
        this.buttonNextScene = new Button(PLAYER_VICTIM_NEXT_BUTTON);
        buttonNextScene.setOnAction(this::nextSceneButton);
    }

    /**
     * Setup grid variables and clear old
     * used cells if this scene was used previously
     *
     * @author Eric De Ron
     */
    public void setupVictimGrid() {
        // Set gird gap for all cells
        gridPlayerVictim.setHgap(5);
        gridPlayerVictim.setVgap(5);

        gridPlayerVictim.getChildren().clear();
    }

    /**
     * @param phase Current phase of the game
     * @return true if not null
     * @author Eric De Ron
     */
    public boolean setPhase(Phase phase) {
        this.phase = phase;
        return phase != null;
    }

    /**
     * Method to switch to the next scene after showing
     * the victim of the phase
     *
     * @param event Event of the called action listener
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * @author Florian Müller
     */
    public void nextSceneButton(ActionEvent event) {
        model.getVictims().resetLovers();
        if (boardLayoutViewModel.showWinner() == null) {
            if (phase == Phase.NIGHT) {
                if (model.isSheriff()) {
                    // Sheriff scene
                    boardLayoutViewModel.disableConfessorMenu();
                    scene = GameScene.SHERIFF;
                    nextScene();
                } else if (phase == Phase.NIGHT) {
                    boardLayoutViewModel.disableConfessorMenu();
                    scene = GameScene.LYNCH_VICTIM_CHOOSING;
                    nextScene();
                }
            } else {
                boardLayoutViewModel.disableConfessorMenu();
                // Next night scene
                scene = GameScene.NEW_NIGHT;
                nextScene();
            }

        }
    }

    /**
     * @param testPlayer Player to be set by a test class
     *                   to test methods of this class
     * @author Eric De Ron
     */
    public void setTestPlayer(Player testPlayer) {
        this.testPlayer = testPlayer;
    }

    /**
     * @return Return grid pane with currently
     * placed objects on it
     * @author Eric De Ron
     */
    public GridPane getGridPlayerVictim() {
        return gridPlayerVictim;
    }

    /**
     * @return Return currently set title
     * of the scene
     * @author Eric De Ron
     */
    public Label getLblVarSceneTitle() {
        return lblVarSceneTitle;
    }

    /**
     * @return Hunter controller for hunter logic
     * @author Eric De Ron
     */
    public HunterViewModel getHunterViewModel() {
        return hunterViewModel;
    }

    public Phase getPhase() {
        return phase;
    }
}
