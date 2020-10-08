package de.werwolf_spielleiter.character;

import de.werwolf_spielleiter.constants.PrivateGameConstants;
import javafx.scene.image.Image;

/**
 * @author Henrik Möhlmann
 * @author Janik Dohrmann
 */
public class CharWerewolf extends Character{

    /**
     * Constructor to create a werewolf character.
     * @author Henrik Möhlmann
     */
    public CharWerewolf() {
        super(PrivateGameConstants.CHAR_WEREWOLF_NAME);
        setCharacterCardBack(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_BACK))));
        setCharacterCardFront(new Image(String.valueOf(getClass().getResource(PrivateGameConstants.CHAR_CARD_FRONT_WEREWOLF))));
    }

    /**
     * @author Janik Dohrmann
     * @return
     */
    @Override
    public String winText() {
        return PrivateGameConstants.CHAR_WEREWOLF_WIN_TEXT;
    }

    /**
     * @author Janik Dohrmann
     * @param obj
     * @return
     */
    public boolean isSameClass(Object obj) {
        return obj instanceof CharWerewolf;
    }
}
