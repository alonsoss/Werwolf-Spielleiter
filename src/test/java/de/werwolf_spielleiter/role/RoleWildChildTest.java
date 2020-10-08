package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.character.CharWildChild;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(JfxTestExtension.class)
class RoleWildChildTest {

    /**
     * @author Eric De Ron
     */
    @Test
    void getName() {
        Role wildChild = new RoleWildChild();
        assertEquals("Wildes Kind", wildChild.getName());
    }

    /**
     * @author Eric De Ron
     */
    @Test
    void getRolesWildChild() {
        Player player = new Player("Wildes Kind");
        player.setCharacter(new CharWildChild());
        Role role = Role.getRoles(player).get(0);
        assertTrue(role instanceof RoleWildChild);
    }
}