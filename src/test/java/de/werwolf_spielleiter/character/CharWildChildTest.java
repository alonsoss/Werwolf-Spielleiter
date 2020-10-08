package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Eric De Ron
 */
@ExtendWith(JfxTestExtension.class)
class CharWildChildTest {
    private CharWildChild wildChild;
    private Image wildChildFront;
    private Image wildChildBack;

    /**
     * @author Eric De Ron
     */
    @BeforeEach
    void initData() {
        wildChild = new CharWildChild();
        wildChildFront = new Image(String.valueOf(getClass().getResource("/images/wild_child_front.jpg")));
        wildChildBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getName() {
        assertEquals("Wildes Kind", wildChild.getName());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(wildChild.getCharacterCardBack());
        assertTrue(wildChild.getCharacterCardBack() != null);
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setCharacterCardBack() {
        wildChild.setCharacterCardBack(wildChildBack);
        assertEquals(wildChildBack, wildChild.getCharacterCardBack());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void setCharacterCardFront() {
        wildChild.setCharacterCardFront(wildChildFront);
        assertEquals(wildChildFront, wildChild.getCharacterCardFront());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(wildChild.getCharacterCardFront());
        assertTrue(wildChild.getCharacterCardFront() != null);
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void testToString() {
        assertEquals("Wildes Kind", wildChild.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Wildes Kind>", wildChild.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("", wildChild.winText());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void isSameClass() {
        assertTrue(wildChild.isSameClass(new CharWildChild()));
        assertFalse(wildChild.isSameClass(null));
        assertFalse(wildChild.isSameClass(new CharWerewolf()));
    }
}