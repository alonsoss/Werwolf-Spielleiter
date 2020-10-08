package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
@ExtendWith(JfxTestExtension.class)
class CharWitchTest {
    private CharWitch witch;
    private Image witchFront;
    private Image witchBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        witch = new CharWitch();
        witchFront = new Image(String.valueOf(getClass().getResource("/images/witch_front.jpg")));
        witchBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Hexe", witch.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(witch.getCharacterCardBack());
        assertTrue(witch.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        witch.setCharacterCardBack(witchBack);
        assertEquals(witchBack, witch.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        witch.setCharacterCardFront(witchFront);
        assertEquals(witchFront, witch.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(witch.getCharacterCardFront());
        assertTrue(witch.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Hexe", witch.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Hexe>", witch.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Dorfbewohner haben gewonnen!", witch.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(witch.isSameClass(new CharWitch()));
        assertFalse(witch.isSameClass(new CharWerewolf()));
        assertFalse(witch.isSameClass(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void heal() {
        assertTrue(witch.canHeal());
        witch.heal();
        assertFalse(witch.canHeal());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void kill() {
        assertTrue(witch.canKill());
        witch.kill();
        assertFalse(witch.canKill());
    }
}