package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharWitch extends Character{
    private boolean healed = false;
    private boolean killed = false;

    /**
     * Constructor to create a witch character.
     * @author Henrik Möhlmann
     */
    public CharWitch() {
        super(PrivateGameConstants.CHAR_WITCH_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_WITCH))));
    }

    /**
     * @author Henrik Möhlmann
     * @return Text displayed if this char wins
     */
    @Override
    public String winText() { return PrivateGameConstants.CHAR_VILLAGER_WIN_TEXT; }

    /**
     * @author Henrik Möhlmann
     * @param obj other object
     * @return true if other object is a witch character
     */
    public boolean isSameClass(Object obj) { return obj instanceof CharWitch; }

    /**
     * @author Henrik Möhlmann
     */
    public void heal() {
        this.healed = true;
    }

    /**
     * @author Henrik Möhlmann
     * @return True if witch can heal, else false
     */
    public boolean canHeal() {
        return !this.healed;
    }

    /**
     * @author Henrik Möhlmann
     */
    public void kill() {
        this.killed = true;
    }

    /**
     * @author Henrik Möhlmann
     * @return True if witch can kill, false else
     */
    public boolean canKill() {
        return !this.killed;
    }
}
