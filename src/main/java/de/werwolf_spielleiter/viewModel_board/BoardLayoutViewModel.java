package de.werwolf_spielleiter.viewModel_board;

import com.google.common.io.Resources;
import de.werwolf_spielleiter.character.Character;
import de.werwolf_spielleiter.fraction.Fraction;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.Role;
import de.werwolf_spielleiter.role.RoleLittleGirl;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.viewModel.ViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;
import static de.werwolf_spielleiter.scene.GameScene.*;

/**
 * Class for the Board Layout
 */
public class BoardLayoutViewModel extends ViewModel {
    private ArrayList<BorderPane> panesList;
    private ArrayList<PlayerLayoutViewModel> viewModelList;
    private BorderPane board;

    private boolean cardsRevealed;
    private boolean jobsRevealed;

    private Alert alertInstructions;
    private Alert alertInfo;

    @FXML
    MenuItem menuItemTrade;

    @FXML
    MenuItem menuItemConfessor;

    /**
     * for testing
     * @author Henrik Möhlmann
     */
    public void testInit() {
        panesList = new ArrayList<>();
        viewModelList = new ArrayList<>();
        for (Player player : model.getPlayerList()) {
            PlayerLayoutViewModel playerLayoutViewModel = new PlayerLayoutViewModel();
            playerLayoutViewModel.setTestGui(player, false);
            playerLayoutViewModel.setModel(model);
            playerLayoutViewModel.showPlayerDetails(player);
            panesList.add(playerLayoutViewModel.getPanePlayer());
            viewModelList.add(playerLayoutViewModel);
        }
    }


    /**
     * Constructor
     */
    public BoardLayoutViewModel() {
    }

    /**
     * Initialize the ViewModel class. Is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        initInstructionsHTML();
        initInfo();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void enableTradeMenu() {
        menuItemTrade.setDisable(false);
    }

    /**
     * @author Henrik Möhlmann
     */
    public void disableTradeMenu() {
        menuItemTrade.setDisable(true);
    }

    public void enableConfessorMenu() {
        menuItemConfessor.setDisable(false);
    }

    public void disableConfessorMenu() {
        menuItemConfessor.setDisable(true);
    }

    /**
     * Creates pane for every Player
     *
     * @author Matthias Hinrichs
     */
    public void createPlayerPanes() {
        panesList = new ArrayList<>();
        viewModelList = new ArrayList<>();
        for (Player player : model.getPlayerList()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(BOARD_LAYOUT_FXML_PLAYER_LAYOUT));
                BorderPane playerLayout = loader.load();
                PlayerLayoutViewModel viewModel = loader.getController();
                viewModel.setModel(model);
                viewModel.setView(view);
                viewModel.showPlayerDetails(player);
                panesList.add(playerLayout);
                viewModelList.add(viewModel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Adds panes to Boardlayout
     *
     * @author Matthias Hinrichs
     * @author Alonso Essenwanger
     */
    public void addPlayerPanesToBoardLayout() {
        VBox topContainer = (VBox) board.getTop();
        HBox top = (HBox) topContainer.getChildren().get(1);
        HBox bottom = (HBox) board.getBottom();
        VBox left = (VBox) board.getLeft();
        VBox right = (VBox) board.getRight();

        top.setAlignment(Pos.CENTER);
        top.setSpacing(5);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(5);
        left.setAlignment(Pos.CENTER);
        left.setSpacing(5);
        right.setAlignment(Pos.CENTER);
        right.setSpacing(5);

        double splitIndex = panesList.size() / BOARD_LAYOUT_SPLIT_DIVIDER;
        List<BorderPane> leftList = panesList.subList(0, (int) Math.round(splitIndex));
        List<PlayerLayoutViewModel> leftViewModelList = viewModelList.subList(0, (int) Math.round(splitIndex));
        List<BorderPane> topList = panesList.subList((int) Math.round(splitIndex), (int) Math.round(splitIndex * BOARD_LAYOUT_TOP_END_FACTOR));
        List<PlayerLayoutViewModel> topViewModelList = viewModelList.subList((int) Math.round(splitIndex), (int) Math.round(splitIndex * BOARD_LAYOUT_TOP_END_FACTOR));
        List<BorderPane> rightList = panesList.subList((int) Math.round(splitIndex * BOARD_LAYOUT_RIGHT_START_FACTOR), (int) Math.round(splitIndex * BOARD_LAYOUT_RIGHT_END_FACTOR));
        List<PlayerLayoutViewModel> rightViewModelList = viewModelList.subList((int) Math.round(splitIndex * BOARD_LAYOUT_RIGHT_START_FACTOR), (int) Math.round(splitIndex * BOARD_LAYOUT_RIGHT_END_FACTOR));
        List<BorderPane> bottomList = panesList.subList((int) Math.round(splitIndex * BOARD_LAYOUT_BOTTOM_START_FACTOR), (int) Math.round(splitIndex * BOARD_LAYOUT_BOTTOM_END_FACTOR));
        List<PlayerLayoutViewModel> bottomViewModelList = viewModelList.subList((int) Math.round(splitIndex * BOARD_LAYOUT_BOTTOM_START_FACTOR), (int) Math.round(splitIndex * BOARD_LAYOUT_BOTTOM_END_FACTOR));

        // ImageView to modify
        ImageView imageView;
        // Resizing value for top and bottom
        // Divide width of top container of the Boardlayout by the number of cards displayed in the top + 1 for extra
        // spacing and substract the needed offset.
        double resizingSpaceTopBottom = topList.get(0).getBoundsInParent().getWidth() * 0.1;
        double resizeValueTopBottom = (top.getWidth() / (topList.size() + 1)) - BOARD_LAYOUT_OFFSET_WIDTH - resizingSpaceTopBottom;
        // Resizing value for left and right
        // Divide height of left container of the Boardlayout by the number of cards displayed in the left + 1 for extra
        // spacing and substract the needed offset.
        double resizingSpaceLeftRight = leftList.get(0).getBoundsInParent().getHeight() * 0.1;
        double resizeValueLeftRight = (left.getHeight() / (leftList.size() + 1)) - BOARD_LAYOUT_OFFSET_HEIGHT - resizingSpaceLeftRight;

        for (BorderPane pane : topList) {
            imageView = (ImageView) pane.getCenter();
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(resizeValueTopBottom);
            pane.setMaxWidth(imageView.getBoundsInLocal().getWidth());
            top.getChildren().add(pane);

        }
        // Bottom and left must be drawn in reverse
        Collections.reverse(bottomList);
        for (BorderPane pane : bottomList) {
            HBox nameBox = (HBox) pane.getTop();
            HBox statusBox = (HBox) pane.getBottom();
            pane.setTop(null);
            pane.setBottom(null);
            pane.setTop(statusBox);
            pane.setBottom(nameBox);
            imageView = (ImageView) pane.getCenter();
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(resizeValueTopBottom);
            pane.setMaxWidth(imageView.getBoundsInLocal().getWidth());
            bottom.getChildren().add(pane);
        }

        for (int x = 0; x < rightList.size(); x++) {
            BorderPane pane = rightList.get(x);
            HBox statusBox = (HBox) pane.getBottom();
            VBox newStatusBox = new VBox();
            newStatusBox.setId(statusBox.getId());
            newStatusBox.getStyleClass().add(PLAYER_ALIVE);
            newStatusBox.setMinWidth(19);
            newStatusBox.setMaxWidth(19);
            pane.setBottom(null);
            pane.setLeft(newStatusBox);
            rightViewModelList.get(x).setStatusPane(newStatusBox);
            imageView = (ImageView) pane.getCenter();
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(resizeValueLeftRight);
            pane.setMaxWidth(imageView.getBoundsInLocal().getWidth() + 19);
            right.getChildren().add(pane);
        }

        Collections.reverse(leftList);
        Collections.reverse(leftViewModelList);
        for (int x = 0; x < leftList.size(); x++) {
            BorderPane pane = leftList.get(x);
            HBox statusBox = (HBox) pane.getBottom();
            VBox newStatusBox = new VBox();
            newStatusBox.setId(statusBox.getId());
            newStatusBox.getStyleClass().add(PLAYER_ALIVE);
            newStatusBox.setMinWidth(19);
            newStatusBox.setMaxWidth(19);
            pane.setBottom(null);
            pane.setRight(newStatusBox);
            leftViewModelList.get(x).setStatusPane(newStatusBox);
            imageView = (ImageView) pane.getCenter();
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(resizeValueLeftRight);
            pane.setMaxWidth(imageView.getBoundsInLocal().getWidth() + 19);
            left.getChildren().add(pane);
        }
        // Cards get resized while playing
        // Set Height/Width of Containers to 1.5 of Height/Width of Card
        top.setPrefHeight(topList.get(0).getBoundsInParent().getHeight() * 1.5);
        bottom.setPrefHeight(topList.get(0).getBoundsInParent().getHeight() * 1.5);
        left.setPrefWidth(leftList.get(0).getBoundsInParent().getWidth() * 1.5);
        right.setPrefWidth(leftList.get(0).getBoundsInParent().getWidth() * 1.5);
        // Set Initial Values for Resizing in Cards
        viewModelList.forEach((viewModel) -> {
            if (topList.contains(viewModel.getPanePlayer()) || bottomList.contains(viewModel.getPanePlayer())) {
                viewModel.setFitHeightWidth(topList.get(0).getBoundsInParent().getHeight(),
                        topList.get(0).getBoundsInParent().getWidth());
            }
            if (leftList.contains(viewModel.getPanePlayer()) || rightList.contains(viewModel.getPanePlayer())) {
                viewModel.setFitHeightWidth(leftList.get(0).getBoundsInParent().getHeight(),
                        leftList.get(0).getBoundsInParent().getWidth());
            }
        });
    }

    /**
     * @author Matthias Hinrichs
     */
    public void placePlayers() {
        createPlayerPanes();
        addPlayerPanesToBoardLayout();
        this.cardsRevealed = false;
        wakeUpVillager();
        sleepVillager();
    }

    public ArrayList<PlayerLayoutViewModel> getViewModelList() {
        return viewModelList;
    }

    /**
     * Return size of panes
     *
     * @author Matthias Hinrichs
     */
    public int getPanesSize() {
        return panesList.size();
    }

    /**
     * @return true if cards are revealed
     * @author Janik Dohrmann
     */
    public boolean isCardsRevealed() {
        return cardsRevealed;
    }

    /**
     * @return true if jobs are revealed
     * @author Henrik Möhlmann
     */
    public boolean isJobsRevealed() {
        return jobsRevealed;
    }

    /**
     * This method returns the class that wins and displays a message on the game board.
     *
     * @return null if no winner
     * a Villager if Villagers win
     * a Werewolf if Werewolves win
     * @author Janik Dohrmann
     */
    @FXML
    public Character showWinner() {
        Character winner = Fraction.getInstance().winner();
        if (winner != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(BOARD_LAYOUT_FXML_WINNER_LAYOUT));
            try {
                loader.setController(this);
                AnchorPane winnerLayout = loader.load();
                Label winnerLabel = (Label) ((VBox) winnerLayout.getChildren().get(0)).getChildren().get(0);
                winnerLabel.setText(winner.winText());
                board.setCenter(winnerLayout);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return winner;
    }

    /**
     * a method to wake up the Villager
     *
     * @author Janik Dohrmann
     */
    @FXML
    public void wakeUpVillager() {
        List<Player> villagerList = Fraction.getInstance().getVillagerList();
        villagerList.forEach(x -> x.toggleAwake());
        showCards(villagerList);
    }

    /**
     * Method to wake up specific role
     *
     * @param role      the role to wake up
     * @param aliveList the list of alive player
     * @author Janik Dohrmann
     */
    public void wakeUpRole(Role role, List<Player> aliveList) {
        if (role instanceof RoleLittleGirl) {
            viewModelList.stream()
                    .filter(x -> aliveList.contains(x.getPlayer()) && x.getPlayer().hasRole(role))
                    .forEach(x -> x.awakening(false));
        } else {
            viewModelList.stream()
                    .filter(x -> aliveList.contains(x.getPlayer()) && x.getPlayer().hasRole(role))
                    .forEach(x -> x.awakening(true));
        }
    }

    /**
     * @author Janik Dohrmann
     */
    @Deprecated
    @FXML
    public void wakeUpAll() {
        List<Player> playerList = model.getPlayerAliveList();
        playerList.forEach(x -> x.toggleAwake());
        showCards(playerList);
    }

    /**
     * @param playerList
     * @author Janik Dohrmann
     */
    @FXML
    private void showCards(List<Player> playerList) {
        // Iterate through playerList to find living Players.
        for (Player p : playerList) {
            if (p.getStatus().equalsIgnoreCase(PLAYER_ALIVE)) {
                // Flip Card
                p.revealCharacterCard(true);
            }
        }
    }

    /**
     * Handles Menuinput to flip the CharacterCards
     *
     * @author Matthias Hinrichs
     */
    @FXML
    public void handleFlipCards() {
        if (cardsRevealed) {
            cardsRevealed = false;
            jobsRevealed = false;
            hideCards(model.getPlayerAliveList());
        } else {
            cardsRevealed = true;
            jobsRevealed = false;
            showCards(model.getPlayerAliveList());
        }
    }

    /**
     * Handles Menuinput to show the Jobs
     *
     * @author Alonso Essenwanger
     */
    @FXML
    public void handleJobCards() {
        if (jobsRevealed) {
            jobsRevealed = false;
            hideJobs(model.getPlayerAliveList());
        } else {
            jobsRevealed = true;
            showJobs(model.getPlayerAliveList());
        }
    }

    /**
     * @param playerList
     * @author Alonso Essenwanger
     */
    @FXML
    private void hideJobs(List<Player> playerList) {
        for (Player p : playerList) {
            p.showHideJobs(jobsRevealed, cardsRevealed);
        }
    }

    /**
     * @param playerList
     * @author Alonso Essenwanger
     */
    @FXML
    private void showJobs(List<Player> playerList) {
        // Iterate through playerList to find living Players.
        for (Player p : playerList) {
            if (p.getStatus().equalsIgnoreCase(PLAYER_ALIVE)) {
                // Flip Card
                p.showHideJobs(jobsRevealed, cardsRevealed);
            }
        }
    }

    /**
     * Handles Menuinput to end the Program
     *
     * @author Matthias Hinrichs
     */
    @FXML
    private void handleExit() {
        Platform.exit();
    }

    /**
     * Handles Menuinput to start a new Round
     *
     * @author Florian Müller
     */
    @FXML
    private void handleNewRound() {
        switch (view.getCurrentScene()){
            case PLAYER_AMOUNT_CHOOSING:
            case PLAYER_NAME_SELECTING:
            case CHARACTER_AMOUNT_CHOOSING:
            case TRADE_AMOUNT_CHOOSING:
               return;
        }
        NewRoundViewModell.getInstance().roundLoop(view, model);
    }

    /**
     * @param playerList
     * @author Janik Dohrmann
     */
    @FXML
    private void hideCards(List<Player> playerList) {
        for (Player p : playerList) {
            p.revealCharacterCard(false);
        }
    }


    /**
     * Method to set the center pane in BoardLayout
     *
     * @param pane to be set in center
     * @author Florian Müller
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    @FXML
    public void setCenter(Parent pane) {
        board.setCenter(pane);
    }

    /**
     * Method to calculate the available width of center of BorderPane
     *
     * @return the available width in center of BorderPane
     * @author Matthias Hinrichs
     */
    public double getCenterWidth() {
        return board.getBoundsInParent().getWidth()
                - board.getLeft().getBoundsInParent().getWidth()
                - board.getRight().getBoundsInParent().getWidth();
    }

    /**
     * A method to let the villager sleep
     *
     * @author Janik Dohrmann
     */
    @FXML
    public void sleepVillager() {
        List<Player> villagerList = Fraction.getInstance().getVillagerList();
        villagerList.forEach(x -> x.toggleAwake());
        hideCards(villagerList);
    }

    /**
     * @author Janik Dohrmann
     */
    @FXML
    public void sleepAll() {
        viewModelList.forEach(x -> x.sleep());
    }

    /**
     * @author Florian Müller
     * unhighlight all player
     */
    public void unhiglightAllPlayers() {
        for (PlayerLayoutViewModel c : viewModelList) {
            c.unhighlightPlayer();
            c.resetClick();
        }
    }

    @Override
    public void getStart() {
    }

    public void setBoard(BorderPane board) {
        this.board = board;
    }
    public BorderPane getBoard(){
        return board;
    }

    /**
     * Handles Menuinput to show game Instructions
     *
     * @author Alonso Essenwanger
     */
    @FXML
    private void handleInstructions() {
        alertInstructions.showAndWait();
    }

    /**
     * Handles Menuinput to show info
     *
     * @author Alonso Essenwanger
     */
    @FXML
    private void handleInfo() {
        alertInfo.showAndWait();
    }

    // For GUI-Testing only
    // TODO: Remove when not needed anymore (also remove MenuItem in fxml-file)
    @FXML
    private void handleKillPlayers() {
        List<Player> villagers = model.getPlayerAliveList().stream()
                .filter(player -> !player.hasRole(new RoleWerewolf()))
                .collect(Collectors.toList());
        List<Player> werewolves = model.getPlayerAliveList().stream()
                .filter(player -> player.hasRole(new RoleWerewolf()))
                .collect(Collectors.toList());
        Player victimWerewolf = villagers.get(0);
        Player victimLover = villagers.get(1);
        Player victimWitch = villagers.get(2);
        Player victimHunter = villagers.get(3);
        Player victimBigBadWolf = villagers.get(4);
        Player victimWhiteWerewolf = werewolves.get(0);
        model.getVictims().setVictimWerewolf(victimWerewolf);
        model.getVictims().setVictimBigBadWolf(victimBigBadWolf);
        model.getVictims().setVictimWhiteWerewolf(victimWhiteWerewolf);
        model.getVictims().setVictimLover(victimLover);
        model.getVictims().setVictimWitch(victimWitch);
        model.getVictims().setVictimHunter(victimHunter);
    }

    /**
     * Initialize the instruction alert with HTML
     *
     * @author Alonso Essenwanger
     */
    private void initInstructionsHTML() {
        try {
            alertInstructions = new Alert(Alert.AlertType.INFORMATION);
            alertInstructions.setHeaderText(" ");
            alertInstructions.getDialogPane().setMinWidth(600);
            WebView webView = new WebView();
            URL url = Resources.getResource(BOARD_LAYOUT_HTML_INTRODUCTION);
            String html = Resources.toString(url, StandardCharsets.UTF_8);
            webView.getEngine().loadContent(html);
            webView.setPrefSize(300, 600);
            alertInstructions.getDialogPane().setContent(webView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the info alert with HTML
     *
     * @author Alonso Essenwanger
     */
    private void initInfo() {
        try {
            alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setHeaderText(" ");
            alertInfo.getDialogPane().setMinWidth(600);
            WebView webView = new WebView();
            URL url = Resources.getResource(BOARD_LAYOUT_HTML_INFO);
            String html = Resources.toString(url, StandardCharsets.UTF_8);
            webView.getEngine().loadContent(html);
            webView.setPrefSize(200, 300);
            alertInfo.getDialogPane().setContent(webView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void confessorAction(ActionEvent actionEvent) {
        scene = CONFESSOR;
        view.switchToScene(scene);
    }
}
