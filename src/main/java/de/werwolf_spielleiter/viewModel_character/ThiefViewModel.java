package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.character.CharBigBadWolf;
import de.werwolf_spielleiter.character.CharGreatWolf;
import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.character.CharWhiteWerewolf;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleThief;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.stream.Collectors;

/**
 * @author Henrik Möhlmann
 */
public class ThiefViewModel extends ViewModel {

    @FXML
    ImageView imageViewCard1;
    @FXML
    ImageView imageViewCard2;
    @FXML
    Button buttonSleep;
    @FXML
    Label lblInfo;

    /**
     * for testing
     * @author Henrik Möhlmann
     */
    public void testInit() {
        imageViewCard1 = new ImageView();
        imageViewCard2 = new ImageView();
        buttonSleep = new Button();
        lblInfo = new Label();
    }

    /**
     * @author Henrik Möhlmann
     */
    @Override
    public void getStart() {
        //if new werewolf is implemented, the buttonEnabledTest and the buttonDisabledTest must be updated
        //both version (with and without trade) for each
        if ((model.getCardThiefList().get(0) instanceof CharWerewolf
                || model.getCardThiefList().get(0) instanceof CharGreatWolf
                || model.getCardThiefList().get(0) instanceof CharBigBadWolf
                || model.getCardThiefList().get(0) instanceof CharWhiteWerewolf)
                &&
                  (model.getCardThiefList().get(1) instanceof CharWerewolf
                || model.getCardThiefList().get(1) instanceof CharGreatWolf
                || model.getCardThiefList().get(1) instanceof CharBigBadWolf
                || model.getCardThiefList().get(1) instanceof CharWhiteWerewolf)) {
            buttonSleep.setDisable(true);
            lblInfo.setText(PrivateGameConstants.THIEF_MUST_SELECT);
        } else {
            lblInfo.setText(PrivateGameConstants.THIEF_CAN_SELECT);
        }
        imageViewCard1.setImage(model.getCardThiefList().get(0).getCharacterCardFront());
        imageViewCard2.setImage(model.getCardThiefList().get(1).getCharacterCardFront());
    }

    /**
     * The left card is chosen.
     * @author Henrik Möhlmann
     */
    public void getFirst() {
        saveFirst();
        next();
    }

    /**
     * for testing
     * @author Henrik Möhlmann
     */
    public void testGetFirst() {
        saveFirst();
    }

    /**
     * @author Henrik Möhlmann
     */
    private void saveFirst() {
        Player thief = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleThief()))
                .collect(Collectors.toList()).get(0);
        PlayerLayoutViewModel playerLayoutViewModel = boardLayoutViewModel.getViewModelList().stream().filter(x -> x.getPlayer().hasRole(new RoleThief())).collect(Collectors.toList()).get(0);
        model.changeThiefToNewCharacter(thief, model.getCardThiefList().get(0));
        if (boardLayoutViewModel.isCardsRevealed()) {
            thief.revealCharacterCard(true);
        }
        if (boardLayoutViewModel.isJobsRevealed()) {
            thief.showHideJobs(true, boardLayoutViewModel.isCardsRevealed());
        }
        endPhase();
    }

    /**
     * The right card is chosen.
     * @author Henrik Möhlmann
     */
    public void getSecond() {
        saveSecond();
        next();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void testGetSecond() {
        saveSecond();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void saveSecond() {
        Player thief = model.getPlayerAliveList().stream()
                .filter(x -> x.hasRole(new RoleThief()))
                .collect(Collectors.toList()).get(0);
        PlayerLayoutViewModel playerLayoutViewModel = boardLayoutViewModel.getViewModelList().stream().filter(x -> x.getPlayer().hasRole(new RoleThief())).collect(Collectors.toList()).get(0);
        model.changeThiefToNewCharacter(thief, model.getCardThiefList().get(1));
        if (boardLayoutViewModel.isCardsRevealed()) {
            thief.revealCharacterCard(true);
        }
        if (boardLayoutViewModel.isJobsRevealed()) {
            thief.showHideJobs(true, boardLayoutViewModel.isCardsRevealed());
        }
        endPhase();
    }

    /**
     * Thief don't want to choose a card.
     * @author Henrik Möhlmann
     */
    public void getNone() {
        endPhase();
        next();
    }

    /**
     * for testing
     * @author Henrik Möhlmann
     */
    public void testGetNone() {
        endPhase();
    }

    /**
     * @author Henrik Möhlmann
     */
    private void endPhase() {
        boardLayoutViewModel.sleepAll();
    }

    /**
     * Go to next scene.
     * @author Henrik Möhlmann
     */
    public void next() {
        scene = GameScene.DOG_WOLF;
        nextScene();
    }
}
