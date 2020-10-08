package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharLittleGirl;
import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(JfxTestExtension.class)
class RoleLittleGirlTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role littleGirl = new RoleLittleGirl();
        assertEquals("Kleines Mädchen", littleGirl.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRolesLittleGirl() {
        Player player = new Player("Player");
        player.setCharacter(new CharLittleGirl());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleLittleGirl);
    }
}