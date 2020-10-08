package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharSeer;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(JfxTestExtension.class)
class RoleSeerTest {
    /**
     * @author Janik Dohrmann
     */
    @Test
    void getName() {
        Role seer = new RoleSeer();
        assertEquals("Seherin", seer.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesSeer() {
        Player player = new Player("Player");
        player.setCharacter(new CharSeer());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleSeer);
    }
}