package de.werwolf_spielleiter.trade;

import de.werwolf_spielleiter.player.Player;

import java.util.LinkedList;
import java.util.List;

public class Trades {
    private static Trades self = null;

    private List<Player> vagabondList = new LinkedList<>();
    private List<Player> farmerList = new LinkedList<>();
    private Player confessor = null;

    /**
     * Method to get the player in the right fraction
     * @author Janik Dohrmann
     * @author Florian Müller
     * @param player the player to be filled in the Trades
     * @return true if the right trades was found,
     *          false if not
     */
    public boolean addPlayer(Player player) {
        if (player == null) {
            return false;
        } else if (player.getTrade().isSameClass(new TradeVagabond())) {
            vagabondList.add(player);
            return true;
        } else if (player.getTrade().isSameClass(new TradeFarmer())) {
            farmerList.add(player);
            return true;
        } else if (player.getTrade().isSameClass(new TradeConfessor())) {
            confessor = player;
            return true;
        } else {
            return false;
        }
    }

    /**
     * remove the player from the farmer list
     * @author Janik Dohrmann
     * @author Florian Müller
     * @param p The player to remove
     * @return true if the right trades was found,
     *          false if not
     */
    public boolean removePlayer(Player p) {
        if (p == null) {
            return false;
        } else if (p.getTrade().isSameClass(new TradeVagabond())) {
            vagabondList.remove(p);
            return true;
        } else if (p.getTrade().isSameClass(new TradeFarmer())) {
            farmerList.remove(p);
            return true;
        } else if (p.getTrade().isSameClass(new TradeConfessor())) {
            confessor = null;
            return true;
        }
        return false;
    }

    /**
     * @return An instance of this class
     * @author Janik Dohrmann
     * @return the Vagabond list
     */
    public List<Player> getVagabondList() {
        return vagabondList;
    }

    public Player getConfessor() {
        return confessor;
    }

    /**
     * get the list of the farmers
     * @return
     * @author Florian Müller
     */
    public List<Player> getFarmerList() {
        return farmerList;
    }

    /**
     * @author Janik Dohrmann
     * @return An instance of this class
     */
    public static Trades getInstance() {
        if (self == null) {
            self = new Trades();
        }
        return self;
    }

    /**
     * Reset the Fraction object
     *
     * @author Henrik Möhlmann
     */
    public static void reset() {
        self = new Trades();
    }
}
