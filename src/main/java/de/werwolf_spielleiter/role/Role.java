package de.werwolf_spielleiter.role;

import de.werwolf_spielleiter.character.*;
import de.werwolf_spielleiter.player.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent the actions of the players
 * @author Henrik Möhlmann
 * @author Eric De Ron
 */
public abstract class Role {
    private String name;

    /**
     * Constructor with name of the role
     * @author Henrik Möhlmann
     * @param name of the role
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * @author Henrik Möhlmann
     * @return the name of the role
     */
    public String getName() {
        return this.name;
    }

    /**
     * @author Henrik Möhlmann
     * @return string representation of the role, the roles name
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Give the Roles as List that are needed for each character, to initialize the players roles.
     * @author Henrik Möhlmann
     * @author Eric De Ron
     * @param player who's roles should be given back
     * @return the playery roles
     */
    public static List<Role> getRoles(Player player) {
        List<Role> retList = new LinkedList<>();
        if (player.getCharacter() instanceof CharWerewolf) {
            retList.add(new RoleWerewolf());
        }
        if (player.getCharacter() instanceof CharWhiteWerewolf) {
            retList.add(new RoleWhiteWerewolf());
            retList.add(new RoleWerewolf());
        }
        if (player.getCharacter() instanceof CharGreatWolf) {
            retList.add(new RoleGreatWolf());
            retList.add(new RoleWerewolf());
        }
        if (player.getCharacter() instanceof CharBigBadWolf) {
            retList.add(new RoleBigBadWolf());
            retList.add(new RoleWerewolf());
        }
        if (player.getCharacter() instanceof CharWitch) {
            retList.add(new RoleWitch());
        }
        if (player.getCharacter() instanceof CharHunter) {
            retList.add(new RoleHunter());
        }
        if (player.getCharacter() instanceof CharSeer) {
            retList.add(new RoleSeer());
        }
        if (player.getCharacter() instanceof CharCupid) {
            retList.add(new RoleCupid());
        }
        if (player.getCharacter() instanceof CharLittleGirl) {
            retList.add(new RoleLittleGirl());
        }
        if (player.getCharacter() instanceof CharThief) {
            retList.add(new RoleThief());
        }
        if (player.getCharacter() instanceof CharWildChild) {
            retList.add(new RoleWildChild());
        }
        if (player.getCharacter() instanceof CharFox) {
            retList.add(new RoleFox());
        }
        if (player.getCharacter() instanceof CharDogWolf) {
            retList.add(new RoleDogWolf());
        }
        return retList;
    }


}
