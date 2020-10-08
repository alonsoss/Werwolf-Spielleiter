package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

public class VictimWerewolf extends Victim{
    private static final String deathReason = PrivateGameConstants.VICTIM_WEREWOLF_REASON;
    private boolean healed = false;
    private boolean infected = false;

    /**
     * @author Henrik Möhlmann
     */
    public VictimWerewolf() {
        super(null, null);
    }

    /**
     * @author Henrik Möhlmann
     * @param player
     */
    public VictimWerewolf(Player player) {
        super(player, deathReason);
    }

    /**
     * Method to get the werewolf victim healed by the witch.
     * @author Henrik Möhlmann
     */
    public void heal() {
        this.healed = true;
    }

    /**
     * @author Henrik Möhlmann
     * @return true if the witch has healed the werewolf victim.
     *          false otherwise
     */
    public boolean isHealed() {
        return this.healed;
    }

    /**
     * Method to get the victim infected by great wolf.
     * @author Henrik Möhlmann
     */
    public void infect() {
        infected = true;
    }

    /**
     * @author Henrik Möhlmann
     * @return true if the great wolf has infected the werewolf victim
     *          false otherwise
     */
    public boolean isInfected() {
        return infected;
    }
}
