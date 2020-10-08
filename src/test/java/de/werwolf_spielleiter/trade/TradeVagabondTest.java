package de.werwolf_spielleiter.trade;

import de.werwolf_spielleiter.character.CharSeer;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class TradeVagabondTest {

    private Trade vagabond;

    @BeforeEach
    void setUp() {
        vagabond = new TradeVagabond();
    }

    @Test
    void toStringTest() {
        assertEquals("Vagabund", vagabond.toString());
    }

    @Test
    void isSameClassTest() {
        assertFalse(vagabond.isSameClass(null));
        assertFalse(vagabond.isSameClass(new CharSeer()));
        assertTrue(vagabond.isSameClass(new TradeVagabond()));
    }
}