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
class CharDogWolfTest {
    private CharDogWolf dogWolf;
    private Image dogWolfFront;
    private Image dogWolfBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        dogWolf = new CharDogWolf();
        dogWolfFront = new Image(String.valueOf(getClass().getResource("/images/dog_wolf_front.jpg")));
        dogWolfBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Wolfshund", dogWolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(dogWolf.getCharacterCardBack());
        assertTrue(dogWolf.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        dogWolf.setCharacterCardBack(dogWolfBack);
        assertEquals(dogWolfBack, dogWolf.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        dogWolf.setCharacterCardFront(dogWolfFront);
        assertEquals(dogWolfFront, dogWolf.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(dogWolf.getCharacterCardFront());
        assertTrue(dogWolf.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Wolfshund", dogWolf.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Wolfshund>", dogWolf.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("", dogWolf.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(dogWolf.isSameClass(new CharDogWolf()));
        assertFalse(dogWolf.isSameClass(new CharVillager()));
        assertFalse(dogWolf.isSameClass(null));
    }
}