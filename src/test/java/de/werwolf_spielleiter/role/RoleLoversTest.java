package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(JfxTestExtension.class)
class RoleLoversTest {
    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        Role lover = new RoleLovers();
        assertEquals("Liebespaar", lover.getName());
    }

}