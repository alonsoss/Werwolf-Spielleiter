package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharCupid;
import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(JfxTestExtension.class)
class RoleCupidTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role cupid = new RoleCupid();
        assertEquals("Amor", cupid.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesCupid() {
        Player player = new Player("Player");
        player.setCharacter(new CharCupid());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleCupid);
    }
}