package de.werwolf_spielleiter.viewModel_board;

import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.trade.TradeFarmer;
import de.werwolf_spielleiter.trade.Trades;
import de.werwolf_spielleiter.viewModel.ChoosingViewModel;
import de.werwolf_spielleiter.viewModel.ViewModel;
import de.werwolf_spielleiter.viewModel_character.HunterViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;

public class PlayerLayoutViewModel extends ViewModel {

    private Player player;
    private boolean click;
    private ChoosingViewModel choosingController;
    private double initialFitHeight;
    private double initialFitWidth;

    @FXML
    private Label lblName;
    @FXML
    private ImageView imageViewCard;
    @FXML
    private BorderPane panePlayer;
    @FXML
    private Pane origStatus;

    private Pane status;

    /**
     * Constructor
     */
    public PlayerLayoutViewModel() {
    }

    /**
     * Initializes the ViewModel class. Is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        this.status = origStatus;
    }

    public void setStatusPane(Pane newStatusPane) {
        this.status = newStatusPane;
    }

    /**
     * for testing
     *
     * @author Matthias Hinrichs
     */
    public void setTestGui() {
        setTestGui(new Player("test"), true);
    }

    /**
     * for testing
     *
     * @param player
     * @param click
     * @author Henrik Möhlmann
     */
    public void setTestGui(Player player, boolean click) {
        this.player = player;
        this.status = new HBox();
        this.panePlayer = new BorderPane();
        this.lblName = new Label();
        this.imageViewCard = new ImageView();
        this.click = click;
    }

    /**
     * @author Matthias Hinrichs
     * @param click
     */
    public void testGuiSetClick(boolean click) {
        this.click = click;
    }

    /**
     * Mark the player as werewolf victim.
     * Get's called in the witch phase.
     *
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     */
    public void markWerewolfVictim() {
        status.getStyleClass().clear();
        status.getStyleClass().add(PLAYER_LAYOUT_PANE_WEREWOLF_VICTIM);
    }

    /**
     * Set the background of the status back to default.
     *
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     */
    public void unmark() {
        status.getStyleClass().clear();
        status.getStyleClass().add(PLAYER_ALIVE);
    }

    public void markSheriff() {
        if (!panePlayer.getStyleClass().contains(PLAYER_LAYOUT_PANE_SHERIFF))
            panePlayer.getStyleClass().add(PLAYER_LAYOUT_PANE_SHERIFF);
    }

    public void unmarkSheriff() {
        panePlayer.getStyleClass().remove(PLAYER_LAYOUT_PANE_SHERIFF);
    }

    /**
     * Mark the player as part of the lovers.
     *
     * @author Henrik Möhlmann
     */
    public void markLovers() {
        if (!lblName.getStyleClass().contains(PLAYER_LAYOUT_PANE_LABEL_LOVER)) {
            lblName.getStyleClass().add(PLAYER_LAYOUT_PANE_LABEL_LOVER);
        }
    }

    /**
     * Unmark the lovers.
     *
     * @author Henrik Möhlmann
     */
    public void unmarkLovers() {
        lblName.getStyleClass().remove(PLAYER_LAYOUT_PANE_LABEL_LOVER);
    }

    /**
     * Show through layout objects that player died.
     * Set the status background to DEAD_COLOR and the normal background color to default.
     *
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     * @author Alonso Essenwanger
     */
    public void die() {
        status.getStyleClass().clear();
        status.getStyleClass().add(PLAYER_LAYOUT_PANE_DEAD);
        panePlayer.getStyleClass().clear();
        panePlayer.getStyleClass().add(PLAYER_LAYOUT_PANE_STYLE);
        ColorAdjust desaturate = new ColorAdjust();
        desaturate.setSaturation(-1);
        imageViewCard.setEffect(desaturate);
        // Show character card of the victim before setting his status
        // to dead, afterwards isn't possible!
        player.revealCharacterCard(true);
    }

    /**
     * To get a reaction on a click on a Player
     *
     * @author Florian Müller
     * @author Matthias Hinrichs
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    @FXML
    private void handlePlayerClick() {
        choosingController = view.getChoosingViewModel();
        HunterViewModel hunterViewModel = view.getPlayerVictimViewModel().getHunterViewModel();

        if (!player.getStatus().equals(PLAYER_LAYOUT_PANE_DEAD)) {
            //handle choosing
            if (view.getCurrentScene() == GameScene.WEREWOLF_VICTIM_CHOOSING
                    || view.getCurrentScene() == GameScene.LYNCH_VICTIM_CHOOSING) {
                click = !click;
                choosingController.choosing();
            }
            //handle witch
            if (view.getCurrentScene() == GameScene.WITCH) {
                click = !click;
                view.getWitchViewModel().handleClick(this);
            }
            // Handle hunter victim choosing
            if (hunterViewModel.isHunterPhase()) {
                click = !click;
                hunterViewModel.choosing(this);
            }
            //handle seer scene
            if (view.getCurrentScene() == GameScene.SEER) {
                click = !click;
                view.getSeerViewModel().handleClick(this);
            }
            //handle cupid scene
            if (view.getCurrentScene() == GameScene.CUPID) {
                click = !click;
                view.getCupidViewModel().handleClick(this);
            }
            // Handle Sheriff scene
            if (view.getCurrentScene() == GameScene.SHERIFF) {
                // handel clicks for the sheriff with trade
                if (model.isPlayWithTrade()) {
                    if (!Trades.getInstance().getFarmerList().isEmpty()) {
                        if (this.getPlayer().getTrade().isSameClass(new TradeFarmer())) {
                            click = !click;
                            view.getSheriffViewModel().choosing();
                        }
                    }
                } else { // handel click for the sheriff without the trade
                    click = !click;
                    view.getSheriffViewModel().choosing();
                }
            } else if (model.isNextSheriffVote() && !model.isHunterPhase()) {
                // handel clicks for the sheriff with trade
                if (model.isPlayWithTrade()) {
                    if (!Trades.getInstance().getFarmerList().isEmpty()) {
                        if (this.getPlayer().getTrade().isSameClass(new TradeFarmer())) {
                            click = !click;
                            view.getSheriffViewModel().choosing(this);
                        }
                    }
                } else { // handel click for the sheriff without the trade
                    click = !click;
                    view.getSheriffViewModel().choosing(this);
                }
            }
            //handle white werewolf
            if (view.getCurrentScene() == GameScene.WHITE_WEREWOLF) {
                click = !click;
                view.getWhiteWerewolfViewModel().handleClick(this);
            }
            //handle big bad wolf
            if (view.getCurrentScene() == GameScene.BIG_BAD_WOLF) {
                click = !click;
                view.getBigBadWolfViewModel().handleClick(this);
            }
            // Handle wild child idol choosing
            if (view.getCurrentScene() == GameScene.WILD_CHILD) {
                click = !click;
                view.getWildChildViewModel().handleClick(this);
            }
            // Handle fox
            if (view.getCurrentScene() == GameScene.FOX) {
                click = !click;
                view.getFoxViewModel().handleClick(this);
            }
            //handle confessor scene
            if (view.getCurrentScene() == GameScene.CONFESSOR) {
                click = !click;
                view.getConfessorViewModel().handleClick(this);
            }
        }
    }

    /**
     * To get the click
     *
     * @return
     * @author Florian Müller
     * @author Matthias Hinrichs
     */
    public boolean getClick() {
        return this.click;
    }


    public void resetClick() {
        click = false;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Fills all labels to show information about the player.
     * If the player is null, all labels are cleared.
     *
     * @param player the player or null
     * @author Matthias Hinrichs
     * @author Alonso Essenwanger
     */
    public void showPlayerDetails(Player player) {
        if (player != null) {
            status.getStyleClass().clear();
            panePlayer.getStyleClass().clear();
            status.getStyleClass().add(PLAYER_ALIVE);
            panePlayer.getStyleClass().add(PLAYER_LAYOUT_PANE_STYLE);
            this.player = player;
            lblName.setText(player.getName());
            // Use binding for Charactercard
            imageViewCard.imageProperty().bind(player.getCharacterCardProperty());
            player.toggleAwake();
        } else {
            lblName.setText("");
            imageViewCard.setImage(null);
        }
    }

    /**
     * To highlight the Player
     *
     * @author Florian Müller
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     */
    @FXML
    public void highlightPlayer() {
        // check if Styleclass is already set before adding
        if (!panePlayer.getStyleClass().contains(PLAYER_LAYOUT_PANE_HIGHLIGHT)) {
            panePlayer.getStyleClass().add(PLAYER_LAYOUT_PANE_HIGHLIGHT);
            imageViewCard.setFitHeight(initialFitHeight * 1.1);
            imageViewCard.setFitWidth(initialFitWidth * 1.1);
        }
    }

    /**
     * To unhighlight the Player
     *
     * @author Florian Müller
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     */
    @FXML
    public void unhighlightPlayer() {
        if (panePlayer.getStyleClass().contains(PLAYER_LAYOUT_PANE_HIGHLIGHT)) {
            panePlayer.getStyleClass().remove(PLAYER_LAYOUT_PANE_HIGHLIGHT);
            // Only resize if Player is not awake
            if(!player.isAwake()) {
                imageViewCard.setFitHeight(initialFitHeight);
                imageViewCard.setFitWidth(initialFitWidth);
            }
        }
    }

    /**
     * The player's actual background color gets set
     *
     * @param active true if Player should wakeup as active, false if Player should wakeup as passive
     * @author Alonso Essenwanger
     * @author Matthias Hinrichs
     */
    @FXML
    public String awakening(boolean active) {
        if (active) {
            // check if Styleclass is already set before adding
            if (!panePlayer.getStyleClass().contains(PLAYER_LAYOUT_PANE_AWAKE)) {
                panePlayer.getStyleClass().add(PLAYER_LAYOUT_PANE_AWAKE);
            }
        } else {
            if (!panePlayer.getStyleClass().contains(PLAYER_LAYOUT_PANE_AWAKE_PASSIVE)) {
                panePlayer.getStyleClass().add(PLAYER_LAYOUT_PANE_AWAKE_PASSIVE);
            }
        }
        if (!player.isAwake()) {
            player.toggleAwake();
        }
        imageViewCard.setFitHeight(initialFitHeight * 1.1);
        imageViewCard.setFitWidth(initialFitWidth * 1.1);
        return panePlayer.getStyleClass().toString();
    }

    /**
     * The player's actual background color gets set to default
     *
     * @author Alonso Essenwanger
     * @author Matthias Hinrichs
     */
    public String sleep() {
        ObservableList classes = panePlayer.getStyleClass();
        if (classes.contains(PLAYER_LAYOUT_PANE_AWAKE) || classes.contains(PLAYER_LAYOUT_PANE_AWAKE_PASSIVE)) {
            imageViewCard.setFitHeight(initialFitHeight);
            imageViewCard.setFitWidth(initialFitWidth);
            if (player.isAwake()) {
                player.toggleAwake();
            }
        }
        panePlayer.getStyleClass().removeAll(PLAYER_LAYOUT_PANE_AWAKE, PLAYER_LAYOUT_PANE_AWAKE_PASSIVE);
        return panePlayer.getStyleClass().toString();
    }

    @Override
    public void getStart() {

    }

    /**
     * Method to set the initial height and width of the ImageView in the Playercard.
     * @author Matthias Hinrichs
     * @param height the initial height of the ImageView in the Playercard
     * @param width the initial width of the ImageView in the Playercard
     */
    public void setFitHeightWidth(double height, double width) {
        this.initialFitHeight = height;
        this.initialFitWidth = width;
    }

    /**
     * Getter for panePlayer
     * @return returns the BorderPane panePlayer
     * @author Matthias Hinrichs
     */
    public BorderPane getPanePlayer() {
        return panePlayer;
    }
}
