package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

/**
 * @author Henrik Möhlmann
 */
public class VictimWitch extends Victim {
    private static final String deathReason = PrivateGameConstants.VICTIM_WITCH_REASON;

    /**
     * @author Henrik Möhlmann
     */
    public VictimWitch() {
        super(null, null);
    }

    /**
     * @author Henrik Möhlmann
     * @param player Victim of the witch
     */
    public VictimWitch(Player player) {
        super(player, deathReason);
    }
}
