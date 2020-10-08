package de.werwolf_spielleiter.fraction;

import de.werwolf_spielleiter.character.*;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class FractionTest {

    private static Fraction fraction;
    private static Player villager; //test villager
    private static Player werewolf; //test werewolf
    private static Player greatWolf; //test great wolf
    private static Player bigBadWolf; //test big bad wolf
    private static Player whiteWerewolf; //test white werewolf
    private static Player otherWerewolf;
    private static Player witch; //test witch
    private static Player hunter; //test hunter
    private static Player seer; //test seer
    private static Player cupid; //test cupid
    private static Player littleGirl; //test little girl
    private static Player thief; //test thief
    private static Player wildChild; //test wild child
    private static Player fox; // test fox
    private static Player dogWolf; //test dog wolf


    /**
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    @BeforeEach
    public void setUp() {
        Fraction.reset();
        fraction = Fraction.getInstance();
        villager = new Player("Max");
        villager.setCharacter(new CharVillager());
        werewolf = new Player("Moritz");
        werewolf.setCharacter(new CharWerewolf());
        greatWolf = new Player("Theresa");
        greatWolf.setCharacter(new CharGreatWolf());
        bigBadWolf = new Player("Lena");
        bigBadWolf.setCharacter(new CharBigBadWolf());
        whiteWerewolf = new Player("Nele");
        whiteWerewolf.setCharacter(new CharWhiteWerewolf());
        otherWerewolf = new Player("Alina");
        otherWerewolf.setCharacter(new CharWerewolf());
        witch = new Player("Fanny");
        witch.setCharacter(new CharWitch());
        hunter = new Player("Alex");
        hunter.setCharacter(new CharHunter());
        seer = new Player("Antje");
        seer.setCharacter(new CharSeer());
        cupid = new Player("Victoria");
        cupid.setCharacter(new CharCupid());
        littleGirl = new Player("Leandra");
        littleGirl.setCharacter(new CharLittleGirl());
        thief = new Player("Larissa");
        thief.setCharacter(new CharThief());
        wildChild = new Player("Hans");
        wildChild.setCharacter(new CharWildChild());
        fox = new Player("Thorsten");
        fox.setCharacter(new CharFox());
        dogWolf = new Player("Sabrina");
        dogWolf.setCharacter(new CharDogWolf());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void getInstanceTest() {
        assertEquals(fraction, Fraction.getInstance());
        fraction.addVillager(villager);
        assertEquals(fraction, Fraction.getInstance());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void addVillagerNullTest() {
        assertFalse(fraction.addVillager(null));
    }

    /**
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    @Test
    public void addVillagerTest() {
        assertTrue(fraction.addVillager(villager));
        assertTrue(fraction.addVillager(witch));
        assertTrue(fraction.addVillager(hunter));
        assertTrue(fraction.addVillager(seer));
        assertTrue(fraction.addVillager(cupid));
        assertTrue(fraction.addVillager(littleGirl));
        assertTrue(fraction.addVillager(thief));
        assertTrue(fraction.addVillager(wildChild));
        assertTrue(fraction.addVillager(fox));
        assertTrue(fraction.addVillager(dogWolf));
        assertFalse(fraction.addVillager(werewolf));
        assertFalse(fraction.addVillager(greatWolf));
        assertFalse(fraction.addVillager(bigBadWolf));
        assertFalse(fraction.addVillager(whiteWerewolf));

        assertEquals(villager, fraction.getVillagerList().get(0));
        assertEquals(witch, fraction.getVillagerList().get(1));
        assertEquals(hunter, fraction.getVillagerList().get(2));
        assertEquals(seer, fraction.getVillagerList().get(3));
        assertEquals(cupid, fraction.getVillagerList().get(4));
        assertEquals(littleGirl, fraction.getVillagerList().get(5));
        assertEquals(thief, fraction.getVillagerList().get(6));
        assertEquals(wildChild, fraction.getVillagerList().get(7));
        assertEquals(fox, fraction.getVillagerList().get(8));
        assertEquals(dogWolf, fraction.getVillagerList().get(9));

        assertTrue(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.getVillagerList().contains(witch));
        assertTrue(fraction.getVillagerList().contains(hunter));
        assertTrue(fraction.getVillagerList().contains(seer));
        assertTrue(fraction.getVillagerList().contains(cupid));
        assertTrue(fraction.getVillagerList().contains(littleGirl));
        assertTrue(fraction.getVillagerList().contains(thief));
        assertTrue(fraction.getVillagerList().contains(wildChild));
        assertTrue(fraction.getVillagerList().contains(fox));
        assertTrue(fraction.getVillagerList().contains(dogWolf));

        assertFalse(fraction.getVillagerList().contains(werewolf));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void addVillagerFalseTest() {
        dogWolf.addRole(new RoleWerewolf());

        assertFalse(fraction.addVillager(dogWolf));

        assertFalse(fraction.getVillagerList().contains(dogWolf));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void addWerewolfNullTest() {
        assertFalse(fraction.addWerewolf(null));
    }

    /**
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    @Test
    public void addWerewolfTest() {
        assertTrue(fraction.addWerewolf(werewolf));
        assertTrue(fraction.addWerewolf(greatWolf));
        assertTrue(fraction.addWerewolf(bigBadWolf));
        wildChild.addRole(new RoleWerewolf());
        assertTrue(fraction.addWerewolf(wildChild));
        assertFalse(fraction.addWerewolf(dogWolf));
        dogWolf.addRole(new RoleWerewolf());
        assertTrue(fraction.addWerewolf(dogWolf));

        assertFalse(fraction.addWerewolf(villager));
        assertFalse(fraction.addWerewolf(whiteWerewolf));
        assertFalse(fraction.addWerewolf(fox));

        assertEquals(werewolf, fraction.getWerewolfList().get(0));
        assertEquals(greatWolf, fraction.getWerewolfList().get(1));
        assertEquals(bigBadWolf, fraction.getWerewolfList().get(2));
        assertEquals(wildChild, fraction.getWerewolfList().get(3));
        assertEquals(dogWolf, fraction.getWerewolfList().get(4));

        assertTrue(fraction.getWerewolfList().contains(werewolf));
        assertTrue(fraction.getWerewolfList().contains(greatWolf));
        assertTrue(fraction.getWerewolfList().contains(bigBadWolf));
        assertTrue(fraction.getWerewolfList().contains(wildChild));
        assertTrue(fraction.getWerewolfList().contains(dogWolf));

        assertFalse(fraction.getWerewolfList().contains(villager));
        assertFalse(fraction.getWerewolfList().contains(whiteWerewolf));
        assertFalse(fraction.getWerewolfList().contains(fox));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void addWhiteWerewolfNullTest() {
        assertFalse(fraction.addWhiteWerewolf(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void addWhiteWerewolfTest() {
        assertTrue(fraction.addWhiteWerewolf(whiteWerewolf));
        assertFalse(fraction.addWhiteWerewolf(villager));
        assertFalse(fraction.addWhiteWerewolf(werewolf));
        assertFalse(fraction.addWhiteWerewolf(greatWolf));
        assertEquals(whiteWerewolf, fraction.getWhiteWerewolfList().get(0));
        assertTrue(fraction.getWhiteWerewolfList().contains(whiteWerewolf));

        assertFalse(fraction.getWhiteWerewolfList().contains(werewolf));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void isVillagerEmptyTest() {
        assertTrue(fraction.isVillagerEmpty());
        fraction.addVillager(villager);
        assertFalse(fraction.isVillagerEmpty());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void isWerewolfEmptyTest() {
        assertTrue(fraction.isWerewolfEmpty());
        fraction.addWerewolf(werewolf);
        assertFalse(fraction.isWerewolfEmpty());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isWhiteWerewolfEmptyTest() {
        assertTrue(fraction.isWhiteWerewolfEmpty());
        fraction.addWhiteWerewolf(whiteWerewolf);
        assertFalse(fraction.isWhiteWerewolfEmpty());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isLoversEmpty1Test() {
        //werewolf and villager as lovers
        assertTrue(fraction.isLoversEmpty());
        fraction.addPlayer(werewolf);
        fraction.addPlayer(villager);
        fraction.changeToLover(werewolf, villager);
        assertFalse(fraction.isLoversEmpty());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isLoversEmpty2Test() {
        //white werewolf and villager as lovers
        assertTrue(fraction.isLoversEmpty());
        fraction.addPlayer(whiteWerewolf);
        fraction.addPlayer(villager);
        fraction.changeToLover(whiteWerewolf, villager);
        assertFalse(fraction.isLoversEmpty());
        assertTrue(fraction.isWhiteWerewolfEmpty());
    }

    /**
     * @author Eric De ROn
     */
    @Test
    void isLoversEmpty3Test() {
        // Wild child in love white werewolf
        assertTrue(fraction.isLoversEmpty());
        fraction.addPlayer(whiteWerewolf);
        fraction.addPlayer(wildChild);
        wildChild.addRole(new RoleWildChild());
        wildChild.addRole(new RoleLovers());
        whiteWerewolf.addRole(new RoleLovers());
        assertTrue(fraction.changeToLover(whiteWerewolf, wildChild));
        assertTrue(fraction.changeWildChildToWerewolf(wildChild));
        assertTrue(fraction.isLoversEmpty());
        assertFalse(fraction.isWhiteWerewolfEmpty());
        assertFalse(fraction.isWerewolfEmpty());
    }

    /**
     * @author Eric De ROn
     */
    @Test
    void isLoversEmpty4Test() {
        // Wild child in love with werewolf
        assertTrue(fraction.isLoversEmpty());
        fraction.addPlayer(werewolf);
        fraction.addPlayer(wildChild);
        wildChild.addRole(new RoleWildChild());
        wildChild.addRole(new RoleLovers());
        werewolf.addRole(new RoleLovers());
        assertTrue(fraction.changeToLover(werewolf, wildChild));
        assertTrue(fraction.changeWildChildToWerewolf(wildChild));
        assertTrue(fraction.isLoversEmpty());
        assertFalse(fraction.isWerewolfEmpty());
        assertEquals(2, fraction.getWerewolfList().size());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void removeVillagerTest() {
        fraction.addVillager(villager);
        assertFalse(fraction.removeVillager(werewolf));
        assertTrue(fraction.removeVillager(villager));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    public void removeWerewolfTest() {
        fraction.addWerewolf(werewolf);
        assertFalse(fraction.removeWerewolf(villager));
        assertTrue(fraction.removeWerewolf(werewolf));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void removeWhiteWerewolfTest() {
        fraction.addWhiteWerewolf(whiteWerewolf);
        assertFalse(fraction.removeWhiteWerewolf(villager));
        assertTrue(fraction.removeWhiteWerewolf(whiteWerewolf));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void removeLover1Test() {
        //werewolf and villager as lovers
        fraction.addPlayer(werewolf);
        fraction.addPlayer(villager);
        fraction.addPlayer(witch);
        fraction.addPlayer(otherWerewolf);
        fraction.changeToLover(werewolf, villager);
        assertFalse(fraction.isLoversEmpty());

        assertFalse(fraction.removeLover(witch));
        assertFalse(fraction.removeLover(otherWerewolf));

        assertTrue(fraction.removeLover(werewolf));
        assertFalse(fraction.getLoversList().contains(werewolf));
        assertTrue(fraction.removeLover(villager));
        assertTrue(fraction.isLoversEmpty());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void removeLover2Test() {
        //white werewolf and villager as lovers
        fraction.addPlayer(whiteWerewolf);
        fraction.addPlayer(villager);
        fraction.addPlayer(witch);
        fraction.addPlayer(otherWerewolf);
        fraction.changeToLover(whiteWerewolf, villager);
        assertFalse(fraction.isLoversEmpty());
        assertTrue(fraction.isWhiteWerewolfEmpty());

        assertFalse(fraction.removeLover(witch));
        assertFalse(fraction.removeLover(otherWerewolf));

        assertTrue(fraction.removeLover(whiteWerewolf));
        assertFalse(fraction.getLoversList().contains(whiteWerewolf));
        assertTrue(fraction.removeLover(villager));
        assertTrue(fraction.isLoversEmpty());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void NoWinner1Test() {
        //Test no winner if a werewolf and a villager are in the game
        fraction.addVillager(villager);
        fraction.addWerewolf(werewolf);
        assertNull(fraction.winner());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void NoWinner2Test() {
        //Test no winner if a white werewolf and a villager are in the game
        fraction.addVillager(villager);
        fraction.addWhiteWerewolf(whiteWerewolf);
        assertNull(fraction.winner());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void NoWinner3Test() {
        //Test no winner if a werewolf, a white werewolf and a villager are in the game
        fraction.addVillager(villager);
        fraction.addWerewolf(werewolf);
        fraction.addWhiteWerewolf(whiteWerewolf);
        assertNull(fraction.winner());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void NoWinner4Test() {
        //test no winner if a villager and werewolf are lovers and one extra villager is in the game
        fraction.addVillager(villager);
        fraction.addWerewolf(werewolf);
        fraction.addVillager(witch);
        fraction.changeToLover(villager, werewolf);
        assertNull(fraction.winner());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void NoWinner5Test() {
        //test no winner if a villager and white werewolf are lovers and one extra villager is in the game
        fraction.addVillager(villager);
        fraction.addWhiteWerewolf(whiteWerewolf);
        fraction.addVillager(witch);
        fraction.changeToLover(villager, whiteWerewolf);
        assertNull(fraction.winner());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void NoWinner6Test() {
        //test no winner if 2 werewolves and 1 villager are in the game, lovers are werewolf and villager
        fraction.addVillager(villager);
        fraction.addWerewolf(werewolf);
        fraction.addWerewolf(otherWerewolf);
        fraction.changeToLover(villager, werewolf);
        assertNull(fraction.winner());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void VillagerWinnerTest() {
        fraction.addVillager(villager);
        //assertEquals(new CharVillager(), fraction.winner());
        assertTrue(fraction.winner().isSameClass(new CharVillager()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void LoversWinner1Test() {
        //werewolf and villager are lovers
        fraction.addWerewolf(werewolf);
        fraction.addVillager(villager);
        fraction.changeToLover(werewolf, villager);
        assertTrue(fraction.winner().isSameClass(new CharCupid()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void LoversWinner2Test() {
        //white werewolf and villager are lovers
        fraction.addWhiteWerewolf(whiteWerewolf);
        fraction.addVillager(villager);
        fraction.changeToLover(whiteWerewolf, villager);
        assertTrue(fraction.winner().isSameClass(new CharCupid()));
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void WerewolfWinnerTest() {
        fraction.addWerewolf(werewolf);
        assertTrue(fraction.winner().isSameClass(new CharWerewolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void WhiteWerewolfWinnerTest() {
        fraction.addWhiteWerewolf(whiteWerewolf);
        assertTrue(fraction.winner().isSameClass(new CharWhiteWerewolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void WildChildWinnerTest() {
        fraction.addWerewolf(wildChild);
        assertTrue(fraction.winner().isSameClass(new CharWerewolf()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void allDeadTest() {
        assertTrue(fraction.winner().isSameClass(new CharHunter()));
    }

    /**
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     */
    @Test
    void addPlayerTest() {
        assertFalse(fraction.addPlayer(null));

        assertTrue(fraction.addPlayer(villager));
        assertTrue(fraction.getVillagerList().contains(villager));

        assertTrue(fraction.addPlayer(werewolf));
        assertTrue(fraction.getWerewolfList().contains(werewolf));

        assertTrue(fraction.addPlayer(greatWolf));
        assertTrue(fraction.getWerewolfList().contains(greatWolf));

        assertTrue(fraction.addPlayer(whiteWerewolf));
        assertTrue(fraction.getWhiteWerewolfList().contains(whiteWerewolf));

        assertTrue(fraction.addPlayer(bigBadWolf));
        assertTrue(fraction.getWerewolfList().contains(bigBadWolf));

        assertTrue(fraction.addPlayer(witch));
        assertTrue(fraction.getVillagerList().contains(witch));

        assertTrue(fraction.addPlayer(hunter));
        assertTrue(fraction.getVillagerList().contains(hunter));

        assertTrue(fraction.addPlayer(seer));
        assertTrue(fraction.getVillagerList().contains(seer));

        assertTrue(fraction.addPlayer(cupid));
        assertTrue(fraction.getVillagerList().contains(cupid));

        assertTrue(fraction.addPlayer(littleGirl));
        assertTrue(fraction.getVillagerList().contains(littleGirl));

        assertTrue(fraction.addPlayer(thief));
        assertTrue(fraction.getVillagerList().contains(thief));

        assertTrue(fraction.addPlayer(wildChild));
        assertTrue(fraction.getVillagerList().contains(wildChild));

        assertTrue(fraction.addPlayer(fox));
        assertTrue(fraction.getVillagerList().contains(fox));

        assertTrue(fraction.addPlayer(dogWolf));
        assertTrue(fraction.getVillagerList().contains(dogWolf));
        dogWolf.addRole(new RoleWerewolf());
        assertTrue(fraction.addPlayer(dogWolf));
        assertTrue(fraction.getWerewolfList().contains(dogWolf));

        //player has no character
        assertFalse(fraction.addPlayer(new Player()));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void printTest() {
        assertNotNull(fraction.print());
        String print = "===== Fraktionen =====\n" +
                "Dorfbewohner: []\n" +
                "Werwoelfe: []\n" +
                "Weißer Werwolf: []\n" +
                "Liebespaar: []\n" +
                "===== ===== =====";
        assertEquals(print, fraction.print());
    }

    /**
     * @author Henrik Möhlmann
     * @author Janik Dohrmann
     */
    @Test
    void dieTest() {
        assertFalse(fraction.die(null));

        fraction.addPlayer(villager);
        fraction.addPlayer(werewolf);
        fraction.addPlayer(whiteWerewolf);
        fraction.addPlayer(cupid);
        fraction.addPlayer(otherWerewolf);
        assertTrue(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.getWerewolfList().contains(werewolf));
        fraction.changeToLover(cupid, otherWerewolf);
        assertTrue(fraction.getLoversList().contains(cupid));
        assertTrue(fraction.getLoversList().contains(otherWerewolf));

        assertTrue(fraction.die(villager));
        assertFalse(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.isVillagerEmpty());

        assertTrue(fraction.die(werewolf));
        assertFalse(fraction.getWerewolfList().contains(werewolf));
        assertTrue(fraction.isWerewolfEmpty());

        assertTrue(fraction.die(whiteWerewolf));
        assertFalse(fraction.getWhiteWerewolfList().contains(whiteWerewolf));
        assertTrue(fraction.isWhiteWerewolfEmpty());

        assertTrue(fraction.die(cupid));
        assertTrue(fraction.die(otherWerewolf));
        assertFalse(fraction.getLoversList().contains(cupid));
        assertFalse(fraction.getLoversList().contains(otherWerewolf));
        assertTrue(fraction.isLoversEmpty());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void die2Test() {
        fraction.addPlayer(whiteWerewolf);
        fraction.addPlayer(seer);
        assertTrue(fraction.getWhiteWerewolfList().contains(whiteWerewolf));
        assertTrue(fraction.getVillagerList().contains(seer));
        fraction.changeToLover(seer, whiteWerewolf);
        assertTrue(fraction.getLoversList().contains(seer));
        assertTrue(fraction.getLoversList().contains(whiteWerewolf));

        assertTrue(fraction.die(seer));
        assertTrue(fraction.die(whiteWerewolf));
        assertFalse(fraction.getLoversList().contains(seer));
        assertFalse(fraction.getLoversList().contains(whiteWerewolf));
        assertTrue(fraction.isLoversEmpty());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeToLover1Test() {
        //werewolf and villager lovers
        fraction.addVillager(villager);
        fraction.addVillager(witch);
        fraction.addWerewolf(werewolf);
        fraction.addWerewolf(otherWerewolf);

        assertFalse(fraction.changeToLover(null, null));
        assertFalse(fraction.changeToLover(null, villager));
        assertFalse(fraction.changeToLover(villager, null));
        assertFalse(fraction.changeToLover(werewolf, otherWerewolf));
        assertFalse(fraction.changeToLover(villager, witch));
        assertTrue(fraction.changeToLover(werewolf, villager));

        assertTrue(fraction.getLoversList().contains(werewolf));
        assertFalse(fraction.getWerewolfList().contains(werewolf));

        assertTrue(fraction.getLoversList().contains(villager));
        assertFalse(fraction.getVillagerList().contains(villager));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeToLover2Test() {
        //white werewolf and villager lovers
        fraction.addVillager(villager);
        fraction.addVillager(witch);
        fraction.addWhiteWerewolf(whiteWerewolf);
        fraction.addWerewolf(otherWerewolf);

        assertFalse(fraction.changeToLover(null, null));
        assertFalse(fraction.changeToLover(null, villager));
        assertFalse(fraction.changeToLover(villager, null));
        assertFalse(fraction.changeToLover(whiteWerewolf, otherWerewolf));
        assertFalse(fraction.changeToLover(villager, witch));
        assertTrue(fraction.changeToLover(whiteWerewolf, villager));

        assertTrue(fraction.getLoversList().contains(whiteWerewolf));
        assertFalse(fraction.getWhiteWerewolfList().contains(whiteWerewolf));

        assertTrue(fraction.getLoversList().contains(villager));
        assertFalse(fraction.getVillagerList().contains(villager));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeToWerwolf1() {
        //werewolf victim is not in love
        fraction.addVillager(villager);
        assertTrue(fraction.getVillagerList().contains(villager));

        assertTrue(fraction.changeToWerewolf(villager));
        assertFalse(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.getWerewolfList().contains(villager));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeToWerewolf2() {
        //werewolf victim is in love with an villager
        villager.addRole(new RoleLovers());
        seer.addRole(new RoleLovers());
        fraction.addVillager(villager);
        fraction.addVillager(seer);

        assertTrue(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.getVillagerList().contains(seer));
        assertTrue(fraction.getLoversList().isEmpty());

        assertFalse(fraction.changeToWerewolf(null));
        assertTrue(fraction.changeToWerewolf(seer));

        assertTrue(fraction.getVillagerList().isEmpty());
        assertTrue(fraction.getLoversList().contains(villager));
        assertTrue(fraction.getLoversList().contains(seer));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void changeToWerewolf3() {
        //werewolf in love with werewolf victim
        villager.addRole(new RoleLovers());
        werewolf.addRole(new RoleLovers());
        fraction.addVillager(villager);
        fraction.addWerewolf(werewolf);

        assertFalse(fraction.changeToWerewolf(villager));
        assertTrue(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.getWerewolfList().contains(werewolf));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void changeWildChildToWerewolfTest1() {
        // Wild child is not in love
        wildChild.addRole(new RoleWildChild());
        fraction.addVillager(wildChild);
        assertTrue(fraction.getVillagerList().contains(wildChild));

        assertTrue(fraction.changeWildChildToWerewolf(wildChild));
        assertFalse(fraction.getVillagerList().contains(wildChild));
        assertTrue(fraction.getWerewolfList().contains(wildChild));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void changeWildChildToWerewolfTest2() {
        // Wild child in love with villager
        villager.addRole(new RoleLovers());
        wildChild.addRole(new RoleLovers());
        wildChild.addRole(new RoleWildChild());
        fraction.addVillager(villager);
        fraction.addVillager(wildChild);

        assertTrue(fraction.getVillagerList().contains(villager));
        assertTrue(fraction.getVillagerList().contains(wildChild));
        assertTrue(fraction.getLoversList().isEmpty());

        assertFalse(fraction.changeWildChildToWerewolf(null));
        assertTrue(fraction.changeWildChildToWerewolf(wildChild));

        assertTrue(fraction.getVillagerList().isEmpty());
        assertTrue(fraction.getLoversList().contains(villager));
        assertTrue(fraction.getLoversList().contains(wildChild));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void changeWildChildToWerewolfTest3() {
        /// Wild child is werewolf already
        wildChild.addRole(new RoleWildChild());
        wildChild.addRole(new RoleWerewolf());
        assertFalse(fraction.changeWildChildToWerewolf(wildChild));
    }
}