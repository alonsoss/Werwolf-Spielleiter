package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.CharFox;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import de.werwolf_spielleiter.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Matthias Hinrichs
 */
@ExtendWith(JfxTestExtension.class)
public class RoleFoxTest {
    private Player player;
    private RoleFox fox;

    /**
     * @author Matthias Hinrichs
     */
    @BeforeEach
    void init() {
        player = new Player("Testplayer");
        player.setCharacter(new CharFox());
        fox = new RoleFox();
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void getNameTest() {
        assertEquals("Fuchs", fox.getName());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void getRolesTest() {
        List<Role> roles = Role.getRoles(player);
        assertEquals(1, roles.size());
        assertTrue(roles.get(0) instanceof RoleFox);
    }

}
