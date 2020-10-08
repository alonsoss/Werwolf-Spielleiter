package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 */
public class CharCupid extends Character {

    /**
     * Constructor to create a cupid character.
     * @author Henrik Möhlmann
     */
    public CharCupid() {
        super(PrivateGameConstants.CHAR_CUPID_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_CUPID))));
    }

    /**
     * @author Henrik Möhlmann
     * @return Text displayed if this char wins.
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_CUPID_WIN_TEXT;
    }

    /**
     * @author Henrik Möhlmann
     * @param obj other object
     * @return true if other object is a cupid character.
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharCupid;
    }
}
