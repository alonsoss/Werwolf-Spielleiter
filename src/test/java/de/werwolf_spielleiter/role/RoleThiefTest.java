package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharThief;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class RoleThiefTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role thief = new RoleThief();
        assertEquals("Dieb", thief.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesThief() {
        Player player = new Player("Player");
        player.setCharacter(new CharThief());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleThief);
    }
}