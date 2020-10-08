package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Eric De Ron
 * @author Henrik Möhlmann
 */
@ExtendWith(JfxTestExtension.class)
class CharHunterTest {
    private CharHunter hunter;
    private Image hunterFront;
    private Image hunterBack;

    /**
     * @author Eric De Ron
     */
    @BeforeEach
    void initData() {
        hunter = new CharHunter();
        hunterFront = new Image(String.valueOf(getClass().getResource("/images/hunter_front.jpg")));
        hunterBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getName() {
        assertEquals("Jäger", hunter.getName());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(hunter.getCharacterCardBack());
        assertTrue(hunter.getCharacterCardBack() != null);
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setCharacterCardBack() {
        hunter.setCharacterCardBack(hunterBack);
        assertEquals(hunterBack, hunter.getCharacterCardBack());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setCharacterCardFront() {
        hunter.setCharacterCardFront(hunterFront);
        assertEquals(hunterFront, hunter.getCharacterCardFront());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(hunter.getCharacterCardFront());
        assertTrue(hunter.getCharacterCardFront() != null);
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void testToString() {
        assertEquals("Jäger", hunter.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Jäger>", hunter.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Es hat keine Fraktion gewonnen!", hunter.winText());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void isSameClass() {
        assertTrue(hunter.isSameClass(new CharHunter()));
        assertFalse(hunter.isSameClass(null));
        assertFalse(hunter.isSameClass(new CharWerewolf()));
    }
}