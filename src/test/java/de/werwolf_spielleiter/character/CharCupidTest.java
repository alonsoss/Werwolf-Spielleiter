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
class CharCupidTest {
    private CharCupid cupid;
    private Image cupidFront;
    private Image cupidBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        cupid = new CharCupid();
        cupidFront = new Image(String.valueOf(getClass().getResource("/images/cupid_front.jpg")));
        cupidBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Amor", cupid.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(cupid.getCharacterCardBack());
        assertTrue(cupid.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        cupid.setCharacterCardBack(cupidBack);
        assertEquals(cupidBack, cupid.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        cupid.setCharacterCardFront(cupidFront);
        assertEquals(cupidFront, cupid.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(cupid.getCharacterCardFront());
        assertTrue(cupid.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Amor", cupid.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Amor>", cupid.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Das Liebespaar hat gewonnen!", cupid.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(cupid.isSameClass(new CharCupid()));
        assertFalse(cupid.isSameClass(new CharWerewolf()));
        assertFalse(cupid.isSameClass(null));
    }
}