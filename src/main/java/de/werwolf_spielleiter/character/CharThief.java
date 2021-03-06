package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharThief extends Character {

    /**
     * Constructor to create a thief character.
     * @author Henrik Möhlmann
     */
    public CharThief() {
        super(PrivateGameConstants.CHAR_THIEF_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_THIEF))));
    }

    /**
     * @author Henrik Möhlmann
     * @return text displayed if this char wins.
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_VILLAGER_WIN_TEXT;
    }

    /**
     * @author Henrik Möhlmann
     * @param obj other object
     * @return true if other object is a thief character.
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharThief;
    }
}
