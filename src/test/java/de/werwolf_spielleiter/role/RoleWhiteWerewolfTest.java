package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharWhiteWerewolf;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
@ExtendWith(JfxTestExtension.class)
class RoleWhiteWerewolfTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role whiteWerewolf = new RoleWhiteWerewolf();
        assertEquals("Weißer Werwolf", whiteWerewolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesWhiteWerewolf() {
        Player player = new Player("Player");
        player.setCharacter(new CharWhiteWerewolf());
        List<Role> roleList = Role.getRoles(player);
        assertTrue(roleList.get(0) instanceof RoleWhiteWerewolf);
        assertTrue(roleList.get(1) instanceof RoleWerewolf);
    }
}