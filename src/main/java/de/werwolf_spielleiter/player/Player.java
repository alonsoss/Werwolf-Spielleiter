package de.werwolf_spielleiter.player;

import de.werwolf_spielleiter.character.Character;
import de.werwolf_spielleiter.constants.PrivateGameConstants;
import de.werwolf_spielleiter.role.Role;

import de.werwolf_spielleiter.trade.Trade;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

/**
 * Basic class to represent a Player.
 */
public class Player {
    private StringProperty name;
    private StringProperty status;
    private Image characterCardBack;
    private Image characterCardFront;
    private ObjectProperty<Image> characterCardProperty;
    private boolean awake = true;
    private Character character;
    private List<Role> rolesList;
    private Trade trade;

    /**
     * Default Constructor
     */
    public Player() { this(PrivateGameConstants.PLAYER_DEFAULT_NAME); }

    /**
     * Constructor with the name of the player
     * @author Henrik Möhlmann
     * @param name of the player
     */
    public Player(String name) {
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(PrivateGameConstants.PLAYER_ALIVE);
        this.rolesList = new LinkedList<>();
    }

    /**
     * @author Henrik Möhlmann
     * @return the players character
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     * @author Henrik Möhlmann
     * @param character the players character to be set
     */
    public void setCharacter(Character character) {
        this.character = character;
        this.characterCardBack = character.getCharacterCardBack();
        this.characterCardFront = character.getCharacterCardFront();
        if (this.characterCardProperty == null) {
            this.characterCardProperty = new SimpleObjectProperty<>();
        }
        characterCardProperty.setValue(characterCardBack);
    }

    /**
     * @author Henrik Möhlmann
     * @return the list of the players roles
     */
    public List<Role> getRolesList() {
        return this.rolesList;
    }

    /**
     * @author Henrik Möhlmann
     * @param rolesList the list of the players roles to be set
     */
    public void setRolesList(List<Role> rolesList) {
        this.rolesList = rolesList;
    }

    /**
     *
     * @param role to add to the player's roles list
     * @return true, if role added
     *          false otherwise
     */
    public boolean addRole(Role role) {
        if (!hasRole(role)) {
            this.rolesList.add(role);
            return true;
        }
        return false;
    }

    /**
     * @author Henrik Möhlmann
     * @param r role to be checked
     * @return true if player has role r
     *          false if player has not
     */
    public boolean hasRole(Role r) {
        if (r == null) {
            return false;
        }
        for (int i = 0; i < rolesList.size(); i++) {
            if (r.getClass() == rolesList.get(i).getClass()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dies the player in it's data and in his controller.
     * @author Henrik Möhlmann
     */
    public void die() {
        setStatus(PrivateGameConstants.PLAYER_DEAD);
    }

    /**
     *
     * @return player name
     */
    public String getName() {
        return name.get();
    }

    /**
     *
     * @param name player name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     *
     * @return StringProperty object of the name
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     *
     * @return status of the player
     */
    public String getStatus() {
        return status.get();
    }

    /**
     *
     * @param status set status of the player
     */
    public void setStatus(String status) {
        this.status.set(status);
    }

    /**
     *
     * @return StringProperty object of the status
     */
    public StringProperty statusProperty() {
        return status;
    }

    /**
     *
     * @return front side of the player card
     */
    public Image getCharacterCardFront() {
        return characterCardFront;
    }

    public Image getCharacterCardBack() {
        return characterCardBack;
    }

    public ObjectProperty<Image> getCharacterCardProperty() {
        return characterCardProperty;
    }

    /**
     * Method to give a string representation of the player.
     * It contains only the name, so it could be put into combo boxes etc.
     * @author Henrik Möhlmann
     * @return name as string
     */
    @Override
    public String toString() {
        return this.name.get();
    }

    /**
     * Method to give a string representation of the player with it's name, character and role
     * @author Henrik Möhlmann
     * @return string representation of player with name, character, roles
     */
    public String print() {
        String ret = "===== Spieler <" + this.name.get() + "> =====\n";
        if (this.character != null && this.character.getName() != null) {
            ret += "Charakter: " + this.character.getName() + "\n";
        }
        ret += "Rollen: " + this.rolesList;
        return ret;
    }

    /**
     * Method to set ImageView to Back of CharacterCard
     *
     * @param reveal if true show front of CharacterCard, show back otherwise
     * @author Matthias Hinrichs
     * @author Eric De Ron
     */
    public void revealCharacterCard(boolean reveal) {
        // Only flip the card of alive players,
        // player which are dead should be shown already
        if (getStatus().equals(PrivateGameConstants.PLAYER_ALIVE)) {
            if (reveal) {
                characterCardProperty.setValue(characterCardFront);
            } else {
                characterCardProperty.setValue(characterCardBack);
            }
        }
    }

    /**
     * A method to toggle if a Player is awake. This method is primarily called by
     * the wakeUp and sleep methods in BoardLayoutController.
     * @author Janik Dohrmann
     */
    public void toggleAwake() {
        awake = !awake;
    }

    /**
     * @author Janik Dohrmann
     * @return true if player is awake
     */
    public boolean isAwake() {
        return awake;
    }

    /**
     * @author Janik Dohrmann
     * @return the trade of the player
     */
    public Trade getTrade() {
        return trade;
    }

    /**
     * @author Janik Dohrmann
     * @param trade the trade to set
     */
    public void setTrade(Trade trade) {
        if (trade != null){
            this.trade = trade;
        }
    }

    /**
     * @author Janik Dohrmann
     * @param trade the trade to test
     * @return true if player has that trade
     *          false else
     */
    public boolean hasTrade(Trade trade) {
        if (this.trade != null) {
            return this.trade.isSameClass(trade);
        } else {
            return false;
        }
    }

    /**
     * @author Alonso Essenwanger
     * @param jobsRevealed Jobs status
     * @param cardsRevealed Cards status
     */
    public void showHideJobs(boolean jobsRevealed, boolean cardsRevealed) {
        if (jobsRevealed) {
            characterCardProperty.setValue(trade.getSign());
        } else {
            if (cardsRevealed) {
                characterCardProperty.setValue(characterCardFront);
            } else {
                characterCardProperty.setValue(characterCardBack);
            }
        }
    }
}
