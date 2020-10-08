package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharWhiteWerewolf extends Character {

    /**
     * Constructor to create a white werewolf character.
     * @author Henrik Möhlmann
     */
    public CharWhiteWerewolf() {
        super(PrivateGameConstants.CHAR_WHITE_WEREWOLF_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_WHITE_WEREWOLF))));
    }

    /**
     * @author Henrik Möhlmann
     * @return the text the text fo winning for the white werewolf
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_WHITE_WEREWOLF_WIN_TEXT;
    }

    /**
     * @author Henrik Möhlmann
     * @param obj the object to test
     * @return true if the object is the same class
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharWhiteWerewolf;
    }
}
