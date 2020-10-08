package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharGreatWolf;
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
class RoleGreatWolfTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role greatWolf = new RoleGreatWolf();
        assertEquals("Urwolf", greatWolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesGreatWolf() {
        Player player = new Player("Player");
        player.setCharacter(new CharGreatWolf());
        List<Role> roleList = Role.getRoles(player);
        assertTrue(roleList.get(0) instanceof RoleGreatWolf);
        assertTrue(roleList.get(1) instanceof RoleWerewolf);
    }
}