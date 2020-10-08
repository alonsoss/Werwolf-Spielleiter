package de.werwolf_spielleiter.fraction;

import de.werwolf_spielleiter.character.*;
import de.werwolf_spielleiter.character.Character;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.RoleLovers;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.role.RoleWildChild;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class stores which player is in which group. This class is a singleton!
 * @author Janik Dohrmann
 * @author Henrik Möhlmann
 * @author Eric De Ron
 */
public class Fraction {

    private static Fraction self = null;

    private List<Player> villagerList = new LinkedList<>();
    private List<Player> werewolfList = new LinkedList<>();
    private List<Player> whiteWerewolfList = new LinkedList<>();
    private List<Player> loversList = new LinkedList<>();

    private Fraction() {}

    /**
     * @author Janik Dohrmann
     * @return a list of players who are in the Villager list
     */
    public List<Player> getVillagerList() {
        return villagerList;
    }

    /**
     * @author Janik Dohrmann
     * @return a list of players who are in the Werewolf list
     */
    public List<Player> getWerewolfList() {
        return werewolfList;
    }

    /**
     * @author Henrik Möhlmann
     * @return a list of players who are in the white werewolf list.
     */
    public List<Player> getWhiteWerewolfList() {
        return whiteWerewolfList;
    }

    /**
     * @author Henrik Möhlmann
     * @return a list of players who are in the Lovers list
     */
    public List<Player> getLoversList() {
        return loversList;
    }

    /**
     * Method to get the player in the right fraction
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @author Eric De Ron
     * @param player the player to be filled in the fraction
     * @return true if the right fraction was found,
     *          false if not
     */
    public boolean addPlayer(Player player) {
        if (player == null) {
            return false;
        } else if (player.getCharacter() instanceof CharVillager
                    || player.getCharacter() instanceof CharWitch
                    || player.getCharacter() instanceof CharHunter
                    || player.getCharacter() instanceof CharCupid
                    || player.getCharacter() instanceof CharSeer
                    || player.getCharacter() instanceof CharLittleGirl
                    || player.getCharacter() instanceof CharThief
                    || player.getCharacter() instanceof CharWildChild
                    || player.getCharacter() instanceof CharFox
                    || (player.getCharacter() instanceof CharDogWolf && !player.hasRole(new RoleWerewolf()))) {
            return addVillager(player);
        } else if (player.getCharacter() instanceof CharWerewolf
                    || player.getCharacter() instanceof CharGreatWolf
                    || player.getCharacter() instanceof CharBigBadWolf
                    || (player.getCharacter() instanceof CharDogWolf && player.hasRole(new RoleWerewolf()))) {
            return addWerewolf(player);
        } else if (player.getCharacter() instanceof CharWhiteWerewolf) {
            return addWhiteWerewolf(player);
        } else {
            return false;
        }
    }

    /**
     * @author Henrik Möhlmann
     * @param player to be removed.
     * @return true if player != null
     *         false if player == null
     */
    public boolean die(Player player) {
        if (player == null) {
            return false;
        }
        removeWerewolf(player);
        removeWhiteWerewolf(player);
        removeVillager(player);
        removeLover(player);
        return true;
    }

    /**
     * @author Henrik Möhlmann
     * @return a string representation of the fractions with the list of every fraction
     */
    public String print() {
        String ret = "===== Fraktionen =====\n";
        ret += "Dorfbewohner: " + villagerList.toString() + "\n";
        ret += "Werwoelfe: " + werewolfList.toString() + "\n";
        ret += "Weißer Werwolf: " + whiteWerewolfList.toString() + "\n";
        ret += "Liebespaar: " + loversList.toString() + "\n";
        ret += "===== ===== =====";
        return ret;
    }

    /**
     * Change the fraction of two players, if one of them is werewolf* and the other villager.
     * @author Henrik Möhlmann
     * @param player1 first player
     * @param player2 second player
     * @return true if successful
     *          false if not successful
     */
    public boolean changeToLover(Player player1, Player player2) {
        if (player1 == null || player2 == null) {
            return false;
        }
        //werewolf and villager are lovers
        if (werewolfList.contains(player1) && villagerList.contains(player2)) {
            werewolfList.remove(player1);
            villagerList.remove(player2);
            loversList.add(player1);
            loversList.add(player2);
            return true;
        }
        if (villagerList.contains(player1) && werewolfList.contains(player2)) {
            villagerList.remove(player1);
            werewolfList.remove(player2);
            loversList.add(player1);
            loversList.add(player2);
            return true;
        }

        //white werewolf and villager are lovers
        if (whiteWerewolfList.contains(player1) && villagerList.contains(player2)) {
            whiteWerewolfList.remove(player1);
            villagerList.remove(player2);
            loversList.add(player1);
            loversList.add(player2);
            return true;
        }
        if (villagerList.contains(player1) && whiteWerewolfList.contains(player2)) {
            villagerList.remove(player1);
            whiteWerewolfList.remove(player2);
            loversList.add(player1);
            loversList.add(player2);
            return true;
        }
        return false;
    }

    /**
     * Change the fraction of infected werwolf victim.
     * @param player the werewolf victim to become a werewolf
     * @return true if werewolf victim successfully transformed to werewolf
     *          false otherwise
     */
    public boolean changeToWerewolf(Player player) {
        if (player == null) {
            return false;
        }
        if (!player.hasRole(new RoleLovers())) {
            villagerList.remove(player);
            werewolfList.add(player);
            return true;
        } else {
            if (villagerList.stream().filter(x -> x.hasRole(new RoleLovers())).collect(Collectors.toList()).size() == 2) {
                //werewolf victim is in love with an villager
                //change the werewolf victim to werewolf
                //and change both to lovers
                villagerList.remove(player);
                werewolfList.add(player);
                Player lover1 = player;
                Player lover2 = villagerList.stream()
                        .filter(x -> x.hasRole(new RoleLovers()))
                        .collect(Collectors.toList()).get(0);
                changeToLover(lover1, lover2);
                return true;
            } else {
                //This should not happen, werwolf can't be in love with the werewolf victim
                System.err.println(PrivateGameConstants.FRACTION_ERROR_FALSE_WEREWOLF_VICTIM);
                return false;
            }
        }
    }

    /**
     * @author Eric De Ron
     * @param player Wild child to change to werewolf
     * @return true if changed successfully
     */
    public boolean changeWildChildToWerewolf(Player player) {
        if (player == null || !player.hasRole(new RoleWildChild())
                || player.hasRole(new RoleWerewolf())) return false;
        if (!player.hasRole(new RoleLovers())) {
            // Not in love
            player.addRole(new RoleWerewolf());
            villagerList.remove(player);
            werewolfList.add(player);
            return true;
        } else if (villagerList.stream().filter(x -> x.hasRole(new RoleLovers())).count() == 2) {
            // Wild child is in love with an villager
            // change it to werewolf and both to lovers
            player.addRole(new RoleWerewolf());
            villagerList.remove(player);
            werewolfList.add(player);
            Player lover2 = villagerList.stream()
                    .filter(x -> x.hasRole(new RoleLovers()))
                    .collect(Collectors.toList()).get(0);
            changeToLover(player, lover2);
            return true;
        } else {
            player.addRole(new RoleWerewolf());
            loversList.remove(player);
            addWerewolf(player);
            // If second lover is white werewolf add to white werewolf fraction
            if (loversList.get(0).getCharacter() instanceof CharWhiteWerewolf) {
                addWhiteWerewolf(loversList.get(0));
            } else {
                // If just werewolf
                addWerewolf(loversList.get(0));
            }
            loversList.clear();
            return true;
        }
    }

    /**
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @author Eric De Ron
     * @param player A Player who is a Villager to add to the villagerList
     * @return  true, if successful added to list
     *          false, if not successful added to list
     */
    public boolean addVillager(Player player) {
        if (player == null) {
            return false;
        }
        if (player.getCharacter() instanceof CharVillager
                || player.getCharacter() instanceof CharWitch
                || player.getCharacter() instanceof  CharHunter
                || player.getCharacter() instanceof CharCupid
                || player.getCharacter() instanceof CharSeer
                || player.getCharacter() instanceof CharLittleGirl
                || player.getCharacter() instanceof CharThief
                || player.getCharacter() instanceof CharWildChild
                || player.getCharacter() instanceof CharFox
                || (player.getCharacter() instanceof CharDogWolf && !player.hasRole(new RoleWerewolf()))) {
            villagerList.add(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @param player player to remove
     * @return returns true if successful
     */
    public boolean removeVillager(Player player) {
        return villagerList.remove(player);
    }

    /**
     * @author Janik Dohrmann
     * @author Eric De Ron
     * @param player A Player who is a Werewolf to add to the villagerList
     * @return  true, if successful added to list
     *          false, if not successful added to list
     */
    public boolean addWerewolf(Player player) {
        if (player == null) {
            return false;
        }
        if (player.getCharacter() instanceof CharWerewolf
                || player.getCharacter() instanceof CharGreatWolf
                || player.getCharacter() instanceof CharBigBadWolf
                || player.getCharacter() instanceof CharWildChild
                || (player.getCharacter() instanceof CharDogWolf && player.hasRole(new RoleWerewolf()))) {
            werewolfList.add(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Janik Dohrmann
     * @param player player to remove
     * @return returns true if successful
     */
    public boolean removeWerewolf(Player player) {
        return werewolfList.remove(player);
    }

    /**
     * @author Henrik Möhlmann
     * @param player a player who is a white werewolf to add to the white werewolf list
     * @return true, if successful added to the list
     *          false otherwise
     */
    public boolean addWhiteWerewolf(Player player) {
        if (player != null && player.getCharacter() instanceof CharWhiteWerewolf) {
            whiteWerewolfList.add(player);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Henrik Möhlmann
     * @param player player to remove
     * @return returns true if successful
     */
    public boolean removeWhiteWerewolf(Player player) {
        return whiteWerewolfList.remove(player);
    }

    /**
     * @author Henrik Möhlmann
     * @param player player to remove
     * @return true if successful
     */
    public boolean removeLover(Player player) {
        return loversList.remove(player);
    }

    /**
     * @author Janik Dohrmann
     * @return returns true if villagerList is Empty
     */
    public boolean isVillagerEmpty() {
        return villagerList.isEmpty();
    }

    /**
     * @author Janik Dohrmann
     * @return returns true if werewolfList is Empty
     */
    public boolean isWerewolfEmpty() {
        return werewolfList.isEmpty();
    }

    /**
     * @author Henrik Möhlmann
     * @return true if white werewolf list is empty
     */
    public boolean isWhiteWerewolfEmpty() {
        return whiteWerewolfList.isEmpty();
    }

    /**
     * @author Henrik Möhlmann
     * @return true if loversList is empty
     */
    public boolean isLoversEmpty() {
        return loversList.isEmpty();
    }

    /**
     * @author Janik Dohrmann
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * @return  null if no winner
     *          a Villager if Villagers win
     *          a Werewolf if Werewolves win
     *          a White Werewolf if the white werewolf wins
     *          a Hunter if no fraction wins
     *          a Cupid if Lovers win
     */
    public Character winner() {
        if (loversWinner()) {
            return new CharCupid();
        } else if (werewolvesWinner()) {
            return new CharWerewolf();
        } else if (whiteWerewolfWinner()) {
            return new CharWhiteWerewolf();
        } else if (villagerWinner()) {
            return new CharVillager();
        } else if (nobodyWinner()) {
            return new CharHunter();
        } else {
            return null;
        }
    }

    /**
     * Check if villagers have won.
     * The group of villagers wins...
     * - if there are no werewolves (in any appearance) in the game (includes no werewolf as lover)
     * - and if there are any villagers in the game.
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @return true if villagers win
     *          otherwise false
     */
    public boolean villagerWinner() {
        if (isWerewolfEmpty() && isWhiteWerewolfEmpty() && isLoversEmpty() && !isVillagerEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Check if werewolves have won.
     * The group of werewolves wins...
     * - if there are no "not werewolves" in the game (includes no other than a werewolf as lover)
     * - and if there are any werewolves in the game.
     * @author Janik Dohrmann
     * @author Henrik Möhlmann
     * @return true if werewolves win
     *          otherwise false
     */
    public boolean werewolvesWinner() {
        if (isVillagerEmpty() && isLoversEmpty() && !isWerewolfEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Check if white werewolf has won.
     * The white werewolf wins, if he is the only living player.
     * @author Henrik Möhlmann
     * @return true if white werewolf wins
     *          false ohterwise
     */
    public boolean whiteWerewolfWinner() {
        if (isVillagerEmpty() && isLoversEmpty() && isWerewolfEmpty() && !isWhiteWerewolfEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Check if lovers have won.
     * The pair of lovers wins...
     * - if the both of them are alive
     * - and no others are in the game
     * @author Henrik Möhlmann
     * @return true if lovers win
     *          otherwise false
     */
    public boolean loversWinner() {
        if (isWerewolfEmpty() && isWhiteWerewolfEmpty() && isVillagerEmpty() && !isLoversEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Check if nobody has won.
     * Nobody has won, if all players are dead.
     * @author Eric De Ron
     * @author Henrik Möhlmann
     * @return true if nobody wins
     *          otherwise false
     */
    public boolean nobodyWinner() {
        if (isLoversEmpty() && isVillagerEmpty() && isWerewolfEmpty() && isWhiteWerewolfEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * @author Janik Dohrmann
     * @return An instance of this class
     */
    public static Fraction getInstance(){
        if (self == null) {
            self = new Fraction();
        }
        return self;
    }

    /**
     * Reset the Fraction object
     * @author Henrik Möhlmann
     */
    public static void reset() {
        self = new Fraction();
    }
}
