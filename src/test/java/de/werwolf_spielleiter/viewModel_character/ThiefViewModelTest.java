package de.werwolf_spielleiter.viewModel_character;

import de.werwolf_spielleiter.character.*;
import de.werwolf_spielleiter.character.Character;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.model.Model;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.Role;
import de.werwolf_spielleiter.role.RoleThief;
import de.werwolf_spielleiter.viewModel_board.BoardLayoutViewModel;
import de.werwolf_spielleiter.viewModel_board.PlayerLayoutViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.CardSelectViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.PlayerCountSceneViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.PlayerNameSceneViewModel;
import de.werwolf_spielleiter.viewModel_gamepreperation.TradeSelectViewModel;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
class ThiefViewModelTest {
    private ThiefViewModel testThiefViewModel;
    private Model testModel;
    private BoardLayoutViewModel testBoardLayoutViewModel;
    private PlayerLayoutViewModel testPlayerThiefLayoutViewModel;

    /**
     * @author Henrik Möhlmann
     */
    private void initWithTrade() {
        testModel = new Model();
        testBoardLayoutViewModel = new BoardLayoutViewModel();
        testBoardLayoutViewModel.setModel(testModel);

        //Setup player count scene
        PlayerCountSceneViewModel playerCountSceneViewModel = new PlayerCountSceneViewModel();
        playerCountSceneViewModel.setModel(testModel);
        playerCountSceneViewModel.initTest();
        playerCountSceneViewModel.save();
        assertEquals(14, testModel.getCountPlayer());

        //Setup player name scene
        PlayerNameSceneViewModel playerNameSceneViewModel = new PlayerNameSceneViewModel();
        playerNameSceneViewModel.setModel(testModel);
        playerNameSceneViewModel.testInit();
        playerNameSceneViewModel.fillTextFieldsTestMethod();
        playerNameSceneViewModel.saveNames();
        assertEquals("Spieler 1", testModel.getPlayerList().get(0).getName());

        //Setup card select scene
        CardSelectViewModel cardSelectViewModel = new CardSelectViewModel();
        cardSelectViewModel.setModel(testModel);
        cardSelectViewModel.testInit();
        cardSelectViewModel.getStart();
        cardSelectViewModel.saveTest();
        assertEquals(1, testModel.getCountWhiteWerewolf());

        //Setup trade select scene
        TradeSelectViewModel tradeSelectViewModel = new TradeSelectViewModel();
        tradeSelectViewModel.setModel(testModel);
        tradeSelectViewModel.testInit();
        tradeSelectViewModel.getStart();
        tradeSelectViewModel.saveTest();
        assertEquals(6, testModel.getCountFarmer());

        //init board layout
        testBoardLayoutViewModel.testInit();
        assertEquals("background awake", testBoardLayoutViewModel.getViewModelList().get(0).awakening(true));

        if (testModel.isRoleInGame(new RoleThief())) {
            //Setup thief scene
            testThiefViewModel = new ThiefViewModel();
            testThiefViewModel.setModel(testModel);
            testThiefViewModel.setBoardLayoutViewModel(testBoardLayoutViewModel);
            testThiefViewModel.testInit();
            testThiefViewModel.getStart();
            //get the playerLayoutViewModel of thief
            testPlayerThiefLayoutViewModel = testBoardLayoutViewModel.getViewModelList().stream()
                    .filter(x -> x.getPlayer().hasRole(new RoleThief())).collect(Collectors.toList()).get(0);
            System.err.println(String.format("Charakter 1: %s, Charakter 2: %s", testModel.getCardThiefList().get(0).getName(), testModel.getCardThiefList().get(1).getName()));
        } else {
            initWithTrade();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    private void initWithoutTrade() {
        testModel = new Model();
        testBoardLayoutViewModel = new BoardLayoutViewModel();
        testBoardLayoutViewModel.setModel(testModel);

        //Setup player count scene
        PlayerCountSceneViewModel playerCountSceneViewModel = new PlayerCountSceneViewModel();
        playerCountSceneViewModel.setModel(testModel);
        playerCountSceneViewModel.initTest();
        playerCountSceneViewModel.save();
        assertEquals(14, testModel.getCountPlayer());

        //Setup player name scene
        PlayerNameSceneViewModel playerNameSceneViewModel = new PlayerNameSceneViewModel();
        playerNameSceneViewModel.setModel(testModel);
        playerNameSceneViewModel.testInit();
        playerNameSceneViewModel.fillTextFieldsTestMethod();
        playerNameSceneViewModel.saveNames();
        assertEquals("Spieler 1", testModel.getPlayerList().get(0).getName());

        //Setup card select scene
        CardSelectViewModel cardSelectViewModel = new CardSelectViewModel();
        cardSelectViewModel.setModel(testModel);
        cardSelectViewModel.testInit();
        cardSelectViewModel.getStart();
        cardSelectViewModel.testSelectRadioTrade(false);
        cardSelectViewModel.saveTest();
        assertEquals(1, testModel.getCountWhiteWerewolf());

        //init board layout
        testBoardLayoutViewModel.testInit();
        assertEquals("background awake", testBoardLayoutViewModel.getViewModelList().get(0).awakening(true));

        if (testModel.isRoleInGame(new RoleThief())) {
            //Setup thief scene
            testThiefViewModel = new ThiefViewModel();
            testThiefViewModel.setModel(testModel);
            testThiefViewModel.setBoardLayoutViewModel(testBoardLayoutViewModel);
            testThiefViewModel.testInit();
            testThiefViewModel.getStart();
            //get the playerLayoutViewModel of thief
            testPlayerThiefLayoutViewModel = testBoardLayoutViewModel.getViewModelList().stream()
                    .filter(x -> x.getPlayer().hasRole(new RoleThief())).collect(Collectors.toList()).get(0);
            System.err.println(String.format("Charakter 1: %s, Charakter 2: %s", testModel.getCardThiefList().get(0).getName(), testModel.getCardThiefList().get(1).getName()));
        } else {
            initWithoutTrade();
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void revealCharacterCardsTest() {
        //show front of character cards
        //thief select card
        //card of thief should be revealed after chosen
        initWithoutTrade();

        //reveal the character cards
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardBack(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleFlipCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //select the left card
        testThiefViewModel.testGetFirst();
        //check if card front is shown after sleeping
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void revealCharacterCardsTradeTest() {
        //show front of character cards
        //thief select card
        //card of thief should be revealed after chosen card
        initWithTrade();

        //reveal the character cards
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardBack(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleFlipCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //select the right card
        testThiefViewModel.testGetSecond();
        //check if card front is shown after sleeping
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void revealTradeTest() {
        //show trade
        //thief select card
        //trade should be shown after chosen card
        initWithTrade();

        //show jobs
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardBack(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleJobCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getTrade().getSign(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //select the right card
        testThiefViewModel.testGetSecond();
        //check if job sign is shown after sleeping
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getTrade().getSign(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void revealCharacterTradeHideTradeTest() {
        //show character card front
        //show trade
        //thief select card
        //hide jobs
        //character card front should be shown
        initWithTrade();

        //reveal the character cards
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardBack(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleFlipCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //show jobs
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleJobCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getTrade().getSign(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //select the left card
        testThiefViewModel.testGetFirst();

        //hide jobs
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getTrade().getSign(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleJobCards();
        //check if character card front is shown
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void revealCharacterTradeRevealCharacterTest() {
        //show character card front
        //show trade
        //thief select card
        //reveal character cards
        //character card back should be shown
        initWithTrade();

        //reveal the character cards
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardBack(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleFlipCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //show jobs
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardFront(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleJobCards();
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getTrade().getSign(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());

        //select the left card
        testThiefViewModel.testGetFirst();

        //reveal character cards
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getTrade().getSign(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
        testBoardLayoutViewModel.handleFlipCards();
        //check if character card back is shown
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardBack(), testPlayerThiefLayoutViewModel.getPlayer().getCharacterCardProperty().getValue());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void buttonEnabledTest() {
        //check if sleep button is enabled if thief cards are not both werewolves
        do {
            initWithoutTrade();
        } while ((testModel.getCardThiefList().get(0) instanceof CharWerewolf
                || testModel.getCardThiefList().get(0) instanceof CharGreatWolf
                || testModel.getCardThiefList().get(0) instanceof CharBigBadWolf
                || testModel.getCardThiefList().get(0) instanceof CharWhiteWerewolf)
                &&
                (testModel.getCardThiefList().get(1) instanceof CharWerewolf
                        || testModel.getCardThiefList().get(1) instanceof CharGreatWolf
                        || testModel.getCardThiefList().get(1) instanceof CharBigBadWolf
                        || testModel.getCardThiefList().get(1) instanceof CharWhiteWerewolf));
        assertFalse(testThiefViewModel.buttonSleep.isDisabled());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void buttonEnabledTradeTest() {
        //check if sleep button is enabled if thief cards are not both werewolves
        do {
            initWithTrade();
        } while ((testModel.getCardThiefList().get(0) instanceof CharWerewolf
                || testModel.getCardThiefList().get(0) instanceof CharGreatWolf
                || testModel.getCardThiefList().get(0) instanceof CharBigBadWolf
                || testModel.getCardThiefList().get(0) instanceof CharWhiteWerewolf)
                &&
                (testModel.getCardThiefList().get(1) instanceof CharWerewolf
                        || testModel.getCardThiefList().get(1) instanceof CharGreatWolf
                        || testModel.getCardThiefList().get(1) instanceof CharBigBadWolf
                        || testModel.getCardThiefList().get(1) instanceof CharWhiteWerewolf));
        assertFalse(testThiefViewModel.buttonSleep.isDisabled());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void buttonDisabledTest() {
        //check if sleep button is disabled if thief cards are both werewolves
        do {
            initWithoutTrade();
        } while (!((testModel.getCardThiefList().get(0) instanceof CharWerewolf
                || testModel.getCardThiefList().get(0) instanceof CharGreatWolf
                || testModel.getCardThiefList().get(0) instanceof CharBigBadWolf
                || testModel.getCardThiefList().get(0) instanceof CharWhiteWerewolf)
                &&
                (testModel.getCardThiefList().get(1) instanceof CharWerewolf
                        || testModel.getCardThiefList().get(1) instanceof CharGreatWolf
                        || testModel.getCardThiefList().get(1) instanceof CharBigBadWolf
                        || testModel.getCardThiefList().get(1) instanceof CharWhiteWerewolf)));
        assertTrue(testThiefViewModel.buttonSleep.isDisabled());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void buttonDisabledTradeTest() {
        //check if sleep button is disabled if thief cards are both werewolves
        do {
            initWithTrade();
        } while (!((testModel.getCardThiefList().get(0) instanceof CharWerewolf
                || testModel.getCardThiefList().get(0) instanceof CharGreatWolf
                || testModel.getCardThiefList().get(0) instanceof CharBigBadWolf
                || testModel.getCardThiefList().get(0) instanceof CharWhiteWerewolf)
                &&
                (testModel.getCardThiefList().get(1) instanceof CharWerewolf
                        || testModel.getCardThiefList().get(1) instanceof CharGreatWolf
                        || testModel.getCardThiefList().get(1) instanceof CharBigBadWolf
                        || testModel.getCardThiefList().get(1) instanceof CharWhiteWerewolf)));
        assertTrue(testThiefViewModel.buttonSleep.isDisabled());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void selectLeftCardTest() {
        initWithoutTrade();
        Character leftCharacter = testModel.getCardThiefList().get(0);
        Player dummyPlayer = new Player();
        dummyPlayer.setCharacter(leftCharacter);

        testThiefViewModel.testGetFirst();
        //test if thief got the left character
        assertTrue(testPlayerThiefLayoutViewModel.getPlayer().getCharacter().isSameClass(leftCharacter));
        //check if thief has gotten the right roles
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getRolesList().toString(), Role.getRoles(dummyPlayer).toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void selectLeftCardTradeTest() {
        initWithTrade();
        Character leftCharacter = testModel.getCardThiefList().get(0);
        Player dummyPlayer = new Player();
        dummyPlayer.setCharacter(leftCharacter);

        testThiefViewModel.testGetFirst();
        //test if thief got the left character
        assertTrue(testPlayerThiefLayoutViewModel.getPlayer().getCharacter().isSameClass(leftCharacter));
        //check if thief has gotten the right roles
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getRolesList().toString(), Role.getRoles(dummyPlayer).toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void selectRightCardTest() {
        initWithoutTrade();
        Character rightCharacter = testModel.getCardThiefList().get(1);
        Player dummyPlayer = new Player();
        dummyPlayer.setCharacter(rightCharacter);

        testThiefViewModel.testGetSecond();
        //test if thief got the left character
        assertTrue(testPlayerThiefLayoutViewModel.getPlayer().getCharacter().isSameClass(rightCharacter));
        //check if thief has gotten the right roles
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getRolesList().toString(), Role.getRoles(dummyPlayer).toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void selectLeftRightTradeTest() {
        initWithTrade();
        Character rightCharacter = testModel.getCardThiefList().get(1);
        Player dummyPlayer = new Player();
        dummyPlayer.setCharacter(rightCharacter);

        testThiefViewModel.testGetSecond();
        //test if thief got the left character
        assertTrue(testPlayerThiefLayoutViewModel.getPlayer().getCharacter().isSameClass(rightCharacter));
        //check if thief has gotten the right roles
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getRolesList().toString(), Role.getRoles(dummyPlayer).toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void selectNoCardTest() {
        initWithoutTrade();
        Player dummyPlayer = new Player();
        dummyPlayer.setCharacter(new CharThief());

        testThiefViewModel.testGetNone();
        //test if thief stays thief after choosing no card
        assertTrue(testPlayerThiefLayoutViewModel.getPlayer().getCharacter() instanceof CharThief);
        //check if thief has gotten the right roles
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getRolesList().toString(), Role.getRoles(dummyPlayer).toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void selectNoCardTradeTest() {
        initWithTrade();
        Player dummyPlayer = new Player();
        dummyPlayer.setCharacter(new CharThief());

        testThiefViewModel.testGetNone();
        //test if thief stays thief after choosing no card
        assertTrue(testPlayerThiefLayoutViewModel.getPlayer().getCharacter() instanceof CharThief);
        //check if thief has gotten the right roles
        assertEquals(testPlayerThiefLayoutViewModel.getPlayer().getRolesList().toString(), Role.getRoles(dummyPlayer).toString());
    }
}