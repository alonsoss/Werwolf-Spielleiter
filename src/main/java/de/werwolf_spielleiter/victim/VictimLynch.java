package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

public class VictimLynch extends Victim {
    private static final String deathReason = PrivateGameConstants.VICTIM_LYNCH_REASON;

    /**
     * @author Florian Müller
     */
    public VictimLynch(){
        super(null, null);
    }
    /**
     * @author FLorian Müller
     */
    public VictimLynch(Player player){
        super(player,deathReason);
    }
}
