package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharBigBadWolf extends Character{

    /**
     * Constructor to create a big bad wolf character.
     * @author Henrik Möhlmann
     */
    public CharBigBadWolf() {
        super(PrivateGameConstants.CHAR_BIG_BAD_WOLF_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_BIG_BAD_WOLF))));
    }

    /**
     * @author Henrik Möhlmann
     * @return the text the text fo winning for the white werewolf
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_WEREWOLF_WIN_TEXT;
    }

    /**
     * @author Henrik Möhlmann
     * @param obj the object to test
     * @return true if the object is the same class
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharBigBadWolf;
    }
}
