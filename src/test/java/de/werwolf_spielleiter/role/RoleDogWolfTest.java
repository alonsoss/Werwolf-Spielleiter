package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharDogWolf;
import de.werwolf_spielleiter.character.CharWerewolf;
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
class RoleDogWolfTest {

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role dogWolf = new RoleDogWolf();
        assertEquals("Wolfshund", dogWolf.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesDogWolf() {
        Player player = new Player("Player");
        player.setCharacter(new CharDogWolf());
        List<Role> roles = Role.getRoles(player);
        assertTrue(roles.get(0) instanceof RoleDogWolf);
    }
}