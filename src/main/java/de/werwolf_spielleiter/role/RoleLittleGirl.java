package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;

import java.lang.annotation.Target;

/**
 * @author Henrik Möhlmann
 */
public class RoleLittleGirl extends Role {
    /**
     * @author Henrik Möhlmann
     */
    public RoleLittleGirl() {
        super(PrivateGameConstants.ROLE_LITTLE_GIRL_NAME);
    }
}
