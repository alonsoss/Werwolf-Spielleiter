package de.werwolf_spielleiter.trade;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(JfxTestExtension.class)
class TradeFarmerTest {

    private Trade farmer;

    @BeforeEach
    void setUp() {
        farmer = new TradeFarmer();
    }
    @Test
    void toStringTest(){
        assertEquals("Bauer", farmer.toString());
    }

    @Test
    void isSameClass() {
        assertFalse(farmer.isSameClass(null));
        assertFalse(farmer.isSameClass(new CharHunter()));
        assertTrue(farmer.isSameClass(new TradeFarmer()));
    }
}