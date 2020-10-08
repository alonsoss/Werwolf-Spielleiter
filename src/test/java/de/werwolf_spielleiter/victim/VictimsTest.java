package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.character.CharVillager;
import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.character.CharWhiteWerewolf;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.role.RoleWhiteWerewolf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 * @author Eric De Ron
 */
@ExtendWith(JfxTestExtension.class)
class VictimsTest {
    private Victims victims;
    private Player victoria;
    private Player werewolf;
    private Player whiteWerewolf;
    private final String WEREWOLF_DEATH_REASON = "Werwolf Opfer";
    private final String BIG_BAD_WOLF_DEATH_REASON = "Großer, böser Wolf Opfer";
    private final String WHITE_WEREWOLF_DEATH_REASON = "Weißer Werwolf Opfer";
    private final String LYNCH_DEATH_REASON = "Lynch Opfer";
    private final String WITCH_DEATH_REASON = "Hexe Opfer";
    private final String HUNTER_DEATH_REASON = "Jäger Opfer";
    private final String LOVER_DEATH_REASON = "Liebeskummer";

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void init() {
        victims = new Victims();
        victoria = new Player("Victoria");
        victoria.setCharacter(new CharVillager());

        werewolf = new Player("Fanny");
        werewolf.setCharacter(new CharWerewolf());
        werewolf.addRole(new RoleWerewolf());

        whiteWerewolf = new Player("Leandra");
        whiteWerewolf.setCharacter(new CharWhiteWerewolf());
        whiteWerewolf.addRole(new RoleWerewolf());
        whiteWerewolf.addRole(new RoleWhiteWerewolf());
    }

    /**
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    @Test
    void toStringTest() {
        victims.setVictimWerewolf(victoria);
        victims.setVictimBigBadWolf(victoria);
        victims.setVictimWhiteWerewolf(werewolf);
        victims.setVictimLynch(victoria);
        victims.setVictimWitch(victoria);
        victims.setVictimHunter(victoria);
        victims.setVictimLover(victoria);
        assertEquals(victoria.toString(), victims.getVictimWerewolf().toString());
        assertEquals(victoria.toString(), victims.getVictimBigBadWolf().toString());
        assertEquals(werewolf.toString(), victims.getVictimWhiteWerewolf().toString());
        assertEquals(victoria.toString(), victims.getVictimLynch().toString());
        assertEquals(victoria.toString(), victims.getVictimWitch().toString());
        assertEquals(victoria.toString(), victims.getVictimHunter().toString());
        assertEquals(victoria.toString(), victims.getVictimLover().toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getWerewolfVictim() {
        assertFalse(victims.getVictimWerewolf().isSet());
        assertTrue(victims.setVictimWerewolf(victoria));
        assertEquals(victoria, victims.getVictimWerewolf().getPlayer());
        assertEquals(WEREWOLF_DEATH_REASON, victims.getVictimWerewolf().getDeathReason());

        victims.getVictimWerewolf().heal();
        assertTrue(victims.getVictimWerewolf().isHealed());

        victims.getVictimWerewolf().infect();
        assertTrue(victims.getVictimWerewolf().isInfected());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setWerewolfVictim() {
        assertFalse(victims.setVictimWerewolf(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getBigBadWolfVictim() {
        assertFalse(victims.getVictimBigBadWolf().isSet());

        assertFalse(victims.setVictimBigBadWolf(werewolf));
        assertFalse(victims.setVictimBigBadWolf(whiteWerewolf));

        assertFalse(victims.getVictimBigBadWolf().isSet());

        assertTrue(victims.setVictimBigBadWolf(victoria));
        assertEquals(victoria, victims.getVictimBigBadWolf().getPlayer());
        assertEquals(BIG_BAD_WOLF_DEATH_REASON, victims.getVictimBigBadWolf().getDeathReason());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setBigBadWolfVictim() {
        assertFalse(victims.setVictimBigBadWolf(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getWhiteWerewolfVictim() {
        assertFalse(victims.getVictimWhiteWerewolf().isSet());

        assertFalse(victims.setVictimWhiteWerewolf(victoria));
        assertFalse(victims.setVictimWhiteWerewolf(whiteWerewolf));

        assertFalse(victims.getVictimWhiteWerewolf().isSet());

        assertTrue(victims.setVictimWhiteWerewolf(werewolf));
        assertEquals(werewolf, victims.getVictimWhiteWerewolf().getPlayer());
        assertEquals(WHITE_WEREWOLF_DEATH_REASON, victims.getVictimWhiteWerewolf().getDeathReason());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setVictimWhiteWerewolf() {
        assertFalse(victims.setVictimWhiteWerewolf(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getLynchVictim() {
        assertFalse(victims.getVictimLynch().isSet());
        assertTrue(victims.setVictimLynch(victoria));
        assertEquals(victoria, victims.getVictimLynch().getPlayer());
        assertEquals(LYNCH_DEATH_REASON, victims.getVictimLynch().getDeathReason());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setLynchVictim() {
        assertFalse(victims.setVictimLynch(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getLoverVictim() {
        assertFalse(victims.getVictimLover().isSet());
        assertTrue(victims.setVictimLover(victoria));
        assertEquals(victoria, victims.getVictimLover().getPlayer());
        assertEquals(LOVER_DEATH_REASON, victims.getVictimLover().getDeathReason());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setLoverVictim() {
        assertFalse(victims.setVictimLover(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getVictimWitch() {
        assertFalse(victims.getVictimWitch().isSet());
        assertTrue(victims.setVictimWitch(victoria));
        assertEquals(victoria, victims.getVictimWitch().getPlayer());
        assertEquals(WITCH_DEATH_REASON, victims.getVictimWitch().getDeathReason());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setVictimWitch() {
        assertFalse(victims.setVictimWitch(null));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getVictimHunter() {
        assertFalse(victims.getVictimHunter().isSet());
        assertTrue(victims.setVictimHunter(victoria));
        assertEquals(victoria, victims.getVictimHunter().getPlayer());
        assertEquals(HUNTER_DEATH_REASON, victims.getVictimHunter().getDeathReason());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setVictimHunterNull() { assertFalse(victims.setVictimHunter(null)); }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void reset() {
        victims.setVictimWerewolf(victoria);
        victims.setVictimBigBadWolf(victoria);
        victims.setVictimWhiteWerewolf(werewolf);
        victims.setVictimWitch(victoria);
        victims.setVictimHunter(victoria);
        victims.setVictimLynch(victoria);
        victims.setVictimLover(victoria);

        assertEquals(victoria, victims.getVictimWerewolf().getPlayer());
        assertEquals(victoria, victims.getVictimBigBadWolf().getPlayer());
        assertEquals(werewolf, victims.getVictimWhiteWerewolf().getPlayer());
        assertEquals(victoria, victims.getVictimWitch().getPlayer());
        assertEquals(victoria, victims.getVictimHunter().getPlayer());
        assertEquals(victoria, victims.getVictimLynch().getPlayer());
        assertEquals(victoria, victims.getVictimLover().getPlayer());

        victims.reset();

        assertFalse(victims.getVictimWerewolf().isSet());
        assertFalse(victims.getVictimBigBadWolf().isSet());
        assertFalse(victims.getVictimWhiteWerewolf().isSet());
        assertFalse(victims.getVictimWitch().isSet());
        assertFalse(victims.getVictimHunter().isSet());
        assertFalse(victims.getVictimLynch().isSet());
        assertFalse(victims.getVictimLover().isSet());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void resetLovers() {
        victims.setVictimLover(victoria);
        assertEquals(victoria, victims.getVictimLover().getPlayer());
        victims.resetLovers();
        assertFalse(victims.getVictimLover().isSet());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertTrue(victims.print().contains("Werwolf Opfer"));
        assertTrue(victims.print().contains("Großer, böser Wolf Opfer"));
        assertTrue(victims.print().contains("Weißer Werwolf Opfer"));
        assertTrue(victims.print().contains("Hexe Opfer"));
        assertTrue(victims.print().contains("Lynch Opfer"));
        assertTrue(victims.print().contains("Liebeskummer Opfer"));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsWerewolfVictim1() {
        //werewolf victim is healed
        assertFalse(victims.hasNightVictims());
        victims.setVictimWerewolf(victoria);
        assertTrue(victims.hasNightVictims());
        victims.getVictimWerewolf().heal();
        assertFalse(victims.hasNightVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsWerewolfVictim2() {
        //werewolf victim is infected
        assertFalse(victims.hasNightVictims());
        victims.setVictimWerewolf(victoria);
        assertTrue(victims.hasNightVictims());
        victims.getVictimWerewolf().infect();
        assertFalse(victims.hasNightVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsWerewolfVictim3() {
        //werewolf victim is healed and infected
        assertFalse(victims.hasNightVictims());
        victims.setVictimWerewolf(victoria);
        assertTrue(victims.hasNightVictims());
        victims.getVictimWerewolf().heal();
        victims.getVictimWerewolf().infect();
        assertFalse(victims.hasNightVictims());
    }


    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsWerewolfVictim4() {
        //werewolf victim normal
        assertFalse(victims.hasNightVictims());
        victims.setVictimWerewolf(victoria);
        assertTrue(victims.hasNightVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsBigBadWolfVictim() {
        assertFalse(victims.hasNightVictims());
        assertTrue(victims.setVictimBigBadWolf(victoria));
        assertTrue(victims.hasNightVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsWitchVictim() {
        assertFalse(victims.hasNightVictims());
        victims.setVictimWitch(victoria);
        assertTrue(victims.hasNightVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasNightVictimsWhiteWerewolfVictim() {
        assertFalse(victims.hasNightVictims());
        victims.setVictimWhiteWerewolf(werewolf);
        assertTrue(victims.hasNightVictims());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasLynchVictims() {
        assertFalse(victims.hasLynchVictims());
        victims.setVictimLynch(victoria);
        assertTrue(victims.hasLynchVictims());
    }
}