package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharBigBadWolf;
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
class RoleBigBadWolfTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role bigBadWolf = new RoleBigBadWolf();
        assertEquals("Großer, böser Wolf", bigBadWolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesBigBadWolf() {
        Player player = new Player("Player");
        player.setCharacter(new CharBigBadWolf());
        List<Role> roleList = Role.getRoles(player);
        assertTrue(roleList.get(0) instanceof RoleBigBadWolf);
        assertTrue(roleList.get(1) instanceof RoleWerewolf);
    }
}