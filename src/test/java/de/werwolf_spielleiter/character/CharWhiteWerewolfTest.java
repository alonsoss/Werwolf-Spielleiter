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
class CharWhiteWerewolfTest {
    private CharWhiteWerewolf whiteWerewolf;
    private Image front;
    private Image back;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        whiteWerewolf = new CharWhiteWerewolf();
        front = new Image(String.valueOf(getClass().getResource("/images/white_werewolf_front.jpg")));
        back = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Weißer Werwolf", whiteWerewolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(whiteWerewolf.getCharacterCardBack());
        assertNotNull(whiteWerewolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        whiteWerewolf.setCharacterCardBack(back);
        assertEquals(back, whiteWerewolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        whiteWerewolf.setCharacterCardFront(front);
        assertEquals(front, whiteWerewolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(whiteWerewolf.getCharacterCardFront());
        assertNotNull(whiteWerewolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Weißer Werwolf", whiteWerewolf.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Weißer Werwolf>", whiteWerewolf.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Der Weiße Werwolf hat gewonnen!", whiteWerewolf.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(whiteWerewolf.isSameClass(new CharWhiteWerewolf()));
        assertFalse(whiteWerewolf.isSameClass(new CharVillager()));
        assertFalse(whiteWerewolf.isSameClass(null));
    }
}