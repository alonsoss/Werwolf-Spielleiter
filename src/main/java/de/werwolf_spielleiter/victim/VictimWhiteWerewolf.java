package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

/**
 * @author Henrik Möhlmann
 */
public class VictimWhiteWerewolf extends Victim {
    private static final String deathReason = PrivateGameConstants.VICTIM_WHITE_WEREWOLF_REASON;

    /**
     * @author Henrik Möhlmann
     */
    public VictimWhiteWerewolf() {
        super(null, null);
    }

    /**
     * @author Henrik Möhlmann
     * @param player victim of the white werewolf
     */
    public VictimWhiteWerewolf(Player player) {
        super(player, deathReason);
    }
}
