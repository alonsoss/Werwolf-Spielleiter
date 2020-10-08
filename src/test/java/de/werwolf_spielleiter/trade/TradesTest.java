package de.werwolf_spielleiter.trade;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class TradesTest {

    private Trades trades;
    Player vagabond;
    Player farmer;
    Player confessor;

    @BeforeEach
    void setup() {
        Trades.reset();
        trades = Trades.getInstance();
        vagabond = new Player();
        vagabond.setTrade(new TradeVagabond());
        farmer = new Player();
        farmer.setTrade(new TradeFarmer());
        confessor = new Player();
        confessor.setTrade(new TradeConfessor());
    }

    @Test
    void addPlayer() {
        assertFalse(trades.addPlayer(null));
        assertTrue(trades.addPlayer(vagabond));
        assertTrue(trades.getVagabondList().contains(vagabond));
        assertTrue(trades.addPlayer(farmer));
        assertTrue(trades.getFarmerList().contains(farmer));
        assertTrue(trades.addPlayer(confessor));
        assertEquals(trades.getConfessor(), confessor);
    }

    @Test
    void removePlayer() {
        assertFalse(trades.removePlayer(null));
        assertTrue(trades.removePlayer(vagabond));
        assertFalse(trades.getVagabondList().contains(vagabond));
        assertTrue(trades.removePlayer(farmer));
        assertFalse(trades.getFarmerList().contains(farmer));
        assertTrue(trades.removePlayer(confessor));
        assertEquals(trades.getConfessor(), null);
    }
}