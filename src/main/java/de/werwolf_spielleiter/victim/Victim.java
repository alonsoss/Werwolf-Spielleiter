package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.player.Player;

public abstract class Victim {
    private Player player;
    private String deathReason;

    /**
     * @author Henrik Möhlmann
     * @param player
     */
    public Victim(Player player, String deathReason) {
        this.player = player;
        this.deathReason = deathReason;
    }

    /**
     * @author Henrik Möhlmann
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @author Henrik Möhlmann
     * @return
     */
    public String getDeathReason() {
        return deathReason;
    }

    /**
     * Reset the victim object.
     * @author Henrik Möhlmann
     */
    public void reset() {
        this.player = null;
        this.deathReason = null;
    }

    /**
     *
     * @author Henrik Möhlmann
     * @return true if the victim is set with a player
     *          false else
     */
    public boolean isSet() {
        return this.player != null;
    }
    /**
     * @author Henrik Möhlmann
     * @return
     */
    @Override
    public String toString() {
        return this.player.toString();
    }
}
