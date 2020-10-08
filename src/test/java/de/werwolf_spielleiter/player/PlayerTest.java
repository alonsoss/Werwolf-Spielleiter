package de.werwolf_spielleiter.player;

import de.werwolf_spielleiter.character.CharHunter;
import de.werwolf_spielleiter.character.CharVillager;
import de.werwolf_spielleiter.character.CharWerewolf;
import de.werwolf_spielleiter.character.CharWitch;
import de.werwolf_spielleiter.role.*;
import de.werwolf_spielleiter.extension.JfxTestExtension;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JfxTestExtension.class)
class PlayerTest {
    private Player testVillager;
    private String testVillagerName = "Hans";
    private StringProperty testVillagerNameProperty;
    private String testVillagerStatus = "alive";
    private StringProperty testVillagerStatusProperty;
    private Player testWerewolf;
    private String testWerewolfName = "Ruth";
    private StringProperty testWerewolfNameProperty;
    private String testWerewolfStatus = "dead";
    private StringProperty testWerewolfStatusProperty;
    private Player testDefaultPlayer;
    private String testDefaultPlayerName = "Spieler";
    private Player testOnlyNamePlayer;
    private String testOnlyNamePlayerName = "Lydia";
    private String testNameVictoria = "Victoria";


    /**
     * @author Matthias Hinrichs
     */
    @BeforeEach
    void initPlayer() {
        testVillager = new Player(testVillagerName);
        testVillager.setCharacter(new CharVillager());
        testVillagerNameProperty = new SimpleStringProperty(testVillagerName);
        testVillagerStatusProperty = new SimpleStringProperty(testVillagerStatus);
        testWerewolf = new Player(testWerewolfName);
        testWerewolf.setCharacter(new CharWerewolf());
        testWerewolf.setStatus(testWerewolfStatus);
        testWerewolfNameProperty = new SimpleStringProperty(testWerewolfName);
        testWerewolfStatusProperty = new SimpleStringProperty(testWerewolfStatus);
        testDefaultPlayer = new Player();
        testOnlyNamePlayer = new Player(testOnlyNamePlayerName);
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testGetName() {
        assertEquals(testVillager.getName(), testVillagerName);
        assertEquals(testWerewolf.getName(), testWerewolfName);
        assertEquals(testDefaultPlayer.getName(), testDefaultPlayerName);
        assertEquals(testOnlyNamePlayer.getName(), testOnlyNamePlayerName);
    }

    /**
     * @author Matthias Hinrichs
     */
    @ParameterizedTest
    @ValueSource(strings = {"Heinz"})
    void testSetName(String name) {
        assertNotEquals(testVillager.getName(), name);
        testVillager.setName(name);
        assertEquals(testVillager.getName(), name);
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testNameProperty() {
        assertEquals(testVillagerNameProperty.toString(), testVillager.nameProperty().toString());
        assertEquals(testWerewolfNameProperty.toString(), testWerewolf.nameProperty().toString());
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testGetStatus() {
        assertEquals(testVillager.getStatus(), testVillagerStatus);
        assertEquals(testWerewolf.getStatus(), testWerewolfStatus);
    }

    /**
     * @author Matthias Hinrichs
     */
    @ParameterizedTest
    @ValueSource(strings = {"alive", "dead"})
    void testSetStatus(String status) {
        testVillager.setStatus(status);
        assertEquals(testVillager.getStatus(), status);
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testStatusProperty() {
        assertEquals(testVillagerStatusProperty.toString(), testVillager.statusProperty().toString());
        assertEquals(testWerewolfStatusProperty.toString(), testWerewolf.statusProperty().toString());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getSetCharacter() {
        Player player = new Player(testNameVictoria);
        assertNull(player.getCharacter());

        player.setCharacter(new CharVillager());
        assertTrue(player.getCharacter() instanceof CharVillager);
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void getRoles() {
        Player player = new Player(testNameVictoria);
        assertTrue(player.getRolesList().isEmpty());

        player.setCharacter(new CharWerewolf());
        player.setRolesList(Role.getRoles(player));

        assertEquals("Werwolf", player.getRolesList().get(0).toString());
        assertEquals(1, player.getRolesList().size());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void addRole() {
        Player victoria = new Player("Victoria");
        assertEquals(0, victoria.getRolesList().size());
        assertTrue(victoria.addRole(new RoleWitch()));
        assertEquals(1, victoria.getRolesList().size());
        assertFalse(victoria.addRole(new RoleWitch()));
        assertEquals(1, victoria.getRolesList().size());
        assertTrue(victoria.addRole(new RoleLovers()));
        assertEquals(2, victoria.getRolesList().size());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void print() {
        Player player = new Player(testNameVictoria);
        player.setCharacter(new CharWerewolf());
        assertNotNull(player.print());
        assertNotEquals("", player.print());
        assertTrue(player.print().contains(testNameVictoria));
        assertTrue(player.print().contains("Werwolf"));
    }

    /**
     * @author Matthias Hinrichs
     */
    @Test
    void testRevealCharacterCard() {
        assertEquals(testVillager.getCharacterCardBack(), testVillager.getCharacterCardProperty().getValue());
        assertNotEquals(testVillager.getCharacterCardFront(), testVillager.getCharacterCardProperty().getValue());
        testVillager.revealCharacterCard(true);
        assertNotEquals(testVillager.getCharacterCardBack(), testVillager.getCharacterCardProperty().getValue());
        assertEquals(testVillager.getCharacterCardFront(), testVillager.getCharacterCardProperty().getValue());
        testVillager.revealCharacterCard(false);
        assertEquals(testVillager.getCharacterCardBack(), testVillager.getCharacterCardProperty().getValue());
        assertNotEquals(testVillager.getCharacterCardFront(), testVillager.getCharacterCardProperty().getValue());
    }

    /**
     * @author Janik Dohrmann
     */
    @Test
    void toggleAwakeTest() {
        assertTrue(testVillager.isAwake());
        testVillager.toggleAwake();
        assertFalse(testVillager.isAwake());
        testVillager.toggleAwake();
        assertTrue(testVillager.isAwake());
    }

    /**
     * @author Henrik Möhlmann
     */
    @Test
    void hasRole() {
        Player witch = new Player("Fanny");
        witch.setCharacter(new CharWitch());
        witch.setRolesList(Role.getRoles(witch));

        Player werewolf = new Player("Victoria");
        werewolf.setCharacter(new CharWerewolf());
        werewolf.setRolesList(Role.getRoles(werewolf));

        Player hunter = new Player("Leandra");
        hunter.setCharacter(new CharHunter());
        hunter.setRolesList(Role.getRoles(hunter));

        assertTrue(witch.hasRole(new RoleWitch()));
        assertTrue(werewolf.hasRole(new RoleWerewolf()));
        assertTrue(hunter.hasRole(new RoleHunter()));

        assertFalse(witch.hasRole(new RoleWerewolf()));
        assertFalse(werewolf.hasRole(new RoleWitch()));
        assertFalse(hunter.hasRole(new RoleWerewolf()));

        assertFalse(werewolf.hasRole(null));
    }
}
