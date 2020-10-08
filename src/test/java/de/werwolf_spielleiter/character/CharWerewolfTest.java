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
class CharWerewolfTest {
    private CharWerewolf werewolf;
    private Image werewolfFront;
    private Image werewolfBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        werewolf = new CharWerewolf();
        werewolfFront = new Image(String.valueOf(getClass().getResource("/images/werewolf_front.jpg")));
        werewolfBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Werwolf", werewolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(werewolf.getCharacterCardBack());
        assertTrue(werewolf.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        werewolf.setCharacterCardBack(werewolfBack);
        assertEquals(werewolfBack, werewolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        werewolf.setCharacterCardFront(werewolfFront);
        assertEquals(werewolfFront, werewolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(werewolf.getCharacterCardFront());
        assertTrue(werewolf.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Werwolf", werewolf.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Werwolf>", werewolf.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Werwölfe haben gewonnen!", werewolf.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(werewolf.isSameClass(new CharWerewolf()));
        assertFalse(werewolf.isSameClass(new CharVillager()));
        assertFalse(werewolf.isSameClass(null));
    }
}