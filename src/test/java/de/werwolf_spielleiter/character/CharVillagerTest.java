package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.extension.JfxTestExtension;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Henrik Möhlmann
 */
@ExtendWith(JfxTestExtension.class)
class CharVillagerTest {
    private CharVillager villager;
    private Image villagerFront;
    private Image villagerBack;

    /**
     * @author Henrik Möhlmann
     */
    @BeforeEach
    void initData() {
        villager = new CharVillager();
        villagerFront = new Image(String.valueOf(getClass().getResource("/images/villager_front.jpg")));
        villagerBack = new Image(String.valueOf(getClass().getResource("/images/backside.jpg")));
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getName() {
        assertEquals("Dorfbewohner", villager.getName());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardBack() {
        assertNotNull(villager.getCharacterCardBack());
        assertTrue(villager.getCharacterCardBack() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardBack() {
        villager.setCharacterCardBack(villagerBack);
        assertEquals(villagerBack, villager.getCharacterCardBack());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void setCharacterCardFront() {
        villager.setCharacterCardFront(villagerFront);
        assertEquals(villagerFront, villager.getCharacterCardFront());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getCharacterCardFront() {
        assertNotNull(villager.getCharacterCardFront());
        assertTrue(villager.getCharacterCardFront() != null);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void testToString() {
        assertEquals("Dorfbewohner", villager.toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        assertEquals("Char<Dorfbewohner>", villager.print());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void winText() {
        assertEquals("Die Dorfbewohner haben gewonnen!", villager.winText());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void isSameClass() {
        assertTrue(villager.isSameClass(new CharVillager()));
        assertFalse(villager.isSameClass(new CharWerewolf()));
        assertFalse(villager.isSameClass(null));
    }
}