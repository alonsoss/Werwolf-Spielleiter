package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
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
class CharThiefTest {
    private CharThief thief;
    private Image thiefFront;
    private Image thiefBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        thief = new CharThief();
        thiefFront = new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_THIEF)));
        thiefBack = new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK)));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Dieb", thief.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(thief.getCharacterCardBack());
        assertTrue(thief.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        thief.setCharacterCardBack(thiefBack);
        assertEquals(thiefBack, thief.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        thief.setCharacterCardFront(thiefFront);
        assertEquals(thiefFront, thief.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(thief.getCharacterCardFront());
        assertTrue(thief.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Dieb", thief.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Dieb>", thief.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Dorfbewohner haben gewonnen!", thief.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(thief.isSameClass(new CharThief()));
        assertFalse(thief.isSameClass(new CharWerewolf()));
        assertFalse(thief.isSameClass(null));
    }
}