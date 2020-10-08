package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.character.CharWitch;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(JfxTestExtension.class)
class RoleHunterTest {

    /**
     * @author Eric De Ron
     */
    @Test
    void getName() {
        Role hunter = new RoleHunter();
        assertEquals("Jäger", hunter.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesHunter() {
        Player player = new Player("Player");
        player.setCharacter(new CharHunter());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleHunter);
    }
}