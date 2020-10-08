package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
@ExtendWith(JfxTestExtension.class)
class RoleWerewolfTest {

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role werewolf = new RoleWerewolf();
        assertEquals("Werwolf", werewolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesWerewolf() {
        Player player = new Player("Player");
        player.setCharacter(new CharWerewolf());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleWerewolf);
    }
}