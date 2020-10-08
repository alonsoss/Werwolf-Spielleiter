package de.werwolf_spielleiter;

import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.role.*;
import de.werwolf_spielleiter.scene.GameScene;
import de.werwolf_spielleiter.scene_init.SceneInit;
import de.werwolf_spielleiter.trade.Trade;
import de.werwolf_spielleiter.trade.Trades;
import de.werwolf_spielleiter.viewModel.*;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.viewModel_character.*;
import de.werwolf_spielleiter.viewModel_gamepreperation.CardSelectViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.PlayerCountSceneViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.PlayerNameSceneViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.TradeSelectViewModel;
import de.werwolf_spielleiter.viewModel_trade.ConfessorViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

import static de.werwolf_spielleiter.constants.PrivateGameConstants.*;
import static de.werwolf_spielleiter.scene.GameScene.*;

public class SceneManager extends Application {
    // Main stage of the javafx application
    private Stage mainStage;

    /* ViewModel */
    private PlayerCountSceneViewModel playerCountSceneViewModel;
    private PlayerNameSceneViewModel playerNameSceneViewModel;
    private CardSelectViewModel cardSelectViewModel;
    private TradeSelectViewModel tradeSelectViewModel;
    private BoardLayoutViewModel boardLayoutViewModel;
    private StartNightViewModel startNightViewModel;
    private ChoosingViewModel choosingViewModel;
    private VotingViewModel votingViewModel;
    private PlayerVictimViewModel playerVictimViewModel;
    private WitchViewModel witchViewModel;
    private SeerViewModel seerViewModel;
    private CupidViewModel cupidViewModel;
    private LoversViewModel loversViewModel;
    private SheriffViewModel sheriffViewModel;
    private ThiefViewModel thiefViewModel;
    private WhiteWerewolfViewModel whiteWerewolfViewModel;
    private GreatWolfViewModel greatWolfViewModel;
    private BigBadWolfViewModel bigBadWolfViewModel;
    private WildChildViewModel wildChildViewModel;
    private FoxViewModel foxViewModel;
    private DogWolfViewModel dogWolfViewModel;
    private ConfessorViewModel confessorViewModel;
    private NoNightVictimsViewModel noNightVictimsViewModel;

    /* Model */
    private Model model;

    /* Variables */
    // Current shown scene (default: first shown scene PLAYER_AMOUNT_CHOOSING)
    private GameScene currentScene = PLAYER_AMOUNT_CHOOSING;

    /* FXML parents */
    // Controls for player amount selector scene
    private Parent playerAmountSelectorScene;
    // Controls for player names selector scene
    private Parent playerNameSelectorScene;
    //Controls for card and card amount selector scene
    private Parent cardSelectorScene;
    //TradeSelect scene
    private Parent tradeSelectorScene;
    // Board Layout
    private Parent boardScene;
    private Parent startNightPane;
    // Choosing Scene Werwolf
    private Parent choosingSceneWerwolf;
    //Panel for great wolf
    private Parent greatWolfScene;
    //Panel for big bad wolf
    private Parent bigBadWolfScene;
    // Panel for white werewolf
    private Parent whiteWerewolfScene;
    // Choosing Scene Lynch
    private Parent choosingSceneLynch;
    // Voting Scene
    private Parent votingScene;
    // Panel for victims
    private Parent playerVictimScene;
    //Panel for Witch Scene
    private Parent witchScene;
    //Panel for Seer Scene
    private Parent seerScene;
    //Panel for cupid scene
    private Parent cupidScene;
    private Parent loversScene;
    // Panel for Sheriff Scene
    private Parent sheriffChossingScene;
    private Parent sheriffVotingScene;
    //Panel for thief scene
    private Parent thiefScene;
    // Panel for wild child scene
    private Parent wildChildScene;
    // Fox Scene
    private Parent foxScene;
    //Panel for dog wolf scene
    private Parent dogWolfScene;
    //Panel for confessor scene
    private Parent confessorScene;
    //Panel for no night Victims
    private Parent noNightVictimsScene;

    private GameScene jumpBackScene;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * First function which gets loaded of the javafx application
     *
     * @param stage Main stage of the javafx application
     * @throws Exception
     * @author Eric De Ron
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.mainStage = stage;
        this.model = new Model();

        // Load all fxml's
        initFXML();
    }

    /**
     * Function which loads all fxml files of the current project
     *
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     */
    public void initFXML() {
        FXMLLoader fxmlLoader;
        Pair<ViewModel, Parent> pair;
        try {
            // Load scene where the user can select the amount of players
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_PLAYER_COUNT));
            this.playerAmountSelectorScene = fxmlLoader.load();
            playerCountSceneViewModel = fxmlLoader.getController();
            playerCountSceneViewModel.setView(this);
            playerCountSceneViewModel.setModel(model);

            // Load scene where the user can set the names of the players
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_PLAYER_NAME));
            this.playerNameSelectorScene = fxmlLoader.load();
            playerNameSceneViewModel = fxmlLoader.getController();
            playerNameSceneViewModel.setView(this);
            playerNameSceneViewModel.setModel(model);

            // Load Scene for Baselayout of Board
            // Load fxml file for Baselayout
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_BOARD_LAYOUT));
            this.boardScene = fxmlLoader.load();
            boardLayoutViewModel = fxmlLoader.getController();
            boardLayoutViewModel.setView(this);
            boardLayoutViewModel.setModel(model);
            boardLayoutViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            SceneInit sceneInit = new SceneInit(model, this, boardLayoutViewModel);

            //initialize the CardSelectorViewModel
            this.cardSelectViewModel = new CardSelectViewModel();
            this.cardSelectViewModel.setView(this);
            this.cardSelectViewModel.setModel(model);
            cardSelectViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            //load the fxml file for Card Selection
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_CARD_SELECT));
            fxmlLoader.setController(this.cardSelectViewModel);
            this.cardSelectorScene = fxmlLoader.load();

            // initialize TradeCount scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_TRADE_SELECT);
            if (pair != null) {
                tradeSelectViewModel = (TradeSelectViewModel) pair.getKey();
                tradeSelectorScene = pair.getValue();
            }

            //initialize the startNightViewModel
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_START_NIGHT);
            if (pair != null) {
                startNightViewModel = (StartNightViewModel) pair.getKey();
                startNightPane = pair.getValue();
            }

            //initialize the VotingViewModel
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_VOTING);
            if (pair != null) {
                votingViewModel = (VotingViewModel) pair.getKey();
                votingScene = pair.getValue();
            }

            // Show victims scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_PLAYER_VICTIM);
            if (pair != null) {
                playerVictimViewModel = (PlayerVictimViewModel) pair.getKey();
                playerVictimScene = pair.getValue();
            }

            // Seer scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_SEER);
            if (pair != null) {
                seerViewModel = (SeerViewModel) pair.getKey();
                seerScene = pair.getValue();
            }

            //lovers scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_LOVERS);
            if (pair != null) {
                loversViewModel = (LoversViewModel) pair.getKey();
                loversScene = pair.getValue();
            }

            // Wild child scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_WILD_CHILD);
            if (pair != null) {
                wildChildViewModel = (WildChildViewModel) pair.getKey();
                wildChildScene = pair.getValue();
            }

            // Fox Scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_FOX);
            if (pair != null) {
                foxViewModel = (FoxViewModel) pair.getKey();
                foxScene = pair.getValue();
            }

            //Confessor Scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_CONFESSOR);
            if (pair != null) {
                confessorViewModel = (ConfessorViewModel) pair.getKey();
                confessorScene = pair.getValue();
            }

            //No Night Victims Scene
            pair = sceneInit.basicInit(SCENE_MANAGER_FXML_NO_NIGHT_VICTIMS);
            if (pair != null) {
                noNightVictimsViewModel = (NoNightVictimsViewModel) pair.getKey();
                noNightVictimsScene = pair.getValue();
            }

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }

        // First setup main game board
        setupGameBoard();

        // Enable first scene
        switchToScene(PLAYER_AMOUNT_CHOOSING);
    }

    /**
     * @author Florian Müller
     * @author Henrik Möhlmann
     */
    public void initChoosingWerewolf() {
        try {
            //initialize the ChoosingViewModel for Werwolf
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_CHOOSING_WEREWOLF));
            this.choosingSceneWerwolf = fxmlLoader.load();
            this.choosingViewModel = fxmlLoader.getController();
            this.choosingViewModel.setView(this);
            this.choosingViewModel.setModel(model);
            this.choosingViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initGreatWolf() {
        FXMLLoader fxmlLoader;
        try {
            this.greatWolfViewModel = new GreatWolfViewModel();
            this.greatWolfViewModel.setView(this);
            this.greatWolfViewModel.setModel(model);
            this.greatWolfViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_GREAT_WOLF));
            fxmlLoader.setController(this.greatWolfViewModel);
            this.greatWolfScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initBigBadWolf() {
        FXMLLoader fxmlLoader;
        try {
            bigBadWolfViewModel = new BigBadWolfViewModel();
            bigBadWolfViewModel.setView(this);
            bigBadWolfViewModel.setModel(model);
            bigBadWolfViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_BIG_BAD_WOLF));
            fxmlLoader.setController(bigBadWolfViewModel);
            bigBadWolfScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initWhiteWerewolf() {
        FXMLLoader fxmlLoader;
        try {
            this.whiteWerewolfViewModel = new WhiteWerewolfViewModel();
            this.whiteWerewolfViewModel.setView(this);
            this.whiteWerewolfViewModel.setModel(model);
            this.whiteWerewolfViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_WHITE_WEREWOLF));
            fxmlLoader.setController(this.whiteWerewolfViewModel);
            this.whiteWerewolfScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Florian Müller
     */
    public void initChoosingLynch() {
        // initialize the ChoosingViewModel for Lynch
        FXMLLoader fxmlLoader;
        try {
            //initialize the ChoosingController for Lynch
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_CHOOSING_LYNCH));
            this.choosingSceneLynch = fxmlLoader.load();
            this.choosingViewModel = fxmlLoader.getController();
            this.choosingViewModel.setView(this);
            this.choosingViewModel.setModel(model);
            this.choosingViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void initChoosingSheriff() {

        // initialize the SheriffViewModel for choosing
        FXMLLoader fxmlLoader;
        try {
            //initialize the SheriffViewModel for choosing
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_CHOOSING_SHERIFF));
            this.sheriffChossingScene = fxmlLoader.load();
            this.sheriffViewModel = fxmlLoader.getController();
            this.sheriffViewModel.setView(this);
            this.sheriffViewModel.setModel(model);
            sheriffViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void initVotingSheriff() {
        // initialize the SheriffViewModel for voting
        FXMLLoader fxmlLoader;
        try {
            //initialize the SheriffViewModel for voting
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_VOTING_SHERIFF));
            fxmlLoader.setController(this.sheriffViewModel);
            this.sheriffVotingScene = fxmlLoader.load();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initWitch() {
        FXMLLoader fxmlLoader;
        try {
            //initialize the WitchController
            this.witchViewModel = new WitchViewModel();
            this.witchViewModel.setView(this);
            this.witchViewModel.setModel(model);
            this.witchViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            //load the fxml file for witch scene
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_WITCH));
            fxmlLoader.setController(this.witchViewModel);
            this.witchScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initCupid() {
        FXMLLoader fxmlLoader;
        try {
            //initialize cupid scene
            this.cupidViewModel = new CupidViewModel();
            this.cupidViewModel.setView(this);
            this.cupidViewModel.setModel(model);
            cupidViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_CUPID));
            fxmlLoader.setController(this.cupidViewModel);
            this.cupidScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initThief() {
        FXMLLoader fxmlLoader;
        try {
            this.thiefViewModel = new ThiefViewModel();
            thiefViewModel.setView(this);
            thiefViewModel.setModel(model);
            this.thiefViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_THIEF));
            fxmlLoader.setController(this.thiefViewModel);
            this.thiefScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    public void initDogWolf() {
        FXMLLoader fxmlLoader;
        try {
            dogWolfViewModel = new DogWolfViewModel();
            dogWolfViewModel.setView(this);
            dogWolfViewModel.setModel(model);
            dogWolfViewModel.setBoardLayoutViewModel(boardLayoutViewModel);
            fxmlLoader = new FXMLLoader(getClass().getResource(SCENE_MANAGER_FXML_DOG_WOLF));
            fxmlLoader.setController(dogWolfViewModel);
            dogWolfScene = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to switch between different
     * defined scenes
     *
     * @param scene Enum to set the scene to switch to
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * @author Matthias Hinrichs
     * @author Florian Müller
     * @author Alonso Essenwanger
     * @author Janik Dohrmann
     */
    public void switchToScene(GameScene scene) {
        // Don't switch on null pointer
        if (scene == null) return;

        setCurrentScene(scene);
        switch (scene) {
            case PLAYER_AMOUNT_CHOOSING:
                // Set playerAmountSelectorScene to center of BoardLayout
                boardLayoutViewModel.setCenter(playerAmountSelectorScene);
                mainStage.show();
                break;
            case PLAYER_NAME_SELECTING:
                // Player names selector scene
                // Setup scene
                playerNameSceneViewModel.getStart();
                // Set playerNameSelectorScene to center of Boardlayout
                boardLayoutViewModel.setCenter(playerNameSelectorScene);
                break;
            case CHARACTER_AMOUNT_CHOOSING:
                /* scene to select characters and their amount */
                //prepare scene
                cardSelectViewModel.getStart();
                // Set cardSelectorScene to center of Boardlayout
                boardLayoutViewModel.setCenter(cardSelectorScene);
                break;
            case TRADE_AMOUNT_CHOOSING:
                tradeSelectViewModel.getStart();
                boardLayoutViewModel.setCenter(tradeSelectorScene);
                break;
            case FIRST_NIGHT:
                boardLayoutViewModel.placePlayers();
                //only proceed, if no fraction has already won
                if (boardLayoutViewModel.showWinner() == null) {
                    //begin first night
                    boardLayoutViewModel.setCenter(startNightPane);
                }
                break;
            case THIEF:
                if (model.getDay() != 1) {
                    switchToScene(DOG_WOLF);
                    return;
                }
                if (!model.isRoleInGame(new RoleThief())) {
                    switchToScene(DOG_WOLF);
                    return;
                }
                if (model.getCardThiefList().size() != 2) {
                    switchToScene(DOG_WOLF);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleThief(), model.getPlayerAliveList());
                initThief();
                thiefViewModel.getStart();
                boardLayoutViewModel.setCenter(thiefScene);
                break;
            case DOG_WOLF:
                if (model.getDay() != 1) {
                    switchToScene(CUPID);
                    return;
                }
                if (!model.isRoleInGame(new RoleDogWolf())) {
                    switchToScene(CUPID);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleDogWolf(), model.getPlayerAliveList());
                initDogWolf();
                dogWolfViewModel.getStart();
                boardLayoutViewModel.setCenter(dogWolfScene);
                break;
            case CUPID:
                if (model.getDay() != 1) {
                    switchToScene(SEER);
                    return;
                }
                if (!model.isRoleInGame(new RoleCupid())) {
                    switchToScene(SEER);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleCupid(), model.getPlayerAliveList());
                initCupid();
                cupidViewModel.getStart();
                boardLayoutViewModel.setCenter(cupidScene);
                break;
            case LOVERS:
                if (model.getDay() != 1) {
                    switchToScene(WEREWOLF_VICTIM_CHOOSING);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleLovers(), model.getPlayerAliveList());
                boardLayoutViewModel.setCenter(loversScene);
                break;
            case SEER:
                //only proceed, if seer is alive
                if (!model.isRoleInGame(new RoleSeer())) {
                    switchToScene(WILD_CHILD);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleSeer(), model.getPlayerAliveList());
                boardLayoutViewModel.setCenter(seerScene);
                seerViewModel.getStart();
                break;
            case WILD_CHILD:
                if (model.getDay() != 1) {
                    switchToScene(FOX);
                    return;
                }
                // Only proceed, if wild child is part of the game
                if (!model.isRoleInGame(new RoleWildChild())) {
                    switchToScene(FOX);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleWildChild(), model.getPlayerAliveList());
                boardLayoutViewModel.setCenter(wildChildScene);
                wildChildViewModel.getStart();
                break;
            case FOX:
                if (!model.isRoleInGame(new RoleFox())) {
                    switchToScene(WEREWOLF_VICTIM_CHOOSING);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleFox(), model.getPlayerAliveList());
                boardLayoutViewModel.setCenter(foxScene);
                foxViewModel.getStart();
                break;
            case WEREWOLF_VICTIM_CHOOSING:
                boardLayoutViewModel.wakeUpRole(new RoleWerewolf(), model.getPlayerAliveList());
                boardLayoutViewModel.wakeUpRole(new RoleLittleGirl(), model.getPlayerAliveList());
                initChoosingWerewolf();
                boardLayoutViewModel.setCenter(choosingSceneWerwolf);
                choosingViewModel.getStart();
                choosingViewModel.choosing();
                break;
            case GREAT_WOLF:
                if (!model.isRoleInGame(new RoleGreatWolf())) {
                    switchToScene(WHITE_WEREWOLF);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleGreatWolf(), model.getPlayerAliveList());
                initGreatWolf();
                boardLayoutViewModel.setCenter(greatWolfScene);
                greatWolfViewModel.getStart();
                break;
            case WHITE_WEREWOLF:
                if ((model.getDay() % 2) != 0) {
                    switchToScene(BIG_BAD_WOLF);
                    return;
                }
                if (!model.isRoleInGame(new RoleWhiteWerewolf())) {
                    switchToScene(BIG_BAD_WOLF);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleWhiteWerewolf(), model.getPlayerAliveList());
                initWhiteWerewolf();
                boardLayoutViewModel.setCenter(whiteWerewolfScene);
                whiteWerewolfViewModel.getStart();
                break;
            case BIG_BAD_WOLF:
                if (model.isWerewolfDead()) {
                    switchToScene(WITCH);
                    return;
                }
                if (!model.isRoleInGame(new RoleBigBadWolf())) {
                    switchToScene(WITCH);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleBigBadWolf(), model.getPlayerAliveList());
                initBigBadWolf();
                boardLayoutViewModel.setCenter(bigBadWolfScene);
                bigBadWolfViewModel.getStart();
                break;
            case WITCH:
                //only proceed, if witch is alive
                if (!model.isRoleInGame(new RoleWitch())) {
                    switchToScene(VICTIM_NIGHT);
                    return;
                }
                boardLayoutViewModel.wakeUpRole(new RoleWitch(), model.getPlayerAliveList());
                initWitch();
                witchViewModel.getStart();
                boardLayoutViewModel.setCenter(witchScene);
                break;
            case SHERIFF:
                if (!model.getSheriffWasVote() && model.isPlayWithTrade() && Trades.getInstance().getFarmerList().isEmpty()) {
                    switchToScene(LYNCH_VICTIM_CHOOSING);
                    return;
                } else if (model.getSheriffWasVote()) {
                    switchToScene(LYNCH_VICTIM_CHOOSING);
                    return;
                }
                initChoosingSheriff();
                boardLayoutViewModel.setCenter(sheriffChossingScene);
                sheriffViewModel.choosing();
                break;
            case SHERIFF_VOTE:
                initVotingSheriff();
                boardLayoutViewModel.setCenter(sheriffVotingScene);
                sheriffViewModel.getStart();
                break;
            case LYNCH_VICTIM_CHOOSING:
                // choosing the player to kill
                initChoosingLynch();
                choosingViewModel.getStart();
                boardLayoutViewModel.setCenter(choosingSceneLynch);
                choosingViewModel.choosing();
                break;
            case LYNCH_VICTIM_VOTE:
                // voting the player
                votingViewModel.getStart();
                boardLayoutViewModel.setCenter(votingScene);
                //mainStage.show();
                break;
            case VICTIM_NIGHT:
                if (!model.getVictims().hasNightVictims()) {
                    switchToScene(NO_NIGHT_VICTIMS);
                    return;
                }
                if (model.isAllowConfession() && Trades.getInstance().getConfessor() != null) {
                    boardLayoutViewModel.enableConfessorMenu();
                }
                jumpBackScene = VICTIM_NIGHT;
                //show night victims
                boardLayoutViewModel.setCenter(playerVictimScene);
                playerVictimViewModel.setPhase(PlayerVictimViewModel.Phase.NIGHT);
                playerVictimViewModel.getStart();
                break;
            case NO_NIGHT_VICTIMS:
                if (model.isAllowConfession() && Trades.getInstance().getConfessor() != null) {
                    boardLayoutViewModel.enableConfessorMenu();
                }
                jumpBackScene = NO_NIGHT_VICTIMS;
                boardLayoutViewModel.setCenter(noNightVictimsScene);
                noNightVictimsViewModel.getStart();
                break;
            case VICTIM_DAY:
                //show lynch victims
                if (!model.getVictims().hasLynchVictims()) {
                    switchToScene(NEW_NIGHT);
                    return;
                }
                if (model.isAllowConfession() && Trades.getInstance().getConfessor() != null) {
                    boardLayoutViewModel.enableConfessorMenu();
                }
                jumpBackScene = VICTIM_DAY;
                boardLayoutViewModel.setCenter(playerVictimScene);
                playerVictimViewModel.setPhase(PlayerVictimViewModel.Phase.DAY);
                playerVictimViewModel.getStart();
                break;
            case CONFESSOR:
                if (model.isAllowConfession()) {
                    boardLayoutViewModel.setCenter(confessorScene);
                    confessorViewModel.getStart();
                } else {
                    switchToScene(jumpBackScene);
                }
                break;
            case NEW_NIGHT:
                //start night scene
                boardLayoutViewModel.setCenter(startNightPane);
                break;
        }
    }

    /**
     * Setups the board layout and
     * main view for the game
     *
     * @author Matthias Hinrichs
     * @author Eric De Ron
     */
    public void setupGameBoard() {
        // Set AppIcon
        mainStage.getIcons().add(new Image(String.valueOf(getClass().getResource(SCENE_MANAGER_WEREWOLF_IMAGE))));
        // Set mainStage to maximized
        mainStage.setMaximized(true);
        // Setup BoardLayout
        boardLayoutViewModel.setBoard((BorderPane) boardScene);
        mainStage.setScene(new Scene(boardScene));
    }

    /**
     * @return Returns the enum of the current displayed scene
     * @author Eric De Ron
     */
    public GameScene getCurrentScene() {
        return currentScene;
    }

    /**
     * @param scene Sets the enum of the current scene
     * @author Eric De Ron
     */
    public void setCurrentScene(GameScene scene) {
        // Don't switch on null pointer
        if (scene == null) return;

        this.currentScene = scene;
    }

    /**
     * @return
     * @author Florian Müller
     */
    public ChoosingViewModel getChoosingViewModel() {
        return choosingViewModel;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public WhiteWerewolfViewModel getWhiteWerewolfViewModel() {
        return whiteWerewolfViewModel;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public BigBadWolfViewModel getBigBadWolfViewModel() {
        return bigBadWolfViewModel;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public WitchViewModel getWitchViewModel() {
        return this.witchViewModel;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public CupidViewModel getCupidViewModel() {
        return cupidViewModel;
    }

    /**
     * @return
     * @author Florian Müller
     */
    public BoardLayoutViewModel getBoardLayoutViewModel() {
        return boardLayoutViewModel;
    }

    /**
     * @return Victim controller from fxml
     * @author Eric De Ron
     */
    public PlayerVictimViewModel getPlayerVictimViewModel() {
        return playerVictimViewModel;
    }

    /**
     * @return
     * @author Janik Dohrmann
     */
    public SeerViewModel getSeerViewModel() {
        return seerViewModel;
    }

    /**
     * @return
     * @author Florian Müller
     */
    public SheriffViewModel getSheriffViewModel() {
        return sheriffViewModel;
    }

    /**
     * @return ViewModel of the wild child
     * @author Eric De Ron
     */
    public WildChildViewModel getWildChildViewModel() {
        return wildChildViewModel;
    }

    /**
     * @return ViewModel of the fox
     * @author Matthias Hinrichs
     */
    public FoxViewModel getFoxViewModel() {
        return foxViewModel;
    }

    public ConfessorViewModel getConfessorViewModel() {
        return confessorViewModel;
    }

    public GameScene getJumpBackScene() {
        return jumpBackScene;
    }

    /**
     * for testing PlayerVictimViewModel
     *
     * @author Florian Müller
     */
    public void testSceneManager() {
        this.sheriffViewModel = new SheriffViewModel();
        sheriffViewModel.testingSheriffViewModel();
    }

    /**
     *
     * @return PlayerNameSceneViewModel
     * @author Florian Müller
     */
    public PlayerNameSceneViewModel getPlayerNameSceneViewModel() {
        return playerNameSceneViewModel;
    }

    /**
     * @author Florian Müller
     * @return CardSelectViewModel
     */
    public CardSelectViewModel getCardSelectViewModel(){
        return cardSelectViewModel;
    }

    /**
     * @author Florian Müller
     * @return TradeSelectViewModel
     */
    public TradeSelectViewModel getTradeSelectViewModel(){
        return tradeSelectViewModel;
    }
}
