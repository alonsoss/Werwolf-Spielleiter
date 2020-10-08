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
class CharBigBadWolfTest {
    private CharBigBadWolf bigBadWolf;
    private Image front;
    private Image back;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        bigBadWolf = new CharBigBadWolf();
        front = new Image(String.valueOf(getClass().getResource("/images/bigBadWolf_front.jpg")));
        back = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Großer, böser Wolf", bigBadWolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(bigBadWolf.getCharacterCardBack());
        assertNotNull(bigBadWolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        bigBadWolf.setCharacterCardBack(back);
        assertEquals(back, bigBadWolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        bigBadWolf.setCharacterCardFront(front);
        assertEquals(front, bigBadWolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(bigBadWolf.getCharacterCardFront());
        assertNotNull(bigBadWolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Großer, böser Wolf", bigBadWolf.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Großer, böser Wolf>", bigBadWolf.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Werwölfe haben gewonnen!", bigBadWolf.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(bigBadWolf.isSameClass(new CharBigBadWolf()));
        assertFalse(bigBadWolf.isSameClass(new CharVillager()));
        assertFalse(bigBadWolf.isSameClass(null));
    }
}