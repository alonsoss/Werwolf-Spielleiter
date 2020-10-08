package de.werwolf_spielleiter.model;

import de.werwolf_spielleiter.character.*;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.fraction.Fraction;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.*;
import de.werwolf_spielleiter.trade.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class ModelTest {
    private static Player villager; //test villager
    private static Player werewolf; //test werewolf
    private static Player whiteWerewolf; //test white werewolf
    public static final Model testModel = new Model();
    // Model for testing
    private Model modelTest;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initModel() {
        Fraction.reset();
        modelTest = new Model();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void initPlayerListTest(int testSize) {
        testModel.initPlayerList(testSize);
        assertEquals(testModel.getCountPlayer(), testSize);
    }

    /**
     * @author Henrik Möhlmann
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void getCountWerwolfTest(int testNumber) {
        modelTest.setCountWerewolf(testNumber);
        assertEquals(testNumber, modelTest.getCountWerewolf());
    }

    /**
     * @author Henrik Möhlmann
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void getCountDorfbewohnerTest(int testNumber) {
        modelTest.setCountVillager(testNumber);
        assertEquals(testNumber, modelTest.getCountVillager());
    }

    /**
     * @author Henrik Möhlmann
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void setCountWerwolfTest(int testNumber) {
        modelTest.setCountWerewolf(testNumber);
        assertEquals(testNumber, modelTest.getCountWerewolf());
    }

    /**
     * @author Henrik Möhlmann
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void setCountDorfbewohnerTest(int testNumber) {
        modelTest.setCountVillager(testNumber);
        assertEquals(testNumber, modelTest.getCountVillager());
    }

    /**
     * @author Henrik Möhlmann
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void getCountPlayerTest(int testNumber) {
        modelTest.setCountPlayer(testNumber);
        assertEquals(testNumber, modelTest.getCountPlayer());
    }

    /**
     * @author Henrik Möhlmann
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/ModelTestSource.csv")
    void setCountPlayerTest(int testNumber) {
        modelTest.setCountPlayer(testNumber);
        assertEquals(testNumber, modelTest.getCountPlayer());
    }

    /**
     * @author Henrik Möhlmann
     * @author Janik Dohrmann
     */
    @Test
    void getCharsStackTest() {
        modelTest.setCountWerewolf(1);
        modelTest.setCountGreatWolf(1);
        modelTest.setCountBigBadWolf(1);
        modelTest.setCountWhiteWerewolf(1);
        modelTest.setCountVillager(1);
        modelTest.setCountWitch(1);
        modelTest.setCountHunter(1);
        modelTest.setCountSeer(1);
        modelTest.setCountCupid(1);
        modelTest.setCountLittleGirl(1);
        modelTest.setCountThief(1);
        modelTest.setCountWildChild(1);
        modelTest.setCountFox(1);
        modelTest.setCountDogWolf(1);

        List stack = modelTest.getCharsStack();
        assertTrue(stack.size() == 14);
        assertTrue(stack.get(0) instanceof CharWerewolf);
        assertTrue(stack.get(1) instanceof CharGreatWolf);
        assertTrue(stack.get(2) instanceof CharBigBadWolf);
        assertTrue(stack.get(3) instanceof CharWhiteWerewolf);
        assertTrue(stack.get(4) instanceof CharVillager);
        assertTrue(stack.get(5) instanceof CharWitch);
        assertTrue(stack.get(6) instanceof CharHunter);
        assertTrue(stack.get(7) instanceof CharSeer);
        assertTrue(stack.get(8) instanceof CharCupid);
        assertTrue(stack.get(9) instanceof CharLittleGirl);
        assertTrue(stack.get(10) instanceof CharThief);
        assertTrue(stack.get(11) instanceof CharWildChild);
        assertTrue(stack.get(12) instanceof CharFox);
        assertTrue(stack.get(13) instanceof CharDogWolf);
    }

    /**
     * @author Henrik Möhlmann
     * @author Janik Dohrmann
     * @author Eric De Ron
     */
    @Test
    void startGameTest() {
        modelTest.setPlayWithTrade(true);
        modelTest.setCountPlayer(7);
        Player victoria = new Player("Victoria");
        Player fanny = new Player("Fanny");
        Player greatWolf = new Player("Urwolf");
        Player bigBadWolf = new Player("Großer, böser Wolf");
        Player whiteWerewolf = new Player("Weißer Werwolf");
        Player witch = new Player("Hexe");
        Player hunter = new Player("Jäger");
        Player cupid = new Player("Amor");
        Player seer = new Player("Seherin");
        Player littleGirl = new Player("Kleines Mädchen");
        Player thief = new Player("Dieb");
        Player wildChild = new Player("Wildes Kind");
        Player fox = new Player("Fuchs");
        Player dogWolf = new Player("Wolfshund");

        modelTest.addPlayer(victoria);
        modelTest.addPlayer(fanny);
        modelTest.addPlayer(greatWolf);
        modelTest.addPlayer(bigBadWolf);
        modelTest.addPlayer(whiteWerewolf);
        modelTest.addPlayer(witch);
        modelTest.addPlayer(hunter);
        modelTest.addPlayer(cupid);
        modelTest.addPlayer(seer);
        modelTest.addPlayer(littleGirl);
        modelTest.addPlayer(thief);
        modelTest.addPlayer(wildChild);
        modelTest.addPlayer(fox);
        modelTest.addPlayer(dogWolf);

        modelTest.setCountWerewolf(1);
        modelTest.setCountGreatWolf(1);
        modelTest.setCountBigBadWolf(1);
        modelTest.setCountWhiteWerewolf(1);
        modelTest.setCountVillager(1);
        modelTest.setCountWitch(1);
        modelTest.setCountHunter(1);
        modelTest.setCountCupid(1);
        modelTest.setCountSeer(1);
        modelTest.setCountLittleGirl(1);
        modelTest.setCountThief(1);
        modelTest.setCountWildChild(1);
        modelTest.setCountFox(1);
        modelTest.setCountDogWolf(1);

        modelTest.setCountVagabond(7);
        modelTest.setCountFarmer(6);
        modelTest.setCountConfessor(1);

        assertNull(victoria.getCharacter());
        assertNull(fanny.getCharacter());
        assertNull(greatWolf.getCharacter());
        assertNull(bigBadWolf.getCharacter());
        assertNull(whiteWerewolf.getCharacter());
        assertNull(witch.getCharacter());
        assertNull(hunter.getCharacter());
        assertNull(cupid.getCharacter());
        assertNull(seer.getCharacter());
        assertNull(littleGirl.getCharacter());
        assertNull(thief.getCharacter());
        assertNull(wildChild.getCharacter());
        assertNull(fox.getCharacter());
        assertNull(dogWolf.getCharacter());

        modelTest.startGame();

        assertNotNull(victoria.getCharacter());
        assertNotNull(fanny.getCharacter());
        assertNotNull(greatWolf.getCharacter());
        assertNotNull(bigBadWolf.getCharacter());
        assertNotNull(whiteWerewolf.getCharacter());
        assertNotNull(witch.getCharacter());
        assertNotNull(hunter.getCharacter());
        assertNotNull(cupid.getCharacter());
        assertNotNull(seer.getCharacter());
        assertNotNull(littleGirl.getCharacter());
        assertNotNull(thief.getCharacter());
        assertNotNull(wildChild.getCharacter());
        assertNotNull(fox.getCharacter());
        assertNotNull(dogWolf.getCharacter());

        assertFalse(modelTest.getFraction().getVillagerList().isEmpty());
        assertFalse(modelTest.getFraction().getWerewolfList().isEmpty());
        assertFalse(modelTest.getFraction().getWhiteWerewolfList().isEmpty());
        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleGreatWolf()));
        assertTrue(modelTest.isRoleInGame(new RoleBigBadWolf()));
        assertTrue(modelTest.isRoleInGame(new RoleWhiteWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleWitch()));
        assertTrue(modelTest.isRoleInGame(new RoleHunter()));
        assertTrue(modelTest.isRoleInGame(new RoleCupid()));
        assertTrue(modelTest.isRoleInGame(new RoleSeer()));
        assertTrue(modelTest.isRoleInGame(new RoleLittleGirl()));
        assertTrue(modelTest.isRoleInGame(new RoleThief()));
        assertTrue(modelTest.isRoleInGame(new RoleWildChild()));
        assertTrue(modelTest.isRoleInGame(new RoleFox()));
        assertTrue(modelTest.isRoleInGame(new RoleDogWolf()));

        assertNotNull(victoria.getTrade());
        assertNotNull(fanny.getTrade());

        assertFalse(Trades.getInstance().getFarmerList().isEmpty());
        assertFalse(Trades.getInstance().getVagabondList().isEmpty());
        assertNotNull(Trades.getInstance().getConfessor());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void startNightTest() {
        assertFalse(modelTest.isNight());
        assertEquals(0, modelTest.getDay());

        System.out.println(modelTest);
        modelTest.startNight();

        assertTrue(modelTest.isNight());
        assertEquals(1, modelTest.getDay());

        modelTest.setNight(false);
        assertFalse(modelTest.isNight());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void addPlayerTest() {
        assertTrue(modelTest.addPlayer(new Player("Victoria")));
        assertTrue(modelTest.getPlayerList().size() == 1);
        assertFalse(modelTest.addPlayer(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getPlayerAliveTest() {
        Player victoria = new Player("Victoria");
        Player fanny = new Player("Fanny");
        modelTest.setCountWerewolf(1);
        modelTest.setCountVillager(1);
        modelTest.addPlayer(victoria);
        modelTest.addPlayer(fanny);
        modelTest.startGame();
        assertTrue(modelTest.getPlayerAliveList().contains(victoria));
        assertTrue(modelTest.getPlayerAliveList().contains(fanny));
        assertTrue(modelTest.getPlayerAliveList().size() == 2);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void cardThiefList() {
        Player victoria = new Player("Victoria");
        Player fanny = new Player("Fanny");

        modelTest.setCountPlayer(2);
        modelTest.addPlayer(victoria);
        modelTest.addPlayer(fanny);
        modelTest.setCountThief(1);
        modelTest.setCountWerewolf(1);
        modelTest.setCountLittleGirl(1);
        modelTest.setCountCupid(1);
        modelTest.startGame();

        assertTrue(modelTest.getCardThiefList().size() == 2 || modelTest.getCardThiefList().size() == 0);
        if (modelTest.getCardThiefList().size() == 2) {
            assertTrue(victoria.getCharacter() instanceof CharThief || fanny.getCharacter() instanceof CharThief);
        } else {
            assertFalse(victoria.getCharacter() instanceof CharThief || fanny.getCharacter() instanceof CharThief);
        }
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countGreatWolfTest() {
        assertFalse(modelTest.setCountGreatWolf(-1));
        assertFalse(modelTest.setCountGreatWolf(2));
        assertTrue(modelTest.setCountGreatWolf(0));
        assertEquals(0, modelTest.getCountGreatWolf());
        assertTrue(modelTest.setCountGreatWolf(1));
        assertEquals(1, modelTest.getCountGreatWolf());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countBigBadWolfTest() {
        assertFalse(modelTest.setCountBigBadWolf(-1));
        assertFalse(modelTest.setCountBigBadWolf(2));
        assertTrue(modelTest.setCountBigBadWolf(0));
        assertEquals(0, modelTest.getCountBigBadWolf());
        assertTrue(modelTest.setCountBigBadWolf(1));
        assertEquals(1, modelTest.getCountBigBadWolf());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countWhiteWerewolfTest() {
        assertFalse(modelTest.setCountWhiteWerewolf(-1));
        assertFalse(modelTest.setCountWhiteWerewolf(2));
        assertTrue(modelTest.setCountWhiteWerewolf(0));
        assertEquals(0, modelTest.getCountWhiteWerewolf());
        assertTrue(modelTest.setCountWhiteWerewolf(1));
        assertEquals(1, modelTest.getCountWhiteWerewolf());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countWitchTest() {
        assertFalse(modelTest.setCountWitch(-1));
        assertFalse(modelTest.setCountWitch(2));
        assertTrue(modelTest.setCountWitch(0));
        assertEquals(0, modelTest.getCountWitch());
        assertTrue(modelTest.setCountWitch(1));
        assertEquals(1, modelTest.getCountWitch());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countHunterTest() {
        assertFalse(modelTest.setCountHunter(-1));
        assertFalse(modelTest.setCountHunter(2));
        assertTrue(modelTest.setCountHunter(0));
        assertEquals(0, modelTest.getCountHunter());
        assertTrue(modelTest.setCountHunter(1));
        assertEquals(1, modelTest.getCountHunter());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countCupidTest() {
        assertFalse(modelTest.setCountCupid(-1));
        assertFalse(modelTest.setCountCupid(2));
        assertTrue(modelTest.setCountCupid(0));
        assertEquals(0, modelTest.getCountCupid());
        assertTrue(modelTest.setCountCupid(1));
        assertEquals(1, modelTest.getCountCupid());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void setCountSeerTest() {
        assertTrue(modelTest.setCountSeer(1));
        assertFalse(modelTest.setCountSeer(2));
        assertEquals(1, modelTest.getCountSeer());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCountLittleGirlTest() {
        assertFalse(modelTest.setCountLittleGirl(-1));
        assertFalse(modelTest.setCountLittleGirl(2));
        assertTrue(modelTest.setCountLittleGirl(0));
        assertEquals(0, modelTest.getCountLittleGirl());
        assertTrue(modelTest.setCountLittleGirl(1));
        assertEquals(1, modelTest.getCountLittleGirl());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCountThiefTest() {
        assertFalse(modelTest.setCountThief(-1));
        assertFalse(modelTest.setCountThief(2));
        assertTrue(modelTest.setCountThief(0));
        assertEquals(0, modelTest.getCountThief());
        assertTrue(modelTest.setCountThief(1));
        assertEquals(1, modelTest.getCountThief());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void countWildChildTest() {
        assertFalse(modelTest.setCountWildChild(-1));
        assertFalse(modelTest.setCountWildChild(2));
        assertTrue(modelTest.setCountWildChild(0));
        assertEquals(0, modelTest.getCountWildChild());
        assertTrue(modelTest.setCountWildChild(1));
        assertEquals(1, modelTest.getCountWildChild());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void countFoxTest() {
        assertFalse(modelTest.setCountFox(-1));
        assertFalse(modelTest.setCountFox(2));
        assertTrue(modelTest.setCountFox(0));
        assertEquals(0, modelTest.getCountFox());
        assertTrue(modelTest.setCountFox(1));
        assertEquals(1, modelTest.getCountFox());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void countDogWolfTest() {
        assertFalse(modelTest.setCountDogWolf(-1));
        assertFalse(modelTest.setCountDogWolf(2));
        assertTrue(modelTest.setCountDogWolf(0));
        assertEquals(0, modelTest.getCountDogWolf());
        assertTrue(modelTest.setCountDogWolf(1));
        assertEquals(1, modelTest.getCountDogWolf());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getVictimsTest() {
        assertNotNull(modelTest.getVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void dieTest() {
        Player victoria = new Player("Victoria");
        Player fanny = new Player("Fanny");
        Player leandra = new Player("Leandra");

        modelTest.setCountWerewolf(1);
        modelTest.setCountVillager(1);
        modelTest.setCountWhiteWerewolf(1);
        modelTest.addPlayer(victoria);
        modelTest.addPlayer(fanny);
        modelTest.addPlayer(leandra);
        modelTest.startGame();
        Fraction fraction = Fraction.getInstance();

        Player werewolf = fraction.getWerewolfList().get(0);
        Player villager = fraction.getVillagerList().get(0);
        Player whiteWerewolf = fraction.getWhiteWerewolfList().get(0);

        assertFalse(modelTest.die(null));

        assertFalse(modelTest.isWerewolfDead());
        assertTrue(modelTest.die(werewolf));
        assertTrue(modelTest.isWerewolfDead());
        assertFalse(modelTest.getPlayerAliveList().contains(werewolf));
        assertTrue(fraction.isWerewolfEmpty());
        assertFalse(fraction.getWerewolfList().contains(werewolf));

        assertTrue(modelTest.die(villager));
        assertFalse(modelTest.getPlayerAliveList().contains(villager));
        assertTrue(fraction.isVillagerEmpty());
        assertFalse(fraction.getVillagerList().contains(villager));

        assertTrue(modelTest.die(whiteWerewolf));
        assertFalse(modelTest.getPlayerAliveList().contains(whiteWerewolf));
        assertTrue(fraction.isWhiteWerewolfEmpty());
        assertFalse(fraction.getWhiteWerewolfList().contains(whiteWerewolf));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameWerewolfTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountWerewolf(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();
        //Fraction fraction = Fraction.getInstance();

        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(new RoleWitch()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameGreatWolfTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountGreatWolf(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleGreatWolf()));
        assertFalse(modelTest.isRoleInGame(new RoleWitch()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameBigBadWolfTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountBigBadWolf(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleBigBadWolf()));
        assertFalse(modelTest.isRoleInGame(new RoleWitch()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameWhiteWerewolfTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountWhiteWerewolf(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleWhiteWerewolf()));
        assertFalse(modelTest.isRoleInGame(new RoleWitch()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameWitchTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountWitch(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleWitch()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameHunterTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountHunter(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleHunter()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameCupidTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountCupid(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleCupid()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameLittleGirlTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountLittleGirl(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleLittleGirl()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameThiefTest() {
        Player victoria = new Player("Victoria");
        modelTest.setCountThief(1);
        modelTest.addPlayer(victoria);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleThief()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void isRoleInGameWildChildTest() {
        Player wildChild = new Player("Wildes Kind");
        modelTest.setCountWildChild(1);
        modelTest.addPlayer(wildChild);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleWildChild()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void isRoleInGameFoxTest() {
        Player fox = new Player("Fuchs");
        modelTest.setCountFox(1);
        modelTest.addPlayer(fox);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleFox()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isRoleInGameDogWolfTest() {
        Player dogWolf = new Player("Wolfshund");
        modelTest.setCountDogWolf(1);
        modelTest.addPlayer(dogWolf);
        modelTest.startGame();

        assertTrue(modelTest.isRoleInGame(new RoleDogWolf()));
        assertFalse(modelTest.isRoleInGame(new RoleWerewolf()));
        assertFalse(modelTest.isRoleInGame(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeThiefToNewCharacterWerwolf() {
        Player victoria = new Player("Victoria");
        victoria.setCharacter(new CharThief());
        victoria.setRolesList(Role.getRoles(victoria));
        modelTest.addPlayer(victoria);
        modelTest.getPlayerAliveList().add(victoria);
        assertTrue(victoria.getCharacter() instanceof CharThief);
        Fraction fraction = Fraction.getInstance();
        fraction.addPlayer(victoria);
        assertTrue(fraction.getVillagerList().contains(victoria));

        assertTrue(modelTest.changeThiefToNewCharacter(victoria, new CharWerewolf()));

        assertFalse(fraction.getVillagerList().contains(victoria));
        assertTrue(fraction.getWerewolfList().contains(victoria));
        assertFalse(modelTest.isRoleInGame(new RoleThief()));
        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeThiefToNewCharacterWhiteWerewolf() {
        Player victoria = new Player("Victoria");
        victoria.setCharacter(new CharThief());
        victoria.setRolesList(Role.getRoles(victoria));
        modelTest.addPlayer(victoria);
        modelTest.getPlayerAliveList().add(victoria);
        assertTrue(victoria.getCharacter() instanceof CharThief);
        Fraction fraction = Fraction.getInstance();
        fraction.addPlayer(victoria);
        assertTrue(fraction.getVillagerList().contains(victoria));

        assertTrue(modelTest.changeThiefToNewCharacter(victoria, new CharWhiteWerewolf()));

        assertFalse(fraction.getVillagerList().contains(victoria));
        assertTrue(fraction.getWhiteWerewolfList().contains(victoria));
        assertFalse(modelTest.isRoleInGame(new RoleThief()));
        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleWhiteWerewolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeThiefToNewCharacterGreatWolf() {
        Player victoria = new Player("Victoria");
        victoria.setCharacter(new CharThief());
        victoria.setRolesList(Role.getRoles(victoria));
        modelTest.addPlayer(victoria);
        modelTest.getPlayerAliveList().add(victoria);
        assertTrue(victoria.getCharacter() instanceof CharThief);
        Fraction fraction = Fraction.getInstance();
        fraction.addPlayer(victoria);
        assertTrue(fraction.getVillagerList().contains(victoria));

        assertTrue(modelTest.changeThiefToNewCharacter(victoria, new CharGreatWolf()));

        assertFalse(fraction.getVillagerList().contains(victoria));
        assertTrue(fraction.getWerewolfList().contains(victoria));
        assertFalse(modelTest.isRoleInGame(new RoleThief()));
        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleGreatWolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeThiefToNewCharacterBigBadWolf() {
        Player victoria = new Player("Victoria");
        victoria.setCharacter(new CharThief());
        victoria.setRolesList(Role.getRoles(victoria));
        modelTest.addPlayer(victoria);
        modelTest.getPlayerAliveList().add(victoria);
        assertTrue(victoria.getCharacter() instanceof CharThief);
        Fraction fraction = Fraction.getInstance();
        fraction.addPlayer(victoria);
        assertTrue(fraction.getVillagerList().contains(victoria));

        assertTrue(modelTest.changeThiefToNewCharacter(victoria, new CharBigBadWolf()));

        assertFalse(fraction.getVillagerList().contains(victoria));
        assertTrue(fraction.getWerewolfList().contains(victoria));
        assertFalse(modelTest.isRoleInGame(new RoleThief()));
        assertTrue(modelTest.isRoleInGame(new RoleWerewolf()));
        assertTrue(modelTest.isRoleInGame(new RoleBigBadWolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeThiefToNewCharacterVillager() {
        Player victoria = new Player("Victoria");
        victoria.setCharacter(new CharThief());
        victoria.setRolesList(Role.getRoles(victoria));
        modelTest.addPlayer(victoria);
        modelTest.getPlayerAliveList().add(victoria);
        assertTrue(victoria.getCharacter() instanceof CharThief);
        Fraction fraction = Fraction.getInstance();
        fraction.addPlayer(victoria);
        assertTrue(fraction.getVillagerList().contains(victoria));

        assertTrue(modelTest.changeThiefToNewCharacter(victoria, new CharSeer()));

        assertFalse(fraction.getWerewolfList().contains(victoria));
        assertTrue(fraction.getVillagerList().contains(victoria));
        assertFalse(modelTest.isRoleInGame(new RoleThief()));
        assertTrue(modelTest.isRoleInGame(new RoleSeer()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeThiefToNewCharacterNull() {
        Player victoria = new Player("Victoria");

        assertFalse(modelTest.changeThiefToNewCharacter(null, null));
        assertFalse(modelTest.changeThiefToNewCharacter(victoria, null));
        assertFalse(modelTest.changeThiefToNewCharacter(null, new CharCupid()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void sherriff() {
        assertFalse(modelTest.isSheriff());
        modelTest.setSheriff(true);
        assertTrue(modelTest.isSheriff());
        modelTest.setSheriff(false);
        assertFalse(modelTest.isSheriff());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void sheriffWasVote() {
        assertFalse(modelTest.getSheriffWasVote());
        modelTest.setSheriffWasVote(true);
        assertTrue(modelTest.getSheriffWasVote());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void nextSheriffVote() {
        assertFalse(modelTest.isNextSheriffVote());
        modelTest.setNextSheriffVote(true);
        assertTrue(modelTest.isNextSheriffVote());
        modelTest.setNextSheriffVote(false);
        assertFalse(modelTest.isNextSheriffVote());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hunterPhase() {
        assertFalse(modelTest.isHunterPhase());
        modelTest.setHunterPhase(true);
        assertTrue(modelTest.isHunterPhase());
        modelTest.setHunterPhase(false);
        assertFalse(modelTest.isHunterPhase());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void gretWolfInfect() {
        assertFalse(modelTest.hasGreatWolfInfected());
        modelTest.greatWolfInfect();
        assertTrue(modelTest.hasGreatWolfInfected());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void werewolfDead() {
        assertFalse(modelTest.isWerewolfDead());
        modelTest.setWerewolfDead();
        assertTrue(modelTest.isWerewolfDead());
    }

    /**
     * Test set and get wild child idol
     * logic
     *
     * @author Eric De Ron
     */
    @Test
    void idolWildChildTest() {
        assertNull(modelTest.getIdolWildChild());
        assertTrue(modelTest.setIdolWildChild(new Player("")));
        assertNotNull(modelTest.getIdolWildChild());
        assertFalse(modelTest.setIdolWildChild(null));
        assertNotNull(modelTest.getIdolWildChild());
    }

    /**
     * Test the get player with role logic
     *
     * @author Eric De Ron
     */
    @Test
    void getFirstPlayerWithRoleTest() {
        Player werewolf = new Player();
        Player wildChild = new Player();
        Player lover = new Player();
        werewolf.addRole(new RoleWerewolf());
        wildChild.addRole(new RoleWildChild());
        lover.addRole(new RoleLovers());
        modelTest.getPlayerAliveList().add(werewolf);
        modelTest.getPlayerAliveList().add(wildChild);
        modelTest.getPlayerAliveList().add(lover);
        assertNull(modelTest.getFirstPlayerWithRole(new RoleHunter()));
        assertEquals(werewolf, modelTest.getFirstPlayerWithRole(new RoleWerewolf()));
        assertEquals(wildChild, modelTest.getFirstPlayerWithRole(new RoleWildChild()));
        assertEquals(lover, modelTest.getFirstPlayerWithRole(new RoleLovers()));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void setAllowConfession() {
        modelTest.setAllowConfession(true);
        assertTrue(modelTest.isAllowConfession());
        modelTest.setAllowConfession(false);
        assertFalse(modelTest.isAllowConfession());
    }

    /**
     * @author Henrik Möhlmann
     * @author Janik Dohrmann
     */
    @Test
    void getTradeStackTest() {
        modelTest.setCountVagabond(1);
        modelTest.setCountFarmer(1);
        modelTest.setCountConfessor(1);

        List<Trade> stack = modelTest.getTradeStack();
        assertEquals(3, stack.size());
        assertTrue(stack.get(0) instanceof TradeVagabond);
        assertTrue(stack.get(1) instanceof TradeFarmer);
        assertTrue(stack.get(2) instanceof TradeConfessor);
    }

    @Test
    void resetModel() {
        modelTest.setCountVagabond(1);
//        villager = new Player("Max");
//        villager.setCharacter(new CharVillager());
//        werewolf = new Player("Moritz");
//        werewolf.setCharacter(new CharWerewolf());
//        whiteWerewolf = new Player("Nele");
//        whiteWerewolf.setCharacter(new CharWhiteWerewolf());
        modelTest.setPlayWithTrade(true);
        modelTest.setCountPlayer(5);
        Player player1 = new Player("Victoria");
        Player player2 = new Player("Fanny");
        Player player3 = new Player("Großer, böser Wolf");
        Player player4 = new Player("Weißer Werwolf");
        Player player5 = new Player("Hexe");

        modelTest.addPlayer(player1);
        modelTest.addPlayer(player2);
        modelTest.addPlayer(player3);
        modelTest.addPlayer(player4);
        modelTest.addPlayer(player5);

        modelTest.setCountWerewolf(1);
        modelTest.setCountBigBadWolf(1);
        modelTest.setCountWhiteWerewolf(1);
        modelTest.setCountVillager(1);
        modelTest.setCountWitch(1);

        modelTest.setCountVagabond(2);
        modelTest.setCountFarmer(2);
        modelTest.setCountConfessor(1);

        modelTest.startGame();
        Player witch = modelTest.getFirstPlayerWithRole(new RoleWitch());
        Player bad = modelTest.getFirstPlayerWithRole(new RoleBigBadWolf());
        witch.addRole(new RoleLovers());
        bad.addRole(new RoleLovers());
        modelTest.getFraction().changeToLover(witch, bad);
        assertFalse(modelTest.getFraction().getLoversList().isEmpty());
        assertFalse(modelTest.getFraction().getWerewolfList().isEmpty());
        assertFalse(modelTest.getFraction().getVillagerList().isEmpty());
        assertFalse(modelTest.getFraction().getWhiteWerewolfList().isEmpty());
        assertEquals(2, modelTest.getPlayerAliveList().stream().filter(x -> x.getTrade() instanceof TradeVagabond).collect(Collectors.toList()).size());
        assertEquals(2, modelTest.getPlayerAliveList().stream().filter(x -> x.getTrade() instanceof TradeFarmer).collect(Collectors.toList()).size());
        Player villager1 = modelTest.getPlayerAliveList().stream().filter(x -> x.getCharacter() instanceof CharVillager).collect(Collectors.toList()).get(0);
        modelTest.getVictims().setVictimWitch(villager1);
        modelTest.getVictims().setVictimLover(villager1);
        modelTest.getVictims().setVictimWhiteWerewolf(bad);
        modelTest.getVictims().setVictimBigBadWolf(villager1);
        modelTest.getVictims().setVictimHunter(villager1);
        modelTest.getVictims().setVictimLynch(villager1);
        modelTest.getVictims().setVictimWerewolf(villager1);
        assertTrue(modelTest.getVictims().getVictimWhiteWerewolf().isSet());
        modelTest.setSheriff(true);
        modelTest.setPlayWithTrade(true);
        modelTest.setHunterPhase(true);
        modelTest.setNextSheriffVote(true);
        modelTest.setWerewolfDead();
        modelTest.greatWolfInfect();
        modelTest.setSheriffWasVote(true);
        modelTest.resetModel();
        assertFalse(modelTest.getSheriffWasVote());
        assertFalse(modelTest.hasGreatWolfInfected());
        assertFalse(modelTest.isHunterPhase());
        assertFalse(modelTest.isPlayWithTrade());
        assertFalse(modelTest.isSheriff());
        assertFalse(modelTest.isWerewolfDead());
        assertFalse(modelTest.isNextSheriffVote());
        assertFalse(modelTest.getVictims().getVictimWhiteWerewolf().isSet());
        assertTrue(modelTest.getPlayerAliveList().isEmpty());
        assertTrue(modelTest.getPlayerList().isEmpty());
        assertTrue(modelTest.getCardThiefList().isEmpty());


    }
}