package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class CharSeerTest {

    private CharSeer charSeer;

    /**
     * @author Janik Dohrmann
     */
    @BeforeEach
    void setUp() {
        charSeer = new CharSeer();
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void winText() {
        assertEquals("Die Dorfbewohner haben gewonnen!", charSeer.winText());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void isSameClass() {
        assertTrue(charSeer.isSameClass(new CharSeer()));
    }
}