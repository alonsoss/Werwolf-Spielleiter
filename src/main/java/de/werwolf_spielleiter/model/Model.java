package de.werwolf_spielleiter.model;

import de.werwolf_spielleiter.character.Character;
import de.werwolf_spielleiter.character.*;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.fraction.Fraction;
import de.werwolf_spielleiter.player.Player;
import de.werwolf_spielleiter.role.Role;
import de.werwolf_spielleiter.role.RoleThief;
import de.werwolf_spielleiter.role.RoleWerewolf;
import de.werwolf_spielleiter.trade.*;
import de.werwolf_spielleiter.victim.Victims;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Model {
    /* Variables */
    private int countPlayer;

    //Amount of every Character
    private int countWerewolf;
    private int countGreatWolf;
    private int countBigBadWolf;
    private int countWhiteWerewolf;
    private int countVillager;
    private int countWitch;
    private int countHunter;
    private int countSeer;
    private int countCupid;
    private int countLittleGirl;
    private int countThief;
    private int countWildChild;
    private int countFox;
    private int countDogWolf;

    // Variable for the Sheriff
    private boolean sheriff = false;
    private boolean sheriffWasVote = false;
    private boolean nextSheriffVote = false;

    private boolean hunterPhase = false;

    private boolean greatWolfInfected = false;

    //to store if at least one werewolf is dead
    private boolean werewolfDead = false;

    //Variables for Trades
    private boolean playWithTrade = false;
    private int countVagabond;
    private int countFarmer;
    private int countConfessor;
    private boolean allowConfession = true;

    // List of players
    private List<Player> playerList;
    private List<Player> playerAliveList;

    //List of cards
    private List<Character> cardThiefList;

    //says if it is night (true) or day (false)
    private boolean night = false;
    //counts the days, is 1 on first night and first day, counted up every new night
    private int day;

    //fraction object
    private Fraction fraction;

    //trade object
    private Trades trades;

    //victims object
    private Victims victims;

    // Idol of the wild child
    private Player idolWildChild;

    /**
     * @author Henrik Möhlmann
     */
    public Model() {
        this.playerList = new LinkedList<>();
        this.playerAliveList = new LinkedList<>();
        this.fraction = Fraction.getInstance();
        this.trades = Trades.getInstance();
        this.victims = new Victims();
        this.cardThiefList = new LinkedList<>();
    }

    /**
     * reset the Model
     *
     * @author Florian Müller
     */
    public void resetModel() {
        this.playerList = new LinkedList<>();
        this.playerAliveList = new LinkedList<>();
        Fraction.reset();
        this.fraction = Fraction.getInstance();
        Trades.reset();
        this.trades = Trades.getInstance();
        this.victims = new Victims();
        this.cardThiefList = new LinkedList<>();
        playWithTrade = false;
        werewolfDead = false;
        greatWolfInfected = false;
        hunterPhase = false;
        nextSheriffVote = false;
        sheriffWasVote = false;
        sheriff = false;
    }

    /**
     * Get the characters for the game, so that they could be given to the players.
     *
     * @return the characters of the game
     * @author Henrik Möhlmann
     * @author Eric De Ron
     */
    public List<Character> getCharsStack() {
        List<Character> stack = new LinkedList<>();
        //werewolf cards
        for (int i = 0; i < countWerewolf; i++) {
            stack.add(new CharWerewolf());
        }
        for (int i = 0; i < countGreatWolf; i++) {
            stack.add(new CharGreatWolf());
        }
        for (int i = 0; i < countBigBadWolf; i++) {
            stack.add(new CharBigBadWolf());
        }
        for (int i = 0; i < countWhiteWerewolf; i++) {
            stack.add(new CharWhiteWerewolf());
        }

        //villager cards
        for (int i = 0; i < countVillager; i++) {
            stack.add(new CharVillager());
        }
        for (int i = 0; i < countWitch; i++) {
            stack.add(new CharWitch());
        }
        for (int i = 0; i < countHunter; i++) {
            stack.add(new CharHunter());
        }
        for (int i = 0; i < countSeer; i++) {
            stack.add(new CharSeer());
        }
        for (int i = 0; i < countCupid; i++) {
            stack.add(new CharCupid());
        }
        for (int i = 0; i < countLittleGirl; i++) {
            stack.add(new CharLittleGirl());
        }
        for (int i = 0; i < countThief; i++) {
            stack.add(new CharThief());
        }
        for (int i = 0; i < countWildChild; i++) {
            stack.add(new CharWildChild());
        }
        for (int i = 0; i < countFox; i++) {
            stack.add(new CharFox());
        }
        for (int i = 0; i < countDogWolf; i++) {
            stack.add(new CharDogWolf());
        }
        return stack;
    }

    /**
     * Start the game.
     * Initializes the data that is needed to store the games information.
     *
     * @author Henrik Möhlmann
     * @author Janik Dohrmann
     */
    public void startGame() {
        //fill playerAlive
        playerList.stream().forEach(x -> playerAliveList.add(x));
        //init the day variable
        day = 0;
        //get the character card stack
        List<Character> stack = getCharsStack();
        //randomly select the cards for the players, set their character and the roles, fill the fraction
        for (int i = 0; i < playerAliveList.size(); i++) {
            Player player = playerAliveList.get(i);
            int random = (int) (Math.random() * 1000) % stack.size();

            player.setCharacter(stack.remove(random));
            player.setRolesList(Role.getRoles(player));
            fraction.addPlayer(player);
        }

        if (playWithTrade) {
            List<Trade> tradeStack = getTradeStack();
            //randomly select the trades for the players, fill the trades
            for (int i = 0; i < playerAliveList.size(); i++) {
                Player player = playerAliveList.get(i);
                int random = (int) (Math.random() * 1000) % tradeStack.size();

                player.setTrade(tradeStack.remove(random));

                trades.addPlayer(player);
            }
        }


        //System.out.println(String.format("Restliche Karten: %s", stack));
        //check if thief is in game and get the cards for the thief
        if (isRoleInGame(new RoleThief())) {
            if (stack.size() >= 2) {
                int random = (int) (Math.random() * 1000) % stack.size();
                cardThiefList.add(stack.remove(random));
                random = (int) (Math.random() * 1000) % stack.size();
                cardThiefList.add(stack.remove(random));
            } else {
                System.err.println(PrivateGameConstants.MODEL_NOT_ENOUGH_CARDS_THIEF);
            }
        }
        //System.out.println("Dieb Karten: " + cardThiefList);
    }

    /**
     * @return
     * @author Janik Dohrmann
     * @return A List with all Trades
     */
    public List<Trade> getTradeStack() {
        List<Trade> stack = new LinkedList<>();
        for (int i = 0; i < countVagabond; i++) {
            stack.add(new TradeVagabond());
        }
        for (int i = 0; i < countFarmer; i++) {
            stack.add(new TradeFarmer());
        }
        for (int i = 0; i < countConfessor; i++) {
            stack.add(new TradeConfessor());
        }
        return stack;
    }

    /**
     * Reset the data structure needed for the nights information.
     *
     * @author Henrik Möhlmann
     */
    public void startNight() {
        night = true;
        day++;
        victims.reset();
    }

    /**
     * @return true if it is night,
     * false if it is day
     * @author Henrik Möhlmann
     */
    public boolean isNight() {
        return night;
    }

    /**
     * @param night set night (if night == true),
     *              set day (if night == false)
     * @author Henrik Möhlmann
     */
    public void setNight(boolean night) {
        this.night = night;
    }

    /**
     * @return the number of the day
     * @author Henrik Möhlmann
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the fraction object
     * @author Henrik Möhlmann
     */
    public Fraction getFraction() {
        return fraction;
    }

    /**
     * Method to get the amount of werewolves in the game.
     *
     * @return the amount of werewolves.
     * @author Henrik Möhlmann
     */
    public int getCountWerewolf() {
        return countWerewolf;
    }

    /**
     * Method to get the amount of great wolves in the game.
     *
     * @return the amount of great wolves
     * @author Henrik Möhlmann
     */
    public int getCountGreatWolf() {
        return countGreatWolf;
    }

    /**
     * Method to get the amount of big bad wolves in the game.
     *
     * @return the amount of big bad wolves
     * @author Henrik Möhlmann
     */
    public int getCountBigBadWolf() {
        return countBigBadWolf;
    }

    /**
     * Method to get the amount of white werewolves in the game.
     *
     * @return the amount of white werewolves.
     * @author Henrik Möhlmann
     */
    public int getCountWhiteWerewolf() {
        return countWhiteWerewolf;
    }

    /**
     * Method to get the amount of Villagers in the game.
     *
     * @return the amount of villagers.
     * @author Henrik Möhlmann
     */
    public int getCountVillager() {
        return countVillager;
    }

    /**
     * Method to get the amount of witches in the game.
     *
     * @return the amount of witches.
     * @author Henrik Möhlmann
     */
    public int getCountWitch() {
        return this.countWitch;
    }

    /**
     * Method to get the amount of hunter in the game
     *
     * @return Amount of hunter
     * @author Eric De Ron
     */
    public int getCountHunter() {
        return countHunter;
    }

    /**
     * Method to get the amount of cupids in the game.
     *
     * @return the amount of cupids
     * @author Henrik Möhlmann
     */
    public int getCountCupid() {
        return countCupid;
    }

    /**
     * Method to get the amount of seer in the game
     *
     * @return Amount of seer
     * @author Janik Dohrmann
     */
    public int getCountSeer() {
        return countSeer;
    }

    /**
     * Method to get the amount of little girls in the game
     *
     * @return the amount of little girls
     * @author Henrik Möhlmann
     */
    public int getCountLittleGirl() {
        return countLittleGirl;
    }

    /**
     * Method to get the amount of thieves in the game.
     *
     * @return the amount of thieves
     * @author Henrik Möhlmann
     */
    public int getCountThief() {
        return countThief;
    }

    /**
     * Method to get the amount of wild child in the game
     *
     * @return Amount of wild child
     * @author Eric De Ron
     */
    public int getCountWildChild() {
        return countWildChild;
    }

    /**
     * Method to get the number of foxes in the game
     *
     * @return number of foxes
     * @author Matthias Hinrichs
     */
    public int getCountFox() {
        return countFox;
    }

    /**
     * Method to get the amount of dog wolves in the game.
     *
     * @return amount of dog wolves
     * @author Henrik Möhlmann
     */
    public int getCountDogWolf() {
        return countDogWolf;
    }

    /**
     * Method to get the amount of vagabonds in the game.
     * @return amount of vagabonds
     * @author Henrik Möhlmann
     */
    public int getCountVagabond() {
        return countVagabond;
    }

    /**
     * Method to get the amount of farmers in the game.
     * @return amount of farmers
     * @author Henrik Möhlmann
     */
    public int getCountFarmer() {
        return countFarmer;
    }

    /**
     * Method to set the amount of Werewolves in the game.
     *
     * @param countWerewolf the amount of Werewolves to be set.
     * @author Henrik Möhlmann
     */
    public void setCountWerewolf(int countWerewolf) {
        this.countWerewolf = countWerewolf;
    }

    /**
     * Method to set the amount of great wolves in the game.
     *
     * @param countGreatWolf the amount of great wolves to be set. It is only allowed to be 0 or 1.
     * @return true if countGreatWolf is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountGreatWolf(int countGreatWolf) {
        if (countGreatWolf == 0 || countGreatWolf == 1) {
            this.countGreatWolf = countGreatWolf;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of big bad wolves in the game.
     *
     * @param countBigBadWolf the amount of big bad wolves to be set. It is only allowed to be 0 or 1.
     * @return true if countBigBadWolf is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountBigBadWolf(int countBigBadWolf) {
        if (countBigBadWolf == 0 || countBigBadWolf == 1) {
            this.countBigBadWolf = countBigBadWolf;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of white werewolves in the game.
     *
     * @param countWhiteWerewolf the amount of white werewolves to be set. It is only allowed to be 0 or 1.
     * @return true if countWhiteWerewolf is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountWhiteWerewolf(int countWhiteWerewolf) {
        if (countWhiteWerewolf == 0 || countWhiteWerewolf == 1) {
            this.countWhiteWerewolf = countWhiteWerewolf;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of villagers in the game.
     *
     * @param countVillager the amount of villagers to be set.
     * @author Henrik Möhlmann
     */
    public void setCountVillager(int countVillager) {
        this.countVillager = countVillager;
    }

    /**
     * Method to set the amount of witches in the game.
     *
     * @param countWitch the amount of witches to be set. It is only allowed to be 0 or 1
     * @return true if countWitch is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountWitch(int countWitch) {
        if (countWitch == 0 || countWitch == 1) {
            this.countWitch = countWitch;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of hunter in the game
     *
     * @param countHunter Amount of hunter to be set,
     *                    it's only allowed to be 0 or 1
     * @return True if countHunter is 0 or 1
     * false otherwise
     * @author Eric De Ron
     */
    public boolean setCountHunter(int countHunter) {
        if (countHunter == 0 || countHunter == 1) {
            this.countHunter = countHunter;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of cupids in the game.
     *
     * @param countCupid Amount of cupids to be set. It is only allowed to be 0 or 1.
     * @return true if countCupid is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountCupid(int countCupid) {
        if (countCupid == 0 || countCupid == 1) {
            this.countCupid = countCupid;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of seer in the game
     *
     * @param countSeer Amount of seer to be set,
     *                  only 1 or 0 are allowed
     * @return True if countSeer is 0 or 1
     * false else
     * @author Janik Dohrmann
     */
    public boolean setCountSeer(int countSeer) {
        if (countSeer == 0 || countSeer == 1) {
            this.countSeer = countSeer;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of little girls in the game.
     *
     * @param countLittleGirl amount of little girls to be set, only 1 or 0 are allowed
     * @return true if countLittleGirl is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountLittleGirl(int countLittleGirl) {
        if (countLittleGirl == 0 || countLittleGirl == 1) {
            this.countLittleGirl = countLittleGirl;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of thieves in the game.
     *
     * @param countThief amount of thieves to be set, only 1 or 0 are allowed.
     * @return true if countThief is 0 or 1
     * false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountThief(int countThief) {
        if (countThief == 0 || countThief == 1) {
            this.countThief = countThief;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of wild child in the game
     *
     * @param countWildChild Amount of wild child to be set,
     *                       it's only allowed to be 0 or 1
     * @return True if countWildChild is 0 or 1
     * false otherwise
     * @author Eric De Ron
     */
    public boolean setCountWildChild(int countWildChild) {
        if (countWildChild == 0 || countWildChild == 1) {
            this.countWildChild = countWildChild;
            return true;
        }
        return false;
    }

    /**
     * Method to set the number of foxes in the game
     *
     * @param countFox number of foxes to be set, only 1 or 0 allowed.
     * @return true if countFox is 0 or 1, false otherwise
     * @author Matthias Hinrichs
     */
    public boolean setCountFox(int countFox) {
        if (countFox == 0 || countFox == 1) {
            this.countFox = countFox;
            return true;
        }
        return false;
    }

    /**
     * Method to set the amount of dog wolves in the game.
     *
     * @param countDogWolf - amount of dog wolves to be set. It is only allowed to be 0 or 1
     * @return true if countDogWolf is 0 or 1
     *          false otherwise
     * @author Henrik Möhlmann
     */
    public boolean setCountDogWolf(int countDogWolf) {
        if (countDogWolf == 0 || countDogWolf == 1) {
            this.countDogWolf = countDogWolf;
            return true;
        }
        return false;
    }

    /**
     * Method to lazy load the player list
     *
     * @param size Amount of the players in the game
     * @author Eric De Ron
     */
    public void initPlayerList(int size) {
        setCountPlayer(size);
        // Lazy load the player list
        if (playerList == null) this.playerList = new ArrayList<>();
    }

    /**
     * Method to get the amount of players in the game.
     *
     * @return the amount of players
     * @author Henrik Möhlmann
     */
    public int getCountPlayer() {
        return countPlayer;
    }

    /**
     * Method to set the amount of players.
     *
     * @param count ist the amount of players to be set
     * @author Henrik Möhlmann
     */
    public void setCountPlayer(int count) {
        this.countPlayer = count;
    }

    /**
     * @return List of players in the model
     * @author Eric De Ron
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     * @return List of all players alive
     * @author Henrik Möhlmann
     */
    public List<Player> getPlayerAliveList() {
        return this.playerAliveList;
    }

    /**
     * @return List of cards for the thief.
     * @author Henrik Möhlmann
     */
    public List<Character> getCardThiefList() {
        return this.cardThiefList;
    }

    /**
     * Checks if a player with the given role is in game.
     * This is necessary to avoid that roles are called that aren't in game anymore.
     *
     * @param role to be checked
     * @return true if some player alive has that role
     * false if nobody alive has that role
     * @author Henrik Möhlmann
     */
    public boolean isRoleInGame(Role role) {
        for (Player player : this.playerAliveList) {
            if (player.hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a player with the given role
     * in the active game
     *
     * @param role to be checked
     * @return If a player exits the player
     * else null
     * @author Eric De Ron
     */
    public Player getFirstPlayerWithRole(Role role) {
        for (Player player : this.playerAliveList) {
            if (player.hasRole(role)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Add a player to the playerList
     *
     * @param player new player
     * @return true if player is not null
     * false else
     * @author Henrik Möhlmann
     */
    public boolean addPlayer(Player player) {
        if (player == null) {
            return false;
        }
        this.playerList.add(player);
        return true;
    }

    /**
     * @return the victims object
     * @author Henrik Möhlmann
     */
    public Victims getVictims() {
        return this.victims;
    }

    /**
     * Method to die a player in the data structure.
     *
     * @param player to be died
     * @author Henrik Möhlmann
     * @author Florian Müller
     */
    public boolean die(Player player) {
        if (player == null) {
            return false;
        }
        this.playerAliveList.remove(player);
        this.fraction.die(player);
        //set the boolean to store if at least one werewolf is dead
        if (player.hasRole(new RoleWerewolf())) {
            setWerewolfDead();
        }
        if (isPlayWithTrade()) {
            Trades.getInstance().removePlayer(player);
        }
        player.die();
        return true;
    }

    /**
     * To set the Variable for the Sheriff for the play.
     *
     * @author Florian Müller
     */
    public void setSheriff(boolean playWithSheriff) {
        this.sheriff = playWithSheriff;
    }

    /**
     * @return the boolean from the sheriff
     * @author Florian Müller
     */
    public boolean isSheriff() {
        return sheriff;
    }

    /**
     * set the sheriff was vote if he was vote
     *
     * @author Florian Müller
     */
    public void setSheriffWasVote(boolean p) {
        sheriffWasVote = p;
    }

    /**
     * get the sheriff was vote variable
     *
     * @return
     * @author Florian Müller
     */
    public boolean getSheriffWasVote() {
        return sheriffWasVote;
    }

    /**
     * get to the scene if the old sheriff died
     *
     * @return
     * @author Florian Müller
     */
    public boolean isNextSheriffVote() {
        return nextSheriffVote;
    }

    /**
     * set the parameter to get in the next scene to chose the new sheriff
     *
     * @param p
     * @author Florian Müller
     */
    public void setNextSheriffVote(boolean p) {
        nextSheriffVote = p;
    }

    /**
     * @param b
     * @author Florian Müller
     */
    public void setHunterPhase(boolean b) {
        hunterPhase = b;
    }

    /**
     * @return
     * @author Florian Müller
     */
    public boolean isHunterPhase() {
        return hunterPhase;
    }

    /**
     * Give the thief the chosen new character
     *
     * @param player       thief
     * @param newCharacter chosen new character for the thief
     * @author Henrik Möhlmann
     */
    public boolean changeThiefToNewCharacter(Player player, Character newCharacter) {
        if (player == null || newCharacter == null) {
            return false;
        }
        fraction.removeVillager(player);
        player.setCharacter(newCharacter);
        player.setRolesList(Role.getRoles(player));
        fraction.addPlayer(player);
        return true;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public boolean hasGreatWolfInfected() {
        return greatWolfInfected;
    }

    /**
     * @author Henrik Möhlmann
     */
    public void greatWolfInfect() {
        greatWolfInfected = true;
    }

    /**
     * @return
     * @author Henrik Möhlmann
     */
    public boolean isWerewolfDead() {
        return werewolfDead;
    }

    /**
     * @author Henrik Möhlmann
     */
    public void setWerewolfDead() {
        werewolfDead = true;
    }

    /**
     * @return
     * @author Janik Dohrmann
     */
    public boolean isPlayWithTrade() {
        return playWithTrade;
    }

    /**
     * @return
     * @author Janik Dohrmann
     */
    public void setPlayWithTrade(boolean playWithTrade) {
        this.playWithTrade = playWithTrade;
    }

    /**
     * @return
     * @author Janik Dohrmann
     */
    public void setCountVagabond(int countVagabond) {
        this.countVagabond = countVagabond;
    }

    /**
     * @param countFarmer
     * @author Florian Müller
     */
    public void setCountFarmer(int countFarmer) {
        this.countFarmer = countFarmer;
    }

    public int getCountConfessor() {
        return countConfessor;
    }

    public void setCountConfessor(int countConfessor) {
        this.countConfessor = countConfessor;
    }

    /**
     * @return A player idol of the wild child
     * if exits
     * @author Eric De Ron
     */
    public Player getIdolWildChild() {
        return idolWildChild;
    }

    /**
     * @param idolWildChild Idol player of the wild child
     * @author Eric De Ron
     */
    public boolean setIdolWildChild(Player idolWildChild) {
        if (idolWildChild == null) return false;
        this.idolWildChild = idolWildChild;
        return true;
    }

    /**
     * @author Janik Dohrmann
     * @return true if confessor is allowed to see a card,
     *          false else
     */
    public boolean isAllowConfession() {
        return allowConfession;
    }

    /**
     * @author Janik Dohrmann
     * @param allowConfession set if the confessor is allowed to see a card
     */
    public void setAllowConfession(boolean allowConfession) {
        this.allowConfession = allowConfession;
    }
}