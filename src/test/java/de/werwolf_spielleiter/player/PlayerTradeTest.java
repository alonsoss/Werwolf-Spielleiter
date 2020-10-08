package de.werwolf_spielleiter.player;

import de.werwolf_spielleiter.trade.TradeVagabond;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTradeTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("player");
    }

    @Test
    void getTradeNull() {
        assertNull(player.getTrade());
    }

    @Test
    void setTrade() {
        player.setTrade(new TradeVagabond());
        assertEquals(new TradeVagabond(), player.getTrade());
    }

    @Test
    void setTradeNull() {
        player.setTrade(null);
        assertNull(player.getTrade());
        player.setTrade(new TradeVagabond());
        player.setTrade(null);
        assertNotNull(player.getTrade());
    }

    @Test
    void hasTrade() {
        assertFalse(player.hasTrade(new TradeVagabond()));
        player.setTrade(new TradeVagabond());
        assertTrue(player.hasTrade(new TradeVagabond()));
    }
}