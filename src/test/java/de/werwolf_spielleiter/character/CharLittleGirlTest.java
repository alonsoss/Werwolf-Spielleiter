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
class CharLittleGirlTest {
    private CharLittleGirl littleGirl;
    private Image littleGirlFront;
    private Image littleGirlBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        littleGirl = new CharLittleGirl();
        littleGirlFront = new Image(String.valueOf(getClass().getResource("/images/littleGirl_front.jpg")));
        littleGirlBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Kleines Mädchen", littleGirl.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(littleGirl.getCharacterCardBack());
        assertTrue(littleGirl.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        littleGirl.setCharacterCardBack(littleGirlBack);
        assertEquals(littleGirlBack, littleGirl.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        littleGirl.setCharacterCardFront(littleGirlFront);
        assertEquals(littleGirlFront, littleGirl.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(littleGirl.getCharacterCardFront());
        assertTrue(littleGirl.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Kleines Mädchen", littleGirl.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Kleines Mädchen>", littleGirl.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Dorfbewohner haben gewonnen!", littleGirl.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(littleGirl.isSameClass(new CharLittleGirl()));
        assertFalse(littleGirl.isSameClass(new CharWerewolf()));
        assertFalse(littleGirl.isSameClass(null));
    }
}