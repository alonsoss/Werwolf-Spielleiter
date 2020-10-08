package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.character.CharWitch;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
@ExtendWith(JfxTestExtension.class)
class RoleWitchTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role witch = new RoleWitch();
        assertEquals("Hexe", witch.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesWitch() {
        Player player = new Player("Player");
        player.setCharacter(new CharWitch());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleWitch);
    }
}