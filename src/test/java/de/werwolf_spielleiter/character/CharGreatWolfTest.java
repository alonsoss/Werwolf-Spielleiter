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
class CharGreatWolfTest {
    private CharGreatWolf greatWolf;
    private Image front;
    private Image back;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        greatWolf = new CharGreatWolf();
        front = new Image(String.valueOf(getClass().getResource("/images/great_wolf_front.jpg")));
        back = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Urwolf", greatWolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(greatWolf.getCharacterCardBack());
        assertNotNull(greatWolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        greatWolf.setCharacterCardBack(back);
        assertEquals(back, greatWolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        greatWolf.setCharacterCardFront(front);
        assertEquals(front, greatWolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(greatWolf.getCharacterCardFront());
        assertNotNull(greatWolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Urwolf", greatWolf.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Urwolf>", greatWolf.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Werwölfe haben gewonnen!", greatWolf.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(greatWolf.isSameClass(new CharGreatWolf()));
        assertFalse(greatWolf.isSameClass(new CharWerewolf()));
        assertFalse(greatWolf.isSameClass(null));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void infect() {
        assertTrue(greatWolf.canInfect());
        greatWolf.infect();
        assertFalse(greatWolf.canInfect());
    }
}