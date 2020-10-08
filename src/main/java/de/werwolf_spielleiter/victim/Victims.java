package de.werwolf_spielleiter.victim;

import de.werwolf_spielleiter.character.CharWhiteWerewolf;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleWerewolf;

/**
 * @author Henrik Möhlmann
 * @author Florian Müller
 * @author Eric De Ron
 */
public class Victims {
    private VictimWerewolf victimWerewolf;
    private VictimBigBadWolf victimBigBadWolf;
    private VictimWhiteWerewolf victimWhiteWerewolf;
    private VictimWitch victimWitch;
    private VictimHunter victimHunter;
    private VictimLynch victimLynch;
    private VictimLover victimLover;

    /**
     * @author Henrik Möhlmann
     */
    public Victims() {
        victimWerewolf = new VictimWerewolf();
        victimBigBadWolf = new VictimBigBadWolf();
        victimWhiteWerewolf = new VictimWhiteWerewolf();
        victimWitch = new VictimWitch();
        victimHunter = new VictimHunter();
        victimLynch = new VictimLynch();
        victimLover = new VictimLover();
    }

    /**
     * @author Henrik Möhlmann
     * @return true if there are night victims
     *          false if there are no night victims
     */
    public boolean hasNightVictims() {
        if ((victimWerewolf.isSet() && !victimWerewolf.isHealed() && !victimWerewolf.isInfected())
                || victimWitch.isSet()
                || victimBigBadWolf.isSet()
                || victimWhiteWerewolf.isSet()) {
            return true;
        }
        return false;
    }

    /**
     * @author Henrik Möhlmann
     * @return true if there are lynch victims
     */
    public boolean hasLynchVictims() {
        if (victimLynch.isSet()) {
            return true;
        }
        return false;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public VictimWerewolf getVictimWerewolf() {
        return victimWerewolf;
    }

    /**
     * @author Henrik Möhlmann
     * @return
     */
    public VictimBigBadWolf getVictimBigBadWolf() {
        return victimBigBadWolf;
    }

    /**
     * @author Henrik Möhlmann
     * @return
     */
    public VictimWhiteWerewolf getVictimWhiteWerewolf() {
        return victimWhiteWerewolf;
    }

    /**
     * @author Henrik Möhlmann
     * @return
     */
    public VictimWitch getVictimWitch() {
        return victimWitch;
    }

    /**
     * @return
     * @author Florian Müller
     */
    public VictimLynch getVictimLynch() {
        return victimLynch;
    }

    /**
     * @author Eric De Ron
     * @return Victim of the hunter if there is one
     */
    public VictimHunter getVictimHunter() { return victimHunter; }

    /**
     * @return victim of heartbreak
     * @author Henrik Möhlmann
     */
    public VictimLover getVictimLover() {
        return victimLover;
    }

    /**
     * @param player
     * @author Henrik Möhlmann
     */
    public boolean setVictimWerewolf(Player player) {
        if (player == null) {
            return false;
        }
        this.victimWerewolf = new VictimWerewolf(player);
        return true;
    }

    /**
     * Set the big bad wolf victim.
     * @param player the victim who can't be a werewolf
     * @return true if player is accepted as victim
     *          false otherwise
     */
    public boolean setVictimBigBadWolf(Player player) {
        if (player == null) {
            return false;
        }
        //victim can't be a werewolf
        if (player.hasRole(new RoleWerewolf())) {
            return false;
        }
        victimBigBadWolf = new VictimBigBadWolf(player);
        return true;
    }

    /**
     * @author Henrik Möhlmann
     * @param player the white werewolf victim
     * @return
     */
    public boolean setVictimWhiteWerewolf(Player player) {
        if (player == null) {
            return false;
        }
        //the white werewolf victim cannot be the white werewolf
        if (player.getCharacter() instanceof CharWhiteWerewolf) {
            return false;
        }
        //the white werewolf victim can only be a werewolf (wakes up in werewolf phase)
        if (!player.hasRole(new RoleWerewolf())) {
            return false;
        }
        this.victimWhiteWerewolf = new VictimWhiteWerewolf(player);
        return true;
    }

    /**
     * @author Henrik Möhlmann
     * @param player the witch victim
     * @return
     */
    public boolean setVictimWitch(Player player) {
        if (player == null) {
            return false;
        }
        this.victimWitch = new VictimWitch(player);
        return true;
    }

    /**
     * @param player
     * @return
     * @author Florian Müller
     */
    public boolean setVictimLynch(Player player) {
        if (player == null) {
            return false;
        }
        this.victimLynch = new VictimLynch(player);
        return true;
    }

    /**
     * @author Eric De Ron
     * @param player Victim of the hunter
     */
    public boolean setVictimHunter(Player player) {
        if (player == null) {
            return false;
        }
        this.victimHunter = new VictimHunter(player);
        return true;
    }

    /**
     * @param player victim of heartbreak
     * @return
     * @author Henrik Möhlmann
     */
    public boolean setVictimLover(Player player) {
        if (player == null) {
            return false;
        }
        this.victimLover = new VictimLover(player);
        return true;
    }

    /**
     * Reset all the victim objects
     *
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    public void reset() {
        victimWerewolf.reset();
        victimBigBadWolf.reset();
        victimWhiteWerewolf.reset();
        victimWitch.reset();
        victimHunter.reset();
        victimLynch.reset();
        victimLover.reset();
    }

    /**
     * @author Henrik Möhlmann
     */
    public void resetLovers() {
        this.victimLover.reset();
    }

    /**
     * Get string representation with every victim and it's players name.
     *
     * @return
     * @author Henrik Möhlmann
     */
    public String print() {
        String ret = "===== Victims Objekt ===== \n";
        String werewolfVictimString = this.victimWerewolf.isSet() ? this.victimWerewolf.toString() : "keins";
        ret += "Werwolf Opfer: " + werewolfVictimString + "\n";
        String bigBadWolfVictimString = victimBigBadWolf.isSet() ? victimBigBadWolf.toString() : "keins";
        ret += "Großer, böser Wolf Opfer: " + bigBadWolfVictimString + "\n";
        String whiteWerewolfVictimString = this.victimWhiteWerewolf.isSet() ? this.victimWhiteWerewolf.toString() : "keins";
        ret += "Weißer Werwolf Opfer: " + whiteWerewolfVictimString + "\n";
        String witchVictimString = this.victimWitch.isSet() ? this.victimWitch.toString() : "keins";
        ret += "Hexe Opfer: " + witchVictimString + "\n";
        String victimLynchString = this.victimLynch.isSet() ? this.victimLynch.toString() : "keins";
        ret += "Lynch Opfer: " + victimLynchString + "\n";
        String victimLoverString = this.victimLover.isSet() ? this.victimLover.toString() : "keins";
        ret += "Liebeskummer Opfer: " + victimLoverString + "\n";
        ret += "===== ===== =====";
        return ret;
    }
}
