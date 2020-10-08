package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Matthias Hinrichs
 */
@ExtendWith(JfxTestExtension.class)
public class CharFoxTest {
    private CharFox fox;
    private Image front;
    private Image back;

    /**
     * @author Matthias Hinrichs
     */
    @BeforeEach
    void init() {
        fox = new CharFox();
        front = new Image(String.valueOf(getClass().getResource("/images/fox_front.jpg")));
        back = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }


    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Fuchs", fox.getName());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void getCharacterCardBackTest() {
        assertEquals(back.getUrl(), fox.getCharacterCardBack().getUrl());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void getCharacterCardFrontTest() {
        assertEquals(front.getUrl(), fox.getCharacterCardFront().getUrl());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void setCharacterCardBackTest() {
        fox.setCharacterCardBack(back);
        assertEquals(back, fox.getCharacterCardBack());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void setCharacterCardFrontTest() {
        fox.setCharacterCardFront(front);
        assertEquals(front, fox.getCharacterCardFront());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void toStringTest() {
        assertEquals("Fuchs", fox.toString());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void printTest() {
        assertEquals("Char<Fuchs>", fox.print());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void winTextTest() {
        assertEquals("Die Dorfbewohner haben gewonnen!", fox.winText());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void isSameClassTest() {
        assertTrue(fox.isSameClass(new CharFox()));
        assertFalse(fox.isSameClass(new CharBigBadWolf()));
        assertFalse(fox.isSameClass(null));
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void checkTest() {
        assertTrue(fox.canCheck());
        fox.forbidChecks();
        assertFalse(fox.canCheck());
    }
}
