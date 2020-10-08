package de.werwolf_spielleiter.trade;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class TradeConfessorTest {
    private Trade confessor;

    @BeforeEach
    void setUp() {
        confessor = new TradeConfessor();
    }
    @Test
    void toStringTest(){
        assertEquals("Beichtvater", confessor.toString());
    }

    @Test
    void isSameClass() {
        assertFalse(confessor.isSameClass(null));
        assertFalse(confessor.isSameClass(new CharHunter()));
        assertFalse(confessor.isSameClass(new TradeFarmer()));
        assertTrue(confessor.isSameClass(new TradeConfessor()));
    }
}