package de.werwolf_spielleiter.viewModel;

import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import de.werwolf_spielleiter.character.Character;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.scene.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handel's the choosing of players for the werewolf victim and the Voting.
 *
 * @author Florian Müller
 */
public class ChoosingViewModel extends ViewModel {

    private final int WERWOLF = 1;
    private final int LYNCH = 4;
    private ArrayList<Player> lynchVictims;
    private Player player;

    @FXML
    public Button buttonSelectConfirm;
    @FXML
    private Label lblChoosing;
    @FXML
    private Button buttonNoVictimSelect;

    public ChoosingViewModel() {
    }


    /**
     * Here the lovers are marked.
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        scene = view.getCurrentScene();
        if (scene == GameScene.WEREWOLF_VICTIM_CHOOSING) {
            boolean werewolfInLove = model.getPlayerAliveList().stream().filter(x -> x.hasRole(new RoleLovers()) && x.hasRole(new RoleWerewolf())).collect(Collectors.toList()).size() > 0;
            if (werewolfInLove) {
                List<PlayerLayoutViewModel> lovers = view.getBoardLayoutViewModel().getViewModelList().stream().filter(x -> x.getPlayer().getStatus().equals("alive") && x.getPlayer().hasRole(new RoleLovers())).collect(Collectors.toList());
                lovers.forEach(x -> x.markLovers());
            }
        }
        if (scene == GameScene.LYNCH_VICTIM_CHOOSING) {
            List<PlayerLayoutViewModel> lovers = view.getBoardLayoutViewModel().getViewModelList().stream().filter(x -> x.getPlayer().getStatus().equals("alive") && x.getPlayer().hasRole(new RoleLovers())).collect(Collectors.toList());
            lovers.forEach(x -> x.markLovers());
        }
    }


    /**
     * Choosing sets from the SceneManager the Layout per default on black Background.
     * Choosing is call in ten PlayerLayoutController to react on a click on a Player
     *
     * @author Florian Müller
     */
    public void choosing() {
        scene = view.getCurrentScene();
        if (scene == GameScene.WEREWOLF_VICTIM_CHOOSING) {
            updateDisplayWerewolf();
        }
        if (scene == GameScene.LYNCH_VICTIM_CHOOSING) {
            updateDisplayLynch();
        }
    }

    /**
     * Update the the display how many players can be picked for the werewolf victim.
     *
     * @author Florian Müller
     * @author Henrik Möhlmann
     */
    private void updateDisplayWerewolf() {
        String display = "Noch x Spieler auswählen";
        Character character = null;
        int clicked = 0;
        boolean werewolfLover = model.getPlayerAliveList().stream().filter(x -> x.hasRole(new RoleLovers()) && x.hasRole(new RoleWerewolf())).collect(Collectors.toList()).size() > 0;

            for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
                if (c.getClick() && c.getPlayer().getStatus().equals("alive")) {
                    player = c.getPlayer();
                    character = player.getCharacter();
                    //prevent that werewolf in love votes for his loved one
                    if (werewolfLover) {
                        if (!(player.hasRole(new RoleWerewolf())) && !player.hasRole(new RoleLovers())) {
                            c.highlightPlayer();
                            clicked++;
                        } else {
                            c.resetClick();
                        }
                    } else {
                        if (!(player.hasRole(new RoleWerewolf()))) {
                            c.highlightPlayer();
                            clicked++;
                        } else {
                            c.resetClick();
                        }
                    }
                } else {
                    c.unhighlightPlayer();
                }
            }
            if (clicked == 0) {
                display = String.format("Es darf noch %d Spieler ausgewählt werden ", WERWOLF);
                buttonSelectConfirm.setDisable(true);
            } else if (clicked == 1) {
                display = "Es dürfen keine weiteren Spieler ausgewählt werden.";
                buttonSelectConfirm.setDisable(false);
            } else {
                int t = clicked - WERWOLF;
                display = String.format("Es müssen noch %d Spieler abgewählt werden.", t);
                buttonSelectConfirm.setDisable(true);
            }
            lblChoosing.setText(display);
    }

    /**
     * @author Florian Müller
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * switch scene
     */
    @FXML
    private void nextSceneNoVictimWerewolf() {
        // Unhighlight all players
        boardLayoutViewModel.unhiglightAllPlayers();
        //model.getPlayerAliveList().forEach(k -> k.sleep());
        boardLayoutViewModel.sleepAll();
        boardLayoutViewModel.getViewModelList().forEach(x -> x.unmarkLovers());
        scene = GameScene.GREAT_WOLF;
        nextScene();
    }

    /**
     * @author Florian Müller
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * switch scene
     */
    @FXML
    private void nextSceneVictimWerewolf() {
        for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
            if (c.getClick()) {
                c.unhighlightPlayer();
                player = c.getPlayer();
                c.resetClick();
            }
        }
        model.getVictims().setVictimWerewolf(player);
        boardLayoutViewModel.sleepAll();
        boardLayoutViewModel.getViewModelList().forEach(x -> x.unmarkLovers());
        scene = GameScene.GREAT_WOLF;
        nextScene();
    }

    /**
     * @author Florian Müller
     * switch scene
     */
    @FXML
    private void nextSceneLynch() {
        for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
            if (c.getClick()) {
                lynchVictims.add(c.getPlayer());
                c.resetClick();
            }
        }
        scene = GameScene.LYNCH_VICTIM_VOTE;
        nextScene();
    }

    /**
     * @author Florian Müller
     * Get the Victims for the voting
     */
    public ArrayList<Player> getLynchVictims() {
        return this.lynchVictims;
    }

    /**
     * Update the the display how many players can be picked for the lynch victims.
     *
     * @author Florian Müller
     */

    public void updateDisplayLynch() {
        String display = "Noch x Spieler auswählen";
        int clicked = 0;
        lynchVictims = new ArrayList<>();
        for (PlayerLayoutViewModel c : boardLayoutViewModel.getViewModelList()) {
            if (c.getClick() && c.getPlayer().getStatus().equals("alive")) {
                c.highlightPlayer();
                clicked++;
                player = c.getPlayer();
            } else {
                c.unhighlightPlayer();
                c.resetClick();
            }
            if (clicked == 0) {
                display = String.format("Es dürfen noch %d Spieler ausgewählt werden.", LYNCH);
                lblChoosing.setText(display);
                buttonSelectConfirm.setDisable(true);
            } else if (clicked < 4) {
                int t = LYNCH - clicked;
                display = String.format("Es dürfen noch %d Spieler ausgewählt werden.", t);
                lblChoosing.setText(display);
                buttonSelectConfirm.setDisable(false);
            } else if (clicked == 4) {
                display = "Es dürfen keine weiteren Spieler ausgewählt werden.";
                lblChoosing.setText(display);
                buttonSelectConfirm.setDisable(false);
            } else {
                int t = clicked - LYNCH;
                display = String.format("Es müssen noch %d Spieler abgewählt werden.", t);
                buttonSelectConfirm.setDisable(true);
            }
        }
        lblChoosing.setText(display);

    }
}


