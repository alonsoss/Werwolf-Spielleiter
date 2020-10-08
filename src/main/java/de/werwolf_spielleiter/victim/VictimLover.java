package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

/**
 * @author Henrik Möhlmann
 */
public class VictimLover extends Victim {
    private static final String deathReason = PrivateGameConstants.VICTIM_LOVER_REASON;

    /**
     * @author Henrik Möhlmann
     */
    public VictimLover() {
        super(null, null);
    }

    /**
     * @param player victim died of heartbreak
     * @author Henrik Möhlmann
     */
    public VictimLover(Player player) {
        super(player, deathReason);
    }
}
