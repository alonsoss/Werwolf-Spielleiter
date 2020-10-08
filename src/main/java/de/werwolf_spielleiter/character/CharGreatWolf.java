package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharGreatWolf extends Character {
    private boolean canInfect = true;

    /**
     * Constructor to create a great wolf character.
     * @author Henrik Möhlmann
     */
    public CharGreatWolf() {
        super(PrivateGameConstants.CHAR_GREAT_WOLF_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_GREAT_WOLF))));
    }

    /**
     * @author Henrik Möhlmann
     * @return text displayed if this char wins
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_WEREWOLF_WIN_TEXT;
    }

    /**
     * @author Henrik Möhlmann
     * @param obj other object
     * @return true if other object is a great wolf character
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharGreatWolf;
    }

    /**
     * infect a werewolf victim
     * @author Henrik Möhlmann
     */
    public void infect() {
        canInfect = false;
    }

    /**
     * @author Henrik Möhlmann
     * @return true if the great wolf can infect a werewolf victim
     */
    public boolean canInfect() {
        return canInfect;
    }
}
