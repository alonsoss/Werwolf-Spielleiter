package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

public class VictimHunter extends Victim {
    private static final String deathReason = PrivateGameConstants.VICTIM_HUNTER_REASON;

    /**
     * @author Eric De Roon
     */
    public VictimHunter(){
        super(null, null);
    }

    /**
     * @author Eric De Ron
     */
    public VictimHunter(Player player){
        super(player,deathReason);
    }
}
