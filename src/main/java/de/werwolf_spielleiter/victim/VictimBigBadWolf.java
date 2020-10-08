package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

/**
 * @author Henrik Möhlmann
 */
public class VictimBigBadWolf extends Victim {
    private static final String deathReason = PrivateGameConstants.VICTIM_BIG_BAD_WOLF_REASON;

    /**
     * @author Henrik Möhlmann
     */
    public VictimBigBadWolf() {
        super(null, null);
    }

    /**
     * @author Henrik Möhlmann
     * @param player victim of the white werewolf
     */
    public VictimBigBadWolf(Player player) {
        super(player, deathReason);
    }
}
